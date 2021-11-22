package com.superngb.monitoring_system.UseCases.AdminPanel;

import com.superngb.monitoring_system.DTOs.GroupDtoModel;
import com.superngb.monitoring_system.DTOs.person.PersonalityDtoModel;
import com.superngb.monitoring_system.DTOs.person.StudentDtoModel;
import com.superngb.monitoring_system.DTOs.person.TeacherDtoModel;
import com.superngb.monitoring_system.DTOs.person.UserDtoModel;
import com.superngb.monitoring_system.UseCases.AdminPanel.InputData.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AdminPanelInputBoundary extends UserDetailsService {
    List<UserDtoModel> getUsers();
    UserDtoModel findUser(Long id);
    UserDtoModel findUserByPersonality(Long id);
    boolean editUser(Long id, UserEditRequestModel userEditRequestModel);
    List<UserDtoModel> filterUsers(UserFilterRequestModel userFilterRequestModel);
    UserDtoModel deleteUser(Long id);
    boolean createUser(UserPostRequestModel userPostRequestModel);

    List<PersonalityDtoModel> getPersonalities();
    PersonalityDtoModel findPersonality(Long id);
    boolean editPersonality(Long id, PersonalityEditRequestModel personalityEditRequestModel);
    List<PersonalityDtoModel> filterPersonalities(PersonalityFilterRequestModel filterPersonalityModel);
    PersonalityDtoModel deletePersonality(Long id);
    boolean createPersonality(PersonalityPostRequestModel personalityPostRequestModel);

    List<GroupDtoModel> getGroups();
    GroupDtoModel findGroup(Long id);
    boolean editGroup(Long id, GroupEditRequestModel groupEditRequestModel);
    List<GroupDtoModel> filterGroups(GroupFilterRequestModel groupFilterRequestModel);
    GroupDtoModel deleteGroup(Long id);
    boolean createGroup(GroupPostRequestModel groupPostRequestModel);

    List<StudentDtoModel> getStudents();
    StudentDtoModel findStudent(Long id);
    StudentDtoModel findStudentByPersonality(Long id);
    List<StudentDtoModel> findStudentsByGroup(Long id);
    boolean editStudent(Long id, StudentEditRequestModel studentEditRequestModel);
    List<StudentDtoModel> filterStudents(StudentFilterRequestModel studentFilterRequestModel);
    StudentDtoModel deleteStudent(Long id);
    boolean createStudent(StudentPostRequestModel studentPostRequestModel);

    List<TeacherDtoModel> getTeachers();
    TeacherDtoModel findTeacher(Long id);
    TeacherDtoModel findTeacherByPersonality(Long id);
    boolean editTeacher(Long id, TeacherEditRequestModel teacherEditRequestModel);
    List<TeacherDtoModel> filterTeachers(TeacherFilterRequestModel teacherFilterRequestModel);
    TeacherDtoModel deleteTeacher(Long id);
    boolean createTeacher(TeacherPostRequestModel teacherPostRequestModel);
}
