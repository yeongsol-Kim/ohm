package com.ohm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GymController {

    @GetMapping("/member/count")
    public String memberCountPage() {
        return "memberCount/memberCount";
    }

}
