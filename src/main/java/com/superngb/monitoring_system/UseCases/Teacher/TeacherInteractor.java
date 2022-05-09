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
import com.superngb.monitoring_system.Entities.Group;
import com.superngb.monitoring_system.Entities.Subject;
import com.superngb.monitoring_system.Entities.event.Checkpoint;
import com.superngb.monitoring_system.Entities.event.Lesson;
import com.superngb.monitoring_system.Entities.mark.Attendance;
import com.superngb.monitoring_system.Entities.mark.Score;
import com.superngb.monitoring_system.Entities.person.Personality;
import com.superngb.monitoring_system.Entities.person.Student;
import com.superngb.monitoring_system.Entities.person.Teacher;
import com.superngb.monitoring_system.Enums.AttendanceEnum;
import com.superngb.monitoring_system.Enums.ScoreEnum;
import com.superngb.monitoring_system.UseCases.Teacher.DataAccess.*;
import com.superngb.monitoring_system.UseCases.Teacher.InputData.Edit.AttendanceEditRequestModel;
import com.superngb.monitoring_system.UseCases.Teacher.InputData.Edit.CheckpointEditRequestModel;
import com.superngb.monitoring_system.UseCases.Teacher.InputData.Edit.LessonEditRequestModel;
import com.superngb.monitoring_system.UseCases.Teacher.InputData.Edit.ScoreEditRequestModel;
import com.superngb.monitoring_system.UseCases.Teacher.InputData.Filter.*;
import com.superngb.monitoring_system.UseCases.Teacher.InputData.Post.AttendancePostRequestModel;
import com.superngb.monitoring_system.UseCases.Teacher.InputData.Post.CheckpointPostRequestModel;
import com.superngb.monitoring_system.UseCases.Teacher.InputData.Post.LessonPostRequestModel;
import com.superngb.monitoring_system.UseCases.Teacher.InputData.Post.ScorePostRequestModel;

import java.util.ArrayList;
import java.util.List;

public class TeacherInteractor implements TeacherInputBoundary{

    private TeacherPersonalityDataAccess teacherPersonalityDataAccess;
    private TeacherGroupDataAccess teacherGroupDataAccess;
    private TeacherStudentDataAccess teacherStudentDataAccess;
    private TeacherTeacherDataAccess teacherTeacherDataAccess;
    private TeacherSubjectDataAccess teacherSubjectDataAccess;
    private TeacherCheckpointDataAccess teacherCheckpointDataAccess;
    private TeacherLessonDataAccess teacherLessonDataAccess;
    private TeacherScoreDataAccess teacherScoreDataAccess;
    private TeacherAttendanceDataAccess teacherAttendanceDataAccess;

    private TeacherOutputBoundary teacherOutputBoundary;

    public TeacherInteractor(TeacherPersonalityDataAccess teacherPersonalityDataAccess,
                             TeacherGroupDataAccess teacherGroupDataAccess,
                             TeacherStudentDataAccess teacherStudentDataAccess,
                             TeacherTeacherDataAccess teacherTeacherDataAccess,
                             TeacherSubjectDataAccess teacherSubjectDataAccess,
                             TeacherCheckpointDataAccess teacherCheckpointDataAccess,
                             TeacherLessonDataAccess teacherLessonDataAccess,
                             TeacherScoreDataAccess teacherScoreDataAccess,
                             TeacherAttendanceDataAccess teacherAttendanceDataAccess,
                             TeacherOutputBoundary teacherOutputBoundary) {
        this.teacherPersonalityDataAccess = teacherPersonalityDataAccess;
        this.teacherGroupDataAccess = teacherGroupDataAccess;
        this.teacherStudentDataAccess = teacherStudentDataAccess;
        this.teacherTeacherDataAccess = teacherTeacherDataAccess;
        this.teacherSubjectDataAccess = teacherSubjectDataAccess;
        this.teacherCheckpointDataAccess = teacherCheckpointDataAccess;
        this.teacherLessonDataAccess = teacherLessonDataAccess;
        this.teacherScoreDataAccess = teacherScoreDataAccess;
        this.teacherAttendanceDataAccess = teacherAttendanceDataAccess;
        this.teacherOutputBoundary = teacherOutputBoundary;
    }

