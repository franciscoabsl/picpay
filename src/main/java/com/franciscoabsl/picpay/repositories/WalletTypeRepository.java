package com.franciscoabsl.picpay.repositories;

import com.franciscoabsl.picpay.models.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {
}
