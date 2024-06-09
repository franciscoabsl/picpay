package com.franciscoabsl.picpay.repositories;

import com.franciscoabsl.picpay.models.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {
}
