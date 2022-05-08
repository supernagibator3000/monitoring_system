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

import java.util.List;

public interface AdminPanelOutputBoundary {
    boolean prepareSuccessPostUserView(UserDtoModel userDtoModel);
    boolean prepareFailPostUserView();
    boolean prepareSuccessEditUserView(UserDtoModel userDtoModel);
    boolean prepareFailEditUserView(UserDtoModel userDtoModel);
    UserDtoModel prepareDeletedUserView(UserDtoModel userDtoModel);
    UserDtoModel prepareFoundUserView(UserDtoModel userDtoModel);
    List<UserDtoModel> convertUsers(List<UserDtoModel> users);

    boolean prepareSuccessPostPersonalityView(PersonalityDtoModel personalityDtoModel);
    boolean prepareFailPostPersonalityView();
    boolean prepareSuccessEditPersonalityView(PersonalityDtoModel personalityDtoModel);
    boolean prepareFailEditPersonalityView(PersonalityDtoModel personalityDtoModel);
    PersonalityDtoModel prepareDeletedPersonalityView(PersonalityDtoModel personalityDtoModel);
    PersonalityDtoModel prepareFoundPersonalityView(PersonalityDtoModel personalityDtoModel);
    List<PersonalityDtoModel> convertPersonalities(List<PersonalityDtoModel> personalityDtoModels);

    boolean prepareSuccessPostGroupView(GroupDtoModel groupDtoModel);
    boolean prepareFailPostGroupView();
    boolean prepareSuccessEditGroupView(GroupDtoModel groupDtoModel);
    boolean prepareFailEditGroupView(GroupDtoModel groupDtoModel);
    GroupDtoModel prepareDeletedGroupView(GroupDtoModel groupDtoModel);
    GroupDtoModel prepareFoundGroupView(GroupDtoModel groupDtoModel);
    List<GroupDtoModel> convertGroups(List<GroupDtoModel> groupDtoModels);

    boolean prepareSuccessPostStudentView(StudentDtoModel studentDtoModel);
    boolean prepareFailPostStudentView();
    boolean prepareSuccessEditStudentView(StudentDtoModel studentDtoModel);
    boolean prepareFailEditStudentView(StudentDtoModel studentDtoModel);
    StudentDtoModel prepareDeletedStudentView(StudentDtoModel studentDtoModel);
    StudentDtoModel prepareFoundStudentView(StudentDtoModel studentDtoModel);
    List<StudentDtoModel> convertStudents(List<StudentDtoModel> studentDtoModels);

    boolean prepareSuccessPostTeacherView(TeacherDtoModel teacherDtoModel);
    boolean prepareFailPostTeacherView();
    boolean prepareSuccessEditTeacherView(TeacherDtoModel teacherDtoModel);
    boolean prepareFailEditTeacherView(TeacherDtoModel teacherDtoModel);
    TeacherDtoModel prepareDeletedTeacherView(TeacherDtoModel teacherDtoModel);
    TeacherDtoModel prepareFoundTeacherView(TeacherDtoModel teacherDtoModel);
    List<TeacherDtoModel> convertTeachers(List<TeacherDtoModel> teacherDtoModels);

    boolean prepareSuccessPostSubjectView(SubjectDtoModel subjectDtoModel);
    boolean prepareFailPostSubjectView();
    boolean prepareSuccessEditSubjectView(SubjectDtoModel subjectDtoModel);
    boolean prepareFailEditSubjectView(SubjectDtoModel subjectDtoModel);
    SubjectDtoModel prepareDeletedSubjectView(SubjectDtoModel subjectDtoModel);
    SubjectDtoModel prepareFoundSubjectView(SubjectDtoModel subjectDtoModel);
    List<SubjectDtoModel> convertSubjects(List<SubjectDtoModel> subjectDtoModels);

    boolean prepareSuccessPostCheckpointView(CheckpointDtoModel checkpointDtoModel);
    boolean prepareFailPostCheckpointView();
    boolean prepareSuccessEditCheckpointView(CheckpointDtoModel checkpointDtoModel);
    boolean prepareFailEditCheckpointView(CheckpointDtoModel checkpointDtoModel);
    CheckpointDtoModel prepareDeletedCheckpointView(CheckpointDtoModel checkpointDtoModel);
    CheckpointDtoModel prepareFoundCheckpointView(CheckpointDtoModel checkpointDtoModel);
    List<CheckpointDtoModel> convertCheckpoints(List<CheckpointDtoModel> checkpointDtoModels);

    boolean prepareSuccessPostLessonView(LessonDtoModel lessonDtoModel);
    boolean prepareFailPostLessonView();
    boolean prepareSuccessEditLessonView(LessonDtoModel lessonDtoModel);
    boolean prepareFailEditLessonView(LessonDtoModel lessonDtoModel);
    LessonDtoModel prepareDeletedLessonView(LessonDtoModel lessonDtoModel);
    LessonDtoModel prepareFoundLessonView(LessonDtoModel lessonDtoModel);
    List<LessonDtoModel> convertLessons(List<LessonDtoModel> lessonDtoModels);

    boolean prepareSuccessPostAttendanceView(AttendanceDtoModel attendanceDtoModel);
    boolean prepareFailPostAttendanceView();
    boolean prepareSuccessEditAttendanceView(AttendanceDtoModel attendanceDtoModel);
    boolean prepareFailEditAttendanceView(AttendanceDtoModel attendanceDtoModel);
    AttendanceDtoModel prepareDeletedAttendanceView(AttendanceDtoModel attendanceDtoModel);
    AttendanceDtoModel prepareFoundAttendanceView(AttendanceDtoModel attendanceDtoModel);
    List<AttendanceDtoModel> convertAttendances(List<AttendanceDtoModel> attendanceDtoModels);

    boolean prepareSuccessPostScoreView(ScoreDtoModel scoreDtoModel);
    boolean prepareFailPostScoreView();
    boolean prepareSuccessEditScoreView(ScoreDtoModel scoreDtoModel);
    boolean prepareFailEditScoreView(ScoreDtoModel scoreDtoModel);
    ScoreDtoModel prepareDeletedScoreView(ScoreDtoModel scoreDtoModel);
    ScoreDtoModel prepareFoundScoreView(ScoreDtoModel scoreDtoModel);
    List<ScoreDtoModel> convertScores(List<ScoreDtoModel> scoreDtoModels);
}
