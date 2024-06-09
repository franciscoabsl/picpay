package com.franciscoabsl.picpay.services;

import com.franciscoabsl.picpay.dtos.TransferDto;
import com.franciscoabsl.picpay.exceptions.TransferNotAllowedException;
import com.franciscoabsl.picpay.exceptions.WalletNotFoundException;
import com.franciscoabsl.picpay.models.Transfer;
import com.franciscoabsl.picpay.models.Wallet;
import com.franciscoabsl.picpay.repositories.TransferRepository;
import com.franciscoabsl.picpay.repositories.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
public class TransferService {

    private final NotificationService notificationService;
    private final AuthorizationService authorizationService;
    private final TransferRepository transferRepository;
    private final WalletService walletService;
    private final WalletRepository walletRepository;

    public TransferService(NotificationService notificationService, AuthorizationService authorizationService, TransferRepository transferRepository, WalletService walletService, WalletRepository walletRepository) {
        this.notificationService = notificationService;
        this.authorizationService = authorizationService;
        this.transferRepository = transferRepository;
        this.walletService = walletService;
        this.walletRepository = walletRepository;
    }

    @Transactional
    public Transfer transfer(TransferDto transferDto) {
        var sender = walletService.findById(transferDto.payer()).orElseThrow(
                ()-> new WalletNotFoundException(transferDto.payer())
        );

        var receiver = walletService.findById(transferDto.payee()).orElseThrow(
                ()-> new WalletNotFoundException(transferDto.payee())
        );

        validateTransfer(transferDto, sender);

        sender.debit(transferDto.value());
        receiver.credit(transferDto.value());

        var transfer = new Transfer(sender, receiver, transferDto.value());

        walletRepository.save(sender);
        walletRepository.save(receiver);

        var transferRes =  transferRepository.save(transfer);

        CompletableFuture.runAsync(() ->  notificationService.sendNotification(transferRes));

        return transferRes;
    }

    private void validateTransfer(TransferDto transferDto, Wallet sender) {

        if (!sender.isSenderAllowed()) {
            throw new TransferNotAllowedException("Sender wallet type not allowed to make transfers");
        }

        if (!sender.isBalanceSufficient(transferDto.value())) {
            throw new TransferNotAllowedException("Sender balance insufficient.");
        }

        if (!authorizationService.isAuthorized(transferDto)) {
            throw new TransferNotAllowedException("Transfer not authorized.");
        }
    }
}
