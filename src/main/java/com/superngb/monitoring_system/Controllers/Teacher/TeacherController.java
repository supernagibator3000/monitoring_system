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
import com.superngb.monitoring_system.UseCases.Teacher.InputData.*;
import com.superngb.monitoring_system.UseCases.Teacher.TeacherInputBoundary;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherInputBoundary teacherInputBoundary;

    public TeacherController(TeacherInputBoundary teacherInputBoundary) {
        this.teacherInputBoundary = teacherInputBoundary;
    }

    // Personalities

    @GetMapping("/allPersonalities")
    public List<PersonalityDtoModel> allPersonalities(){
        return teacherInputBoundary.getPersonalities();
    }

    @GetMapping("/personality/{personalityId}")
    public PersonalityDtoModel showPersonality(@PathVariable Long personalityId){
        return teacherInputBoundary.findPersonality(personalityId);
    }

    @GetMapping("/personality/{personalityId}/student")
    public StudentDtoModel showStudentByPersonality(@PathVariable Long personalityId){
        return teacherInputBoundary.findStudentByPersonality(personalityId);
    }

    @GetMapping("/personality/{personalityId}/teacher")
    public TeacherDtoModel showTeacherByPersonality(@PathVariable Long personalityId){
        return teacherInputBoundary.findTeacherByPersonality(personalityId);
    }

    // Groups

    @GetMapping("/allGroups")
    public List<GroupDtoModel> allGroups(){
        return teacherInputBoundary.getGroups();
    }

    @PostMapping("/filterGroups")
    public List<GroupDtoModel> filterGroups(@RequestBody GroupFilterRequestModel groupFilterRequestModel){
        return teacherInputBoundary.filterGroups(groupFilterRequestModel);
    }

    @GetMapping("/group/{groupId}")
    public GroupDtoModel showGroup(@PathVariable Long groupId){
        return teacherInputBoundary.findGroup(groupId);
    }

    @GetMapping("/group/{groupId}/students")
    public List<StudentDtoModel> showStudentsByGroup(@PathVariable Long groupId){
        return teacherInputBoundary.findStudentsByGroup(groupId);
    }

    // Students

    @GetMapping("/allStudents")
    public List<StudentDtoModel> allStudents(){
        return teacherInputBoundary.getStudents();
    }

    @PostMapping("/filterStudents")
    public List<StudentDtoModel> filterStudents(@RequestBody StudentFilterRequestModel studentFilterRequestModel){
        return teacherInputBoundary.filterStudents(studentFilterRequestModel);
    }

    @GetMapping("/student/{studentId}")
    public StudentDtoModel showStudent(@PathVariable Long studentId){
        return teacherInputBoundary.findStudent(studentId);
    }

    // Teachers

    @GetMapping("/allTeachers")
    public List<TeacherDtoModel> allTeachers(){
        return teacherInputBoundary.getTeachers();
    }

    @GetMapping("/teacher/{teacherId}")
    public TeacherDtoModel showTeacher(@PathVariable Long teacherId){
        return teacherInputBoundary.findTeacher(teacherId);
    }

    // Subjects

    @GetMapping("/allSubjects")
    public List<SubjectDtoModel> allSubjects(){
        return teacherInputBoundary.getSubjects();
    }

    @PostMapping("/filterSubjects")
    public List<SubjectDtoModel> filterSubjects(@RequestBody SubjectFilterRequestModel subjectFilterRequestModel){
        return teacherInputBoundary.filterSubjects(subjectFilterRequestModel);
    }

    @GetMapping("/subject/{subjectId}")
    public SubjectDtoModel showSubject(@PathVariable Long subjectId){
        return teacherInputBoundary.findSubject(subjectId);
    }

    // Checkpoints

    @GetMapping("/allCheckpoints")
    public List<CheckpointDtoModel> allCheckpoints(){
        return teacherInputBoundary.getCheckpoints();
    }

    @PostMapping("/filterCheckpoints")
    public List<CheckpointDtoModel> filterCheckpoints(@RequestBody CheckpointFilterRequestModel checkpointFilterRequestModel){
        return teacherInputBoundary.filterCheckpoints(checkpointFilterRequestModel);
    }

    @DeleteMapping("/deleteCheckpoint/{checkpointId}")
    public CheckpointDtoModel deleteCheckpoint(@PathVariable Long checkpointId){
        return teacherInputBoundary.deleteCheckpoint(checkpointId);
    }

    @GetMapping("/checkpoint/{checkpointId}")
    public CheckpointDtoModel showCheckpoint(@PathVariable Long checkpointId){
        return teacherInputBoundary.findCheckpoint(checkpointId);
    }

    @PutMapping("/checkpoint/{checkpointId}")
    public boolean editCheckpoint(@PathVariable Long checkpointId,
                                  @RequestBody CheckpointEditRequestModel checkpointEditRequestModel){
        return teacherInputBoundary.editCheckpoint(checkpointId, checkpointEditRequestModel);
    }

    @PostMapping("/addCheckpoint")
    public boolean addCheckpoint(@RequestBody CheckpointPostRequestModel checkpointPostRequestModel){
        return teacherInputBoundary.createCheckpoint(checkpointPostRequestModel);
    }

    // Lessons

    @GetMapping("/allLessons")
    public List<LessonDtoModel> allLessons(){
        return teacherInputBoundary.getLessons();
    }

    @PostMapping("/filterLessons")
    public List<LessonDtoModel> filterLessons(@RequestBody LessonFilterRequestModel lessonFilterRequestModel){
        return teacherInputBoundary.filterLessons(lessonFilterRequestModel);
    }

    @DeleteMapping("/deleteLesson/{lessonId}")
    public LessonDtoModel deleteLesson(@PathVariable Long lessonId){
        return teacherInputBoundary.deleteLesson(lessonId);
    }

    @GetMapping("/lesson/{lessonId}")
    public LessonDtoModel showLesson(@PathVariable Long lessonId){
        return teacherInputBoundary.findLesson(lessonId);
    }

    @PutMapping("/lesson/{lessonId}")
    public boolean editLesson(@PathVariable Long lessonId,
                              @RequestBody LessonEditRequestModel lessonEditRequestModel){
        return teacherInputBoundary.editLesson(lessonId, lessonEditRequestModel);
    }

    @PostMapping("/addLesson")
    public boolean addLesson(@RequestBody LessonPostRequestModel lessonPostRequestModel){
        return teacherInputBoundary.createLesson(lessonPostRequestModel);
    }

    // Attendance

    @GetMapping("/allAttendance")
    public List<AttendanceDtoModel> allAttendance(){
        return teacherInputBoundary.getAttendance();
    }

    @PostMapping("/filterAttendance")
    public List<AttendanceDtoModel> filterAttendance(@RequestBody AttendanceFilterRequestModel attendanceFilterRequestModel){
        return teacherInputBoundary.filterAttendance(attendanceFilterRequestModel);
    }

    @DeleteMapping("/deleteAttendance/{attendanceId}")
    public AttendanceDtoModel deleteAttendance(@PathVariable Long attendanceId){
        return teacherInputBoundary.deleteAttendance(attendanceId);
    }

    @GetMapping("/attendance/{attendanceId}")
    public AttendanceDtoModel showAttendance(@PathVariable Long attendanceId){
        return teacherInputBoundary.findAttendance(attendanceId);
    }

    @PutMapping("/attendance/{attendanceId}")
    public boolean editAttendance(@PathVariable Long attendanceId,
                                  @RequestBody AttendanceEditRequestModel attendanceEditRequestModel){
        return teacherInputBoundary.editAttendance(attendanceId, attendanceEditRequestModel);
    }

    @PostMapping("/addAttendance")
    public boolean addAttendance(@RequestBody AttendancePostRequestModel attendancePostRequestModel){
        return teacherInputBoundary.createAttendance(attendancePostRequestModel);
    }

    // Scores

    @GetMapping("/allScores")
    public List<ScoreDtoModel> allScores(){
        return teacherInputBoundary.getScores();
    }

    @PostMapping("/filterScores")
    public List<ScoreDtoModel> filterScores(@RequestBody ScoreFilterRequestModel scoreFilterRequestModel){
        return teacherInputBoundary.filterScores(scoreFilterRequestModel);
    }

    @DeleteMapping("/deleteScore/{scoreId}")
    public ScoreDtoModel deleteScore(@PathVariable Long scoreId){
        return teacherInputBoundary.deleteScore(scoreId);
    }

    @GetMapping("/score/{scoreId}")
    public ScoreDtoModel showScore(@PathVariable Long scoreId){
        return teacherInputBoundary.findScore(scoreId);
    }

    @PutMapping("/score/{scoreId}")
    public boolean editScore(@PathVariable Long scoreId,
                             @RequestBody ScoreEditRequestModel scoreEditRequestModel){
        return teacherInputBoundary.editScore(scoreId, scoreEditRequestModel);
    }

    @PostMapping("/addScore")
    public boolean addScore(@RequestBody ScorePostRequestModel scorePostRequestModel){
        return teacherInputBoundary.createScore(scorePostRequestModel);
    }
}
