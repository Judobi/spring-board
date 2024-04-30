package com.example.springboard.controller;

import com.example.springboard.dto.User;
import com.example.springboard.dto.UserSignUp;
import com.example.springboard.global.StatusCode;
import com.example.springboard.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpHeaders;
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

    //todo - 로그인 토큰 구현 후, 로그인 성공시 토큰 발급 추가 필요
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam(value = "id") @NotBlank String id,
                      @RequestParam(value = "pw") @NotBlank String pw){

        // loginService.login 실행 -> 성공 : user 객체 반환, 실패 : null
        User loginUser = userService.login(id,pw);
        if(loginUser == null){
            return ResponseEntity
                    .status(StatusCode.LOGIN_FAIL.getStatus())
                    .body(StatusCode.LOGIN_FAIL.getMessage());
        }

        return ResponseEntity
                .status(StatusCode.LOGIN_SUCCESS.getStatus())
                .body(StatusCode.LOGIN_SUCCESS.getMessage());
    }

    /**
     * 아이디 중복 확인
     * @param id 회원가입하고 싶은 아이디
     */
    @GetMapping("/login/{id}")
    public ResponseEntity<?> checkLoginID(@PathVariable("id") @NotBlank String id){
        if(userService.checkLoginID(id)){
            return ResponseEntity
                    .status(StatusCode.LOGIN_ID_FAIL.getStatus())
                    .body(StatusCode.LOGIN_ID_FAIL.getMessage());
        }
        return ResponseEntity
                .status(StatusCode.LOGIN_ID_SUCCESS.getStatus())
                .body(StatusCode.LOGIN_ID_SUCCESS.getMessage());
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody UserSignUp userSignUp, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                String msg = objectError.getDefaultMessage();
                sb.append(msg);
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }

        userService.signUp(userSignUp);
        return ResponseEntity.status(HttpStatus.CREATED).body(StatusCode.SIGNUP_SUCCESS.getMessage());
    }


}
