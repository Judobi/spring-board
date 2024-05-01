package com.example.springboard.controller;

import com.example.springboard.dto.UserInfoDto;
import com.example.springboard.service.impl.UserServiceImpl;
import com.example.springboard.vo.User;
import com.example.springboard.dto.UserRequest;
import com.example.springboard.global.StatusCode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    /**
     * 로그인
     * @param userRequest id, pw가 포함된 객체
     * @return 로그인 여부에 관련된 응답
     * ----
     * todo - 로그인 토큰 구현 후, 로그인 성공시 토큰 발급 추가 필요
     *  그리고 탈퇴한 유저의 경우 지금은 아이디 비밀번호 체크라고 나오는데 이부분에 대한 안내메시지 출력하도록 하는 분기처리가 필요.
     * select 결과로 받은 user 객체에서 accessid를 포함해서 access_token 발급 예정
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest userRequest){

        // loginService.login 실행 -> 성공 : user 객체 반환, 실패 : null
        User loginUser = userService.login(userRequest);
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
     * 로그아웃
     * @param userRequest userid와 refresh토큰을 받아서 디비에서 refresh토큰 제거
     */
    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(@RequestBody UserRequest userRequest){
        //토큰 구현시 추가 예정
    }

    /**
     * 아이디 중복 확인
     * @param id 회원가입하고 싶은 아이디
     */
    @GetMapping("/signup/{id}")
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

    /**
     * 회원가입
     * @param userRequest : 회원가입을 위한 객체, id pw nickname을 포함하고 있다
     * @param bindingResult : userSignUp 객체 필드에 유효하지 않는 값을 가질 경우에 포함 되는 에러메세지 객체
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody UserRequest userRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                String msg = objectError.getDefaultMessage();
                sb.append(msg);
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }

        userService.signUp(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(StatusCode.SIGNUP_SUCCESS.getMessage());
    }

    /**
     * 회원정보 조회
     * @param id
     * @return user 객체를 반환되나 uid와 accessid를 제외해서 select를 해와서 이 두 값은 0으로 전달.
     */
    @GetMapping("/members/{id}")
    public ResponseEntity<?> getUserDetail(@PathVariable("id") String id){
        UserInfoDto user = new UserInfoDto(userService.getUserDetail(id));
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


    /**
     * 회원정보 수정(=비밀번호 변경)
     * @param id 회원 아이디 (uid 아님)
     * @param userRequest 변경할 정보 객체
     * ---
     * 변경해야할 요소에 대한 고민
     * 1. 현재 비밀번호를 확인 후, 이 api를 사용한다는 가정 -> 비밀번호 확인 api필요
     * 2. 변경할 비밀번호와 현재 비밀번호를 동시에 받기 -> 로직 수정 필요 + 현재비밀번호 미일치에 대한 예외 추가
     */
    @PutMapping("/members/{id}")
    public ResponseEntity<?> updatePw(@PathVariable("id") String id, @Valid @RequestBody UserRequest userRequest, BindingResult bindingResult){
        //todo - 추후 이부분 중복 유지시 메소드화 하기
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                String msg = objectError.getDefaultMessage();
                sb.append(msg);
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }

        userService.userUpdate(userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(StatusCode.USER_UPDATE_SUCCESS.getMessage());
    }

    // 이 경우 외부에서 유저 id와 api 링크만 알면 회원탈퇴가 가능. 조치가 필요함 -> access 토큰 조회
    @DeleteMapping("/members/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String id){
        userService.userDelete(id);
    }

}
