package com.superngb.monitoring_system.UseCases.AdminPanel;

import com.superngb.monitoring_system.DTOs.GroupDtoModel;
import com.superngb.monitoring_system.DTOs.person.PersonalityDtoModel;
import com.superngb.monitoring_system.DTOs.person.StudentDtoModel;
import com.superngb.monitoring_system.DTOs.person.TeacherDtoModel;
import com.superngb.monitoring_system.DTOs.person.UserDtoModel;
import com.superngb.monitoring_system.Entities.Group;
import com.superngb.monitoring_system.Entities.Role;
import com.superngb.monitoring_system.Entities.person.Personality;
import com.superngb.monitoring_system.Entities.person.Student;
import com.superngb.monitoring_system.Entities.person.Teacher;
import com.superngb.monitoring_system.Entities.person.User;
import com.superngb.monitoring_system.Enums.RoleEnum;
import com.superngb.monitoring_system.UseCases.AdminPanel.DataAccessInterfaces.*;
import com.superngb.monitoring_system.UseCases.AdminPanel.InputData.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdminPanelInteractor implements AdminPanelInputBoundary{

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private AdminPanelUserDataAccess adminPanelUserDataAccess;
    private AdminPanelRoleDataAccess adminPanelRoleDataAccess;
    private AdminPanelPersonalityDataAccess adminPanelPersonalityDataAccess;
    private AdminPanelGroupDataAccess adminPanelGroupDataAccess;
    private AdminPanelStudentDataAccess adminPanelStudentDataAccess;
    private AdminPanelTeacherDataAccess adminPanelTeacherDataAccess;

    private AdminPanelOutputBoundary adminPanelOutputBoundary;

    public AdminPanelInteractor(AdminPanelUserDataAccess adminPanelUserDataAccess,
                                AdminPanelRoleDataAccess adminPanelRoleDataAccess,
                                AdminPanelPersonalityDataAccess adminPanelPersonalityDataAccess,
                                AdminPanelGroupDataAccess adminPanelGroupDataAccess,
                                AdminPanelStudentDataAccess adminPanelStudentDataAccess,
                                AdminPanelOutputBoundary adminPanelOutputBoundary,
                                AdminPanelTeacherDataAccess adminPanelTeacherDataAccess) {
        this.adminPanelUserDataAccess = adminPanelUserDataAccess;
        this.adminPanelRoleDataAccess = adminPanelRoleDataAccess;
        this.adminPanelPersonalityDataAccess = adminPanelPersonalityDataAccess;
        this.adminPanelGroupDataAccess = adminPanelGroupDataAccess;
        this.adminPanelStudentDataAccess = adminPanelStudentDataAccess;
        this.adminPanelTeacherDataAccess = adminPanelTeacherDataAccess;

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
        return adminPanelOutputBoundary.prepareFindedUserView(userDtoModel);
    }

    @Override
    public UserDtoModel findUserByPersonality(Long id) {
        User user = adminPanelUserDataAccess.findByPersonalityId(id);
        UserDtoModel userDtoModel = UserDtoModel.userMapper(user);
        return adminPanelOutputBoundary.prepareFindedUserView(userDtoModel);
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
        User user = adminPanelUserDataAccess.deleteById(id);
        UserDtoModel userDtoModel = UserDtoModel.userMapper(user);
        return adminPanelOutputBoundary.prepareDeletedUserView(userDtoModel);
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
        return adminPanelOutputBoundary.prepareFindedPersonalityView(personalityDtoModel);
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
        return adminPanelOutputBoundary.prepareFindedGroupView(groupDtoModel);
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
        return adminPanelOutputBoundary.prepareFindedStudentView(studentDtoModel);
    }

    @Override
    public StudentDtoModel findStudentByPersonality(Long id) {
        Student student = adminPanelStudentDataAccess.findByPersonalityId(id);
        StudentDtoModel studentDtoModel = StudentDtoModel.studentMapper(student);
        return adminPanelOutputBoundary.prepareFindedStudentView(studentDtoModel);
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

        if(group == null || group == student.getGroup())
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
        if (group == null)
            return adminPanelOutputBoundary.prepareFailPostStudentView();

        Student studentByStudentCardId = adminPanelStudentDataAccess.findByStudentCardId(studentPostRequestModel.getStudentCardId());
        Student studentByPersonalityId = adminPanelStudentDataAccess.findByPersonalityId(studentPostRequestModel.getPersonalityId());
        if (studentByStudentCardId != null || studentByPersonalityId != null)
            return adminPanelOutputBoundary.prepareFailPostStudentView();

        Student student = new Student();
        student.setPersonality(personality);
        student.setStudentCardId(studentPostRequestModel.getStudentCardId());
        student.setGroup(group);
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
        return adminPanelOutputBoundary.prepareFindedTeacherView(teacherDtoModel);
    }

    @Override
    public TeacherDtoModel findTeacherByPersonality(Long id) {
        Teacher teacher = adminPanelTeacherDataAccess.findByPersonalityId(id);
        TeacherDtoModel teacherDtoModel = TeacherDtoModel.teacherMapper(teacher);
        return adminPanelOutputBoundary.prepareFindedTeacherView(teacherDtoModel);
    }

    @Override
    public boolean editTeacher(Long id, TeacherEditRequestModel teacherEditRequestModel) {

        return false;
    }

    @Override
    public List<TeacherDtoModel> filterTeachers(TeacherFilterRequestModel teacherFilterRequestModel) {
        return null;
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
        adminPanelTeacherDataAccess.save(teacher);

        return adminPanelOutputBoundary.prepareSuccessPostTeacherView(TeacherDtoModel.teacherMapper(teacher));
    }

}
