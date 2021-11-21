package com.superngb.monitoring_system.Controllers.AdminPanel;

import com.superngb.monitoring_system.DTOs.person.UserDtoModel;
import com.superngb.monitoring_system.UseCases.AdminPanel.AdminPanelInputBoundary;
import com.superngb.monitoring_system.UseCases.AdminPanel.InputData.EditUserRequestModel;
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
        return adminPanelInputBoundary.getAll();
    }

    @PostMapping("/filterUsers")
    public List<UserDtoModel> filter(@RequestBody FilterUserRequestModel filterUserRequestModel){
        return adminPanelInputBoundary.filterUsers(filterUserRequestModel);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public UserDtoModel delete(@PathVariable Long userId){
        return adminPanelInputBoundary.deleteUser(userId);
    }


    @GetMapping("/user/{userId}")
    public UserDtoModel showUser(@PathVariable Long userId){
        return adminPanelInputBoundary.findConcreteUser(userId);
    }

    @PutMapping("/user/{userId}")
    public boolean editUser(@PathVariable Long userId,
                            @RequestBody EditUserRequestModel editUserRequestModel){
        return adminPanelInputBoundary.editUser(userId,editUserRequestModel);
    }

}