    // Personalities

    @Override
    public List<PersonalityDtoModel> getPersonalities() {
        List<Personality> personalityList = teacherPersonalityDataAccess.getAll();
        List<PersonalityDtoModel> personalityDtoModelList = PersonalityDtoModel.listPersonalitiesMapper(personalityList);
        return teacherOutputBoundary.convertPersonalities(personalityDtoModelList);
    }

    @Override
    public PersonalityDtoModel findPersonality(Long id) {
        Personality personality = teacherPersonalityDataAccess.findById(id);
        PersonalityDtoModel personalityDtoModel = PersonalityDtoModel.personalityMapper(personality);
        return teacherOutputBoundary.prepareFoundPersonalityView(personalityDtoModel);
    }

    // Groups

    @Override
    public List<GroupDtoModel> getGroups() {
        List<Group> groupList = teacherGroupDataAccess.getAll();
        List<GroupDtoModel> groupDtoModelList = GroupDtoModel.listGroupsMapper(groupList);
        return teacherOutputBoundary.convertGroups(groupDtoModelList);
    }

    @Override
    public GroupDtoModel findGroup(Long id) {
        Group group = teacherGroupDataAccess.findById(id);
        GroupDtoModel groupDtoModel = GroupDtoModel.groupMapper(group);
        return teacherOutputBoundary.prepareFoundGroupView(groupDtoModel);
    }

    @Override
    public List<GroupDtoModel> filterGroups(GroupFilterRequestModel groupFilterRequestModel) {
        List<Group> groupList = teacherGroupDataAccess.filter(groupFilterRequestModel.getId(),
                groupFilterRequestModel.getName());
        List<GroupDtoModel> groupDtoModelList = GroupDtoModel.listGroupsMapper(groupList);
        return teacherOutputBoundary.convertGroups(groupDtoModelList);
    }

    // Students

    @Override
    public List<StudentDtoModel> getStudents() {
        List<Student> studentList = teacherStudentDataAccess.getAll();
        List<StudentDtoModel> studentDtoModelList = StudentDtoModel.listStudentsMapper(studentList);
        return teacherOutputBoundary.convertStudents(studentDtoModelList);
    }

    @Override
    public StudentDtoModel findStudent(Long id) {
        Student student = teacherStudentDataAccess.findById(id);
        StudentDtoModel studentDtoModel = StudentDtoModel.studentMapper(student);
        return teacherOutputBoundary.prepareFoundStudentView(studentDtoModel);
    }

    @Override
    public StudentDtoModel findStudentByPersonality(Long id) {
        Student student = teacherStudentDataAccess.findByPersonalityId(id);
        StudentDtoModel studentDtoModel = StudentDtoModel.studentMapper(student);
        return teacherOutputBoundary.prepareFoundStudentView(studentDtoModel);
    }

    @Override
    public List<StudentDtoModel> findStudentsByGroup(Long id) {
        List<Student> studentList = teacherStudentDataAccess.findAllByGroupId(id);
        List<StudentDtoModel> studentDtoModelList = StudentDtoModel.listStudentsMapper(studentList);
        return teacherOutputBoundary.convertStudents(studentDtoModelList);
    }

    @Override
    public List<StudentDtoModel> filterStudents(StudentFilterRequestModel studentFilterRequestModel) {
        List<Student> studentList = teacherStudentDataAccess.filter(studentFilterRequestModel.getId(),
                studentFilterRequestModel.getStudentCardId(),
                studentFilterRequestModel.getPersonalityId(),
                studentFilterRequestModel.getGroupId());
        List<StudentDtoModel> studentDtoModelList = StudentDtoModel.listStudentsMapper(studentList);
        return teacherOutputBoundary.convertStudents(studentDtoModelList);
    }

    // Teachers

