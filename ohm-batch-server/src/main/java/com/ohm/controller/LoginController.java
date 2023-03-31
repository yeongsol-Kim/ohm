package com.ohm.controller;

import com.ohm.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final ManagerRepository managerRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "login/loginPage";
    }

    @GetMapping("/aa")
    public void aa() {
        System.out.println(managerRepository.findByUsername("asdd11").orElse(null).getUsername());

    }
}
