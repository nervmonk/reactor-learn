package com.reactorintroduction.sec11.client;

public class ClientError extends RuntimeException {
    public ClientError() {
        super("Bad Request");
    }
}