    @Override
    public List<TeacherDtoModel> getTeachers() {
        List<Teacher> teacherList = teacherTeacherDataAccess.getAll();
        List<TeacherDtoModel> teacherDtoModelList = TeacherDtoModel.listTeachersMapper(teacherList);
        return teacherOutputBoundary.convertTeachers(teacherDtoModelList);
    }

    @Override
    public TeacherDtoModel findTeacher(Long id) {
        Teacher teacher = teacherTeacherDataAccess.findById(id);
        TeacherDtoModel teacherDtoModel = TeacherDtoModel.teacherMapper(teacher);
        return teacherOutputBoundary.prepareFoundTeacherView(teacherDtoModel);
    }

    @Override
    public TeacherDtoModel findTeacherByPersonality(Long id) {
        Teacher teacher = teacherTeacherDataAccess.findByPersonalityId(id);
        TeacherDtoModel teacherDtoModel = TeacherDtoModel.teacherMapper(teacher);
        return teacherOutputBoundary.prepareFoundTeacherView(teacherDtoModel);
    }

    // Subjects

    @Override
    public List<SubjectDtoModel> getSubjects() {
        List<Subject> subjectList = teacherSubjectDataAccess.getAll();
        List<SubjectDtoModel> subjectDtoModelList = SubjectDtoModel.listSubjectsMapper(subjectList);
        return teacherOutputBoundary.convertSubjects(subjectDtoModelList);
    }

    @Override
    public SubjectDtoModel findSubject(Long id) {
        Subject subject = teacherSubjectDataAccess.findById(id);
        SubjectDtoModel subjectDtoModel = SubjectDtoModel.subjectMapper(subject);
        return teacherOutputBoundary.prepareFoundSubjectView(subjectDtoModel);
    }

    @Override
    public SubjectDtoModel findSubjectsByGroups(List<Long> groups) {

        return null;
    }

    @Override
    public List<SubjectDtoModel> filterSubjects(SubjectFilterRequestModel subjectFilterRequestModel) {
        List<Subject> subjectList = teacherSubjectDataAccess.filter(subjectFilterRequestModel.getId(),
                subjectFilterRequestModel.getName());
        List<SubjectDtoModel> subjectDtoModelList = SubjectDtoModel.listSubjectsMapper(subjectList);
        return teacherOutputBoundary.convertSubjects(subjectDtoModelList);
    }

    // Checkpoints

    @Override
    public List<CheckpointDtoModel> getCheckpoints() {
        List<Checkpoint> checkpointList = teacherCheckpointDataAccess.getAll();
        List<CheckpointDtoModel> checkpointDtoModelList = CheckpointDtoModel.listCheckpointsMapper(checkpointList);
        return teacherOutputBoundary.convertCheckpoints(checkpointDtoModelList);
    }

    @Override
    public CheckpointDtoModel findCheckpoint(Long id) {
        Checkpoint checkpoint = teacherCheckpointDataAccess.findById(id);
        CheckpointDtoModel checkpointDtoModel = CheckpointDtoModel.checkpointMapper(checkpoint);
        return teacherOutputBoundary.prepareFoundCheckpointView(checkpointDtoModel);
    }

    @Override
    public CheckpointDtoModel findCheckpointByName(String name) {
        Checkpoint checkpoint = teacherCheckpointDataAccess.findByName(name);
        CheckpointDtoModel checkpointDtoModel = CheckpointDtoModel.checkpointMapper(checkpoint);
        return teacherOutputBoundary.prepareFoundCheckpointView(checkpointDtoModel);
    }

    @Override
    public CheckpointDtoModel findCheckpointBySubjectId(Long subjectId) {
        Checkpoint checkpoint = teacherCheckpointDataAccess.findBySubjectId(subjectId);
        CheckpointDtoModel checkpointDtoModel = CheckpointDtoModel.checkpointMapper(checkpoint);
        return teacherOutputBoundary.prepareFoundCheckpointView(checkpointDtoModel);
    }

