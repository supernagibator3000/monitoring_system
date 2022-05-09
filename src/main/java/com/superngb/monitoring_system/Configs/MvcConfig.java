package com.superngb.monitoring_system.Configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/profile").setViewName("profile");
        registry.addViewController("/admin").setViewName("admin/admin");
        registry.addViewController("/admin/personalities").setViewName("admin/admin_personalities");
        registry.addViewController("/admin/users").setViewName("admin/admin_users");
        registry.addViewController("/admin/teachers").setViewName("admin/admin_teachers");
        registry.addViewController("/admin/students").setViewName("admin/admin_students");
        registry.addViewController("/admin/groups").setViewName("admin/admin_groups");
        registry.addViewController("/admin/subjects").setViewName("admin/admin_subjects");
        registry.addViewController("/admin/events").setViewName("admin/admin_events");
        registry.addViewController("/teacher").setViewName("teacher/teacher");
        registry.addViewController("/teacher/subjects").setViewName("teacher/teacher_subjects");
    }
}
