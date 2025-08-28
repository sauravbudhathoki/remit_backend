package com.remit.remittance.validator;

import com.remit.remittance.dto.RemitRequestDTO;
import com.remit.remittance.dto.RemitResponseDTO;
import com.remit.remittance.exception.ValidationException;
import com.remit.remittance.utils.FieldValidationsUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.remit.remittance.utils.FieldValidationsUtils.*;
import static java.lang.Double.parseDouble;


@Component
public class RemitFormValidator {

    public List<String> validate(RemitRequestDTO dto) {

        List<String> errors = new ArrayList<>();


        if (dto.getSourceAgent() == null || dto.getSourceAgent().isBlank()) {
            errors.add("Source Agent is Required!");
        }
        if (dto.getGlobalRemitControlNo() == null
                || dto.getGlobalRemitControlNo().isBlank()) {
            errors.add("Global Remit Controller NO cannot be Empty.");
        }
        if (dto.getSenderName() == null
                || dto.getSenderName().isBlank()
                || containsNumbers(dto.getSenderName())) {
            errors.add("Sender name cannot be Empty and Numbers!");
        }
        if (dto.getAddress() == null
                || dto.getAddress().isBlank()) {
            errors.add("Address cannot be Empty!");
        }
        if (dto.getSenderTelephone() == null
                || dto.getSenderTelephone().isBlank()
                || !isValidPhone(dto.getSenderTelephone())) {
            errors.add("Sender Telephone cannot be Empty and Alphabets!");
        }
        if (dto.getSenderMobile() == null
                || dto.getSenderMobile().isBlank()
                || isInvalidMobile(dto.getSenderMobile())) {
            errors.add("Sender Mobile cannot be Empty and Alphabets!");
        }
        if (dto.getSendersIdType() == null
                || dto.getSendersIdType().isBlank()
                || containsNumbers(dto.getSendersIdType())) {
            errors.add("Id Type cannot be Empty and Numbers!");
        }
        if (dto.getSenderIdNo() == null
                || dto.getSenderIdNo().isBlank()) {
            errors.add("ID number cannot be Empty!");
        }
        if (dto.getSource() == null
                || dto.getSource().isBlank()) {
            errors.add("Source cannot be Empty!");
        }
        if (dto.getPurposeOfFund() == null
                || dto.getPurposeOfFund().isBlank()) {
            errors.add("Purpose of Fund cannot be Empty!");
        }
        if (dto.getBeneficiaryName() == null
                || dto.getBeneficiaryName().isBlank()
                || containsNumbers(dto.getBeneficiaryName())) {
            errors.add("Beneficiary Name cannot be Empty and Numbers!");
        }
        if (dto.getBeneficiaryAddress() == null
                || dto.getBeneficiaryAddress().isBlank()) {
            errors.add("Beneficiary Address cannot be Empty!");
        }
        if (dto.getBeneficiaryMobile() == null
                || dto.getBeneficiaryMobile().isBlank()
                || isInvalidMobile(dto.getBeneficiaryMobile())) {
            errors.add("Beneficiary Mobile cannot be Empty!");
        }
        if (dto.getIdType() == null
                || dto.getIdType().isBlank()
                || containsNumbers(dto.getIdType())) {
            errors.add("ID Type cannot be Empty and Numbers!");
        }
        if (dto.getBeneficiaryIdNo() == null
                || dto.getBeneficiaryIdNo().isBlank()) {
            errors.add("Beneficiary ID cannot be Empty!");
        }
        if (dto.getAgentLocation() == null
                || dto.getAgentLocation().isBlank()) {
            errors.add("Location cannot be Empty!");
        }
        if (dto.getAgentDescription() == null
                || dto.getAgentDescription().isBlank()) {
            errors.add("Agent Description cannot be Empty!");
        }
        if (dto.getAgentAddress() == null
                || dto.getAgentAddress().isBlank()) {
            errors.add("Agent Address cannot be Empty!");
        }
        if (dto.getCurrency() == null
                || dto.getCurrency().isBlank()
            || isInvalidCurrency(dto.getCurrency())){
            errors.add("Currency cannot be Empty!");
        }
        if (dto.getLocalAmount() == null
                || dto.getLocalAmount() <= 0) {
            errors.add("Amount cannot be Empty, Negative and Zero!");
        }
//        if (dto.getServiceCharge() == null
//                || dto.getServiceCharge() < 0) {
//            errors.add("Service Field cannot be Empty and Negative!");
//        }
//        if (dto.getExchangeRate() == null
//                || dto.getExchangeRate() < 0) {
//            errors.add("Exchange Rate cannot be Empty and Negative!");
//        }
//        if (dto.getAmount() == null
//                || dto.getAmount() < 0) {
//            errors.add("Amount cannot be Empty and Negative!");
//        }
        if (dto.getAmountInWords() == null
                || dto.getAmountInWords().isBlank()
                || containsNumbers(dto.getAmountInWords())) {
            errors.add("Amout in Words cannot be Empty and Numbers!");
        }
        if (dto.getReferenceNo() == null
                || dto.getReferenceNo().isBlank()) {
            errors.add("Reference Number cannot be Empty !");
        }

        if (!errors.isEmpty()) {
            return errors;
        }

return Collections.emptyList();
    }


}
