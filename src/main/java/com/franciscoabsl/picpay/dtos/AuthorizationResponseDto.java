package com.franciscoabsl.picpay.dtos;

import java.util.HashMap;

public record AuthorizationResponseDto(String status, HashMap<String, String> data) {}
