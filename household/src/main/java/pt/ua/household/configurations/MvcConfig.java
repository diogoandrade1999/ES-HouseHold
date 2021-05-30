package pt.ua.household.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/dashboard").setViewName("dashboard");
        registry.addViewController("/house").setViewName("house");
        registry.addViewController("/user/login").setViewName("login");
        registry.addViewController("/user/logout").setViewName("login");
        registry.addViewController("/user/register").setViewName("register");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**", "/img/**", "/css/**", "/js/**", "/fonts/**", "/includes/**",
                "/vendor/**").addResourceLocations("/webjars/", "classpath:/static/img/", "classpath:/static/css/",
                        "classpath:/static/js/", "classpath:/static/fonts/", "classpath:/static/includes/",
                        "classpath:/static/vendor/");
    }

}