    @Override
    public boolean editCheckpoint(Long id, CheckpointEditRequestModel checkpointEditRequestModel) {
        Checkpoint checkpoint = teacherCheckpointDataAccess.findById(id);
        Checkpoint checkpointByName = teacherCheckpointDataAccess.findByName(checkpoint.getName());

        if (checkpointByName != null && checkpointByName != checkpoint)
            return  teacherOutputBoundary.prepareFailEditCheckpointView(CheckpointDtoModel.checkpointMapper(checkpoint));

        if (!checkpointEditRequestModel.getName().equals(""))
            checkpoint.setName(checkpointEditRequestModel.getName());

        teacherCheckpointDataAccess.save(checkpoint);

        CheckpointDtoModel checkpointDtoModel = CheckpointDtoModel.checkpointMapper(checkpoint);
        return teacherOutputBoundary.prepareSuccessEditCheckpointView(checkpointDtoModel);
    }

    @Override
    public List<CheckpointDtoModel> filterCheckpoints(CheckpointFilterRequestModel checkpointFilterRequestModel) {
        List<Checkpoint> checkpointList = teacherCheckpointDataAccess.filter(
                checkpointFilterRequestModel.getId(),
                checkpointFilterRequestModel.getName(),
                checkpointFilterRequestModel.getSubjectId());
        List<CheckpointDtoModel> checkpointDtoModelList = CheckpointDtoModel.listCheckpointsMapper(checkpointList);
        return teacherOutputBoundary.convertCheckpoints(checkpointDtoModelList);
    }

    @Override
    public CheckpointDtoModel deleteCheckpoint(Long id) {
        return null;
    }

    @Override
    public boolean createCheckpoint(CheckpointPostRequestModel checkpointPostRequestModel) {
        if (checkpointPostRequestModel.getName().equals(""))
            return teacherOutputBoundary.prepareFailPostCheckpointView();

        Subject subjectById = teacherSubjectDataAccess.findById(checkpointPostRequestModel.getSubjectId());
        if (subjectById == null)
            return teacherOutputBoundary.prepareFailPostCheckpointView();

        for (Checkpoint checkpoint: subjectById.getCheckpoints()){
            if (checkpoint.getName().equals(checkpointPostRequestModel.getName()))
                return teacherOutputBoundary.prepareFailPostCheckpointView();
        }

        Checkpoint checkpoint = new Checkpoint();
        checkpoint.setName(checkpointPostRequestModel.getName());
        checkpoint.setSubject(subjectById);

        checkpoint.setScoreList(new ArrayList<>());

        teacherCheckpointDataAccess.save(checkpoint);

        List<Group> groupList = subjectById.getGroups();
        for (Group group: groupList){
            for (Student student: group.getStudents()){
                createScore(new ScorePostRequestModel(student.getId(), "пусто", checkpoint.getId()));
            }
        }

        return teacherOutputBoundary.prepareSuccessPostCheckpointView(CheckpointDtoModel.checkpointMapper(checkpoint));
    }


    // Lessons

    @Override
    public List<LessonDtoModel> getLessons() {
        List<Lesson> lessonList = teacherLessonDataAccess.getAll();
        List<LessonDtoModel> lessonDtoModelList = LessonDtoModel.listLessonsMapper(lessonList);
        return teacherOutputBoundary.convertLessons(lessonDtoModelList);
    }

    @Override
    public LessonDtoModel findLesson(Long id) {
        Lesson lesson = teacherLessonDataAccess.findById(id);
        LessonDtoModel lessonDtoModel = LessonDtoModel.lessonMapper(lesson);
        return teacherOutputBoundary.prepareFoundLessonView(lessonDtoModel);
    }

    @Override
    public LessonDtoModel findLessonByName(String name) {
        Lesson lesson = teacherLessonDataAccess.findByName(name);
        LessonDtoModel lessonDtoModel = LessonDtoModel.lessonMapper(lesson);
        return teacherOutputBoundary.prepareFoundLessonView(lessonDtoModel);
    }

