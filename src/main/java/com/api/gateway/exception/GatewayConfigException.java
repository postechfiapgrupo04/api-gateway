package com.api.gateway.exception;

public class GatewayConfigException extends RuntimeException {

    public GatewayConfigException(String message) {
        super(message);
    }

    public GatewayConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
