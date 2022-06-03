package com.onlinesbi.app.services;

import com.onlinesbi.app.controller.FundTransferRequest;
import com.onlinesbi.app.controller.FundTransferResponse;
import com.onlinesbi.app.utils.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class APIResponseServiceImpl {

    @ServiceActivator(inputChannel = "httpResponseChannel")
    public Message<Void> apiResponse(Message<FundTransferResponse> response) {
        String requestId = (String)response.getHeaders().get("requestId");
        RequestHolder.REQ_MAP.get(requestId).setResult(response.getPayload());
        log.info("Response sent "+response.getPayload());
        return  null;
    }
}
