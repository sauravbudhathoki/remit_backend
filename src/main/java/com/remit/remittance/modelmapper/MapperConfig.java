package com.remit.remittance.modelmapper;

import com.remit.remittance.dto.RemitResponseDTO;
import com.remit.remittance.entity.RemitForm;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

