package com.remit.remittance.service;


import com.remit.remittance.dto.RemitRequestDTO;
import com.remit.remittance.dto.RemitResponseDTO;
import com.remit.remittance.entity.RemitForm;
import com.remit.remittance.exception.ValidationException;
import com.remit.remittance.repository.RemitRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RemitServiceImpl implements RemitServiceI {

    private final RemitRepository repository;
    private final ModelMapper modelMapper;
    private static final double STATIC_SERVICE_CHARGE = 500.0;

    public RemitServiceImpl(RemitRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RemitResponseDTO create(RemitRequestDTO requestDTO) {

        modelMapper.typeMap(RemitRequestDTO.class, RemitForm.class)
                .addMappings(m -> m.skip(RemitForm::setId));

        RemitForm entity = modelMapper.map(requestDTO,RemitForm.class);



//calculate exchang rate and amount
        entity.setExchangeRate(getExchangeRate(entity.getCurrency()));
        entity.setServiceCharge(500.0);
        entity.setAmount((entity.getLocalAmount() * entity.getExchangeRate()) + entity.getServiceCharge());


        RemitForm saved = repository.save(entity);
        return modelMapper.map(saved, RemitResponseDTO.class);
    }

    @Override
    public List<RemitResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }


    @Override
    public Page<RemitResponseDTO> getAllPaginated(Pageable pageable) {
        // Fetch paginated entities from repository
        Page<RemitForm> page = repository.findAll(pageable);

        // Map entities to DTOs
        List<RemitResponseDTO> dtoList = page.getContent()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());

        // Return as Page<RemitResponseDTO>
        return new PageImpl<>(dtoList, pageable, page.getTotalElements());
    }

    @Override
    public RemitResponseDTO getById(Long id) {
        RemitForm form = repository.findById(id)
                .orElseThrow(() -> new ValidationException("Remit with ID " + id + " not found."));
        return mapToResponseDTO(form);
    }

    @Override
    public RemitResponseDTO update(Long id, RemitRequestDTO requestDTO) {
        RemitForm existing = repository.findById(id)
                .orElseThrow(() -> new ValidationException("Remit with ID " + id + " not found."));

        //dto ma present vako field matra update garxa
        //null navako field update garxaif
        if (requestDTO.getSourceAgent() != null) existing.setSourceAgent(requestDTO.getSourceAgent());
        if (requestDTO.getGlobalRemitControlNo() != null)
            existing.setGlobalRemitControlNo(requestDTO.getGlobalRemitControlNo());
        if (requestDTO.getSenderName() != null) existing.setSenderName(requestDTO.getSenderName());
        if (requestDTO.getAddress() != null) existing.setAddress(requestDTO.getAddress());
        if (requestDTO.getSenderTelephone() != null) existing.setSenderTelephone(requestDTO.getSenderTelephone());
        if (requestDTO.getSenderMobile() != null) existing.setSenderMobile(requestDTO.getSenderMobile());
        if (requestDTO.getSendersIdType() != null) existing.setSendersIdType(requestDTO.getSendersIdType());
        if (requestDTO.getSenderIdNo() != null) existing.setSenderIdNo(requestDTO.getSenderIdNo());
        if (requestDTO.getSource() != null) existing.setSource(requestDTO.getSource());
        if (requestDTO.getPurposeOfFund() != null) existing.setPurposeOfFund(requestDTO.getPurposeOfFund());
        if (requestDTO.getBeneficiaryName() != null) existing.setBeneficiaryName(requestDTO.getBeneficiaryName());
        if (requestDTO.getBeneficiaryAddress() != null)
            existing.setBeneficiaryAddress(requestDTO.getBeneficiaryAddress());
        if (requestDTO.getBeneficiaryMobile() != null) existing.setBeneficiaryMobile(requestDTO.getBeneficiaryMobile());
        if (requestDTO.getBeneficiaryTelephone() != null)
            existing.setBeneficiaryTelephone(requestDTO.getBeneficiaryTelephone());
        if (requestDTO.getIdType() != null) existing.setIdType(requestDTO.getIdType());
        if (requestDTO.getBeneficiaryIdNo() != null) existing.setBeneficiaryIdNo(requestDTO.getBeneficiaryIdNo());
        if (requestDTO.getAgentLocation() != null) existing.setAgentLocation(requestDTO.getAgentLocation());
        if (requestDTO.getAgentDescription() != null) existing.setAgentDescription(requestDTO.getAgentDescription());
        if (requestDTO.getAgentAddress() != null) existing.setAgentAddress(requestDTO.getAgentAddress());
        if (requestDTO.getCurrency() != null) existing.setCurrency(requestDTO.getCurrency());

        if (requestDTO.getLocalAmount() != null) existing.setLocalAmount(requestDTO.getLocalAmount());
        //if (requestDTO.getServiceCharge() != null) existing.setServiceCharge(requestDTO.getServiceCharge());


        existing.setServiceCharge(500.0);
        existing.setExchangeRate(getExchangeRate(existing.getCurrency()));
        existing.setAmount((existing.getLocalAmount() * existing.getExchangeRate())  + existing.getServiceCharge());

        if (requestDTO.getAmountInWords() != null) existing.setAmountInWords(requestDTO.getAmountInWords());
        if (requestDTO.getReferenceNo() != null) existing.setReferenceNo(requestDTO.getReferenceNo());
        if (requestDTO.getRemarks() != null) existing.setRemarks(requestDTO.getRemarks());

        RemitForm saved = repository.save(existing);
        return modelMapper.map(saved, RemitResponseDTO.class);
    }


