package com.thullyoo.bilheteria_api.exceptions.global;

import org.springframework.http.HttpStatus;


import java.util.List;

public record ExceptionResponse(Integer code, HttpStatus status, String message, List<Details> details){
}
