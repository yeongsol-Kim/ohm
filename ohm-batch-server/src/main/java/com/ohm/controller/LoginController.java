package com.ohm.controller;

import com.ohm.entity.Manager.Manager;
import com.ohm.repository.GymRepository;
import com.ohm.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final ManagerRepository managerRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "login/loginPage";
    }

}
