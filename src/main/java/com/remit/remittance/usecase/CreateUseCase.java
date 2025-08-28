package com.remit.remittance.usecase;


import com.remit.remittance.constantsmessage.ConstantsMessage;
import com.remit.remittance.dto.GlobalApiResponse;
//import com.remit.remittance.dto.RemitRequestDTO;
//import com.remit.remittance.dto.RemitResponseDTO;
//import com.remit.remittance.exception.ValidationErrorResponse;
import com.remit.remittance.dto.RemitRequestDTO;
import com.remit.remittance.dto.RemitResponseDTO;
import com.remit.remittance.service.RemitServiceI;
import com.remit.remittance.validator.RemitFormValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateUseCase {

    private final RemitServiceI remitServiceI;
    private final RemitFormValidator remitFormValidator;


    public GlobalApiResponse<RemitResponseDTO> CreateRemit(RemitRequestDTO requestDTO) {

        //validated direclty from RequestDTO
        List<String> errors = remitFormValidator.validate(requestDTO);
        if (!errors.isEmpty()) {
            return GlobalApiResponse.failure(ConstantsMessage.VALIDATION_FAILED, errors);
        }
        //try creatinf remit
        try {
            return GlobalApiResponse.success(
                    ConstantsMessage.REMIT_CREATED_SUCCESS,
                    remitServiceI.create(requestDTO)
            );
        } catch (Exception e) {
            return GlobalApiResponse.failure(ConstantsMessage.REMIT_CREATE_FAILED, e.getMessage());

        }
    }
}

