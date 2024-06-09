package com.franciscoabsl.picpay.services;

import com.franciscoabsl.picpay.dtos.CreateWalletDto;
import com.franciscoabsl.picpay.models.Wallet;
import com.franciscoabsl.picpay.repositories.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet save(CreateWalletDto walletDto) {
        return walletRepository.save(walletDto.toWallet());
    }
}
