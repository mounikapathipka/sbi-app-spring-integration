package com.onlinesbi.app.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RequestHolder {
    public static Map<String, DeferredResult> REQ_MAP = new ConcurrentHashMap<>();

    public static DeferredResult<ResponseEntity<?>> createDeferredResult(String requestId,long timeout){
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>(timeout);
        deferredResult.onCompletion(() -> {
            if(REQ_MAP.containsKey(requestId)){
                REQ_MAP.remove(requestId);
            }
        });

        deferredResult.onTimeout(() -> {
            deferredResult.setErrorResult(
                    ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout occurred."));
            if(REQ_MAP.containsKey(requestId)){
                REQ_MAP.remove(requestId);
            }
        });

        deferredResult.onError((throwable) -> {
            deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("INTERNAL_SERVER_ERROR occurred."));

            if(REQ_MAP.containsKey(requestId)){
                REQ_MAP.remove(requestId);
            }
        });
        REQ_MAP.put(requestId,deferredResult);
        return deferredResult;
    }


}
