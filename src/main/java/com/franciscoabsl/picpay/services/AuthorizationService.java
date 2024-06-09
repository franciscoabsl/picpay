package com.franciscoabsl.picpay.services;

import com.franciscoabsl.picpay.clients.AuthorizationClient;
import com.franciscoabsl.picpay.dtos.TransferDto;
import com.franciscoabsl.picpay.exceptions.PicPayException;
import com.franciscoabsl.picpay.exceptions.TransferNotAllowedException;
import com.franciscoabsl.picpay.models.Transfer;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

@Service
public class AuthorizationService {

    private final String STATUS = "status";
    private final String STATUS_FAIL = "fail";

    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(TransferDto transferDto) {

        try {
            var resp = authorizationClient.isAuthorized();
            if (Objects.requireNonNull(resp.getBody()).status().equals(STATUS_FAIL)) {
                throw new TransferNotAllowedException("Transfer not authorized.");
            }
        } catch (Exception e) {
            throw new TransferNotAllowedException("Transfer not authorized.");
        }
        return true;
    }
}
