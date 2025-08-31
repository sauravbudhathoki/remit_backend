package com.remit.remittance.usecase;

import com.remit.remittance.constantsmessage.ConstantsMessage;
import com.remit.remittance.dto.GlobalApiResponse;
import com.remit.remittance.dto.RemitResponseDTO;
import com.remit.remittance.entity.RemitForm;
import com.remit.remittance.repository.RemitRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindBySenderNameUseCase {

    private final RemitRepository remitRepository;
    private final ModelMapper modelMapper;

    public GlobalApiResponse<List<RemitResponseDTO>> findBySenderNameAndOptionalBeneficiary(
            String senderName, String beneficiaryName) {

        try {
            List<RemitForm> remits;

            if (beneficiaryName != null && !beneficiaryName.isBlank()) {
                remits = remitRepository
                        .findBySenderNameStartingWithAndBeneficiaryNameContainingIgnoreCase(
                                senderName, beneficiaryName);
            } else {
                remits = remitRepository.findBySenderNameStartingWithIgnoreCase(senderName);
            }

            if (remits.isEmpty()) {
                return GlobalApiResponse.failure(
                        ConstantsMessage.NO_DATA_FOUND,
                        "No remits found for the given search criteria."
                );
            }

            List<RemitResponseDTO> responseDTOs = remits.stream()
                    .map(remit -> modelMapper.map(remit, RemitResponseDTO.class))
                    .toList();

            return GlobalApiResponse.success(ConstantsMessage.REMIT_FETCHED_SUCCESS, responseDTOs);

        } catch (Exception e) {
            return GlobalApiResponse.failure(ConstantsMessage.REMIT_FETCHED_FAILED, e.getMessage());
        }
    }
}
