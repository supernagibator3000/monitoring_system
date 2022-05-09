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
import com.superngb.monitoring_system.UseCases.Teacher.InputData.Edit.AttendanceEditRequestModel;
import com.superngb.monitoring_system.UseCases.Teacher.InputData.Edit.CheckpointEditRequestModel;
import com.superngb.monitoring_system.UseCases.Teacher.InputData.Edit.LessonEditRequestModel;
import com.superngb.monitoring_system.UseCases.Teacher.InputData.Edit.ScoreEditRequestModel;
import com.superngb.monitoring_system.UseCases.Teacher.InputData.Filter.*;
import com.superngb.monitoring_system.UseCases.Teacher.InputData.Post.AttendancePostRequestModel;
import com.superngb.monitoring_system.UseCases.Teacher.InputData.Post.CheckpointPostRequestModel;
import com.superngb.monitoring_system.UseCases.Teacher.InputData.Post.LessonPostRequestModel;
import com.superngb.monitoring_system.UseCases.Teacher.InputData.Post.ScorePostRequestModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TeacherInputBoundary {

    List<PersonalityDtoModel> getPersonalities();
    PersonalityDtoModel findPersonality(Long id);

    List<GroupDtoModel> getGroups();
    GroupDtoModel findGroup(Long id);
    List<GroupDtoModel> filterGroups(GroupFilterRequestModel groupFilterRequestModel);

    List<StudentDtoModel> getStudents();
    StudentDtoModel findStudent(Long id);
    StudentDtoModel findStudentByPersonality(Long id);
    List<StudentDtoModel> findStudentsByGroup(Long id);
    List<StudentDtoModel> filterStudents(StudentFilterRequestModel studentFilterRequestModel);

    List<TeacherDtoModel> getTeachers();
    TeacherDtoModel findTeacher(Long id);
    TeacherDtoModel findTeacherByPersonality(Long id);

    List<SubjectDtoModel> getSubjects();
    SubjectDtoModel findSubject(Long id);
    SubjectDtoModel findSubjectsByGroups(List<Long> groups);
    List<SubjectDtoModel> filterSubjects(SubjectFilterRequestModel subjectFilterRequestModel);

    List<CheckpointDtoModel> getCheckpoints();
    CheckpointDtoModel findCheckpoint(Long id);
    CheckpointDtoModel findCheckpointByName(String name);
    CheckpointDtoModel findCheckpointBySubjectId(Long subjectId);
    boolean editCheckpoint(Long id, CheckpointEditRequestModel checkpointEditRequestModel);
    List<CheckpointDtoModel> filterCheckpoints(CheckpointFilterRequestModel checkpointFilterRequestModel);
    CheckpointDtoModel deleteCheckpoint(Long id);
    boolean createCheckpoint(CheckpointPostRequestModel checkpointPostRequestModel);

    List<LessonDtoModel> getLessons();
    LessonDtoModel findLesson(Long id);
    LessonDtoModel findLessonByName(String name);
    LessonDtoModel findLessonBySubjectId(Long subjectId);
    boolean editLesson(Long id, LessonEditRequestModel lessonEditRequestModel);
    List<LessonDtoModel> filterLessons(LessonFilterRequestModel lessonFilterRequestModel);
    LessonDtoModel deleteLesson(Long id);
    boolean createLesson(LessonPostRequestModel lessonPostRequestModel);

    List<AttendanceDtoModel> getAttendance();
    List<AttendanceDtoModel> findAttendanceByLesson(Long lessonId);
    AttendanceDtoModel findAttendance(Long id);
    boolean editAttendance(Long id, AttendanceEditRequestModel attendanceEditRequestModel);
    List<AttendanceDtoModel> filterAttendance(AttendanceFilterRequestModel attendanceFilterRequestModel);
    AttendanceDtoModel deleteAttendance(Long id);
    boolean createAttendance(AttendancePostRequestModel attendancePostRequestModel);

    List<ScoreDtoModel> getScores();
    List<ScoreDtoModel> findScoresByCheckpoint(Long checkpointId);
    ScoreDtoModel findScore(Long id);
    boolean editScore(Long id, ScoreEditRequestModel scoreEditRequestModel);
    List<ScoreDtoModel> filterScores(ScoreFilterRequestModel scoreFilterRequestModel);
    ScoreDtoModel deleteScore(Long id);
    boolean createScore(ScorePostRequestModel scorePostRequestModel);
}
