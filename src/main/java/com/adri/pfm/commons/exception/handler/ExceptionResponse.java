package com.adri.pfm.commons.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


public record ExceptionResponse(Date timestamp, String message, String details){
}
