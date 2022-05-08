package com.superngb.monitoring_system.Controllers.Teacher;

import com.superngb.monitoring_system.DTOs.GroupDtoModel;
import com.superngb.monitoring_system.DTOs.SubjectDtoModel;
import com.superngb.monitoring_system.DTOs.event.CheckpointDtoModel;
import com.superngb.monitoring_system.DTOs.event.LessonDtoModel;
import com.superngb.monitoring_system.DTOs.mark.AttendanceDtoModel;
import com.superngb.monitoring_system.DTOs.mark.ScoreDtoModel;
import com.superngb.monitoring_system.DTOs.person.PersonalityDtoModel;
import com.superngb.monitoring_system.DTOs.person.StudentDtoModel;
import com.superngb.monitoring_system.DTOs.person.TeacherDtoModel;
import com.superngb.monitoring_system.UseCases.Teacher.TeacherOutputBoundary;

import java.util.List;

public class TeacherPresenter implements TeacherOutputBoundary {

    // Personality

    @Override
    public PersonalityDtoModel prepareFoundPersonalityView(PersonalityDtoModel personalityDtoModel) {
        return personalityDtoModel;
    }

    @Override
    public List<PersonalityDtoModel> convertPersonalities(List<PersonalityDtoModel> personalityDtoModels) {
        return personalityDtoModels;
    }

    // Group

    @Override
    public GroupDtoModel prepareFoundGroupView(GroupDtoModel groupDtoModel) {
        return groupDtoModel;
    }

    @Override
    public List<GroupDtoModel> convertGroups(List<GroupDtoModel> groupDtoModels) {
        return groupDtoModels;
    }

    // Students

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
    public TeacherDtoModel prepareFoundTeacherView(TeacherDtoModel teacherDtoModel) {
        return teacherDtoModel;
    }

    @Override
    public List<TeacherDtoModel> convertTeachers(List<TeacherDtoModel> teacherDtoModels) {
        return teacherDtoModels;
    }

    // Subjects

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
