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

@Service("accountVerificationService")
@AllArgsConstructor
@Slf4j
public class AccountVerificationServiceImpl {

    private MessageChannel fundVerificationServiceChannel;

    private MessageChannel invalidAccountErrorResponseChannel;

    @ServiceActivator(inputChannel = "accountVerificationChannel", outputChannel = "accountVerificationRouterChannel")
    public Message<FundTransferRequest>  verifyAccount(Message<FundTransferRequest> fundTransferRequestMessage) {
        log.info("Received message from myInputChannel : " + fundTransferRequestMessage.getPayload()+" Thread.currentThread "+Thread.currentThread());
        FundTransferRequest fundTransferRequest = fundTransferRequestMessage.getPayload();
        boolean isAccountVerified = true;
        if(fundTransferRequest.getToAccountNumber() == null || fundTransferRequest.getSenderAccountNumber() == null){
            isAccountVerified = false;
        }

        if(fundTransferRequest.getRecipientIFSCCode() == null || fundTransferRequest.getSenderbankIFSCCode() == null){
            isAccountVerified = false;
        }

        Message<FundTransferRequest> responseMessage = MessageBuilder.withPayload(fundTransferRequestMessage.getPayload())
                .setHeader("IsAccountVerified", isAccountVerified)
                .copyHeaders(fundTransferRequestMessage.getHeaders())
                .build();
        return responseMessage;
    }

    @Router(inputChannel = "accountVerificationRouterChannel")
    public MessageChannel accountVerificationRouter(@Header("IsAccountVerified") boolean isAccountVerified) {
        log.info("routeBasedOnHeader  Thread.currentThread "+Thread.currentThread());
        if(isAccountVerified){
            return fundVerificationServiceChannel;
        }
        return invalidAccountErrorResponseChannel;
    }


}
