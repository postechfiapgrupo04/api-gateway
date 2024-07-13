package com.api.gateway.configTest;

import com.api.gateway.config.GatewayConfig;
import com.api.gateway.exception.GatewayConfigException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder.Builder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GatewayConfigTest {
    
    @Mock
    private RouteLocatorBuilder routeLocatorBuilder;
    
    @Mock
    private Builder routesBuilder;
    
    @Mock
    private RouteLocator routeLocator;
    
    @InjectMocks
    private GatewayConfig gatewayConfig;
    
    @BeforeEach
    void setUp() {
        lenient().when(routeLocatorBuilder.routes()).thenReturn(routesBuilder);
        lenient().when(routesBuilder.route(anyString(), any())).thenReturn(routesBuilder);
        lenient().when(routesBuilder.build()).thenReturn(routeLocator);
    }
    
    @Test
    void testGatewayRoutes() {
        RouteLocator result = gatewayConfig.gatewayRoutes(routeLocatorBuilder);
        assertNotNull(result);
        verify(routeLocatorBuilder, times(1)).routes();
        verify(routesBuilder, times(6)).route(anyString(), any());
        verify(routesBuilder, times(1)).build();
    }
    
    @Test
    void testGatewayRoutesException() {
        when(routeLocatorBuilder.routes()).thenThrow(new RuntimeException("Simulated Exception"));
        
        assertThrows(GatewayConfigException.class, () -> gatewayConfig.gatewayRoutes(routeLocatorBuilder));
    }
}
