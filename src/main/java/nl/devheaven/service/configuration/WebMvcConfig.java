package nl.devheaven.service.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Configures the resource handlers for the Swagger UI.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    /**
     * Adds resource handlers for the swagger-ui since that automatic
     * mapping of resource handlers is disabled in settings.
     *
     * @param registry the resource handler registry.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }

    /**
     * Adds cors mapping to allow access from anywhere.
     *
     * @param registry the cors registry.
     */
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
    }

}