    @Override
    public LessonDtoModel findLessonBySubjectId(Long subjectId) {
        Lesson lesson = teacherLessonDataAccess.findBySubjectId(subjectId);
        LessonDtoModel lessonDtoModel = LessonDtoModel.lessonMapper(lesson);
        return teacherOutputBoundary.prepareFoundLessonView(lessonDtoModel);    }

    @Override
    public boolean editLesson(Long id, LessonEditRequestModel lessonEditRequestModel) {
        Lesson lesson = teacherLessonDataAccess.findById(id);
        Lesson lessonByName = teacherLessonDataAccess.findByName(lesson.getName());

        if (lessonByName != null && lessonByName != lesson)
            return  teacherOutputBoundary.prepareFailEditLessonView(LessonDtoModel.lessonMapper(lesson));

        if (!lessonEditRequestModel.getName().equals(""))
            lesson.setName(lessonEditRequestModel.getName());

        teacherLessonDataAccess.save(lesson);

        LessonDtoModel lessonDtoModel = LessonDtoModel.lessonMapper(lesson);
        return teacherOutputBoundary.prepareSuccessEditLessonView(lessonDtoModel);
    }

    @Override
    public List<LessonDtoModel> filterLessons(LessonFilterRequestModel lessonFilterRequestModel) {
        List<Lesson> lessonList = teacherLessonDataAccess.filter(
                lessonFilterRequestModel.getId(),
                lessonFilterRequestModel.getName(),
                lessonFilterRequestModel.getSubjectId());
        List<LessonDtoModel> lessonDtoModelList = LessonDtoModel.listLessonsMapper(lessonList);
        return teacherOutputBoundary.convertLessons(lessonDtoModelList);
    }

    @Override
    public LessonDtoModel deleteLesson(Long id) {
        return null;
    }

    @Override
    public boolean createLesson(LessonPostRequestModel lessonPostRequestModel) {
        if (lessonPostRequestModel.getName().equals(""))
            return teacherOutputBoundary.prepareFailPostLessonView();

        Subject subjectById = teacherSubjectDataAccess.findById(lessonPostRequestModel.getSubjectId());
        if (subjectById == null)
            return teacherOutputBoundary.prepareFailPostLessonView();

        for (Lesson lesson: subjectById.getLessons()){
            if (lesson.getName().equals(lessonPostRequestModel.getName()))
                return teacherOutputBoundary.prepareFailPostLessonView();
        }

        Lesson lesson = new Lesson();
        lesson.setName(lessonPostRequestModel.getName());
        lesson.setSubject(subjectById);

        lesson.setAttendanceList(new ArrayList<>());

        teacherLessonDataAccess.save(lesson);

        List<Group> groupList = subjectById.getGroups();
        for (Group group: groupList){
            for (Student student: group.getStudents()){
                createAttendance(new AttendancePostRequestModel(student.getId(), "", lesson.getId()));
            }
        }

        return teacherOutputBoundary.prepareSuccessPostLessonView(LessonDtoModel.lessonMapper(lesson));
    }

    // Attendance

    @Override
    public List<AttendanceDtoModel> getAttendance() {
        List<Attendance> attendanceList = teacherAttendanceDataAccess.getAll();
        List<AttendanceDtoModel> attendanceDtoModelList = AttendanceDtoModel.listAttendanceMapper(attendanceList);
        return teacherOutputBoundary.convertAttendances(attendanceDtoModelList);
    }

    @Override
    public List<AttendanceDtoModel> findAttendanceByLesson(Long lessonId) {
        List<Attendance> attendanceList = teacherAttendanceDataAccess.findAllByLessonId(lessonId);
        List<AttendanceDtoModel> attendanceDtoModelList = AttendanceDtoModel.listAttendanceMapper(attendanceList);
        return teacherOutputBoundary.convertAttendances(attendanceDtoModelList);
    }

    @Override
    public AttendanceDtoModel findAttendance(Long id) {
        Attendance attendance = teacherAttendanceDataAccess.findById(id);
        AttendanceDtoModel attendanceDtoModel = AttendanceDtoModel.attendanceMapper(attendance);
        return teacherOutputBoundary.prepareFoundAttendanceView(attendanceDtoModel);
    }

