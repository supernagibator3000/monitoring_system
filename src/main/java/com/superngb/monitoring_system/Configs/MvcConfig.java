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
        registry.addViewController("/admin/users_panel").setViewName("admin/admin_users");
        registry.addViewController("/admin/groups_panel").setViewName("admin/admin_groups");
    }
}
