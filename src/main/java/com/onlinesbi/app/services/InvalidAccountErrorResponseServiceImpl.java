package com.onlinesbi.app.services;

import com.onlinesbi.app.controller.FundTransferRequest;
import com.onlinesbi.app.controller.FundTransferResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InvalidAccountErrorResponseServiceImpl {
    @ServiceActivator(inputChannel = "invalidAccountErrorResponseChannel", outputChannel = "httpResponseChannel")
    public Message<FundTransferResponse> verifyFunds(Message<FundTransferRequest> fundTransferRequestMessage) {
        log.info("Received message from fundVerificationServiceChannel : " + fundTransferRequestMessage.getPayload()+" Thread.currentThread "+Thread.currentThread());
        Message<FundTransferResponse> responseMessage = MessageBuilder.withPayload(FundTransferResponse.builder()
                        .response(" account  details are invalid")
                        .build())
                .setHeader("IsFundVerified", true)
                .copyHeaders(fundTransferRequestMessage.getHeaders())
                .build();
        return responseMessage;
    }
}
