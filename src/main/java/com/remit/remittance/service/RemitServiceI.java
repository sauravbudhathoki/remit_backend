package com.remit.remittance.service;

import com.remit.remittance.dto.RemitRequestDTO;
import com.remit.remittance.dto.RemitResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RemitServiceI {

    RemitResponseDTO create(RemitRequestDTO requestDTO);

    List<RemitResponseDTO> getAll();

    RemitResponseDTO getById(Long id);

    RemitResponseDTO update(Long id,RemitRequestDTO requestDTO);

    Page<RemitResponseDTO> getAllPaginated(Pageable pageable);
//
   void delete(Long id);
   List<RemitResponseDTO> getBySenderName(String senderName);

    List<RemitResponseDTO> getBySenderNameandOptionalBeneficiary(String senderName, String beneficiaryName);

}
