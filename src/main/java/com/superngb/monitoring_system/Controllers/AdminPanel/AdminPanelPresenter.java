package com.superngb.monitoring_system.Controllers.AdminPanel;

import com.superngb.monitoring_system.DTOs.GroupDtoModel;
import com.superngb.monitoring_system.DTOs.SubjectDtoModel;
import com.superngb.monitoring_system.DTOs.event.CheckpointDtoModel;
import com.superngb.monitoring_system.DTOs.event.LessonDtoModel;
import com.superngb.monitoring_system.DTOs.mark.AttendanceDtoModel;
import com.superngb.monitoring_system.DTOs.mark.ScoreDtoModel;
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
    public UserDtoModel prepareFoundUserView(UserDtoModel userDtoModel) {
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
    public PersonalityDtoModel prepareFoundPersonalityView(PersonalityDtoModel personalityDtoModel) {
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
    public GroupDtoModel prepareFoundGroupView(GroupDtoModel groupDtoModel) {
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
    public StudentDtoModel prepareFoundStudentView(StudentDtoModel studentDtoModel) {
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
        return true;
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
    public TeacherDtoModel prepareFoundTeacherView(TeacherDtoModel teacherDtoModel) {
        return teacherDtoModel;
    }

    @Override
    public List<TeacherDtoModel> convertTeachers(List<TeacherDtoModel> teacherDtoModels) {
        return teacherDtoModels;
    }

    // Subjects

    @Override
    public boolean prepareSuccessPostSubjectView(SubjectDtoModel subjectDtoModel) {
        return true;
    }

    @Override
    public boolean prepareFailPostSubjectView() {
        return false;
    }

    @Override
    public boolean prepareSuccessEditSubjectView(SubjectDtoModel subjectDtoModel) {
        return true;
    }

    @Override
    public boolean prepareFailEditSubjectView(SubjectDtoModel subjectDtoModel) {
        return false;
    }

    @Override
    public SubjectDtoModel prepareDeletedSubjectView(SubjectDtoModel subjectDtoModel) {
        return subjectDtoModel;
    }

    @Override
    public SubjectDtoModel prepareFoundSubjectView(SubjectDtoModel subjectDtoModel) {
        return subjectDtoModel;
    }

    @Override
    public List<SubjectDtoModel> convertSubjects(List<SubjectDtoModel> subjectDtoModels) {
        return subjectDtoModels;
    }

    // Checkpoints

    @Override
    public boolean prepareSuccessPostCheckpointView(CheckpointDtoModel checkpointDtoModel) {
        return true;
    }

    @Override
    public boolean prepareFailPostCheckpointView() {
        return false;
    }

    @Override
    public boolean prepareSuccessEditCheckpointView(CheckpointDtoModel checkpointDtoModel) {
        return true;
    }

    @Override
    public boolean prepareFailEditCheckpointView(CheckpointDtoModel checkpointDtoModel) {
        return false;
    }

    @Override
    public CheckpointDtoModel prepareDeletedCheckpointView(CheckpointDtoModel checkpointDtoModel) {
        return checkpointDtoModel;
    }

    @Override
    public CheckpointDtoModel prepareFoundCheckpointView(CheckpointDtoModel checkpointDtoModel) {
        return checkpointDtoModel;
    }

    @Override
    public List<CheckpointDtoModel> convertCheckpoints(List<CheckpointDtoModel> checkpointDtoModels) {
        return checkpointDtoModels;
    }

    // Lessons

    @Override
    public boolean prepareSuccessPostLessonView(LessonDtoModel lessonDtoModel) {
        return true;
    }

    @Override
    public boolean prepareFailPostLessonView() {
        return false;
    }

    @Override
    public boolean prepareSuccessEditLessonView(LessonDtoModel lessonDtoModel) {
        return true;
    }

    @Override
    public boolean prepareFailEditLessonView(LessonDtoModel lessonDtoModel) {
        return false;
    }

    @Override
    public LessonDtoModel prepareDeletedLessonView(LessonDtoModel lessonDtoModel) {
        return lessonDtoModel;
    }

    @Override
    public LessonDtoModel prepareFoundLessonView(LessonDtoModel lessonDtoModel) {
        return lessonDtoModel;
    }

    @Override
    public List<LessonDtoModel> convertLessons(List<LessonDtoModel> lessonDtoModels) {
        return lessonDtoModels;
    }

    // Attendance

    @Override
    public boolean prepareSuccessPostAttendanceView(AttendanceDtoModel attendanceDtoModel) {
        return true;
    }

    @Override
    public boolean prepareFailPostAttendanceView() {
        return false;
    }

    @Override
    public boolean prepareSuccessEditAttendanceView(AttendanceDtoModel attendanceDtoModel) {
        return true;
    }

    @Override
    public boolean prepareFailEditAttendanceView(AttendanceDtoModel attendanceDtoModel) {
        return false;
    }

    @Override
    public AttendanceDtoModel prepareDeletedAttendanceView(AttendanceDtoModel attendanceDtoModel) {
        return attendanceDtoModel;
    }

    @Override
    public AttendanceDtoModel prepareFoundAttendanceView(AttendanceDtoModel attendanceDtoModel) {
        return attendanceDtoModel;
    }

    @Override
    public List<AttendanceDtoModel> convertAttendances(List<AttendanceDtoModel> attendanceDtoModels) {
        return attendanceDtoModels;
    }

    // Scores

    @Override
    public boolean prepareSuccessPostScoreView(ScoreDtoModel scoreDtoModel) {
        return true;
    }

    @Override
    public boolean prepareFailPostScoreView() {
        return false;
    }

    @Override
    public boolean prepareSuccessEditScoreView(ScoreDtoModel scoreDtoModel) {
        return true;
    }

    @Override
    public boolean prepareFailEditScoreView(ScoreDtoModel scoreDtoModel) {
        return false;
    }

    @Override
    public ScoreDtoModel prepareDeletedScoreView(ScoreDtoModel scoreDtoModel) {
        return scoreDtoModel;
    }

    @Override
    public ScoreDtoModel prepareFoundScoreView(ScoreDtoModel scoreDtoModel) {
        return scoreDtoModel;
    }

    @Override
    public List<ScoreDtoModel> convertScores(List<ScoreDtoModel> scoreDtoModels) {
        return scoreDtoModels;
    }

}
