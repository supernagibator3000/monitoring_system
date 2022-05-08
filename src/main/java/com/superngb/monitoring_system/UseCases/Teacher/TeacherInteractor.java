package com.superngb.monitoring_system.UseCases.Teacher;

import com.superngb.monitoring_system.DTOs.GroupDtoModel;
import com.superngb.monitoring_system.DTOs.SubjectDtoModel;
import com.superngb.monitoring_system.DTOs.event.CheckpointDtoModel;
import com.superngb.monitoring_system.DTOs.event.LessonDtoModel;
import com.superngb.monitoring_system.DTOs.mark.AttendanceDtoModel;
import com.superngb.monitoring_system.DTOs.mark.ScoreDtoModel;
import com.superngb.monitoring_system.DTOs.person.PersonalityDtoModel;
import com.superngb.monitoring_system.DTOs.person.StudentDtoModel;
import com.superngb.monitoring_system.DTOs.person.TeacherDtoModel;
import com.superngb.monitoring_system.UseCases.AdminPanel.InputData.*;
import com.superngb.monitoring_system.UseCases.Teacher.DataAccess.*;

import java.util.List;

public class TeacherInteractor implements TeacherInputBoundary{

    private final TeacherPersonalityDataAccess teacherPersonalityDataAccess;
    private final TeacherGroupDataAccess teacherGroupDataAccess;
    private final TeacherStudentDataAccess teacherStudentDataAccess;
    private final TeacherTeacherDataAccess teacherTeacherDataAccess;
    private final TeacherSubjectDataAccess teacherSubjectDataAccess;
    private final TeacherCheckpointDataAccess teacherCheckpointDataAccess;
    private final TeacherLessonDataAccess teacherLessonDataAccess;
    private final TeacherScoreDataAccess teacherScoreDataAccess;
    private final TeacherAttendanceDataAccess teacherAttendanceDataAccess;

    private final TeacherOutputBoundary adminPanelOutputBoundary;

    public TeacherInteractor(TeacherPersonalityDataAccess teacherPersonalityDataAccess,
                             TeacherGroupDataAccess teacherGroupDataAccess,
                             TeacherStudentDataAccess teacherStudentDataAccess,
                             TeacherTeacherDataAccess teacherTeacherDataAccess,
                             TeacherSubjectDataAccess teacherSubjectDataAccess,
                             TeacherCheckpointDataAccess teacherCheckpointDataAccess,
                             TeacherLessonDataAccess teacherLessonDataAccess,
                             TeacherScoreDataAccess teacherScoreDataAccess,
                             TeacherAttendanceDataAccess teacherAttendanceDataAccess,
                             TeacherOutputBoundary adminPanelOutputBoundary) {
        this.teacherPersonalityDataAccess = teacherPersonalityDataAccess;
        this.teacherGroupDataAccess = teacherGroupDataAccess;
        this.teacherStudentDataAccess = teacherStudentDataAccess;
        this.teacherTeacherDataAccess = teacherTeacherDataAccess;
        this.teacherSubjectDataAccess = teacherSubjectDataAccess;
        this.teacherCheckpointDataAccess = teacherCheckpointDataAccess;
        this.teacherLessonDataAccess = teacherLessonDataAccess;
        this.teacherScoreDataAccess = teacherScoreDataAccess;
        this.teacherAttendanceDataAccess = teacherAttendanceDataAccess;
        this.adminPanelOutputBoundary = adminPanelOutputBoundary;
    }

    @Override
    public List<PersonalityDtoModel> getPersonalities() {
        return null;
    }

    @Override
    public PersonalityDtoModel findPersonality(Long id) {
        return null;
    }

    @Override
    public List<GroupDtoModel> getGroups() {
        return null;
    }

    @Override
    public GroupDtoModel findGroup(Long id) {
        return null;
    }

    @Override
    public List<GroupDtoModel> filterGroups(GroupFilterRequestModel groupFilterRequestModel) {
        return null;
    }

    @Override
    public List<StudentDtoModel> getStudents() {
        return null;
    }

    @Override
    public StudentDtoModel findStudent(Long id) {
        return null;
    }

    @Override
    public StudentDtoModel findStudentByPersonality(Long id) {
        return null;
    }

    @Override
    public List<StudentDtoModel> findStudentsByGroup(Long id) {
        return null;
    }