    @Override
    public boolean editAttendance(Long id, AttendanceEditRequestModel attendanceEditRequestModel) {
        Attendance attendance = teacherAttendanceDataAccess.findById(id);
        if (attendance == null)
            return teacherOutputBoundary.prepareFailEditAttendanceView(AttendanceDtoModel.attendanceMapper(attendance));

        switch (attendanceEditRequestModel.getMark()){
            case "не был":
                attendance.setAttendanceMark(AttendanceEnum.ABSENT);
                break;
            case "не был по уважительной причине":
                attendance.setAttendanceMark(AttendanceEnum.REASON);
                break;
            case "болел":
                attendance.setAttendanceMark(AttendanceEnum.ILL);
                break;
            case "был":
                attendance.setAttendanceMark(AttendanceEnum.ATTENDED);
                break;
            default:
                attendance.setAttendanceMark(AttendanceEnum.EMPTY);
                break;
        }
        teacherAttendanceDataAccess.save(attendance);

        return teacherOutputBoundary.prepareSuccessPostAttendanceView(AttendanceDtoModel.attendanceMapper(attendance));
    }

    @Override
    public List<AttendanceDtoModel> filterAttendance(AttendanceFilterRequestModel attendanceFilterRequestModel) {
        List<Attendance> attendanceList = teacherAttendanceDataAccess.filter(attendanceFilterRequestModel.getId(),
                attendanceFilterRequestModel.getStudentId(), attendanceFilterRequestModel.getLessonId());
        List<AttendanceDtoModel> attendanceDtoModelList = AttendanceDtoModel.listAttendanceMapper(attendanceList);
        return teacherOutputBoundary.convertAttendances(attendanceDtoModelList);
    }

    @Override
    public AttendanceDtoModel deleteAttendance(Long id) {
        return null;
    }

    @Override
    public boolean createAttendance(AttendancePostRequestModel attendancePostRequestModel) {
        if (attendancePostRequestModel.getStudentId() == null || attendancePostRequestModel.getLessonId() == null)
            return teacherOutputBoundary.prepareFailPostAttendanceView();

        Student student = teacherStudentDataAccess.findById(attendancePostRequestModel.getStudentId());
        Lesson lesson = teacherLessonDataAccess.findById(attendancePostRequestModel.getLessonId());
        if (student == null || lesson == null)
            return teacherOutputBoundary.prepareFailPostAttendanceView();

        Score scoreByCheckpointIdAndStudentId = teacherScoreDataAccess.findByCheckpointIdAndStudentId(attendancePostRequestModel.getLessonId(), attendancePostRequestModel.getStudentId());

        if (scoreByCheckpointIdAndStudentId != null)
            return teacherOutputBoundary.prepareFailPostAttendanceView();

        Attendance attendance = new Attendance();
        attendance.setLesson(lesson);
        attendance.setStudent(student);
        switch (attendancePostRequestModel.getMark()){
            case "не был":
                attendance.setAttendanceMark(AttendanceEnum.ABSENT);
                break;
            case "не был по уважительной причине":
                attendance.setAttendanceMark(AttendanceEnum.REASON);
                break;
            case "болел":
                attendance.setAttendanceMark(AttendanceEnum.ILL);
                break;
            case "был":
                attendance.setAttendanceMark(AttendanceEnum.ATTENDED);
                break;
            default:
                attendance.setAttendanceMark(AttendanceEnum.EMPTY);
                break;
        }
        teacherAttendanceDataAccess.save(attendance);

        return teacherOutputBoundary.prepareSuccessPostAttendanceView(AttendanceDtoModel.attendanceMapper(attendance));
    }


    // Scores

    @Override
    public List<ScoreDtoModel> getScores() {
        List<Score> scoreList = teacherScoreDataAccess.getAll();
        List<ScoreDtoModel> scoreDtoModelList = ScoreDtoModel.listScoresMapper(scoreList);
        return teacherOutputBoundary.convertScores(scoreDtoModelList);
    }

