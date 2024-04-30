package com.example.springboard.controller;

import com.example.springboard.dto.User;
import com.example.springboard.dto.UserSignUp;
import com.example.springboard.global.StatusCode;
import com.example.springboard.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
     * @param id 회원가입하고 싶은 아이디
     */
    @GetMapping("/login/{id}")
    public String checkLoginID(@PathVariable("id") String id){
        if(userService.checkLoginID(id)){
            return StatusCode.Login_ID_FAIL.getMessage();
        }
        return StatusCode.LOGIN_ID_SUCCESS.getMessage();
    }

    //todo - 리턴 response 객체 만들기 -> UserSignup 속 NotBlank message를 상태코드와 함께 사용자에게 리턴해야함
    @PostMapping("/signup")
    public String signUp(@Valid @RequestBody UserSignUp userSignUp, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                String msg = objectError.getDefaultMessage();
                sb.append(msg);
            });
            return sb.toString();
        }

        userService.signUp(userSignUp);
        return userSignUp.toString();
    }


}
