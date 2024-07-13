package com.api.gateway.config;

import com.api.gateway.exception.GatewayConfigException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class GatewayConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(GatewayConfig.class);
	
	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		try {
			logger.info("Configurando Rotas do Gateway");
			
			return builder.routes()
					.route("get_pedidos", r -> r.method(HttpMethod.GET)
							.and()
							.path("/orders/**")
							.uri("http://localhost:8000/")
					)
					.route("get_cliente", r -> r.method(HttpMethod.GET)
							.and()
							.path("/user/**")
							.uri("http://localhost:8001/")
					)
					.route("criar_pedido", r -> r.method(HttpMethod.POST)
							.and()
							.path("/orders**")
							.uri("http://localhost:8000/")
					)
					.route("get_produtos", r -> r.method(HttpMethod.GET)
							.and()
							.path("/produtos")
							.uri("http://localhost:8080/")
					)
					.route("get_deliverys", r -> r.method(HttpMethod.GET)
							.and()
							.path("/delivery/findDeliverys")
							.uri("http://localhost:8006/")
					)
					.route("update_delivery", r -> r.method(HttpMethod.POST)
							.and()
							.path("/delivery/updateDeliveryStatus")
							.uri("http://localhost:8006/")
					)
					.build();
		} catch (Exception e) {
			logger.error("Erro ao configurar as rotas do gateway", e);
			throw new GatewayConfigException("Erro ao configurar as rotas do gateway", e);
		}
	}
}
