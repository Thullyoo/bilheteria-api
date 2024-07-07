package com.thullyoo.bilheteria_api.exceptions.global;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Details {
    private String field;
    private String message;

    public Details(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
