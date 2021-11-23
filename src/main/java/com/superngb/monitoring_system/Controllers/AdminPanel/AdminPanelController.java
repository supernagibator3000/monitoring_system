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
import com.superngb.monitoring_system.UseCases.AdminPanel.AdminPanelInputBoundary;
import com.superngb.monitoring_system.UseCases.AdminPanel.InputData.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminPanelController {

    private final AdminPanelInputBoundary adminPanelInputBoundary;

    public AdminPanelController(AdminPanelInputBoundary adminPanelInputBoundary) {
        this.adminPanelInputBoundary = adminPanelInputBoundary;
    }

    // Users

    @GetMapping("/allUsers")
    public List<UserDtoModel> allUsers(){
        return adminPanelInputBoundary.getUsers();
    }

    @PostMapping("/filterUsers")
    public List<UserDtoModel> filterUsers(@RequestBody UserFilterRequestModel userFilterRequestModel){
        return adminPanelInputBoundary.filterUsers(userFilterRequestModel);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public UserDtoModel deleteUser(@PathVariable Long userId){
        return adminPanelInputBoundary.deleteUser(userId);
    }

    @GetMapping("/user/{userId}")
    public UserDtoModel showUser(@PathVariable Long userId){
        return adminPanelInputBoundary.findUser(userId);
    }

    @PutMapping("/user/{userId}")
    public boolean editUser(@PathVariable Long userId,
                            @RequestBody UserEditRequestModel userEditRequestModel){
        return adminPanelInputBoundary.editUser(userId, userEditRequestModel);
    }

    @PostMapping("/addUser")
    public boolean addUser(@RequestBody UserPostRequestModel userPostRequestModel){
        return adminPanelInputBoundary.createUser(userPostRequestModel);
    }

    // Personalities

    @GetMapping("/allPersonalities")
    public List<PersonalityDtoModel> allPersonalities(){
        return adminPanelInputBoundary.getPersonalities();
    }

    @PostMapping("/filterPersonalities")
    public List<PersonalityDtoModel> filterPersonalities(@RequestBody PersonalityFilterRequestModel personalityFilterRequestModel){
        return adminPanelInputBoundary.filterPersonalities(personalityFilterRequestModel);
    }

    @DeleteMapping("/deletePersonality/{personalityId}")
    public PersonalityDtoModel deletePersonality(@PathVariable Long personalityId){
        return adminPanelInputBoundary.deletePersonality(personalityId);
    }

    @GetMapping("/personality/{personalityId}")
    public PersonalityDtoModel showPersonality(@PathVariable Long personalityId){
        return adminPanelInputBoundary.findPersonality(personalityId);
    }

    @PutMapping("/personality/{personalityId}")
    public boolean editPersonality(@PathVariable Long personalityId,
                            @RequestBody PersonalityEditRequestModel personalityEditRequestModel){
        return adminPanelInputBoundary.editPersonality(personalityId, personalityEditRequestModel);
    }

    @PostMapping("/addPersonality")
    public boolean addPersonality(@RequestBody PersonalityPostRequestModel personalityPostRequestModel){
        return adminPanelInputBoundary.createPersonality(personalityPostRequestModel);
    }

    @GetMapping("/personality/{personalityId}/user")
    public UserDtoModel showUserByPersonality(@PathVariable Long personalityId){
        return adminPanelInputBoundary.findUserByPersonality(personalityId);
    }

    @GetMapping("/personality/{personalityId}/student")
    public StudentDtoModel showStudentByPersonality(@PathVariable Long personalityId){
        return adminPanelInputBoundary.findStudentByPersonality(personalityId);
    }

    @GetMapping("/personality/{personalityId}/teacher")
    public TeacherDtoModel showTeacherByPersonality(@PathVariable Long personalityId){
        return adminPanelInputBoundary.findTeacherByPersonality(personalityId);
    }

    // Groups

    @GetMapping("/allGroups")
    public List<GroupDtoModel> allGroups(){
        return adminPanelInputBoundary.getGroups();
    }

    @PostMapping("/filterGroups")
    public List<GroupDtoModel> filterGroups(@RequestBody GroupFilterRequestModel groupFilterRequestModel){
        return adminPanelInputBoundary.filterGroups(groupFilterRequestModel);
    }

    @DeleteMapping("/deleteGroup/{groupId}")
    public GroupDtoModel deleteGroup(@PathVariable Long groupId){
        return adminPanelInputBoundary.deleteGroup(groupId);
    }

    @GetMapping("/group/{groupId}")
    public GroupDtoModel showGroup(@PathVariable Long groupId){
        return adminPanelInputBoundary.findGroup(groupId);
    }

    @PutMapping("/group/{groupId}")
    public boolean editGroup(@PathVariable Long groupId,
                                   @RequestBody GroupEditRequestModel groupEditRequestModel){
        return adminPanelInputBoundary.editGroup(groupId, groupEditRequestModel);
    }

    @PostMapping("/addGroup")
    public boolean addGroup(@RequestBody GroupPostRequestModel groupPostRequestModel){
        return adminPanelInputBoundary.createGroup(groupPostRequestModel);
    }

    @GetMapping("/group/{groupId}/students")
    public List<StudentDtoModel> showStudentsByGroup(@PathVariable Long groupId){
        return adminPanelInputBoundary.findStudentsByGroup(groupId);
    }

    // Students

    @GetMapping("/allStudents")
    public List<StudentDtoModel> allStudents(){
        return adminPanelInputBoundary.getStudents();
    }

    @PostMapping("/filterStudents")
    public List<StudentDtoModel> filterStudents(@RequestBody StudentFilterRequestModel studentFilterRequestModel){
        return adminPanelInputBoundary.filterStudents(studentFilterRequestModel);
    }

    @DeleteMapping("/deleteStudent/{studentId}")
    public StudentDtoModel deleteStudent(@PathVariable Long studentId){
        return adminPanelInputBoundary.deleteStudent(studentId);
    }

    @GetMapping("/student/{studentId}")
    public StudentDtoModel showStudent(@PathVariable Long studentId){
        return adminPanelInputBoundary.findStudent(studentId);
    }

    @PutMapping("/student/{studentId}")
    public boolean editStudent(@PathVariable Long studentId,
                             @RequestBody StudentEditRequestModel studentEditRequestModel){
        return adminPanelInputBoundary.editStudent(studentId, studentEditRequestModel);
    }

    @PostMapping("/addStudent")
    public boolean addStudent(@RequestBody StudentPostRequestModel studentPostRequestModel){
        return adminPanelInputBoundary.createStudent(studentPostRequestModel);
    }

    // Teachers

    @GetMapping("/allTeachers")
    public List<TeacherDtoModel> allTeachers(){
        return adminPanelInputBoundary.getTeachers();
    }

    @PostMapping("/filterTeachers")
    public List<TeacherDtoModel> filterTeachers(@RequestBody TeacherFilterRequestModel teacherFilterRequestModel){
        return adminPanelInputBoundary.filterTeachers(teacherFilterRequestModel);
    }

    @DeleteMapping("/deleteTeacher/{teacherId}")
    public TeacherDtoModel deleteTeacher(@PathVariable Long teacherId){
        return adminPanelInputBoundary.deleteTeacher(teacherId);
    }

    @GetMapping("/teacher/{teacherId}")
    public TeacherDtoModel showTeacher(@PathVariable Long teacherId){
        return adminPanelInputBoundary.findTeacher(teacherId);
    }

    @PutMapping("/teacher/{teacherId}")
    public boolean editTeacher(@PathVariable Long teacherId,
                               @RequestBody TeacherEditRequestModel teacherEditRequestModel){
        return adminPanelInputBoundary.editTeacher(teacherId, teacherEditRequestModel);
    }

    @PostMapping("/addTeacher")
    public boolean addTeacher(@RequestBody TeacherPostRequestModel teacherPostRequestModel){
        return adminPanelInputBoundary.createTeacher(teacherPostRequestModel);
    }

    // Subjects

    @GetMapping("/allSubjects")
    public List<SubjectDtoModel> allSubjects(){
        return adminPanelInputBoundary.getSubjects();
    }

    @PostMapping("/filterSubjects")
    public List<SubjectDtoModel> filterSubjects(@RequestBody SubjectFilterRequestModel subjectFilterRequestModel){
        return adminPanelInputBoundary.filterSubjects(subjectFilterRequestModel);
    }

    @DeleteMapping("/deleteSubject/{subjectId}")
    public SubjectDtoModel deleteSubject(@PathVariable Long subjectId){
        return adminPanelInputBoundary.deleteSubject(subjectId);
    }

    @GetMapping("/subject/{subjectId}")
    public SubjectDtoModel showSubject(@PathVariable Long subjectId){
        return adminPanelInputBoundary.findSubject(subjectId);
    }

    @PutMapping("/subject/{subjectId}")
    public boolean editSubject(@PathVariable Long subjectId,
                               @RequestBody SubjectEditRequestModel subjectEditRequestModel){
        return adminPanelInputBoundary.editSubject(subjectId, subjectEditRequestModel);
    }

    @PostMapping("/addSubject")
    public boolean addSubject(@RequestBody SubjectPostRequestModel subjectPostRequestModel){
        return adminPanelInputBoundary.createSubject(subjectPostRequestModel);
    }

    // Checkpoints

    @GetMapping("/allCheckpoints")
    public List<CheckpointDtoModel> allCheckpoints(){
        return adminPanelInputBoundary.getCheckpoints();
    }

    @PostMapping("/filterCheckpoints")
    public List<CheckpointDtoModel> filterCheckpoints(@RequestBody CheckpointFilterRequestModel checkpointFilterRequestModel){
        return adminPanelInputBoundary.filterCheckpoints(checkpointFilterRequestModel);
    }

    @DeleteMapping("/deleteCheckpoint/{checkpointId}")
    public CheckpointDtoModel deleteCheckpoint(@PathVariable Long checkpointId){
        return adminPanelInputBoundary.deleteCheckpoint(checkpointId);
    }

    @GetMapping("/checkpoint/{checkpointId}")
    public CheckpointDtoModel showCheckpoint(@PathVariable Long checkpointId){
        return adminPanelInputBoundary.findCheckpoint(checkpointId);
    }

    @PutMapping("/checkpoint/{checkpointId}")
    public boolean editCheckpoint(@PathVariable Long checkpointId,
                               @RequestBody CheckpointEditRequestModel checkpointEditRequestModel){
        return adminPanelInputBoundary.editCheckpoint(checkpointId, checkpointEditRequestModel);
    }

    @PostMapping("/addCheckpoint")
    public boolean addCheckpoint(@RequestBody CheckpointPostRequestModel checkpointPostRequestModel){
        return adminPanelInputBoundary.createCheckpoint(checkpointPostRequestModel);
    }

    // Lessons

    @GetMapping("/allLessons")
    public List<LessonDtoModel> allLessons(){
        return adminPanelInputBoundary.getLessons();
    }

    @PostMapping("/filterLessons")
    public List<LessonDtoModel> filterLessons(@RequestBody LessonFilterRequestModel lessonFilterRequestModel){
        return adminPanelInputBoundary.filterLessons(lessonFilterRequestModel);
    }

    @DeleteMapping("/deleteLesson/{lessonId}")
    public LessonDtoModel deleteLesson(@PathVariable Long lessonId){
        return adminPanelInputBoundary.deleteLesson(lessonId);
    }

    @GetMapping("/lesson/{lessonId}")
    public LessonDtoModel showLesson(@PathVariable Long lessonId){
        return adminPanelInputBoundary.findLesson(lessonId);
    }

    @PutMapping("/lesson/{lessonId}")
    public boolean editLesson(@PathVariable Long lessonId,
                                  @RequestBody LessonEditRequestModel lessonEditRequestModel){
        return adminPanelInputBoundary.editLesson(lessonId, lessonEditRequestModel);
    }

    @PostMapping("/addLesson")
    public boolean addLesson(@RequestBody LessonPostRequestModel lessonPostRequestModel){
        return adminPanelInputBoundary.createLesson(lessonPostRequestModel);
    }

    // Attendance

    @GetMapping("/allAttendance")
    public List<AttendanceDtoModel> allAttendance(){
        return adminPanelInputBoundary.getAttendance();
    }

    @PostMapping("/filterAttendance")
    public List<AttendanceDtoModel> filterAttendance(@RequestBody AttendanceFilterRequestModel attendanceFilterRequestModel){
        return adminPanelInputBoundary.filterAttendance(attendanceFilterRequestModel);
    }

    @DeleteMapping("/deleteAttendance/{attendanceId}")
    public AttendanceDtoModel deleteAttendance(@PathVariable Long attendanceId){
        return adminPanelInputBoundary.deleteAttendance(attendanceId);
    }

    @GetMapping("/attendance/{attendanceId}")
    public AttendanceDtoModel showAttendance(@PathVariable Long attendanceId){
        return adminPanelInputBoundary.findAttendance(attendanceId);
    }

    @PutMapping("/attendance/{attendanceId}")
    public boolean editAttendance(@PathVariable Long attendanceId,
                              @RequestBody AttendanceEditRequestModel attendanceEditRequestModel){
        return adminPanelInputBoundary.editAttendance(attendanceId, attendanceEditRequestModel);
    }

    @PostMapping("/addAttendance")
    public boolean addAttendance(@RequestBody AttendancePostRequestModel attendancePostRequestModel){
        return adminPanelInputBoundary.createAttendance(attendancePostRequestModel);
    }

    // Scores

    @GetMapping("/allScores")
    public List<ScoreDtoModel> allScores(){
        return adminPanelInputBoundary.getScores();
    }

    @PostMapping("/filterScores")
    public List<ScoreDtoModel> filterScores(@RequestBody ScoreFilterRequestModel scoreFilterRequestModel){
        return adminPanelInputBoundary.filterScores(scoreFilterRequestModel);
    }

    @DeleteMapping("/deleteScore/{scoreId}")
    public ScoreDtoModel deleteScore(@PathVariable Long scoreId){
        return adminPanelInputBoundary.deleteScore(scoreId);
    }

    @GetMapping("/score/{scoreId}")
    public ScoreDtoModel showScore(@PathVariable Long scoreId){
        return adminPanelInputBoundary.findScore(scoreId);
    }

    @PutMapping("/score/{scoreId}")
    public boolean editScore(@PathVariable Long scoreId,
                              @RequestBody ScoreEditRequestModel scoreEditRequestModel){
        return adminPanelInputBoundary.editScore(scoreId, scoreEditRequestModel);
    }

    @PostMapping("/addScore")
    public boolean addScore(@RequestBody ScorePostRequestModel scorePostRequestModel){
        return adminPanelInputBoundary.createScore(scorePostRequestModel);
    }
}
