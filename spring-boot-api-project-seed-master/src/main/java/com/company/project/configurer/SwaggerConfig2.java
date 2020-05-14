/**
 * com.lew.jlight.web.config.SwaggerConfig.java
 * Copyright 2017 Lifangyu, Inc. All rights reserved.
 * GomeGJ PROPRIETARY/CONFIDENTIAL.Use is subject to license terms.
 */
package com.company.project.configurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Desc:swagger api 的配置
 * <p>
 * 访问:http://ip:port/swagger-ui.html
 * </p>
 * <p>
 */

//@Configuration
//@EnableSwagger2
public class SwaggerConfig2 {

    /**
     * api信息
     *
     * @param name        标题
     * @param description 描述
     * @param version     版本
     * @return
     */
    private ApiInfo apiInfo(String name, String description, String version) {
        return new ApiInfoBuilder().title(name).description(description).version(version).build();
    }

    /**
     * 按照路由来分组
     *
     * @return
     */
    /*
     * @Bean public Docket web_api_bm() { return new
     * Docket(DocumentationType.SWAGGER_2) .apiInfo(apiInfo("price-api", "XX模块",
     * "1.0")) .select() .apis(RequestHandlerSelectors.any())
     * .paths(PathSelectors.ant("/app/**")) .build() .groupName("XX接口文档V1.0")
     * .pathMapping("/"); }
     */
    @Bean
    public Docket web_api_admin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("spring-boot-api-project-base", "智能平台", "1.0"))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/**"))
                .build()
                .groupName("智能平台接口文档V1.0")
                .pathMapping("/");
    }


    @Bean
    public Docket web_api_cu() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("spring-boot-api-project-base", "智能平台", "1.0"))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/f*/**"))
                .build()
                .groupName("前端展示V1.0")
                .pathMapping("/");
    }
    

    @Bean
    public Docket web_api_ht() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("spring-boot-api-project-base", "智能平台", "1.0"))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/t*/**"))
                .build()
                .groupName("后台管理V1.0")
                .pathMapping("/");
    }
}