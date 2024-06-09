package com.franciscoabsl.picpay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransferNotAllowedException extends PicPayException {

    private final String detailMessage;

    public TransferNotAllowedException(String detailMessage) {
        this.detailMessage = detailMessage;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb =  ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Transfer not allowed.");
        pb.setDetail(detailMessage);

        return pb;
    }
}
