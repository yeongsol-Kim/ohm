package com.ohm.controller;

import com.ohm.dto.GymDto.GymDto;
import com.ohm.service.GymService;
import com.ohm.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class GymController {

    private final GymService gymService;


    @GetMapping("/login/success")
    public String loginSuccess() {


        Long gymId = SecurityUtil.getCurrentGymId().orElse(null);
        return "redirect:/currentCountManage";
    }

    @GetMapping("/currentCountManage")
    public String myGymManageCurrentCountPage(Model model) {

        Long gymId = SecurityUtil.getCurrentGymId().orElse(null);
        if (gymId.equals(null)) return "error/400";

        // 정보 가져오기
        GymDto gymInfo = gymService.getGymMemberCountPageInfo(gymId);
        model.addAttribute("gym", gymInfo);

        return "memberCount/memberCount";
    }

    @GetMapping("/gym/increaseCurrentCount")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_TRAINER')")
    public String increaseMyGymCurrentCount(HttpServletRequest request) {
        Long gymId = SecurityUtil.getCurrentGymId().orElse(null);
        if (gymId.equals(null)) return "error/400";
        gymService.increaseCurrentCount(gymId);
        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("/gym/decreaseCurrentCount")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_TRAINER')")
    public String decreaseMyGymCurrentCount(HttpServletRequest request) {
        Long gymId = SecurityUtil.getCurrentGymId().orElse(null);
        if (gymId.equals(null)) return "error/400";
        gymService.decreaseCurrentCount(gymId);
        return "redirect:" + request.getHeader("Referer");
    }



}
