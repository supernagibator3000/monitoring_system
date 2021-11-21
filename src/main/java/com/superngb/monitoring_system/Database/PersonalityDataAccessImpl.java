package com.superngb.monitoring_system.Database;

import com.superngb.monitoring_system.Entities.person.Personality;
import com.superngb.monitoring_system.Repositories.person.PersonalityRepository;
import com.superngb.monitoring_system.UseCases.AdminPanel.DataAccessInterfaces.AdminPanelPersonalityDataAccess;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Comparator;
import java.util.List;

public class PersonalityDataAccessImpl implements AdminPanelPersonalityDataAccess {

    PersonalityRepository personalityRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public PersonalityDataAccessImpl(PersonalityRepository personalityRepository) {
        this.personalityRepository = personalityRepository;
    }


    @Override
    public List<Personality> getAll() {
        return personalityRepository.findAll();
    }

    @Override
    public List<Personality> filter(Long id, String firstName, String secondName, String middleName) {
        if (id == null && firstName.equals("") && secondName.equals("") && middleName.equals("")){
            return getAll();
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Personality> criteriaQuery = criteriaBuilder.createQuery(Personality.class);
        Root<Personality> personality = criteriaQuery.from(Personality.class);

        Predicate predicateId = criteriaBuilder.equal(personality.get("id"), id);
        Predicate predicateFirstName = criteriaBuilder.like(personality.get("firstName"), firstName);
        Predicate predicateSecondName = criteriaBuilder.like(personality.get("secondName"), secondName);
        Predicate predicateMiddleName = criteriaBuilder.like(personality.get("middleName"), middleName);

        Predicate predicate = null;

        if(id != null) {
            predicate = criteriaBuilder.and(predicateId);
        }
        if(!firstName.equals("")){
            if (predicate != null)
                predicate = criteriaBuilder.and(predicate, predicateFirstName);
            else
                predicate = predicateFirstName;
        }
        if(!secondName.equals("")){
            if (predicate != null)
                predicate = criteriaBuilder.and(predicate, predicateFirstName);
            else
                predicate = predicateSecondName;
        }
        if(!middleName.equals("")){
            if (predicate != null)
                predicate = criteriaBuilder.and(predicate, predicateFirstName);
            else
                predicate = predicateMiddleName;
        }

        criteriaQuery.select(personality).where(predicate);
        List<Personality> personalities = entityManager.createQuery(criteriaQuery).getResultList();
        personalities.sort(Comparator.comparingLong(Personality::getId));

        return personalities;
    }

    @Override
    public Personality findById(Long id) {
        return personalityRepository.findById(id).orElse(null);
    }

    @Override
    public Personality findByFirstName(String firstName) {
        return personalityRepository.findByFirstName(firstName);
    }

    @Override
    public Personality findBySecondName(String secondName) {
        return personalityRepository.findBySecondName(secondName);
    }

    @Override
    public Personality findByMiddleName(String middleName) {
        return personalityRepository.findByMiddleName(middleName);
    }

    @Override
    public Personality findByEmail(String email) {
        return personalityRepository.findByEmail(email);
    }

    @Override
    public void save(Personality personality) {
        personalityRepository.save(personality);
    }

    @Override
    public Personality deleteById(Long id) {
        Personality personality = personalityRepository.findById(id).orElse(null);
        if (personality != null){
            personalityRepository.deleteById(id);
            return personality;
        }
        else return null;
    }
}
