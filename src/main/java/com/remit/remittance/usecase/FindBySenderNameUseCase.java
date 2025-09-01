package com.remit.remittance.usecase;

import com.remit.remittance.constantsmessage.ConstantsMessage;
import com.remit.remittance.dto.GlobalApiResponse;
import com.remit.remittance.dto.RemitResponseDTO;
import com.remit.remittance.entity.RemitForm;
import com.remit.remittance.repository.RemitRepository;
import com.remit.remittance.service.RemitServiceI;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindBySenderNameUseCase {

    private final RemitRepository remitRepository;
    private final ModelMapper modelMapper;
    private final RemitServiceI remitServiceI;

    public GlobalApiResponse<List<RemitResponseDTO>> findBySenderNameAndOptionalBeneficiary(
            String senderName, String beneficiaryName) {

        try {
            List<RemitResponseDTO> remits = remitServiceI.getBySenderNameandOptionalBeneficiary(senderName, beneficiaryName);
            return GlobalApiResponse.success(
                    ConstantsMessage.REMIT_FETCHED_SUCCESS, remits
            );
        } catch (RuntimeException e) {
            return GlobalApiResponse.failure(
                    ConstantsMessage.NO_DATA_FOUND,
                    e.getMessage()
            );
        } catch (Exception e) {
            return GlobalApiResponse.failure(
                    ConstantsMessage.REMIT_FETCHED_FAILED,
                    e.getMessage()
            );
        }
    }
}



