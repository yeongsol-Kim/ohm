package com.ohm.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GymController {


    @GetMapping("/member/count")
    @PreAuthorize("hasRole('ROLE_CEO')")
    public String memberCountPage() {
        return "memberCount/memberCount";
    }


    @GetMapping("/member/count2")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String memberCountPage2() {
        return "memberCount/memberCount";
    }

}
