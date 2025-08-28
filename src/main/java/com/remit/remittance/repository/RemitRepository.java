package com.remit.remittance.repository;

import com.remit.remittance.entity.RemitForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RemitRepository extends JpaRepository<RemitForm, Long> {
    List<RemitForm> findBySenderName(String senderName);
}
