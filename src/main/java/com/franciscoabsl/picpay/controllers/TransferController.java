package com.franciscoabsl.picpay.controllers;

import com.franciscoabsl.picpay.dtos.TransferDto;
import com.franciscoabsl.picpay.models.Transfer;
import com.franciscoabsl.picpay.services.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<Transfer> transfer(@RequestBody @Valid TransferDto transferDto) {
        var resp = transferService.transfer(transferDto);

        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }
}
