package com.remit.remittance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemitRequestDTO {
    private String sourceAgent;
    private String senderName;
    private String globalRemitControlNo;
    private String address;
    private String senderMobile;
    private String senderTelephone;
    private String sendersIdType;
    private String senderIdNo;
    private String source;
    private String purposeOfFund;
    private String beneficiaryName;
    private String beneficiaryAddress;
    private String beneficiaryTelephone;
    private String beneficiaryMobile;
    private String idType;
    private String beneficiaryIdNo;
    private String agentLocation;
    private String agentDescription;
    private String agentAddress;
    private String currency;
    private Double localAmount;
//    private Double serviceCharge;

    private String amountInWords;
    private String referenceNo;
    private String remarks;
}
