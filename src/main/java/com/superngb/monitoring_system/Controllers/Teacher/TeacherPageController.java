package com.superngb.monitoring_system.Controllers.Teacher;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TeacherPageController {

    @GetMapping("/teacher/subjects/{subjectId}")
    public String showSubject(@PathVariable Long subjectId){
        return "teacher/teacher_subject";
    }

    @GetMapping("/teacher/subject/{subjectId}/group/{groupId}")
    public String showGroupMarks(@PathVariable Long subjectId, @PathVariable Long groupId){
        return "teacher/teacher_subject_group";
    }
}
