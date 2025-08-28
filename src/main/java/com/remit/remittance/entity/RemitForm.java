package com.remit.remittance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class RemitForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sourceAgent;
    private String globalRemitControlNo;
    private String senderName;
    private String address;
    private String senderTelephone;
    private String senderMobile;
    private String sendersIdType;
    private String senderIdNo;
    private String source;
    private String purposeOfFund;
    private String beneficiaryName;
    private String beneficiaryAddress;
    private String beneficiaryMobile;
    private String beneficiaryTelephone;
    private String idType;
    private String beneficiaryIdNo;
    private String agentLocation;
    private String agentDescription;
    private String agentAddress;
    private String currency;
    private Double localAmount;
    private Double serviceCharge;
    private Double exchangeRate;
    private Double amount;
    private String amountInWords;
    private String referenceNo;
    private String remarks;
}
