package com.superngb.monitoring_system.Controllers.AdminPanel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    @GetMapping("/admin/personalities/{personalityId}")
    public String showConcreteUser(@PathVariable Long personalityId){
        return "admin/admin_personality";
    }
}