//----------------DELETE---------------------------

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    //----------GETBYSENDERNAME------------------
    @Override
    public List<RemitResponseDTO> getBySenderName(String senderName) {
        List<RemitForm> remits = repository.findBySenderNameStartingWithIgnoreCase(senderName);

        if (remits.isEmpty()) {
            throw new RuntimeException("No remit found with sender name: " + senderName);
        }

        // Map all RemitForm objects to RemitResponseDTO
        return remits.stream()
                .map(remit -> modelMapper.map(remit, RemitResponseDTO.class))
                .collect(Collectors.toList());
    }



    public List<RemitResponseDTO> getBySenderNameandOptionalBeneficiary(String senderName,String beneficiaryName) {
        List<RemitForm> remits ;

//case 1:senderName anf beneficiaryName both provide gareko belama
        if(senderName!=null && !senderName.isEmpty() && beneficiaryName!=null && !beneficiaryName.isEmpty()){
            remits=repository.findBySenderNameStartingWithIgnoreCase(senderName);
            String searchBeneficiary=beneficiaryName.toLowerCase();
            remits=remits.stream()
                    .filter(r->r.getBeneficiaryName()!=null &&
                            r.getBeneficiaryName().toLowerCase().startsWith(searchBeneficiary))
                    .collect(Collectors.toList());
        }


        //case 2:only sender name provided
        else if(senderName!=null && !senderName.isEmpty()){
            remits=repository.findBySenderNameStartingWithIgnoreCase(senderName);
        }
        //case 3:only beneficiaryName provided
        else if (beneficiaryName!=null && !beneficiaryName.isEmpty()){
            String searchBeneficiary=beneficiaryName.toLowerCase();
            remits=repository.findAll().stream()
                    .filter(r->r.getBeneficiaryName()!=null &&
                            r.getBeneficiaryName().toLowerCase().startsWith(searchBeneficiary))
                    .collect(Collectors.toList());
        }
        //case 4:neither provided
        else {
            return repository.findAll().stream()
                    .map(r -> modelMapper.map(r, RemitResponseDTO.class))
                    .collect(Collectors.toList());
        }

        // Check if the result is empty **after handling all cases**
         if(remits.isEmpty()) {
            String message;
            if ((senderName != null && !senderName.isEmpty()) && (beneficiaryName != null && !beneficiaryName.isEmpty())) {
                message = "No remit found with sender: " + senderName + " and beneficiary: " + beneficiaryName;
            } else if (senderName != null && !senderName.isEmpty()) {
                message = "No remit found with sender: " + senderName;
            } else if (beneficiaryName != null && !beneficiaryName.isEmpty()) {
                message = "No remit found with beneficiary: " + beneficiaryName;
            } else {
                message = "No remit found for the given criteria";
            }
            throw new RuntimeException(message);
        }




        // Map all RemitForm objects to RemitResponseDTO
        return remits.stream()
                .map(r-> modelMapper.map(r, RemitResponseDTO.class))
                .collect(Collectors.toList());
    }




    //-------------------------------MAPPERS-----------------------------
    //RequestDTO->Entity
    private RemitForm mapToEntity(RemitRequestDTO dto) {
        RemitForm form = modelMapper.map(dto, RemitForm.class);

        form.setServiceCharge(500.0);
        form.setExchangeRate(getExchangeRate(dto.getCurrency()));
        form.setAmount(form.getServiceCharge() + form.getExchangeRate() * form.getLocalAmount());
        return form;
    }

    //Entity->ResponseDTO
    private RemitResponseDTO mapToResponseDTO(RemitForm form) {
        RemitResponseDTO responseDTO = modelMapper.map(form, RemitResponseDTO.class);

        return responseDTO;
    }

    private Double getExchangeRate(String currency) {
        return switch (currency.toUpperCase()) {
            case "USD" -> 139.0;
            case "EUR" -> 160.0;
            case "GBP" -> 120.0;
            case "NPR" -> 1.0;
            case "INR" -> 1.6;
            default -> 1.0;
        };
    }

}