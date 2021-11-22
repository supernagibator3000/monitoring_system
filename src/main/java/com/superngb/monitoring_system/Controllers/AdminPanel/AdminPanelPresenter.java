package com.superngb.monitoring_system.Controllers.AdminPanel;

import com.superngb.monitoring_system.DTOs.GroupDtoModel;
import com.superngb.monitoring_system.DTOs.person.PersonalityDtoModel;
import com.superngb.monitoring_system.DTOs.person.StudentDtoModel;
import com.superngb.monitoring_system.DTOs.person.TeacherDtoModel;
import com.superngb.monitoring_system.DTOs.person.UserDtoModel;
import com.superngb.monitoring_system.UseCases.AdminPanel.AdminPanelOutputBoundary;

import java.util.List;

public class AdminPanelPresenter implements AdminPanelOutputBoundary {

    @Override
    public boolean prepareSuccessPostUserView(UserDtoModel userDtoModel) {
        return true;
    }

    @Override
    public boolean prepareFailPostUserView() {
        return false;
    }

    @Override
    public boolean prepareSuccessEditUserView(UserDtoModel userDtoModel) {
        return true;
    }

    @Override
    public boolean prepareFailEditUserView(UserDtoModel userDtoModel) {
        return false;
    }

    @Override
    public UserDtoModel prepareDeletedUserView(UserDtoModel userDtoModel) {
        return userDtoModel;
    }

    @Override
    public UserDtoModel prepareFindedUserView(UserDtoModel userDtoModel) {
        return userDtoModel;
    }

    @Override
    public List<UserDtoModel> convertUsers(List<UserDtoModel> users) {
        return users;
    }

    @Override
    public boolean prepareSuccessPostPersonalityView(PersonalityDtoModel personalityDtoModel) {
        return true;
    }

    @Override
    public boolean prepareFailPostPersonalityView() {
        return false;
    }

    // Personality

    @Override
    public boolean prepareSuccessEditPersonalityView(PersonalityDtoModel personalityDtoModel) {
        return true;
    }

    @Override
    public boolean prepareFailEditPersonalityView(PersonalityDtoModel personalityDtoModel) {
        return false;
    }

    @Override
    public PersonalityDtoModel prepareDeletedPersonalityView(PersonalityDtoModel personalityDtoModel) {
        return personalityDtoModel;
    }

    @Override
    public PersonalityDtoModel prepareFindedPersonalityView(PersonalityDtoModel personalityDtoModel) {
        return personalityDtoModel;
    }

    @Override
    public List<PersonalityDtoModel> convertPersonalities(List<PersonalityDtoModel> personalityDtoModels) {
        return personalityDtoModels;
    }

    @Override
    public boolean prepareSuccessPostGroupView(GroupDtoModel groupDtoModel) {
        return true;
    }

    @Override
    public boolean prepareFailPostGroupView() {
        return false;
    }

    // Group

    @Override
    public boolean prepareSuccessEditGroupView(GroupDtoModel groupDtoModel) {
        return true;
    }

    @Override
    public boolean prepareFailEditGroupView(GroupDtoModel groupDtoModel) {
        return false;
    }

    @Override
    public GroupDtoModel prepareDeletedGroupView(GroupDtoModel groupDtoModel) {
        return groupDtoModel;
    }

    @Override
    public GroupDtoModel prepareFindedGroupView(GroupDtoModel groupDtoModel) {
        return groupDtoModel;
    }

    @Override
    public List<GroupDtoModel> convertGroups(List<GroupDtoModel> groupDtoModels) {
        return groupDtoModels;
    }

    @Override
    public boolean prepareSuccessPostStudentView(StudentDtoModel studentDtoModel) {
        return true;
    }

    @Override
    public boolean prepareFailPostStudentView() {
        return false;
    }

    // Students

    @Override
    public boolean prepareSuccessEditStudentView(StudentDtoModel studentDtoModel) {
        return true;
    }

    @Override
    public boolean prepareFailEditStudentView(StudentDtoModel studentDtoModel) {
        return false;
    }

    @Override
    public StudentDtoModel prepareDeletedStudentView(StudentDtoModel studentDtoModel) {
        return studentDtoModel;
    }

    @Override
    public StudentDtoModel prepareFindedStudentView(StudentDtoModel studentDtoModel) {
        return studentDtoModel;
    }

    @Override
    public List<StudentDtoModel> convertStudents(List<StudentDtoModel> studentDtoModels) {
        return studentDtoModels;
    }

    // Teachers

    @Override
    public boolean prepareSuccessPostTeacherView(TeacherDtoModel teacherDtoModel) {
        return true;
    }

    @Override
    public boolean prepareFailPostTeacherView() {
        return false;
    }

    @Override
    public boolean prepareSuccessEditTeacherView(TeacherDtoModel teacherDtoModel) {
        return false;
    }

    @Override
    public boolean prepareFailEditTeacherView(TeacherDtoModel teacherDtoModel) {
        return false;
    }

    @Override
    public TeacherDtoModel prepareDeletedTeacherView(TeacherDtoModel teacherDtoModel) {
        return teacherDtoModel;
    }

    @Override
    public TeacherDtoModel prepareFindedTeacherView(TeacherDtoModel teacherDtoModel) {
        return teacherDtoModel;
    }

    @Override
    public List<TeacherDtoModel> convertTeachers(List<TeacherDtoModel> teacherDtoModels) {
        return teacherDtoModels;
    }
}
