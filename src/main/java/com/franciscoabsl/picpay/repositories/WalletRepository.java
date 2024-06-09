package com.franciscoabsl.picpay.repositories;

import com.franciscoabsl.picpay.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
