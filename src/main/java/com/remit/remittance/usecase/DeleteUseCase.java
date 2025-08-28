package com.remit.remittance.usecase;

import com.remit.remittance.constantsmessage.ConstantsMessage;
import com.remit.remittance.dto.GlobalApiResponse;
import com.remit.remittance.exception.ValidationException;
import com.remit.remittance.service.RemitServiceI;
import org.springframework.stereotype.Component;

@Component

public class DeleteUseCase {

    private final RemitServiceI remitServiceI;

    public DeleteUseCase(RemitServiceI remitServiceI){
        this.remitServiceI = remitServiceI;
    }
    public GlobalApiResponse deleteRemit(Long id) {
        try{
            remitServiceI.getById(id); //exist garxa ki nai check garxa
            remitServiceI.delete(id);
            return GlobalApiResponse.success(ConstantsMessage.REMIT_DELETED_SUCCESS,null);
        }catch (Exception e){
            return GlobalApiResponse.failure(ConstantsMessage.REMIT_DELETE_FAILED,e.getMessage());
        }

    }

}
