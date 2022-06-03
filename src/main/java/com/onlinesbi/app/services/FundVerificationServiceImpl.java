package com.onlinesbi.app.services;

import com.onlinesbi.app.controller.FundTransferRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class FundVerificationServiceImpl {

    private MessageChannel fundTransferCompletedChannel;

    private MessageChannel insufficientFundErrorResponseChannel;

    @ServiceActivator(inputChannel = "fundVerificationServiceChannel", outputChannel = "fundVerificationRouterChannel")
    public Message<FundTransferRequest> verifyFunds(Message<FundTransferRequest> fundTransferRequestMessage) {
        log.info("Received message from fundVerificationServiceChannel : " + fundTransferRequestMessage.getPayload()+" Thread.currentThread "+Thread.currentThread());
        boolean isLowFundBalance = true;
        FundTransferRequest fundTransferRequest = fundTransferRequestMessage.getPayload();
        if(fundTransferRequest.getAmount() > 1000){
            isLowFundBalance=false;
        }
        Message<FundTransferRequest> responseMessage = MessageBuilder.withPayload(fundTransferRequestMessage.getPayload())
                .setHeader("isLowFundBalance", isLowFundBalance)
                .copyHeaders(fundTransferRequestMessage.getHeaders())
                .build();
        return responseMessage;
    }

    @Router(inputChannel = "fundVerificationRouterChannel")
    public MessageChannel fundVerificationRouterChannel(@Header("isLowFundBalance") boolean isLowFundBalance) {
        log.info("fundVerificationRouterChannel  Thread.currentThread "+Thread.currentThread());
        if(isLowFundBalance){
            return insufficientFundErrorResponseChannel;
        }
        return fundTransferCompletedChannel;
    }

}
