package com.ohm.controller.admin;

import com.ohm.dto.GymDto.GymDto;
import com.ohm.service.GymService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class AdminGymController {

    private final GymService gymService;


    @GetMapping("/member/count/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String memberCountPage(@PathVariable(name = "id") Long id, Model model) {
        // 권한 검증

        // 정보 가져오기
        GymDto gymInfo = gymService.getGymMemberCountPageInfo(id);
        model.addAttribute("gym", gymInfo);
        model.addAttribute("id", id);

        return "memberCount/memberCount";
    }

    @GetMapping("/member/count/increase/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String increaseMemberCount(HttpServletRequest request, @PathVariable(name = "id") Long id) {
        //권한 검증


        gymService.increaseCurrentCount(id);
        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("/member/count/decrease/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String decreaseMemberCount(HttpServletRequest request, @PathVariable(name = "id") Long id) {
        //권한 검증


        gymService.decreaseCurrentCount(id);
        return "redirect:" + request.getHeader("Referer");
    }
}