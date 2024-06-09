package com.franciscoabsl.picpay.controllers;

import com.franciscoabsl.picpay.dtos.CreateWalletDto;
import com.franciscoabsl.picpay.models.Wallet;
import com.franciscoabsl.picpay.services.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<Wallet> createWallet(@RequestBody CreateWalletDto walletDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(walletService.save(walletDto));
    }


}
