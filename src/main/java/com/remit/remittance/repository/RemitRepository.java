package com.remit.remittance.repository;

import com.remit.remittance.entity.RemitForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RemitRepository extends JpaRepository<RemitForm, Long> {
    List<RemitForm> findBySenderNameStartingWithIgnoreCase(String senderName);

    List<RemitForm> findBySenderNameStartingWithAndBeneficiaryNameContainingIgnoreCase(String senderName, String beneficiaryName);
}
