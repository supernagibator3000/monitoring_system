package com.superngb.monitoring_system.DTOs.person;

import com.superngb.monitoring_system.Entities.person.Personality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalityDtoModel implements Serializable {

    private Long id;
    private String firstName;
    private String secondName;
    private String middleName;
    private String email;

    static public PersonalityDtoModel personalityMapper(Personality personality){
        return new PersonalityDtoModel(personality.getId(), personality.getFirstName(), personality.getSecondName(), personality.getMiddleName(), personality.getEmail());
    }

    static List<PersonalityDtoModel> listPersonalitiesMapper(List<Personality> personalities){
        List<PersonalityDtoModel> personalityDtoModels = new ArrayList<>();
        for (Personality personality: personalities)
            personalityDtoModels.add(personalityMapper(personality));
        return personalityDtoModels;
    }
}
