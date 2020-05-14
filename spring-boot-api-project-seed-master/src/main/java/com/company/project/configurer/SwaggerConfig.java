package com.company.project.configurer;

import static com.google.common.base.Predicates.or;
import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.ant;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationScopeBuilder;
import springfox.documentation.builders.ImplicitGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.LoginEndpoint;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.google.common.base.Predicate;

/**
 * @author Zee
 * @createDate 2017年4月13日 下午3:17:54
 * @updateDate 2017年4月13日 下午3:17:54
 * @description Swagger相关的配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private List<Parameter> setHeaderToken() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("sign").description("请求签名，如sign=19e907700db7ad91318424a97c54ed57").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return pars;
    }
	
	@Bean
	public Docket gpApi() {
		Predicate<String> or = or(PathSelectors.ant("/**"));
		return new Docket(DocumentationType.SWAGGER_2).groupName("BaseApi").apiInfo(gpApiInfo()).select().paths(or).build()//
				.genericModelSubstitutes(DeferredResult.class)//
				.forCodeGeneration(false)//
				.globalOperationParameters(setHeaderToken())
				.pathMapping("/");
	}


	private ApiInfo gpApiInfo() {
		return new ApiInfo("基础通用功能（base）", // 标题
				"用户、角色、权限、日志、消息、附件等通用表的API文档。", // 描述
				"General Purpose", // 版本
				"", new Contact("LX", "LX", "luxiang"), // 作者
				"", // 许可
				"", // 许可地址
				Collections.emptyList());
	}


	public Docket petApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("full-petstore-api").apiInfo(apiInfo()).select().paths(petstorePaths()).build().securitySchemes(newArrayList(oauth())).securityContexts(newArrayList(securityContext()));
	}

	public Docket categoryApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("category-api").apiInfo(apiInfo()).select().paths(categoryPaths()).build().ignoredParameterTypes(ApiIgnore.class).enableUrlTemplating(true);
	}

	public Docket multipartApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("multipart-api").apiInfo(apiInfo()).select().paths(multipartPaths()).build();
	}

	private Predicate<String> categoryPaths() {
		return regex("/category.*");
	}

	private Predicate<String> multipartPaths() {
		return regex("/upload.*");
	}

	public Docket userApi() {
		AuthorizationScope[] authScopes = new AuthorizationScope[1];
		authScopes[0] = new AuthorizationScopeBuilder().scope("read").description("read access").build();
		SecurityReference securityReference = SecurityReference.builder().reference("test").scopes(authScopes).build();

		ArrayList<SecurityContext> securityContexts = newArrayList(SecurityContext.builder().securityReferences(newArrayList(securityReference)).build());
		return new Docket(DocumentationType.SWAGGER_2).securitySchemes(newArrayList(new BasicAuth("test"))).securityContexts(securityContexts).groupName("user-api").apiInfo(apiInfo()).select().paths(userOnlyEndpoints()).build();
	}

	private Predicate<String> petstorePaths() {
		return or(regex("/api/pet.*"), regex("/api/user.*"), regex("/api/store.*"));
	}

	private Predicate<String> userOnlyEndpoints() {
		return new Predicate<String>() {
			@Override
			public boolean apply(String input) {
				return input.contains("user");
			}
		};
	}

	@SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Springfox petstore API").description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum " + "has been the industry's standard dummy text ever since the 1500s, when an unknown printer " + "took a " + "galley of type and scrambled it to make a type specimen book. It has survived not only five " + "centuries, but also the leap into electronic typesetting, remaining essentially unchanged. " + "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum " + "passages, and more recently with desktop publishing software like Aldus PageMaker including " + "versions of Lorem Ipsum.").termsOfServiceUrl("http://springfox.io").contact("springfox").license("Apache License Version 2.0")
				.licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE").version("2.0").build();
	}

	@Bean
	SecurityContext securityContext() {
		AuthorizationScope readScope = new AuthorizationScope("read:pets", "read your pets");
		AuthorizationScope[] scopes = new AuthorizationScope[1];
		scopes[0] = readScope;
		SecurityReference securityReference = SecurityReference.builder().reference("petstore_auth").scopes(scopes).build();

		return SecurityContext.builder().securityReferences(newArrayList(securityReference)).forPaths(ant("/api/pet.*")).build();
	}

	@Bean
	SecurityScheme oauth() {
		return new OAuthBuilder().name("petstore_auth").grantTypes(grantTypes()).scopes(scopes()).build();
	}

	@Bean
	SecurityScheme apiKey() {
		return new ApiKey("api_key", "api_key", "header");
	}

	List<AuthorizationScope> scopes() {
		return newArrayList(new AuthorizationScope("write:wines", "modify pets in your account"), new AuthorizationScope("read:pets", "read your pets"));
	}

	List<GrantType> grantTypes() {
		GrantType grantType = new ImplicitGrantBuilder().loginEndpoint(new LoginEndpoint("http://petstore.swagger.io/api/oauth/dialog")).build();
		return newArrayList(grantType);
	}

}
