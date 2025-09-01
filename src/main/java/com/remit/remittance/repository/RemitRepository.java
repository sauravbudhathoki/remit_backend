package com.remit.remittance.repository;

import com.remit.remittance.entity.RemitForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Queue;

public interface RemitRepository extends JpaRepository<RemitForm, Long> {
   List<RemitForm> findBySenderNameStartingWithIgnoreCase(String senderName);

    List<RemitForm> findBySenderNameStartingWithAndBeneficiaryNameStartingWithIgnoreCase(String senderName, String beneficiaryName);


   List<RemitForm> findByBeneficiaryNameStartingWithIgnoreCase(String beneficiaryName);


}
