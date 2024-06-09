package com.franciscoabsl.picpay.services;

import com.franciscoabsl.picpay.clients.AuthorizationClient;
import com.franciscoabsl.picpay.exceptions.PicPayException;
import com.franciscoabsl.picpay.models.Transfer;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthorizationService {

    private final String STATUS = "status";
    private final String STATUS_FAIL = "fail";

    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(Transfer transfer) {
        var resp = authorizationClient.isAuthorized();

        if (Objects.requireNonNull(resp.getBody()).data().get(STATUS).equals(STATUS_FAIL)) {
           throw new PicPayException();
        }

        return true;
    }
}
