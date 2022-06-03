package com.onlinesbi.app.controller;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FundTransferRequest {

    String requestId;
    String senderAccountNumber;
    String senderbankIFSCCode;
    String toAccountNumber;
    String recipientIFSCCode;
    long amount;

}
