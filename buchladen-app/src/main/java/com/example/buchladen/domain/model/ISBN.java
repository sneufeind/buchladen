package com.example.buchladen.domain.model;

import lombok.NonNull;
import lombok.Value;

import java.util.regex.Pattern;

@Value(staticConstructor = "withCode")
public class ISBN {

    private static final Pattern PATTERN = Pattern.compile("^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$");

    String code;

    private ISBN(@NonNull final String code){
        if( !PATTERN.matcher(code).matches() )
            throw new IllegalArgumentException("Dies ist keine gültige ISBN.");
        this.code = code;
    }
}
