package com.superngb.monitoring_system.UseCases.AdminPanel;

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

    List<SubjectDtoModel> getSubjects();
    SubjectDtoModel findSubject(Long id);
    SubjectDtoModel findSubjectsByGroups(List<Long> groups);
    boolean editSubject(Long id, SubjectEditRequestModel subjectEditRequestModel);
    List<SubjectDtoModel> filterSubjects(SubjectFilterRequestModel subjectFilterRequestModel);
    SubjectDtoModel deleteSubject(Long id);
    boolean createSubject(SubjectPostRequestModel subjectPostRequestModel);

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
