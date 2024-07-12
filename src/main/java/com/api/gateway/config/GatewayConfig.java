package com.api.gateway.config;

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
		logger.info("Configurando Rotas do Gateway");
		
		return builder.routes()
				.route("get_pedidos", r -> r.method(HttpMethod.GET)
						.and()
						.path("/orders/**")
						.uri("http://localhost:8000/")
				)
				.route("criar_pedido", r -> r.method(HttpMethod.POST)
						.and()
						.path("/orders**")
						.uri("http://localhost:8000/")
				)
				.route("get_produtos", r -> r.method(HttpMethod.GET)
						.and()
						.path("/produtos/**")
						.uri("http://localhost:8000/")
				)
				.route("atualizar_pedido", r -> r.method(HttpMethod.PUT)
						.and()
						.path("/api/pedidos/{id}")
						.filters(f -> f.rewritePath("/api/pedidos/(?<id>.*)", "/api/pedidos/${id}"))
						.uri("http://localhost:8081/")
				)
				.route("deletar_pedido", r -> r.method(HttpMethod.DELETE)
						.and()
						.path("/api/pedidos/{id}")
						.filters(f -> f.rewritePath("/api/pedidos/(?<id>.*)", "/api/pedidos/${id}"))
						.uri("http://localhost:8081/")
				)
				.build();
	}
}
