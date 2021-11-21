package com.superngb.monitoring_system.Configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/admin").setViewName("admin/admin");
        registry.addViewController("/admin/personalities").setViewName("admin/admin_personalities");
        registry.addViewController("/admin/users").setViewName("admin/admin_users");
    }
}
