package com.superngb.monitoring_system.Controllers.AdminPanel;

import com.superngb.monitoring_system.DTOs.person.PersonalityDtoModel;
import com.superngb.monitoring_system.DTOs.person.UserDtoModel;
import com.superngb.monitoring_system.UseCases.AdminPanel.AdminPanelInputBoundary;
import com.superngb.monitoring_system.UseCases.AdminPanel.InputData.EditPersonalityRequestModel;
import com.superngb.monitoring_system.UseCases.AdminPanel.InputData.EditUserRequestModel;
import com.superngb.monitoring_system.UseCases.AdminPanel.InputData.FilterPersonalityRequestModel;
import com.superngb.monitoring_system.UseCases.AdminPanel.InputData.FilterUserRequestModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminPanelController {

    private final AdminPanelInputBoundary adminPanelInputBoundary;

    public AdminPanelController(AdminPanelInputBoundary adminPanelInputBoundary) {
        this.adminPanelInputBoundary = adminPanelInputBoundary;
    }

    @GetMapping("/allUsers")
    public List<UserDtoModel> allUsers(){
        return adminPanelInputBoundary.getUsers();
    }

    @PostMapping("/filterUsers")
    public List<UserDtoModel> filterUsers(@RequestBody FilterUserRequestModel filterUserRequestModel){
        return adminPanelInputBoundary.filterUsers(filterUserRequestModel);
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
                            @RequestBody EditUserRequestModel editUserRequestModel){
        return adminPanelInputBoundary.editUser(userId,editUserRequestModel);
    }


    @GetMapping("/allPersonalities")
    public List<PersonalityDtoModel> allPersonalities(){
        return adminPanelInputBoundary.getPersonalities();
    }

    @PostMapping("/filterPersonalities")
    public List<PersonalityDtoModel> filterPersonalities(@RequestBody FilterPersonalityRequestModel filterPersonalityRequestModel){
        return adminPanelInputBoundary.filterPersonalities(filterPersonalityRequestModel);
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
                            @RequestBody EditPersonalityRequestModel editPersonalityRequestModel){
        return adminPanelInputBoundary.editPersonality(personalityId,editPersonalityRequestModel);
    }

}