    @Override
    public List<ScoreDtoModel> findScoresByCheckpoint(Long checkpointId) {
        List<Score> scoreList = teacherScoreDataAccess.findAllByLessonId(checkpointId);
        List<ScoreDtoModel> scoreDtoModelList = ScoreDtoModel.listScoresMapper(scoreList);
        return teacherOutputBoundary.convertScores(scoreDtoModelList);
    }

    @Override
    public ScoreDtoModel findScore(Long id) {
        Score score = teacherScoreDataAccess.findById(id);
        ScoreDtoModel scoreDtoModel = ScoreDtoModel.scoreMapper(score);
        return teacherOutputBoundary.prepareFoundScoreView(scoreDtoModel);
    }

    @Override
    public boolean editScore(Long id, ScoreEditRequestModel scoreEditRequestModel) {
        Score score = teacherScoreDataAccess.findById(id);
        if (score == null)
            return teacherOutputBoundary.prepareFailEditScoreView(ScoreDtoModel.scoreMapper(score));

        switch (scoreEditRequestModel.getMark()){
            case "неудовлетворительно":
                score.setScoreMark(ScoreEnum.TWO);
                break;
            case "удовлетворительно":
                score.setScoreMark(ScoreEnum.THREE);
                break;
            case "хорошо":
                score.setScoreMark(ScoreEnum.FOUR);
                break;
            case "отлично":
                score.setScoreMark(ScoreEnum.FIVE);
                break;
            default:
                score.setScoreMark(ScoreEnum.EMPTY);
                break;
        }
        teacherScoreDataAccess.save(score);

        return teacherOutputBoundary.prepareSuccessEditScoreView(ScoreDtoModel.scoreMapper(score));
    }

    @Override
    public List<ScoreDtoModel> filterScores(ScoreFilterRequestModel scoreFilterRequestModel) {
        List<Score> scoreList = teacherScoreDataAccess.filter(scoreFilterRequestModel.getId(),
                scoreFilterRequestModel.getStudentId(), scoreFilterRequestModel.getCheckpointId());
        List<ScoreDtoModel> scoreDtoModelList = ScoreDtoModel.listScoresMapper(scoreList);
        return teacherOutputBoundary.convertScores(scoreDtoModelList);
    }

    @Override
    public ScoreDtoModel deleteScore(Long id) {
        return null;
    }

    @Override
    public boolean createScore(ScorePostRequestModel scorePostRequestModel) {
        if (scorePostRequestModel.getStudentId() == null || scorePostRequestModel.getCheckpointId() == null)
            return teacherOutputBoundary.prepareFailPostScoreView();

        Student student = teacherStudentDataAccess.findById(scorePostRequestModel.getStudentId());
        Checkpoint checkpoint = teacherCheckpointDataAccess.findById(scorePostRequestModel.getCheckpointId());
        if (student == null || checkpoint == null)
            return teacherOutputBoundary.prepareFailPostScoreView();

        Score scoreByCheckpointIdAndStudentId = teacherScoreDataAccess.findByCheckpointIdAndStudentId(scorePostRequestModel.getCheckpointId(), scorePostRequestModel.getStudentId());

        if (scoreByCheckpointIdAndStudentId != null)
            return teacherOutputBoundary.prepareFailPostScoreView();

        Score score = new Score();
        score.setCheckpoint(checkpoint);
        score.setStudent(student);
        switch (scorePostRequestModel.getMark()){
            case "неудовлетворительно":
                score.setScoreMark(ScoreEnum.TWO);
                break;
            case "удовлетворительно":
                score.setScoreMark(ScoreEnum.THREE);
                break;
            case "хорошо":
                score.setScoreMark(ScoreEnum.FOUR);
                break;
            case "отлично":
                score.setScoreMark(ScoreEnum.FIVE);
                break;
            default:
                score.setScoreMark(ScoreEnum.EMPTY);
                break;
        }
        teacherScoreDataAccess.save(score);

        return teacherOutputBoundary.prepareSuccessPostScoreView(ScoreDtoModel.scoreMapper(score));
    }
}
