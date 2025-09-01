package com.remit.remittance.controller;

import com.remit.remittance.dto.GlobalApiResponse;
import com.remit.remittance.dto.RemitRequestDTO;
import com.remit.remittance.dto.RemitResponseDTO;
import com.remit.remittance.service.RemitServiceI;
import com.remit.remittance.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RemitController {

    private final CreateUseCase createUseCase;
    private final GetAllUseCase getAllUseCase;
    private final GetByIdUseCase getByIdUseCase;
    private  final UpdateUseCase updateUseCase;
    private final FindBySenderNameUseCase findBySenderNameUseCase;

    private final DeleteUseCase deleteUseCase;
    private final RemitServiceI remitServiceI;


    @PostMapping("/remittance/create")
        public GlobalApiResponse<RemitResponseDTO> create(@RequestBody RemitRequestDTO requestDTO){
       return createUseCase.CreateRemit(requestDTO);

    }

    @GetMapping("/remittance/fetch")
    public GlobalApiResponse<Page<RemitResponseDTO>> getAll(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "5")int size
    ){
return  getAllUseCase.getAllRemit(page,size);
    }

    @GetMapping("/remittance")
    public GlobalApiResponse<RemitResponseDTO> getById(@RequestParam Long id){
      return getByIdUseCase.getSpecificRemit(id);
    }



//    @GetMapping("/remittance/sender")
//    public GlobalApiResponse<List<RemitResponseDTO>> getBySenderName(@RequestParam String senderName,@RequestParam(required = false) String beneficiaryName) {
//        return findBySenderNameUseCase.findBySenderName(senderName,beneficiaryName);
//    }
@GetMapping("/remittance/sender")
public GlobalApiResponse<List<RemitResponseDTO>> getBySender(
        @RequestParam(required = false) String senderName,
        @RequestParam(required = false) String beneficiaryName) {

    try {
        List<RemitResponseDTO> remits = remitServiceI.getBySenderNameandOptionalBeneficiary(senderName, beneficiaryName);
        return GlobalApiResponse.success("Fetched successfully", remits);
    } catch (RuntimeException e) {
        return GlobalApiResponse.failure(e.getMessage(), null);
    }
}



    @PutMapping("/remittance/update")
    public GlobalApiResponse<RemitResponseDTO> update(@RequestParam Long id, @RequestBody RemitRequestDTO requestDTO){
      return updateUseCase.updateRemit(id,requestDTO);
    }

    @DeleteMapping("/remittance/delete")
    public GlobalApiResponse delete(@RequestParam Long id){
      return deleteUseCase.deleteRemit(id);
    }


}
