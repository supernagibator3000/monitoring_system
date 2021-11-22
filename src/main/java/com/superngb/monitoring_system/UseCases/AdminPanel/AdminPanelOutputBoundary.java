package com.superngb.monitoring_system.UseCases.AdminPanel;

import com.superngb.monitoring_system.DTOs.GroupDtoModel;
import com.superngb.monitoring_system.DTOs.person.PersonalityDtoModel;
import com.superngb.monitoring_system.DTOs.person.StudentDtoModel;
import com.superngb.monitoring_system.DTOs.person.TeacherDtoModel;
import com.superngb.monitoring_system.DTOs.person.UserDtoModel;

import java.util.List;

public interface AdminPanelOutputBoundary {
    boolean prepareSuccessPostUserView(UserDtoModel userDtoModel);
    boolean prepareFailPostUserView();
    boolean prepareSuccessEditUserView(UserDtoModel userDtoModel);
    boolean prepareFailEditUserView(UserDtoModel userDtoModel);
    UserDtoModel prepareDeletedUserView(UserDtoModel userDtoModel);
    UserDtoModel prepareFindedUserView(UserDtoModel userDtoModel);
    List<UserDtoModel> convertUsers(List<UserDtoModel> users);

    boolean prepareSuccessPostPersonalityView(PersonalityDtoModel personalityDtoModel);
    boolean prepareFailPostPersonalityView();
    boolean prepareSuccessEditPersonalityView(PersonalityDtoModel personalityDtoModel);
    boolean prepareFailEditPersonalityView(PersonalityDtoModel personalityDtoModel);
    PersonalityDtoModel prepareDeletedPersonalityView(PersonalityDtoModel personalityDtoModel);
    PersonalityDtoModel prepareFindedPersonalityView(PersonalityDtoModel personalityDtoModel);
    List<PersonalityDtoModel> convertPersonalities(List<PersonalityDtoModel> personalityDtoModels);

    boolean prepareSuccessPostGroupView(GroupDtoModel groupDtoModel);
    boolean prepareFailPostGroupView();
    boolean prepareSuccessEditGroupView(GroupDtoModel groupDtoModel);
    boolean prepareFailEditGroupView(GroupDtoModel groupDtoModel);
    GroupDtoModel prepareDeletedGroupView(GroupDtoModel groupDtoModel);
    GroupDtoModel prepareFindedGroupView(GroupDtoModel groupDtoModel);
    List<GroupDtoModel> convertGroups(List<GroupDtoModel> groupDtoModels);

    boolean prepareSuccessPostStudentView(StudentDtoModel studentDtoModel);
    boolean prepareFailPostStudentView();
    boolean prepareSuccessEditStudentView(StudentDtoModel studentDtoModel);
    boolean prepareFailEditStudentView(StudentDtoModel studentDtoModel);
    StudentDtoModel prepareDeletedStudentView(StudentDtoModel studentDtoModel);
    StudentDtoModel prepareFindedStudentView(StudentDtoModel studentDtoModel);
    List<StudentDtoModel> convertStudents(List<StudentDtoModel> studentDtoModels);

    boolean prepareSuccessPostTeacherView(TeacherDtoModel teacherDtoModel);
    boolean prepareFailPostTeacherView();
    boolean prepareSuccessEditTeacherView(TeacherDtoModel teacherDtoModel);
    boolean prepareFailEditTeacherView(TeacherDtoModel teacherDtoModel);
    TeacherDtoModel prepareDeletedTeacherView(TeacherDtoModel teacherDtoModel);
    TeacherDtoModel prepareFindedTeacherView(TeacherDtoModel teacherDtoModel);
    List<TeacherDtoModel> convertTeachers(List<TeacherDtoModel> teacherDtoModels);
}
