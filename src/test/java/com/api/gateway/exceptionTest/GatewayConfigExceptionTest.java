package com.api.gateway.exceptionTest;

import com.api.gateway.exception.GatewayConfigException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GatewayConfigExceptionTest {

    @Test
    void testExceptionWithMessage() {
        String errorMessage = "Test error message";
        GatewayConfigException exception = new GatewayConfigException(errorMessage);

        assertNotNull(exception);
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testExceptionWithMessageAndCause() {
        String errorMessage = "Test error message";
        Throwable cause = new RuntimeException("Cause of the error");
        GatewayConfigException exception = new GatewayConfigException(errorMessage, cause);

        assertNotNull(exception);
        assertEquals(errorMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
