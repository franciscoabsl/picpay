package com.franciscoabsl.picpay.services;

import com.franciscoabsl.picpay.dtos.CreateWalletDto;
import com.franciscoabsl.picpay.exceptions.WalletDataAlreadyExistsException;
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
        var walletDb =  walletRepository.findByCpfCnpjOrEmail(walletDto.cpfCnpj(), walletDto.email());

        if (walletDb.isPresent()) {
            throw new WalletDataAlreadyExistsException("CpfCnpj or Email already exists");
        }
        return walletRepository.save(walletDto.toWallet());
    }
}
