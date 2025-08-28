package com.remit.remittance.usecase;

import com.remit.remittance.constantsmessage.ConstantsMessage;
import com.remit.remittance.dto.GlobalApiResponse;
import com.remit.remittance.dto.RemitRequestDTO;
import com.remit.remittance.dto.RemitResponseDTO;
import com.remit.remittance.exception.ValidationException;
import com.remit.remittance.service.RemitServiceI;
import com.remit.remittance.validator.RemitFormValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UpdateUseCase {

    private  final RemitServiceI remitServiceI;
    private final RemitFormValidator remitFormValidator;


    public GlobalApiResponse updateRemit(Long id, RemitRequestDTO requestDTO) {

            List<String> errors =remitFormValidator.validate(requestDTO);
            if(!errors.isEmpty()){
                return GlobalApiResponse.failure(ConstantsMessage.VALIDATION_FAILED,errors);
            }

            //update remit
        try {
            return GlobalApiResponse.success(
                    ConstantsMessage.REMIT_UPDATED_SUCCESS,
                    remitServiceI.update(Long.valueOf(id), requestDTO)
            );
        }catch (Exception e){
            return GlobalApiResponse.failure(ConstantsMessage.REMIT_UPDATE_FAILED,e.getMessage());
        }
    }
}
