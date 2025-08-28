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

    public GlobalApiResponse<List<RemitResponseDTO>> findBySenderName(String senderName) {
        try {
            List<RemitForm> remits = remitRepository.findBySenderName(senderName);

            if (remits.isEmpty()) {
                return GlobalApiResponse.failure(
                        ConstantsMessage.NO_DATA_FOUND,
                        "No remit found with senderName: " + senderName
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
