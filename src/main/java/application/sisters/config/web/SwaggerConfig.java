package application.sisters.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("application.sisters"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaInfo());

    }

    /**
     * Create meta info
     *
     * @return ApiInfo
     */
    private ApiInfo metaInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Sisters Shop Swagger API",
                "Sisters Shop Swagger API for private use",
                "1.0",
                "Terms of Service",
                new Contact("Vadym Pylypchenko", "https://pvadym.github.io/", "vadym_pylyp@ukr.net"),
                "Apache License Version 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0"
        );

        return apiInfo;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}