package com.superngb.monitoring_system.Database;

import com.superngb.monitoring_system.Entities.Group;
import com.superngb.monitoring_system.Repositories.GroupRepository;
import com.superngb.monitoring_system.UseCases.AdminPanel.DataAccess.AdminPanelGroupDataAccess;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupDataAccessImpl implements AdminPanelGroupDataAccess {

    private GroupRepository groupRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public GroupDataAccessImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public List<Group> filter(Long id, String name) {
        if (id == null && name.equals("")){
            return getAll();
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
        Root<Group> group = criteriaQuery.from(Group.class);

        Predicate predicateId = criteriaBuilder.equal(group.get("id"), id);
        Predicate predicateName = criteriaBuilder.like(group.get("name"), name);

        Predicate predicate = null;

        if(id != null) {
            predicate = criteriaBuilder.and(predicateId);
        }
        if(!name.equals("")){
            if (predicate != null)
                predicate = criteriaBuilder.and(predicate, predicateName);
            else
                predicate = predicateName;
        }


        criteriaQuery.select(group).where(predicate);
        List<Group> groupList = entityManager.createQuery(criteriaQuery).getResultList();

        Set<Group> groupSet = new HashSet<>(groupList);
        groupList.clear();
        groupList.addAll(groupSet);

        groupList.sort(Comparator.comparingLong(Group::getId));

        return groupList;
    }

    @Override
    public Group findById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }

    @Override
    public Group findByName(String name) {
        return groupRepository.findByName(name);
    }

    @Override
    public void save(Group group) {
        groupRepository.save(group);
    }

    @Override
    public Group deleteById(Long id) {
        Group group = groupRepository.findById(id).orElse(null);
        if(group != null){
            groupRepository.deleteById(id);
            return group;
        }
        else return null;
    }
}
