package com.superngb.monitoring_system.Controllers.AdminPanel;

import com.superngb.monitoring_system.DTOs.GroupDtoModel;
import com.superngb.monitoring_system.DTOs.person.PersonalityDtoModel;
import com.superngb.monitoring_system.DTOs.person.StudentDtoModel;
import com.superngb.monitoring_system.DTOs.person.TeacherDtoModel;
import com.superngb.monitoring_system.DTOs.person.UserDtoModel;
import com.superngb.monitoring_system.UseCases.AdminPanel.AdminPanelInputBoundary;
import com.superngb.monitoring_system.UseCases.AdminPanel.InputData.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminPanelController {

    private final AdminPanelInputBoundary adminPanelInputBoundary;

    public AdminPanelController(AdminPanelInputBoundary adminPanelInputBoundary) {
        this.adminPanelInputBoundary = adminPanelInputBoundary;
    }

    // Users

    @GetMapping("/allUsers")
    public List<UserDtoModel> allUsers(){
        return adminPanelInputBoundary.getUsers();
    }

    @PostMapping("/filterUsers")
    public List<UserDtoModel> filterUsers(@RequestBody UserFilterRequestModel userFilterRequestModel){
        return adminPanelInputBoundary.filterUsers(userFilterRequestModel);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public UserDtoModel deleteUser(@PathVariable Long userId){
        return adminPanelInputBoundary.deleteUser(userId);
    }

    @GetMapping("/user/{userId}")
    public UserDtoModel showUser(@PathVariable Long userId){
        return adminPanelInputBoundary.findUser(userId);
    }

    @PutMapping("/user/{userId}")
    public boolean editUser(@PathVariable Long userId,
                            @RequestBody UserEditRequestModel userEditRequestModel){
        return adminPanelInputBoundary.editUser(userId, userEditRequestModel);
    }

    @PostMapping("/addUser")
    public boolean addUser(@RequestBody UserPostRequestModel userPostRequestModel){
        return adminPanelInputBoundary.createUser(userPostRequestModel);
    }

    // Personalities

    @GetMapping("/allPersonalities")
    public List<PersonalityDtoModel> allPersonalities(){
        return adminPanelInputBoundary.getPersonalities();
    }

    @PostMapping("/filterPersonalities")
    public List<PersonalityDtoModel> filterPersonalities(@RequestBody PersonalityFilterRequestModel personalityFilterRequestModel){
        return adminPanelInputBoundary.filterPersonalities(personalityFilterRequestModel);
    }

    @DeleteMapping("/deletePersonality/{personalityId}")
    public PersonalityDtoModel deletePersonality(@PathVariable Long personalityId){
        return adminPanelInputBoundary.deletePersonality(personalityId);
    }

    @GetMapping("/personality/{personalityId}")
    public PersonalityDtoModel showPersonality(@PathVariable Long personalityId){
        return adminPanelInputBoundary.findPersonality(personalityId);
    }

    @PutMapping("/personality/{personalityId}")
    public boolean editPersonality(@PathVariable Long personalityId,
                            @RequestBody PersonalityEditRequestModel personalityEditRequestModel){
        return adminPanelInputBoundary.editPersonality(personalityId, personalityEditRequestModel);
    }

    @PostMapping("/addPersonality")
    public boolean addPersonality(@RequestBody PersonalityPostRequestModel personalityPostRequestModel){
        return adminPanelInputBoundary.createPersonality(personalityPostRequestModel);
    }

    @GetMapping("/personality/{personalityId}/user")
    public UserDtoModel showUserByPersonality(@PathVariable Long personalityId){
        return adminPanelInputBoundary.findUserByPersonality(personalityId);
    }

    @GetMapping("/personality/{personalityId}/student")
    public StudentDtoModel showStudentByPersonality(@PathVariable Long personalityId){
        return adminPanelInputBoundary.findStudentByPersonality(personalityId);
    }

    @GetMapping("/personality/{personalityId}/teacher")
    public TeacherDtoModel showTeacherByPersonality(@PathVariable Long personalityId){
        return adminPanelInputBoundary.findTeacherByPersonality(personalityId);
    }

    // Groups

    @GetMapping("/allGroups")
    public List<GroupDtoModel> allGroups(){
        return adminPanelInputBoundary.getGroups();
    }

    @PostMapping("/filterGroups")
    public List<GroupDtoModel> filterGroups(@RequestBody GroupFilterRequestModel groupFilterRequestModel){
        return adminPanelInputBoundary.filterGroups(groupFilterRequestModel);
    }

    @DeleteMapping("/deleteGroup/{groupId}")
    public GroupDtoModel deleteGroup(@PathVariable Long groupId){
        return adminPanelInputBoundary.deleteGroup(groupId);
    }

    @GetMapping("/group/{groupId}")
    public GroupDtoModel showGroup(@PathVariable Long groupId){
        return adminPanelInputBoundary.findGroup(groupId);
    }

    @PutMapping("/group/{groupId}")
    public boolean editGroup(@PathVariable Long groupId,
                                   @RequestBody GroupEditRequestModel groupEditRequestModel){
        return adminPanelInputBoundary.editGroup(groupId, groupEditRequestModel);
    }

    @PostMapping("/addGroup")
    public boolean addGroup(@RequestBody GroupPostRequestModel groupPostRequestModel){
        return adminPanelInputBoundary.createGroup(groupPostRequestModel);
    }

    // Students

    @GetMapping("/allStudents")
    public List<StudentDtoModel> allStudents(){
        return adminPanelInputBoundary.getStudents();
    }

    @PostMapping("/filterStudents")
    public List<StudentDtoModel> filterStudents(@RequestBody StudentFilterRequestModel studentFilterRequestModel){
        return adminPanelInputBoundary.filterStudents(studentFilterRequestModel);
    }

    @DeleteMapping("/deleteStudent/{studentId}")
    public StudentDtoModel deleteStudent(@PathVariable Long studentId){
        return adminPanelInputBoundary.deleteStudent(studentId);
    }

    @GetMapping("/student/{studentId}")
    public StudentDtoModel showStudent(@PathVariable Long studentId){
        return adminPanelInputBoundary.findStudent(studentId);
    }

    @PutMapping("/student/{studentId}")
    public boolean editStudent(@PathVariable Long studentId,
                             @RequestBody StudentEditRequestModel studentEditRequestModel){
        return adminPanelInputBoundary.editStudent(studentId, studentEditRequestModel);
    }

    @PostMapping("/addStudent")
    public boolean addStudent(@RequestBody StudentPostRequestModel studentPostRequestModel){
        return adminPanelInputBoundary.createStudent(studentPostRequestModel);
    }

    // Teachers

    @GetMapping("/allTeachers")
    public List<TeacherDtoModel> allTeachers(){
        return adminPanelInputBoundary.getTeachers();
    }

    @PostMapping("/filterTeachers")
    public List<TeacherDtoModel> filterTeachers(@RequestBody TeacherFilterRequestModel teacherFilterRequestModel){
        return adminPanelInputBoundary.filterTeachers(teacherFilterRequestModel);
    }

    @DeleteMapping("/deleteTeacher/{teacherId}")
    public TeacherDtoModel deleteTeacher(@PathVariable Long teacherId){
        return adminPanelInputBoundary.deleteTeacher(teacherId);
    }

    @GetMapping("/teacher/{teacherId}")
    public TeacherDtoModel showTeacher(@PathVariable Long teacherId){
        return adminPanelInputBoundary.findTeacher(teacherId);
    }

    @PutMapping("/teacher/{teacherId}")
    public boolean editTeacher(@PathVariable Long teacherId,
                               @RequestBody TeacherEditRequestModel teacherEditRequestModel){
        return adminPanelInputBoundary.editTeacher(teacherId, teacherEditRequestModel);
    }

    @PostMapping("/addTeacher")
    public boolean addTeacher(@RequestBody TeacherPostRequestModel teacherPostRequestModel){
        return adminPanelInputBoundary.createTeacher(teacherPostRequestModel);
    }
}
