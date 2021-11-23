package com.superngb.monitoring_system.Controllers.AdminPanel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminPanelPageController {

    @GetMapping("/admin/personalities/{personalityId}")
    public String showUser(@PathVariable Long personalityId){
        return "admin/admin_personality";
    }

    @GetMapping("/admin/groups/{groupId}")
    public String showGroup(@PathVariable Long groupId){
        return "admin/admin_group";
    }

    @GetMapping("/admin/subjects/{subjectId}")
    public String showSubject(@PathVariable Long subjectId){
        return "admin/admin_subject";
    }

    @GetMapping("/admin/subject/{subjectId}/group/{groupId}")
    public String showGroupMarks(@PathVariable Long subjectId, @PathVariable Long groupId){
        return "admin/admin_subject_group";
    }

}
