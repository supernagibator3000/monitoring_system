package com.superngb.monitoring_system.UseCases.Teacher;

import com.superngb.monitoring_system.DTOs.GroupDtoModel;
import com.superngb.monitoring_system.DTOs.SubjectDtoModel;
import com.superngb.monitoring_system.DTOs.event.CheckpointDtoModel;
import com.superngb.monitoring_system.DTOs.event.LessonDtoModel;
import com.superngb.monitoring_system.DTOs.mark.AttendanceDtoModel;
import com.superngb.monitoring_system.DTOs.mark.ScoreDtoModel;
import com.superngb.monitoring_system.DTOs.person.StudentDtoModel;
import com.superngb.monitoring_system.DTOs.person.TeacherDtoModel;

import java.util.List;

public interface TeacherOutputBoundary {

    GroupDtoModel prepareFindedGroupView(GroupDtoModel groupDtoModel);
    List<GroupDtoModel> convertGroups(List<GroupDtoModel> groupDtoModels);

    StudentDtoModel prepareFindedStudentView(StudentDtoModel studentDtoModel);
    List<StudentDtoModel> convertStudents(List<StudentDtoModel> studentDtoModels);

    TeacherDtoModel prepareFindedTeacherView(TeacherDtoModel teacherDtoModel);
    List<TeacherDtoModel> convertTeachers(List<TeacherDtoModel> teacherDtoModels);

    SubjectDtoModel prepareFindedSubjectView(SubjectDtoModel subjectDtoModel);
    List<SubjectDtoModel> convertSubjects(List<SubjectDtoModel> subjectDtoModels);

    boolean prepareSuccessPostCheckpointView(CheckpointDtoModel checkpointDtoModel);
    boolean prepareFailPostCheckpointView();
    boolean prepareSuccessEditCheckpointView(CheckpointDtoModel checkpointDtoModel);
    boolean prepareFailEditCheckpointView(CheckpointDtoModel checkpointDtoModel);
    CheckpointDtoModel prepareDeletedCheckpointView(CheckpointDtoModel checkpointDtoModel);
    CheckpointDtoModel prepareFindedCheckpointView(CheckpointDtoModel checkpointDtoModel);
    List<CheckpointDtoModel> convertCheckpoints(List<CheckpointDtoModel> checkpointDtoModels);

    boolean prepareSuccessPostLessonView(LessonDtoModel lessonDtoModel);
    boolean prepareFailPostLessonView();
    boolean prepareSuccessEditLessonView(LessonDtoModel lessonDtoModel);
    boolean prepareFailEditLessonView(LessonDtoModel lessonDtoModel);
    LessonDtoModel prepareDeletedLessonView(LessonDtoModel lessonDtoModel);
    LessonDtoModel prepareFindedLessonView(LessonDtoModel lessonDtoModel);
    List<LessonDtoModel> convertLessons(List<LessonDtoModel> lessonDtoModels);

    boolean prepareSuccessPostAttendanceView(AttendanceDtoModel attendanceDtoModel);
    boolean prepareFailPostAttendanceView();
    boolean prepareSuccessEditAttendanceView(AttendanceDtoModel attendanceDtoModel);
    boolean prepareFailEditAttendanceView(AttendanceDtoModel attendanceDtoModel);
    AttendanceDtoModel prepareDeletedAttendanceView(AttendanceDtoModel attendanceDtoModel);
    AttendanceDtoModel prepareFindedAttendanceView(AttendanceDtoModel attendanceDtoModel);
    List<AttendanceDtoModel> convertAttendances(List<AttendanceDtoModel> attendanceDtoModels);

    boolean prepareSuccessPostScoreView(ScoreDtoModel scoreDtoModel);
    boolean prepareFailPostScoreView();
    boolean prepareSuccessEditScoreView(ScoreDtoModel scoreDtoModel);
    boolean prepareFailEditScoreView(ScoreDtoModel scoreDtoModel);
    ScoreDtoModel prepareDeletedScoreView(ScoreDtoModel scoreDtoModel);
    ScoreDtoModel prepareFindedScoreView(ScoreDtoModel scoreDtoModel);
    List<ScoreDtoModel> convertScores(List<ScoreDtoModel> scoreDtoModels);
}
