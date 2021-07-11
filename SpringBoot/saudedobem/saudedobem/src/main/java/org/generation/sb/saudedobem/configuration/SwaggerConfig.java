package org.generation.sb.saudedobem.configuration;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("org.generation.sb.saudedobem.controller"))
				.paths(PathSelectors.any()).build().apiInfo(metadata()).useDefaultResponseMessages(false)
				.globalResponses(HttpMethod.GET, responseMessageForGET())
				.globalResponses(HttpMethod.POST, responseMessageForPOST())
				.globalResponses(HttpMethod.PUT, responseMessageForPUT())
				.globalResponses(HttpMethod.DELETE, responseMessageForDELETE());
	}

	public static ApiInfo metadata() {
		return new ApiInfoBuilder().title("API - Saúde do Bem").description("Projeto API Spring - Saúde do Bem")
				.version("0.7").license("Apache License Version 2.0").licenseUrl("http://localhost:8080/swagger-ui/")
				.contact(contact()).build();
	}

	private static Contact contact() {
		return new Contact("Grupo 03 - Generation Brasil", "https://github.com/gabrgcr/ProjetoIntegradorGen",
				"gabriel.cerdeira@outlook.com");
	}

	private static List<Response> responseMessageForGET() {
		return new ArrayList<Response>() {
			private static final long serialVersionUID = 1L;
			{
				add(new ResponseBuilder().code("200").description("Sucesso!").build());
				add(new ResponseBuilder().code("204").description("Sem Conteúdo!").build());
				add(new ResponseBuilder().code("401").description("Não Autorizado!").build());
				add(new ResponseBuilder().code("404").description("Não Encontrado!").build());
				add(new ResponseBuilder().code("500").description("Erro!").build());
			}
		};
	}

	private static List<Response> responseMessageForPOST() {
		return new ArrayList<Response>() {
			private static final long serialVersionUID = 1L;
			{
				add(new ResponseBuilder().code("201").description("Objeto Criado!").build());
				add(new ResponseBuilder().code("202").description("Aceito!").build());
				add(new ResponseBuilder().code("400").description("Requisição Incorreta!").build());
				add(new ResponseBuilder().code("401").description("Sem Autorização!").build());
				add(new ResponseBuilder().code("406").description("Negado!").build());
				add(new ResponseBuilder().code("409").description("Conflito!").build());
				add(new ResponseBuilder().code("500").description("Erro!").build());
			}
		};
	}

	private static List<Response> responseMessageForPUT() {
		return new ArrayList<Response>() {
			private static final long serialVersionUID = 1L;
			{
				add(new ResponseBuilder().code("200").description("Sucesso!").build());
				add(new ResponseBuilder().code("400").description("Requisição Incorreta!").build());
				add(new ResponseBuilder().code("404").description("Não Encontrado!").build());
				add(new ResponseBuilder().code("409").description("Conflito!").build());
				add(new ResponseBuilder().code("500").description("Erro!").build());
			}
		};
	}

	private static List<Response> responseMessageForDELETE() {
		return new ArrayList<Response>() {
			private static final long serialVersionUID = 1L;
			{
				add(new ResponseBuilder().code("200").description("Sucesso!").build());
				add(new ResponseBuilder().code("404").description("Não Encontrado!").build());
				add(new ResponseBuilder().code("500").description("Erro!").build());
			}
		};
	}
}
