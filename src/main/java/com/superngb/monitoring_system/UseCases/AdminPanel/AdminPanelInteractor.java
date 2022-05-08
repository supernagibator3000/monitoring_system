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
import com.superngb.monitoring_system.Entities.Group;
import com.superngb.monitoring_system.Entities.Role;
import com.superngb.monitoring_system.Entities.Subject;
import com.superngb.monitoring_system.Entities.event.Checkpoint;
import com.superngb.monitoring_system.Entities.event.Lesson;
import com.superngb.monitoring_system.Entities.mark.Attendance;
import com.superngb.monitoring_system.Entities.mark.Score;
import com.superngb.monitoring_system.Entities.person.Personality;
import com.superngb.monitoring_system.Entities.person.Student;
import com.superngb.monitoring_system.Entities.person.Teacher;
import com.superngb.monitoring_system.Entities.person.User;
import com.superngb.monitoring_system.Enums.AttendanceEnum;
import com.superngb.monitoring_system.Enums.RoleEnum;
import com.superngb.monitoring_system.Enums.ScoreEnum;
import com.superngb.monitoring_system.UseCases.AdminPanel.DataAccess.*;
import com.superngb.monitoring_system.UseCases.AdminPanel.InputData.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class AdminPanelInteractor implements AdminPanelInputBoundary{

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AdminPanelUserDataAccess adminPanelUserDataAccess;
    private final AdminPanelRoleDataAccess adminPanelRoleDataAccess;
    private final AdminPanelPersonalityDataAccess adminPanelPersonalityDataAccess;
    private final AdminPanelGroupDataAccess adminPanelGroupDataAccess;
    private final AdminPanelStudentDataAccess adminPanelStudentDataAccess;
    private final AdminPanelTeacherDataAccess adminPanelTeacherDataAccess;
    private final AdminPanelSubjectDataAccess adminPanelSubjectDataAccess;
    private final AdminPanelCheckpointDataAccess adminPanelCheckpointDataAccess;
    private final AdminPanelLessonDataAccess adminPanelLessonDataAccess;
    private final AdminPanelScoreDataAccess adminPanelScoreDataAccess;
    private final AdminPanelAttendanceDataAccess adminPanelAttendanceDataAccess;

    private final AdminPanelOutputBoundary adminPanelOutputBoundary;

    public AdminPanelInteractor(AdminPanelUserDataAccess adminPanelUserDataAccess,
                                AdminPanelRoleDataAccess adminPanelRoleDataAccess,
                                AdminPanelPersonalityDataAccess adminPanelPersonalityDataAccess,
                                AdminPanelGroupDataAccess adminPanelGroupDataAccess,
                                AdminPanelStudentDataAccess adminPanelStudentDataAccess,
                                AdminPanelOutputBoundary adminPanelOutputBoundary,
                                AdminPanelTeacherDataAccess adminPanelTeacherDataAccess,
                                AdminPanelSubjectDataAccess adminPanelSubjectDataAccess,
                                AdminPanelCheckpointDataAccess adminPanelCheckpointDataAccess,
                                AdminPanelLessonDataAccess adminPanelLessonDataAccess,
                                AdminPanelScoreDataAccess adminPanelScoreDataAccess,
                                AdminPanelAttendanceDataAccess adminPanelAttendanceDataAccess) {
        this.adminPanelUserDataAccess = adminPanelUserDataAccess;
        this.adminPanelRoleDataAccess = adminPanelRoleDataAccess;
        this.adminPanelPersonalityDataAccess = adminPanelPersonalityDataAccess;
        this.adminPanelGroupDataAccess = adminPanelGroupDataAccess;
        this.adminPanelStudentDataAccess = adminPanelStudentDataAccess;
        this.adminPanelTeacherDataAccess = adminPanelTeacherDataAccess;
        this.adminPanelSubjectDataAccess = adminPanelSubjectDataAccess;
        this.adminPanelCheckpointDataAccess = adminPanelCheckpointDataAccess;
        this.adminPanelLessonDataAccess = adminPanelLessonDataAccess;
        this.adminPanelScoreDataAccess = adminPanelScoreDataAccess;
        this.adminPanelAttendanceDataAccess = adminPanelAttendanceDataAccess;

        this.adminPanelOutputBoundary = adminPanelOutputBoundary;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = adminPanelUserDataAccess.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

// Users

    @Override
    public List<UserDtoModel> getUsers() {
        List<User> users = adminPanelUserDataAccess.getAll();
        List<UserDtoModel> userDtoModels = UserDtoModel.listUsersMapper(users);
        return adminPanelOutputBoundary.convertUsers(userDtoModels);
    }

    @Override
    public UserDtoModel findUser(Long id) {
        User user = adminPanelUserDataAccess.findById(id);
        UserDtoModel userDtoModel = UserDtoModel.userMapper(user);
        return adminPanelOutputBoundary.prepareFoundUserView(userDtoModel);
    }

    @Override
    public UserDtoModel findUserByPersonality(Long id) {
        User user = adminPanelUserDataAccess.findByPersonalityId(id);
        UserDtoModel userDtoModel = UserDtoModel.userMapper(user);
        return adminPanelOutputBoundary.prepareFoundUserView(userDtoModel);
    }

    @Override
    public boolean editUser(Long id, UserEditRequestModel userEditRequestModel) {
        User user = adminPanelUserDataAccess.findById(id);
        User userByName = adminPanelUserDataAccess.findByUsername(userEditRequestModel.getUsername());
        if(userByName!=null && userByName!=user)
            return adminPanelOutputBoundary.prepareFailEditUserView(UserDtoModel.userMapper(user));

        if (!userEditRequestModel.getUsername().equals(""))
            user.setUsername(userEditRequestModel.getUsername());

        List<Role> roles = user.getRoles();
        boolean alreadyExistRoleUser = false;
        boolean alreadyExistRoleAdmin = false;
        for (Role role:user.getRoles()){
            if (role.getName().equals(RoleEnum.ROLE_USER.name())) {
                alreadyExistRoleUser = true;
            }
            if (role.getName().equals(RoleEnum.ROLE_ADMIN.name())) {
                alreadyExistRoleAdmin = true;
            }
        }
        if (!userEditRequestModel.getRoleUser().equals("")){
            if (!alreadyExistRoleUser) roles.add(adminPanelRoleDataAccess.findById(RoleEnum.ROLE_USER.getId()));
        }
        else{
            if(alreadyExistRoleUser) roles.remove(adminPanelRoleDataAccess.findById(RoleEnum.ROLE_USER.getId()));
        }
        if (!userEditRequestModel.getRoleAdmin().equals("")){
            if (!alreadyExistRoleAdmin) roles.add(adminPanelRoleDataAccess.findById(RoleEnum.ROLE_ADMIN.getId()));
        }
        else{
            if (alreadyExistRoleAdmin) roles.remove(adminPanelRoleDataAccess.findById(RoleEnum.ROLE_ADMIN.getId()));
        }
        user.setRoles(roles);
        adminPanelUserDataAccess.save(user);
        UserDtoModel userDtoModel = UserDtoModel.userMapper(user);
        return adminPanelOutputBoundary.prepareSuccessEditUserView(userDtoModel);
    }

    @Override
    public List<UserDtoModel> filterUsers(UserFilterRequestModel userFilterRequestModel) {
        List<User> users = adminPanelUserDataAccess.filter(userFilterRequestModel.getId(),
                userFilterRequestModel.getPersonality(),
                userFilterRequestModel.getUsername(),
                userFilterRequestModel.getRoleUser(),
                userFilterRequestModel.getRoleAdmin());
        List<UserDtoModel> userDtoModels = UserDtoModel.listUsersMapper(users);
        return adminPanelOutputBoundary.convertUsers(userDtoModels);
    }

    @Override
    public UserDtoModel deleteUser(Long id) {
//        User user = adminPanelUserDataAccess.deleteById(id);
//        UserDtoModel userDtoModel = UserDtoModel.userMapper(user);
//        return adminPanelOutputBoundary.prepareDeletedUserView(userDtoModel);
        return null;
    }

    @Override
    public boolean createUser(UserPostRequestModel userPostRequestModel) {
        if (userPostRequestModel.getPersonalityId() == null
                || userPostRequestModel.getUsername().equals("")
                || userPostRequestModel.getPassword().equals(""))
            return adminPanelOutputBoundary.prepareFailPostUserView();

        Personality personality = adminPanelPersonalityDataAccess.findById(userPostRequestModel.getPersonalityId());
        if (personality == null)
            return adminPanelOutputBoundary.prepareFailPostUserView();

        User userByPersonalityId = adminPanelUserDataAccess.findByPersonalityId(userPostRequestModel.getPersonalityId());
        User userByUsername = adminPanelUserDataAccess.findByUsername(userPostRequestModel.getUsername());
        if (userByPersonalityId != null || userByUsername != null)
            return adminPanelOutputBoundary.prepareFailPostUserView();

        User user = new User();
        user.setPersonality(personality);
        user.setUsername(userPostRequestModel.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userPostRequestModel.getPassword()));
//        user.setRoles(Collections.singletonList(new Role(RoleEnum.ROLE_USER.getId(), RoleEnum.ROLE_USER.name())));
        List<Role> roleList = new ArrayList<>();
        roleList.add(new Role(RoleEnum.ROLE_USER.getId(), RoleEnum.ROLE_USER.name()));
        if (userPostRequestModel.getRole().equals("ROLE_ADMIN"))
            roleList.add(new Role(RoleEnum.ROLE_ADMIN.getId(), RoleEnum.ROLE_ADMIN.name()));

        user.setRoles(roleList);

        adminPanelUserDataAccess.save(user);

        return adminPanelOutputBoundary.prepareSuccessPostUserView(UserDtoModel.userMapper(user));
    }

    // Personalities

    @Override
    public List<PersonalityDtoModel> getPersonalities() {
        List<Personality> personalityList = adminPanelPersonalityDataAccess.getAll();
        List<PersonalityDtoModel> personalityDtoModelList = PersonalityDtoModel.listPersonalitiesMapper(personalityList);
        return adminPanelOutputBoundary.convertPersonalities(personalityDtoModelList);
    }

    @Override
    public PersonalityDtoModel findPersonality(Long id) {
        Personality personality = adminPanelPersonalityDataAccess.findById(id);
        PersonalityDtoModel personalityDtoModel = PersonalityDtoModel.personalityMapper(personality);
        return adminPanelOutputBoundary.prepareFoundPersonalityView(personalityDtoModel);
    }

    @Override
    public boolean editPersonality(Long id, PersonalityEditRequestModel personalityEditRequestModel) {
        Personality personality = adminPanelPersonalityDataAccess.findById(id);
        Personality personalityByEmail = adminPanelPersonalityDataAccess.findByEmail(personalityEditRequestModel.getEmail());

        if(personalityByEmail!=null && personalityByEmail!=personality)
            return adminPanelOutputBoundary.prepareFailEditPersonalityView(PersonalityDtoModel.personalityMapper(personality));

        if (!personalityEditRequestModel.getEmail().equals(""))
            personality.setFirstName(personalityEditRequestModel.getEmail());

        if(!personalityEditRequestModel.getFirstName().equals(""))
            personality.setFirstName(personalityEditRequestModel.getFirstName());

        if(!personalityEditRequestModel.getSecondName().equals(""))
            personality.setSecondName(personalityEditRequestModel.getSecondName());

        if(!personalityEditRequestModel.getMiddleName().equals(""))
            personality.setMiddleName(personalityEditRequestModel.getMiddleName());

        adminPanelPersonalityDataAccess.save(personality);
        PersonalityDtoModel personalityDtoModel = PersonalityDtoModel.personalityMapper(personality);
        return adminPanelOutputBoundary.prepareSuccessEditPersonalityView(personalityDtoModel);
    }

    @Override
    public List<PersonalityDtoModel> filterPersonalities(PersonalityFilterRequestModel filterPersonalityModel) {
        List<Personality> personalityList = adminPanelPersonalityDataAccess.filter(filterPersonalityModel.getId(),
                filterPersonalityModel.getFirstName(),
                filterPersonalityModel.getSecondName(),
                filterPersonalityModel.getMiddleName());
        List<PersonalityDtoModel> personalityDtoModels = PersonalityDtoModel.listPersonalitiesMapper(personalityList);
        return adminPanelOutputBoundary.convertPersonalities(personalityDtoModels);
    }

    @Override
    public PersonalityDtoModel deletePersonality(Long id) {
        // delete user+teacher+student
        return null;
    }

    @Override
    public boolean createPersonality(PersonalityPostRequestModel personalityPostRequestModel) {
        if (personalityPostRequestModel.getFirstName().equals("")
                || personalityPostRequestModel.getSecondName().equals("")
                || personalityPostRequestModel.getMiddleName().equals("")
                || personalityPostRequestModel.getEmail().equals(""))
            return adminPanelOutputBoundary.prepareFailPostPersonalityView();

        Personality personalityByEmail = adminPanelPersonalityDataAccess.findByEmail(personalityPostRequestModel.getEmail());
        if (personalityByEmail != null)
            return adminPanelOutputBoundary.prepareFailPostPersonalityView();

        Personality personality = new Personality();
        personality.setFirstName(personalityPostRequestModel.getFirstName());
        personality.setSecondName(personalityPostRequestModel.getSecondName());
        personality.setMiddleName(personalityPostRequestModel.getMiddleName());
        personality.setEmail(personalityPostRequestModel.getEmail());
        adminPanelPersonalityDataAccess.save(personality);

        return adminPanelOutputBoundary.prepareSuccessPostPersonalityView(PersonalityDtoModel.personalityMapper(personality));
    }

    // Groups

    @Override
    public List<GroupDtoModel> getGroups() {
        List<Group> groupList = adminPanelGroupDataAccess.getAll();
        List<GroupDtoModel> groupDtoModelList = GroupDtoModel.listGroupsMapper(groupList);
        return adminPanelOutputBoundary.convertGroups(groupDtoModelList);
    }

    @Override
    public GroupDtoModel findGroup(Long id) {
        Group group = adminPanelGroupDataAccess.findById(id);
        GroupDtoModel groupDtoModel = GroupDtoModel.groupMapper(group);
        return adminPanelOutputBoundary.prepareFoundGroupView(groupDtoModel);
    }

    @Override
    public boolean editGroup(Long id, GroupEditRequestModel groupEditRequestModel) {
        Group group = adminPanelGroupDataAccess.findById(id);
        Group groupByName = adminPanelGroupDataAccess.findByName(groupEditRequestModel.getName());

        if(groupByName!=null && groupByName!=group)
            return adminPanelOutputBoundary.prepareFailEditGroupView(GroupDtoModel.groupMapper(group));

        if (groupEditRequestModel.getName() != null && !groupEditRequestModel.getName().equals(""))
            group.setName(groupEditRequestModel.getName());

        Student student = adminPanelStudentDataAccess.findByStudentCardId(groupEditRequestModel.getStudentCardId());
        if (student != null) {
            student.setGroup(group);
            adminPanelStudentDataAccess.save(student);
        }

        adminPanelGroupDataAccess.save(group);
        GroupDtoModel groupDtoModel = GroupDtoModel.groupMapper(group);
        return adminPanelOutputBoundary.prepareSuccessEditGroupView(groupDtoModel);
    }

    @Override
    public List<GroupDtoModel> filterGroups(GroupFilterRequestModel groupFilterRequestModel) {
        List<Group> groupList = adminPanelGroupDataAccess.filter(groupFilterRequestModel.getId(),
                groupFilterRequestModel.getName());
        List<GroupDtoModel> groupDtoModelList = GroupDtoModel.listGroupsMapper(groupList);
        return adminPanelOutputBoundary.convertGroups(groupDtoModelList);
    }

    @Override
    public GroupDtoModel deleteGroup(Long id) {
        // subject + students
        return null;
    }

    @Override
    public boolean createGroup(GroupPostRequestModel groupPostRequestModel) {
        if (groupPostRequestModel.getName().equals(""))
            return adminPanelOutputBoundary.prepareFailPostGroupView();

        Group groupByName = adminPanelGroupDataAccess.findByName(groupPostRequestModel.getName());
        if (groupByName != null)
            return adminPanelOutputBoundary.prepareFailPostGroupView();

        Group group = new Group();
        group.setName(groupPostRequestModel.getName());

        group.setStudents(new ArrayList<>());
        group.setSubjects(new ArrayList<>());

        adminPanelGroupDataAccess.save(group);

        return adminPanelOutputBoundary.prepareSuccessPostGroupView(GroupDtoModel.groupMapper(group));
    }

    // Students

    @Override
    public List<StudentDtoModel> getStudents() {
        List<Student> studentList = adminPanelStudentDataAccess.getAll();
        List<StudentDtoModel> studentDtoModelList = StudentDtoModel.listStudentsMapper(studentList);
        return adminPanelOutputBoundary.convertStudents(studentDtoModelList);
    }

    @Override
    public StudentDtoModel findStudent(Long id) {
        Student student = adminPanelStudentDataAccess.findById(id);
        StudentDtoModel studentDtoModel = StudentDtoModel.studentMapper(student);
        return adminPanelOutputBoundary.prepareFoundStudentView(studentDtoModel);
    }

    @Override
    public StudentDtoModel findStudentByPersonality(Long id) {
        Student student = adminPanelStudentDataAccess.findByPersonalityId(id);
        StudentDtoModel studentDtoModel = StudentDtoModel.studentMapper(student);
        return adminPanelOutputBoundary.prepareFoundStudentView(studentDtoModel);
    }

    @Override
    public List<StudentDtoModel> findStudentsByGroup(Long id) {
        List<Student> studentList = adminPanelStudentDataAccess.findAllByGroupId(id);
        List<StudentDtoModel> studentDtoModelList = StudentDtoModel.listStudentsMapper(studentList);
        return adminPanelOutputBoundary.convertStudents(studentDtoModelList);
    }

    @Override
    public boolean editStudent(Long id, StudentEditRequestModel studentEditRequestModel) {
        Student student = adminPanelStudentDataAccess.findById(id);
        Group group = adminPanelGroupDataAccess.findByName(studentEditRequestModel.getGroup());

        if(group == null || group == student.getGroup() || student == null)
            return adminPanelOutputBoundary.prepareFailEditStudentView(StudentDtoModel.studentMapper(student));

        if (!studentEditRequestModel.getGroup().equals(""))
            student.setGroup(group);

        adminPanelStudentDataAccess.save(student);
        StudentDtoModel studentDtoModel = StudentDtoModel.studentMapper(student);
        return adminPanelOutputBoundary.prepareSuccessEditStudentView(studentDtoModel);
    }

    @Override
    public List<StudentDtoModel> filterStudents(StudentFilterRequestModel studentFilterRequestModel) {
        List<Student> studentList = adminPanelStudentDataAccess.filter(studentFilterRequestModel.getId(),
                studentFilterRequestModel.getStudentCardId(),
                studentFilterRequestModel.getPersonalityId(),
                studentFilterRequestModel.getGroupId());
        List<StudentDtoModel> studentDtoModelList = StudentDtoModel.listStudentsMapper(studentList);
        return adminPanelOutputBoundary.convertStudents(studentDtoModelList);
    }

    @Override
    public StudentDtoModel deleteStudent(Long id) {
        // mark + subject
        return null;
    }

    @Override
    public boolean createStudent(StudentPostRequestModel studentPostRequestModel) {
        if (studentPostRequestModel.getPersonalityId() == null
                || studentPostRequestModel.getStudentCardId().equals("")
                || studentPostRequestModel.getGroupName().equals(""))
            return adminPanelOutputBoundary.prepareFailPostStudentView();

        Personality personality = adminPanelPersonalityDataAccess.findById(studentPostRequestModel.getPersonalityId());
        if (personality == null)
            return adminPanelOutputBoundary.prepareFailPostStudentView();

        Group group = adminPanelGroupDataAccess.findByName(studentPostRequestModel.getGroupName());
        if (group.getId() == null)
            return adminPanelOutputBoundary.prepareFailPostStudentView();

        Student studentByStudentCardId = adminPanelStudentDataAccess.findByStudentCardId(studentPostRequestModel.getStudentCardId());
        Student studentByPersonalityId = adminPanelStudentDataAccess.findByPersonalityId(studentPostRequestModel.getPersonalityId());
        if (studentByStudentCardId != null || studentByPersonalityId != null)
            return adminPanelOutputBoundary.prepareFailPostStudentView();

        Student student = new Student();
        student.setPersonality(personality);
        student.setStudentCardId(studentPostRequestModel.getStudentCardId());
        student.setGroup(group);

        student.setSubjects(new ArrayList<>());
        student.setScores(new ArrayList<>());
        student.setAttendance(new ArrayList<>());

        adminPanelStudentDataAccess.save(student);

        return adminPanelOutputBoundary.prepareSuccessPostStudentView(StudentDtoModel.studentMapper(student));
    }

    @Override
    public List<TeacherDtoModel> getTeachers() {
        List<Teacher> teacherList = adminPanelTeacherDataAccess.getAll();
        List<TeacherDtoModel> teacherDtoModelList = TeacherDtoModel.listTeachersMapper(teacherList);
        return adminPanelOutputBoundary.convertTeachers(teacherDtoModelList);
    }

    @Override
    public TeacherDtoModel findTeacher(Long id) {
        Teacher teacher = adminPanelTeacherDataAccess.findById(id);
        TeacherDtoModel teacherDtoModel = TeacherDtoModel.teacherMapper(teacher);
        return adminPanelOutputBoundary.prepareFoundTeacherView(teacherDtoModel);
    }

    @Override
    public TeacherDtoModel findTeacherByPersonality(Long id) {
        Teacher teacher = adminPanelTeacherDataAccess.findByPersonalityId(id);
        TeacherDtoModel teacherDtoModel = TeacherDtoModel.teacherMapper(teacher);
        return adminPanelOutputBoundary.prepareFoundTeacherView(teacherDtoModel);
    }

    @Override
    public boolean editTeacher(Long id, TeacherEditRequestModel teacherEditRequestModel) {
        Teacher teacher = adminPanelTeacherDataAccess.findById(id);
        List<Subject> subjects = teacher.getSubjects();

        if (teacher == null)
            return adminPanelOutputBoundary.prepareFailEditTeacherView(TeacherDtoModel.teacherMapper(teacher));

        for (Long subjectId: teacherEditRequestModel.getSubjects()){
            Subject subject = adminPanelSubjectDataAccess.findById(subjectId);
            if (subject == null)
                return adminPanelOutputBoundary.prepareFailEditTeacherView(TeacherDtoModel.teacherMapper(teacher));

            subjects.add(subject);
        }
        teacher.setSubjects(subjects);

        adminPanelTeacherDataAccess.save(teacher);
        TeacherDtoModel teacherDtoModel = TeacherDtoModel.teacherMapper(teacher);
        return adminPanelOutputBoundary.prepareSuccessEditTeacherView(teacherDtoModel);
    }

    @Override
    public List<TeacherDtoModel> filterTeachers(TeacherFilterRequestModel teacherFilterRequestModel) {
        List<Teacher> teacherList = adminPanelTeacherDataAccess.filter(teacherFilterRequestModel.getId(),
                teacherFilterRequestModel.getPersonalityId());
        List<TeacherDtoModel> teacherDtoModelList = TeacherDtoModel.listTeachersMapper(teacherList);
        return adminPanelOutputBoundary.convertTeachers(teacherDtoModelList);
    }

    @Override
    public TeacherDtoModel deleteTeacher(Long id) {
        return null;
    }

    @Override
    public boolean createTeacher(TeacherPostRequestModel teacherPostRequestModel) {
        if (teacherPostRequestModel.getPersonalityId() == null)
            return adminPanelOutputBoundary.prepareFailPostTeacherView();

        Personality personality = adminPanelPersonalityDataAccess.findById(teacherPostRequestModel.getPersonalityId());
        if (personality == null)
            return adminPanelOutputBoundary.prepareFailPostTeacherView();

        Teacher teacherByPersonalityId = adminPanelTeacherDataAccess.findByPersonalityId(teacherPostRequestModel.getPersonalityId());
        if (teacherByPersonalityId != null)
            return adminPanelOutputBoundary.prepareFailPostTeacherView();

        Teacher teacher = new Teacher();
        teacher.setPersonality(personality);

        teacher.setSubjects(new ArrayList<>());

        adminPanelTeacherDataAccess.save(teacher);

        return adminPanelOutputBoundary.prepareSuccessPostTeacherView(TeacherDtoModel.teacherMapper(teacher));
    }

    @Override
    public List<SubjectDtoModel> getSubjects() {
        List<Subject> subjectList = adminPanelSubjectDataAccess.getAll();
        List<SubjectDtoModel> subjectDtoModelList = SubjectDtoModel.listSubjectsMapper(subjectList);
        return adminPanelOutputBoundary.convertSubjects(subjectDtoModelList);
    }

    @Override
    public SubjectDtoModel findSubject(Long id) {
        Subject subject = adminPanelSubjectDataAccess.findById(id);
        SubjectDtoModel subjectDtoModel = SubjectDtoModel.subjectMapper(subject);
        return adminPanelOutputBoundary.prepareFoundSubjectView(subjectDtoModel);
    }

    @Override
    public SubjectDtoModel findSubjectsByGroups(List<Long> groups) {

        return null;
    }

    @Override
    public boolean editSubject(Long id, SubjectEditRequestModel subjectEditRequestModel) {
        Subject subject = adminPanelSubjectDataAccess.findById(id);
        List<Group> groupList = subject.getGroups();
        List<Teacher> teacherList = subject.getTeachers();

        if (subject == null)
            return adminPanelOutputBoundary.prepareFailEditSubjectView(SubjectDtoModel.subjectMapper(subject));

        if (subjectEditRequestModel.getName() != null && !subjectEditRequestModel.getName().equals("")){
            subject.setName(subjectEditRequestModel.getName());
        }

        if (subjectEditRequestModel.getGroups() != null) {
            for (Long groupId : subjectEditRequestModel.getGroups()) {
                Group group = adminPanelGroupDataAccess.findById(groupId);
                if (group == null)
                    return adminPanelOutputBoundary.prepareFailEditSubjectView(SubjectDtoModel.subjectMapper(subject));

                List<Subject> groupSubjects = group.getSubjects();
                groupSubjects.add(subject);
                adminPanelGroupDataAccess.save(group);

                groupList.add(group);
            }
            subject.setGroups(groupList);
        }
        if (subjectEditRequestModel.getTeachers() != null) {
            for (Long teacherId : subjectEditRequestModel.getTeachers()) {
                Teacher teacher = adminPanelTeacherDataAccess.findById(teacherId);
                if (teacher == null)
                    return adminPanelOutputBoundary.prepareFailEditSubjectView(SubjectDtoModel.subjectMapper(subject));

                List<Subject> teacherSubjects = teacher.getSubjects();
                teacherSubjects.add(subject);
                adminPanelTeacherDataAccess.save(teacher);

                teacherList.add(teacher);
            }
            subject.setGroups(groupList);
        }

        adminPanelSubjectDataAccess.save(subject);
        SubjectDtoModel subjectDtoModel = SubjectDtoModel.subjectMapper(subject);
        return adminPanelOutputBoundary.prepareSuccessEditSubjectView(subjectDtoModel);
    }

    @Override
    public List<SubjectDtoModel> filterSubjects(SubjectFilterRequestModel subjectFilterRequestModel) {
        List<Subject> subjectList = adminPanelSubjectDataAccess.filter(subjectFilterRequestModel.getId(),
                subjectFilterRequestModel.getName());
        List<SubjectDtoModel> subjectDtoModelList = SubjectDtoModel.listSubjectsMapper(subjectList);
        return adminPanelOutputBoundary.convertSubjects(subjectDtoModelList);
    }

    @Override
    public SubjectDtoModel deleteSubject(Long id) {
        return null;
    }

    @Override
    public boolean createSubject(SubjectPostRequestModel subjectPostRequestModel) {
        if (subjectPostRequestModel.getName().equals(""))
            return adminPanelOutputBoundary.prepareFailPostSubjectView();

        Subject subjectByName = adminPanelSubjectDataAccess.findByName(subjectPostRequestModel.getName());
        if (subjectByName != null)
            return adminPanelOutputBoundary.prepareFailPostSubjectView();

        Subject subject = new Subject();
        subject.setName(subjectPostRequestModel.getName());

        subject.setLessons(new ArrayList<>());
        subject.setCheckpoints(new ArrayList<>());
        subject.setGroups(new ArrayList<>());
        subject.setStudents(new ArrayList<>());
        subject.setTeachers(new ArrayList<>());

        adminPanelSubjectDataAccess.save(subject);

        return adminPanelOutputBoundary.prepareSuccessPostSubjectView(SubjectDtoModel.subjectMapper(subject));
    }

    // Checkpoints

    @Override
    public List<CheckpointDtoModel> getCheckpoints() {
        List<Checkpoint> checkpointList = adminPanelCheckpointDataAccess.getAll();
        List<CheckpointDtoModel> checkpointDtoModelList = CheckpointDtoModel.listCheckpointsMapper(checkpointList);
        return adminPanelOutputBoundary.convertCheckpoints(checkpointDtoModelList);
    }

    @Override
    public CheckpointDtoModel findCheckpoint(Long id) {
        Checkpoint checkpoint = adminPanelCheckpointDataAccess.findById(id);
        CheckpointDtoModel checkpointDtoModel = CheckpointDtoModel.checkpointMapper(checkpoint);
        return adminPanelOutputBoundary.prepareFoundCheckpointView(checkpointDtoModel);
    }

    @Override
    public CheckpointDtoModel findCheckpointByName(String name) {
        Checkpoint checkpoint = adminPanelCheckpointDataAccess.findByName(name);
        CheckpointDtoModel checkpointDtoModel = CheckpointDtoModel.checkpointMapper(checkpoint);
        return adminPanelOutputBoundary.prepareFoundCheckpointView(checkpointDtoModel);
    }

    @Override
    public CheckpointDtoModel findCheckpointBySubjectId(Long subjectId) {
        Checkpoint checkpoint = adminPanelCheckpointDataAccess.findBySubjectId(subjectId);
        CheckpointDtoModel checkpointDtoModel = CheckpointDtoModel.checkpointMapper(checkpoint);
        return adminPanelOutputBoundary.prepareFoundCheckpointView(checkpointDtoModel);
    }

    @Override
    public boolean editCheckpoint(Long id, CheckpointEditRequestModel checkpointEditRequestModel) {
        Checkpoint checkpoint = adminPanelCheckpointDataAccess.findById(id);
        Checkpoint checkpointByName = adminPanelCheckpointDataAccess.findByName(checkpoint.getName());

        if (checkpointByName != null && checkpointByName != checkpoint)
            return  adminPanelOutputBoundary.prepareFailEditCheckpointView(CheckpointDtoModel.checkpointMapper(checkpoint));

        if (!checkpointEditRequestModel.getName().equals(""))
            checkpoint.setName(checkpointEditRequestModel.getName());

        adminPanelCheckpointDataAccess.save(checkpoint);

        CheckpointDtoModel checkpointDtoModel = CheckpointDtoModel.checkpointMapper(checkpoint);
        return adminPanelOutputBoundary.prepareSuccessEditCheckpointView(checkpointDtoModel);
    }

    @Override
    public List<CheckpointDtoModel> filterCheckpoints(CheckpointFilterRequestModel checkpointFilterRequestModel) {
        List<Checkpoint> checkpointList = adminPanelCheckpointDataAccess.filter(
                checkpointFilterRequestModel.getId(),
                checkpointFilterRequestModel.getName(),
                checkpointFilterRequestModel.getSubjectId());
        List<CheckpointDtoModel> checkpointDtoModelList = CheckpointDtoModel.listCheckpointsMapper(checkpointList);
        return adminPanelOutputBoundary.convertCheckpoints(checkpointDtoModelList);
    }

    @Override
    public CheckpointDtoModel deleteCheckpoint(Long id) {
        return null;
    }

    @Override
    public boolean createCheckpoint(CheckpointPostRequestModel checkpointPostRequestModel) {
        if (checkpointPostRequestModel.getName().equals(""))
            return adminPanelOutputBoundary.prepareFailPostCheckpointView();

        Subject subjectById = adminPanelSubjectDataAccess.findById(checkpointPostRequestModel.getSubjectId());
        if (subjectById == null)
            return adminPanelOutputBoundary.prepareFailPostCheckpointView();

        for (Checkpoint checkpoint: subjectById.getCheckpoints()){
            if (checkpoint.getName().equals(checkpointPostRequestModel.getName()))
                return adminPanelOutputBoundary.prepareFailPostCheckpointView();
        }

        Checkpoint checkpoint = new Checkpoint();
        checkpoint.setName(checkpointPostRequestModel.getName());
        checkpoint.setSubject(subjectById);

        checkpoint.setScoreList(new ArrayList<>());

        adminPanelCheckpointDataAccess.save(checkpoint);

        List<Group> groupList = subjectById.getGroups();
        for (Group group: groupList){
            for (Student student: group.getStudents()){
                createScore(new ScorePostRequestModel(student.getId(), "", checkpoint.getId()));
            }
        }

        return adminPanelOutputBoundary.prepareSuccessPostCheckpointView(CheckpointDtoModel.checkpointMapper(checkpoint));
    }

    // Lessons

    @Override
    public List<LessonDtoModel> getLessons() {
        List<Lesson> lessonList = adminPanelLessonDataAccess.getAll();
        List<LessonDtoModel> lessonDtoModelList = LessonDtoModel.listLessonsMapper(lessonList);
        return adminPanelOutputBoundary.convertLessons(lessonDtoModelList);
    }

    @Override
    public LessonDtoModel findLesson(Long id) {
        Lesson lesson = adminPanelLessonDataAccess.findById(id);
        LessonDtoModel lessonDtoModel = LessonDtoModel.lessonMapper(lesson);
        return adminPanelOutputBoundary.prepareFoundLessonView(lessonDtoModel);
    }

    @Override
    public LessonDtoModel findLessonByName(String name) {
        Lesson lesson = adminPanelLessonDataAccess.findByName(name);
        LessonDtoModel lessonDtoModel = LessonDtoModel.lessonMapper(lesson);
        return adminPanelOutputBoundary.prepareFoundLessonView(lessonDtoModel);
    }

    @Override
    public LessonDtoModel findLessonBySubjectId(Long subjectId) {
        Lesson lesson = adminPanelLessonDataAccess.findBySubjectId(subjectId);
        LessonDtoModel lessonDtoModel = LessonDtoModel.lessonMapper(lesson);
        return adminPanelOutputBoundary.prepareFoundLessonView(lessonDtoModel);    }

    @Override
    public boolean editLesson(Long id, LessonEditRequestModel lessonEditRequestModel) {
        Lesson lesson = adminPanelLessonDataAccess.findById(id);
        Lesson lessonByName = adminPanelLessonDataAccess.findByName(lesson.getName());

        if (lessonByName != null && lessonByName != lesson)
            return  adminPanelOutputBoundary.prepareFailEditLessonView(LessonDtoModel.lessonMapper(lesson));

        if (!lessonEditRequestModel.getName().equals(""))
            lesson.setName(lessonEditRequestModel.getName());

        adminPanelLessonDataAccess.save(lesson);

        LessonDtoModel lessonDtoModel = LessonDtoModel.lessonMapper(lesson);
        return adminPanelOutputBoundary.prepareSuccessEditLessonView(lessonDtoModel);
    }

    @Override
    public List<LessonDtoModel> filterLessons(LessonFilterRequestModel lessonFilterRequestModel) {
        List<Lesson> lessonList = adminPanelLessonDataAccess.filter(
                lessonFilterRequestModel.getId(),
                lessonFilterRequestModel.getName(),
                lessonFilterRequestModel.getSubjectId());
        List<LessonDtoModel> lessonDtoModelList = LessonDtoModel.listLessonsMapper(lessonList);
        return adminPanelOutputBoundary.convertLessons(lessonDtoModelList);
    }

    @Override
    public LessonDtoModel deleteLesson(Long id) {
        return null;
    }

    @Override
    public boolean createLesson(LessonPostRequestModel lessonPostRequestModel) {
        if (lessonPostRequestModel.getName().equals(""))
            return adminPanelOutputBoundary.prepareFailPostLessonView();

        Subject subjectById = adminPanelSubjectDataAccess.findById(lessonPostRequestModel.getSubjectId());
        if (subjectById == null)
            return adminPanelOutputBoundary.prepareFailPostLessonView();

        for (Lesson lesson: subjectById.getLessons()){
            if (lesson.getName().equals(lessonPostRequestModel.getName()))
                return adminPanelOutputBoundary.prepareFailPostLessonView();
        }

        Lesson lesson = new Lesson();
        lesson.setName(lessonPostRequestModel.getName());
        lesson.setSubject(subjectById);

        lesson.setAttendanceList(new ArrayList<>());

        adminPanelLessonDataAccess.save(lesson);

        List<Group> groupList = subjectById.getGroups();
        for (Group group: groupList){
            for (Student student: group.getStudents()){
                createAttendance(new AttendancePostRequestModel(student.getId(), "", lesson.getId()));
            }
        }

        return adminPanelOutputBoundary.prepareSuccessPostLessonView(LessonDtoModel.lessonMapper(lesson));
    }

    // Attendance

    @Override
    public List<AttendanceDtoModel> getAttendance() {
        List<Attendance> attendanceList = adminPanelAttendanceDataAccess.getAll();
        List<AttendanceDtoModel> attendanceDtoModelList = AttendanceDtoModel.listAttendanceMapper(attendanceList);
        return adminPanelOutputBoundary.convertAttendances(attendanceDtoModelList);
    }

    @Override
    public List<AttendanceDtoModel> findAttendanceByLesson(Long lessonId) {
        List<Attendance> attendanceList = adminPanelAttendanceDataAccess.findAllByLessonId(lessonId);
        List<AttendanceDtoModel> attendanceDtoModelList = AttendanceDtoModel.listAttendanceMapper(attendanceList);
        return adminPanelOutputBoundary.convertAttendances(attendanceDtoModelList);
    }

    @Override
    public AttendanceDtoModel findAttendance(Long id) {
        Attendance attendance = adminPanelAttendanceDataAccess.findById(id);
        AttendanceDtoModel attendanceDtoModel = AttendanceDtoModel.attendanceMapper(attendance);
        return adminPanelOutputBoundary.prepareFoundAttendanceView(attendanceDtoModel);
    }

    @Override
    public boolean editAttendance(Long id, AttendanceEditRequestModel attendanceEditRequestModel) {
        Attendance attendance = adminPanelAttendanceDataAccess.findById(id);
        if (attendance == null)
            return adminPanelOutputBoundary.prepareFailEditAttendanceView(AttendanceDtoModel.attendanceMapper(attendance));

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
        adminPanelAttendanceDataAccess.save(attendance);

        return adminPanelOutputBoundary.prepareSuccessPostAttendanceView(AttendanceDtoModel.attendanceMapper(attendance));
    }

    @Override
    public List<AttendanceDtoModel> filterAttendance(AttendanceFilterRequestModel attendanceFilterRequestModel) {
        List<Attendance> attendanceList = adminPanelAttendanceDataAccess.filter(attendanceFilterRequestModel.getId(),
                attendanceFilterRequestModel.getStudentId(), attendanceFilterRequestModel.getLessonId());
        List<AttendanceDtoModel> attendanceDtoModelList = AttendanceDtoModel.listAttendanceMapper(attendanceList);
        return adminPanelOutputBoundary.convertAttendances(attendanceDtoModelList);
    }

    @Override
    public AttendanceDtoModel deleteAttendance(Long id) {
        return null;
    }

    @Override
    public boolean createAttendance(AttendancePostRequestModel attendancePostRequestModel) {
        if (attendancePostRequestModel.getStudentId() == null || attendancePostRequestModel.getLessonId() == null)
            return adminPanelOutputBoundary.prepareFailPostAttendanceView();

        Student student = adminPanelStudentDataAccess.findById(attendancePostRequestModel.getStudentId());
        Lesson lesson = adminPanelLessonDataAccess.findById(attendancePostRequestModel.getLessonId());
        if (student == null || lesson == null)
            return adminPanelOutputBoundary.prepareFailPostAttendanceView();

        Score scoreByCheckpointIdAndStudentId = adminPanelScoreDataAccess.findByCheckpointIdAndStudentId(attendancePostRequestModel.getLessonId(), attendancePostRequestModel.getStudentId());

        if (scoreByCheckpointIdAndStudentId != null)
            return adminPanelOutputBoundary.prepareFailPostAttendanceView();

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
        adminPanelAttendanceDataAccess.save(attendance);

        return adminPanelOutputBoundary.prepareSuccessPostAttendanceView(AttendanceDtoModel.attendanceMapper(attendance));
    }

    // Scores

    @Override
    public List<ScoreDtoModel> getScores() {
        List<Score> scoreList = adminPanelScoreDataAccess.getAll();
        List<ScoreDtoModel> scoreDtoModelList = ScoreDtoModel.listScoresMapper(scoreList);
        return adminPanelOutputBoundary.convertScores(scoreDtoModelList);
    }

    @Override
    public List<ScoreDtoModel> findScoresByCheckpoint(Long checkpointId) {
        List<Score> scoreList = adminPanelScoreDataAccess.findAllByLessonId(checkpointId);
        List<ScoreDtoModel> scoreDtoModelList = ScoreDtoModel.listScoresMapper(scoreList);
        return adminPanelOutputBoundary.convertScores(scoreDtoModelList);
    }

    @Override
    public ScoreDtoModel findScore(Long id) {
        Score score = adminPanelScoreDataAccess.findById(id);
        ScoreDtoModel scoreDtoModel = ScoreDtoModel.scoreMapper(score);
        return adminPanelOutputBoundary.prepareFoundScoreView(scoreDtoModel);
    }

    @Override
    public boolean editScore(Long id, ScoreEditRequestModel scoreEditRequestModel) {
        Score score = adminPanelScoreDataAccess.findById(id);
        if (score == null)
            return adminPanelOutputBoundary.prepareFailEditScoreView(ScoreDtoModel.scoreMapper(score));

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
        adminPanelScoreDataAccess.save(score);

        return adminPanelOutputBoundary.prepareSuccessEditScoreView(ScoreDtoModel.scoreMapper(score));
    }

    @Override
    public List<ScoreDtoModel> filterScores(ScoreFilterRequestModel scoreFilterRequestModel) {
        List<Score> scoreList = adminPanelScoreDataAccess.filter(scoreFilterRequestModel.getId(),
                scoreFilterRequestModel.getStudentId(), scoreFilterRequestModel.getCheckpointId());
        List<ScoreDtoModel> scoreDtoModelList = ScoreDtoModel.listScoresMapper(scoreList);
        return adminPanelOutputBoundary.convertScores(scoreDtoModelList);
    }

    @Override
    public ScoreDtoModel deleteScore(Long id) {
        return null;
    }

    @Override
    public boolean createScore(ScorePostRequestModel scorePostRequestModel) {
        if (scorePostRequestModel.getStudentId() == null || scorePostRequestModel.getCheckpointId() == null)
            return adminPanelOutputBoundary.prepareFailPostScoreView();

        Student student = adminPanelStudentDataAccess.findById(scorePostRequestModel.getStudentId());
        Checkpoint checkpoint = adminPanelCheckpointDataAccess.findById(scorePostRequestModel.getCheckpointId());
        if (student == null || checkpoint == null)
            return adminPanelOutputBoundary.prepareFailPostScoreView();

        Score scoreByCheckpointIdAndStudentId = adminPanelScoreDataAccess.findByCheckpointIdAndStudentId(scorePostRequestModel.getCheckpointId(), scorePostRequestModel.getStudentId());

        if (scoreByCheckpointIdAndStudentId != null)
            return adminPanelOutputBoundary.prepareFailPostScoreView();

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
        adminPanelScoreDataAccess.save(score);

        return adminPanelOutputBoundary.prepareSuccessPostScoreView(ScoreDtoModel.scoreMapper(score));
    }

}
