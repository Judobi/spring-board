package com.example.springboard.controller;

import com.example.springboard.dto.User;
import com.example.springboard.global.status.StatusCode;
import com.example.springboard.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public void login(@RequestParam(value = "id") String id,
                      @RequestParam(value = "pw") String pw){
        System.out.println(id + pw);

        // loginService.login 실행 -> 성공 : user 객체 반환, 실패 : null
        User loginUser = userService.login(id,pw);
        if(loginUser == null){
            System.out.println("로그인 실패");
            return;
        }

        System.out.println("123");

    }

    /**
     * 아이디 중복 확인
     * @param id
     * @return
     */
    @GetMapping("/login/{id}")
    public String checkLoginID(@PathVariable("id") String id){
        if(userService.checkLoginID(id)){
            return StatusCode.Login_ID_FAIL.getMessage();
        }

        return StatusCode.LOGIN_ID_SUCCESS.getMessage();
    }


}
