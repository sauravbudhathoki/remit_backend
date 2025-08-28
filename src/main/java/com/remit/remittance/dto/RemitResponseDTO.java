package com.remit.remittance.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RemitResponseDTO<T> {
    private Long id;
    private String sourceAgent;
   private String globalRemitControlNo;
   private String senderName;
   private String beneficiaryName;
   private String currency;
   private Double localAmount;
   private String purposeOfFund;
   private String referenceNo;
   private Double exchangeRate;
   private Double serviceCharge;
   private String agentLocation;
   private String agentAddress;
    private String address;
    private String senderTelephone;
    private String senderMobile;
    private String sendersIdType;
    private String senderIdNo;
    private String source;
    private String beneficiaryAddress;
    private String beneficiaryMobile;
    private String beneficiaryTelephone;
    private String idType;
    private String beneficiaryIdNo;
    private String agentDescription;
    private Double amount;
    private String amountInWords;
    private String remarks;

   private List<String> errors;
}
