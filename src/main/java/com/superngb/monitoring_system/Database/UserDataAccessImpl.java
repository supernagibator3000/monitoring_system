package com.superngb.monitoring_system.Database;

import com.superngb.monitoring_system.Entities.Role;
import com.superngb.monitoring_system.Entities.person.User;
import com.superngb.monitoring_system.Enums.RoleEnum;
import com.superngb.monitoring_system.Repositories.person.UserRepository;
import com.superngb.monitoring_system.UseCases.AdminPanel.DataAccessInterfaces.AdminPanelUserDataAccess;
import com.superngb.monitoring_system.UseCases.UserAuthorization.UserAuthorizationUserDataAccess;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDataAccessImpl implements UserAuthorizationUserDataAccess, AdminPanelUserDataAccess {

    private final UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public UserDataAccessImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> filter(Long id, String username, String ROLE_USER, String ROLE_ADMIN) {
        if (id==null && username.equals("") && ROLE_USER.equals("") && ROLE_ADMIN.equals("")){
            return getAll();
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> user = criteriaQuery.from(User.class);

        Join<User, Role> join = user.join("roles", JoinType.INNER);

        Predicate predicateForId = criteriaBuilder.equal(user.get("id"), id);
        Predicate predicateForUsername = criteriaBuilder.like(user.get("username"), username);
        Predicate predicateForRoleUser = criteriaBuilder.like(join.get("name").as(String.class), RoleEnum.ROLE_USER.name());
        Predicate predicateForRoleAdmin = criteriaBuilder.like(join.get("name").as(String.class),RoleEnum.ROLE_ADMIN.name());

        Predicate fieldPredicate = null;
        Predicate rolePredicate = null;
        Predicate finalePredicate;

        if(id!=null){
            fieldPredicate = criteriaBuilder.and(predicateForId);
        }
        if(!username.equals("")){
            if (fieldPredicate != null) fieldPredicate = criteriaBuilder.and(fieldPredicate,predicateForUsername);
            else fieldPredicate = predicateForUsername;
        }
        if(!ROLE_USER.equals("")){
            rolePredicate = criteriaBuilder.and(predicateForRoleUser);
        }
        if(!ROLE_ADMIN.equals("")){
            if (rolePredicate != null) rolePredicate = criteriaBuilder.or(rolePredicate,predicateForRoleAdmin);
            else rolePredicate = predicateForRoleAdmin;
        }

        if (fieldPredicate==null) finalePredicate = rolePredicate;
        else if (rolePredicate==null) finalePredicate = fieldPredicate;
        else finalePredicate = criteriaBuilder.and(fieldPredicate,rolePredicate);

        criteriaQuery.select(user).where(finalePredicate);
        List<User> users = entityManager.createQuery(criteriaQuery).getResultList();

        Set<User> userSet = new HashSet<>(users);
        users.clear();
        users.addAll(userSet);

        users.sort(Comparator.comparingLong(User::getId));

        return users;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User deleteById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user!=null){
            userRepository.deleteById(id);
            return user;
        }
        else return null;
    }
}
