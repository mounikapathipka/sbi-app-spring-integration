package com.onlinesbi.app.controller;

import com.onlinesbi.app.services.RequestHandlerServiceImpl;
import com.onlinesbi.app.utils.RequestHolder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
public class FundsTransferController {

    private RequestHolder requestHolder;

    private RequestHandlerServiceImpl requestHandlerService;

    @PostMapping("/transferFunds")
    public DeferredResult<ResponseEntity<?>> overseasProxyLookup(
            @RequestBody FundTransferRequest fundTransferRequest,
            @RequestHeader Map<String, String> headers) throws Exception {
        DeferredResult<ResponseEntity<?>> result = requestHolder.createDeferredResult(fundTransferRequest.getRequestId(),30000);
        requestHandlerService.handelRequest(fundTransferRequest,headers);
        return result;
    }
}
