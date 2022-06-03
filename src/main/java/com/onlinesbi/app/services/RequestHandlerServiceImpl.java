package com.onlinesbi.app.services;

import com.onlinesbi.app.controller.FundTransferRequest;
import com.onlinesbi.app.gateway.FundsTransferGateway;
import lombok.AllArgsConstructor;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("requestHandlerService")
@AllArgsConstructor
public class RequestHandlerServiceImpl {

    private FundsTransferGateway fundsTransferGateway;

    public void handelRequest(FundTransferRequest fundTransferRequest, Map<String, String> headers) {
        Message<FundTransferRequest> requestMessage = MessageBuilder.withPayload(fundTransferRequest)
                .setHeader("requestId", fundTransferRequest.getRequestId())
                .copyHeaders(headers)
                .build();
        fundsTransferGateway.trasferFunds(requestMessage);
    }
}
