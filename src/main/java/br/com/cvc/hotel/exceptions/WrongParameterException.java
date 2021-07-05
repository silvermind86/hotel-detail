package br.com.cvc.hotel.exceptions;

public class WrongParameterException extends RuntimeException {
    public WrongParameterException(String msg) {
        super(msg);
    }
}
