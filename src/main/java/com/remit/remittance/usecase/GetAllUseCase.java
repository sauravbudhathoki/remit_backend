package com.remit.remittance.usecase;

import com.remit.remittance.constantsmessage.ConstantsMessage;
import com.remit.remittance.dto.GlobalApiResponse;
import com.remit.remittance.dto.RemitResponseDTO;
import com.remit.remittance.service.RemitServiceI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllUseCase {

    private final RemitServiceI remitServiceI;

    public GetAllUseCase(RemitServiceI remitServiceI){
        this.remitServiceI = remitServiceI;
    }
    public GlobalApiResponse<Page<RemitResponseDTO>> getAllRemit(int page,int size) {


        try {
            Page<RemitResponseDTO> all =remitServiceI.getAllPaginated(PageRequest.of(page,size));
            if (all.isEmpty()) {
                return GlobalApiResponse.failure(ConstantsMessage.NO_DATA_FOUND, null);
            }
            return GlobalApiResponse.success(ConstantsMessage.REMIT_FETCHED_SUCCESS,all);
            }catch (Exception e){
                return GlobalApiResponse.failure(ConstantsMessage.REMIT_FETCHED_FAILED,e.getMessage());

            }

            //return new RemitResponseDTO<>(true,"Fetched successfully",null,all);

//        } catch (ValidationException e) {
//            throw e;
//        } catch (Exception e) {
//            throw new RuntimeException("Unexpected error occured while fetching all remits.");
        }

    }

