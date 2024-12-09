package com.adri.pfm.commons.controller;

import org.springframework.http.ResponseEntity;

public interface PfmController {

    default ResponseEntity<ResponseMessage> generateSuccessResponse(String message) {
        ResponseMessage responseMessage = new ResponseMessage(message);
        return ResponseEntity.ok(responseMessage);
    }
}
