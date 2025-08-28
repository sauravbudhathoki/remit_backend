package com.remit.remittance.usecase;

import com.remit.remittance.constantsmessage.ConstantsMessage;
import com.remit.remittance.dto.GlobalApiResponse;
import com.remit.remittance.dto.RemitResponseDTO;
import com.remit.remittance.exception.ValidationException;
import com.remit.remittance.service.RemitServiceI;
import org.springframework.stereotype.Component;

@Component
public class GetByIdUseCase {

    private final RemitServiceI remitServiceI;

    public GetByIdUseCase(RemitServiceI remitServiceI){
        this.remitServiceI = remitServiceI;
    }
    public GlobalApiResponse<RemitResponseDTO> getSpecificRemit(Long id) {
        try {
            RemitResponseDTO dto =remitServiceI.getById(id);
            return GlobalApiResponse.success(ConstantsMessage.REMIT_FETCHED_SUCCESS,dto);
        }catch (Exception e){
            return GlobalApiResponse.failure(ConstantsMessage.REMIT_FETCHED_FAILED,e.getMessage());
        }
    }
}
