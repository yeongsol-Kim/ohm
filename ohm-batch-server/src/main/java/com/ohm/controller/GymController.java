package com.ohm.controller;

import com.ohm.dto.GymDto.GymDto;
import com.ohm.service.GymService;
import com.ohm.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class GymController {

    private final GymService gymService;


    @GetMapping("/login/success")
    public String loginSuccess() {
//        Long gymId = SecurityUtil.getCurrentGymId().orElse(null);
//        if (gymId == null) {
//            return "error/404";
//        }

        Long gymId = SecurityUtil.getCurrentGymId().orElse(null);
        System.out.println(gymId);
        return "redirect:/currentCountManage";
    }

    @GetMapping("/currentCountManage")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String myGymManageCurrentCountPage(Model model) {

        Long gymId = SecurityUtil.getCurrentGymId().orElse(null);
        if (gymId.equals(null)) return "error/400";

        // 정보 가져오기
        GymDto gymInfo = gymService.getGymMemberCountPageInfo(gymId);
        model.addAttribute("gym", gymInfo);

        return "memberCount/memberCount";
    }

    @GetMapping("/gym/increaseCurrentCount")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String increaseMyGymCurrentCount(HttpServletRequest request) {
        Long gymId = SecurityUtil.getCurrentGymId().orElse(null);
        if (gymId.equals(null)) return "error/400";
        gymService.increaseCurrentCount(gymId);
        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("/gym/decreaseCurrentCount")
//    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String decreaseMyGymCurrentCount(HttpServletRequest request) {
        Long gymId = SecurityUtil.getCurrentGymId().orElse(null);
        if (gymId.equals(null)) return "error/400";
        gymService.decreaseCurrentCount(gymId);
        return "redirect:" + request.getHeader("Referer");
    }



}
