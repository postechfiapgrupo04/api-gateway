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
				/*.route("get_delivery", r -> r.method(HttpMethod.GET)
						.and()
						.path("/delivery/**")
						//.filters(f -> f.rewritePath("/api/pedidos/(?<id>.*)", "/api/pedidos/${id}"))
						.uri("http://localhost:8006/")
				)*/
				.route("get_deliverys", r -> r.method(HttpMethod.GET)
						.and()
						.path("/delivery/findDeliverys")
						//.filters(f -> f.rewritePath("/api/pedidos/(?<id>.*)", "/api/pedidos/${id}"))
						.uri("http://localhost:8006/")
				)


				.route("update_delivery", r -> r.method(HttpMethod.POST)
						.and()
						.path("/delivery/updateDeliveryStatus")
						//.filters(f -> f.rewritePath("/api/pedidos/(?<id>.*)", "/api/pedidos/${id}"))
						.uri("http://localhost:8006/")
				)
				.build();
	}
}
