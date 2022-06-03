package com.onlinesbi.app.gateway;

import com.onlinesbi.app.controller.FundTransferRequest;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway
public interface FundsTransferGateway {

    @Gateway(requestChannel = "accountVerificationChannel")
    public void trasferFunds(Message<FundTransferRequest> message);

}