    @Override
    public List<StudentDtoModel> filterStudents(StudentFilterRequestModel studentFilterRequestModel) {
        return null;
    }

    @Override
    public List<TeacherDtoModel> getTeachers() {
        return null;
    }

    @Override
    public TeacherDtoModel findTeacher(Long id) {
        return null;
    }

    @Override
    public TeacherDtoModel findTeacherByPersonality(Long id) {
        return null;
    }

    @Override
    public List<SubjectDtoModel> getSubjects() {
        return null;
    }

    @Override
    public SubjectDtoModel findSubject(Long id) {
        return null;
    }

    @Override
    public SubjectDtoModel findSubjectsByGroups(List<Long> groups) {
        return null;
    }

    @Override
    public List<SubjectDtoModel> filterSubjects(SubjectFilterRequestModel subjectFilterRequestModel) {
        return null;
    }

    @Override
    public List<CheckpointDtoModel> getCheckpoints() {
        return null;
    }

    @Override
    public CheckpointDtoModel findCheckpoint(Long id) {
        return null;
    }

    @Override
    public CheckpointDtoModel findCheckpointByName(String name) {
        return null;
    }

    @Override
    public CheckpointDtoModel findCheckpointBySubjectId(Long subjectId) {
        return null;
    }

    @Override
    public boolean editCheckpoint(Long id, CheckpointEditRequestModel checkpointEditRequestModel) {
        return false;
    }

    @Override
    public List<CheckpointDtoModel> filterCheckpoints(CheckpointFilterRequestModel checkpointFilterRequestModel) {
        return null;
    }

    @Override
    public CheckpointDtoModel deleteCheckpoint(Long id) {
        return null;
    }

    @Override
    public boolean createCheckpoint(CheckpointPostRequestModel checkpointPostRequestModel) {
        return false;
    }

    @Override
    public List<LessonDtoModel> getLessons() {
        return null;
    }

    @Override
    public LessonDtoModel findLesson(Long id) {
        return null;
    }

    @Override
    public LessonDtoModel findLessonByName(String name) {
        return null;
    }

    @Override
    public LessonDtoModel findLessonBySubjectId(Long subjectId) {
        return null;
    }

    @Override
    public boolean editLesson(Long id, LessonEditRequestModel lessonEditRequestModel) {
        return false;
    }

    @Override
    public List<LessonDtoModel> filterLessons(LessonFilterRequestModel lessonFilterRequestModel) {
        return null;
    }

    @Override
    public LessonDtoModel deleteLesson(Long id) {
        return null;
    }

    @Override
    public boolean createLesson(LessonPostRequestModel lessonPostRequestModel) {
        return false;
    }

    @Override
    public List<AttendanceDtoModel> getAttendance() {
        return null;
    }

    @Override
    public List<AttendanceDtoModel> findAttendanceByLesson(Long lessonId) {
        return null;
    }

    @Override
    public AttendanceDtoModel findAttendance(Long id) {
        return null;
    }

    @Override
    public boolean editAttendance(Long id, AttendanceEditRequestModel attendanceEditRequestModel) {
        return false;
    }

    @Override
    public List<AttendanceDtoModel> filterAttendance(AttendanceFilterRequestModel attendanceFilterRequestModel) {
        return null;
    }

    @Override
    public AttendanceDtoModel deleteAttendance(Long id) {
        return null;
    }

    @Override
    public boolean createAttendance(AttendancePostRequestModel attendancePostRequestModel) {
        return false;
    }

    @Override
    public List<ScoreDtoModel> getScores() {
        return null;
    }

    @Override
    public List<ScoreDtoModel> findScoresByCheckpoint(Long checkpointId) {
        return null;
    }

    @Override
    public ScoreDtoModel findScore(Long id) {
        return null;
    }

    @Override
    public boolean editScore(Long id, ScoreEditRequestModel scoreEditRequestModel) {
        return false;
    }

    @Override
    public List<ScoreDtoModel> filterScores(ScoreFilterRequestModel scoreFilterRequestModel) {
        return null;
    }

    @Override
    public ScoreDtoModel deleteScore(Long id) {
        return null;
    }

    @Override
    public boolean createScore(ScorePostRequestModel scorePostRequestModel) {
        return false;
    }
}
