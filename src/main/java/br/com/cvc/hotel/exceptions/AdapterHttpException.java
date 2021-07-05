package br.com.cvc.hotel.exceptions;

import org.springframework.web.client.HttpClientErrorException;

public class AdapterHttpException extends RuntimeException {
    public AdapterHttpException(String msg, HttpClientErrorException httpEx) {
        super(msg, httpEx);
    }
}
