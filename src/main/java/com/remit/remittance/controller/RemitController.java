package com.remit.remittance.controller;

import com.remit.remittance.dto.GlobalApiResponse;
import com.remit.remittance.dto.RemitRequestDTO;
import com.remit.remittance.dto.RemitResponseDTO;
import com.remit.remittance.usecase.*;
import lombok.RequiredArgsConstructor;
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




    @PostMapping("/remittance/create")
        public GlobalApiResponse<RemitResponseDTO> create(@RequestBody RemitRequestDTO requestDTO){
       return createUseCase.CreateRemit(requestDTO);

    }

    @GetMapping("/remittance/fetch")
    public GlobalApiResponse<List<RemitResponseDTO>> getAll(){
return  getAllUseCase.getAllRemit();
    }

    @GetMapping("/remittance")
    public GlobalApiResponse<RemitResponseDTO> getById(@RequestParam Long id){
      return getByIdUseCase.getSpecificRemit(id);
    }



    @GetMapping("/remittance/sender")
    public GlobalApiResponse<List<RemitResponseDTO>> getBySenderName(@RequestParam String senderName) {
        return findBySenderNameUseCase.findBySenderName(senderName);
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
