package com.example.springboard.controller;

import com.example.springboard.dto.UserInfoRespnse;
import com.example.springboard.dto.UserSignupRequest;
import com.example.springboard.dto.UserUpdateRequest;
import com.example.springboard.global.response.ResultCode;
import com.example.springboard.global.response.ResultResponse;
import com.example.springboard.service.UserService;
import com.example.springboard.dto.UserRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /* todo
     * 0501 피드백  - 수정 필요사항 (개선 후, 이 주석은 삭제)
     * 1. 일관적인 성공, 실패(에러) 메세지 전달할 것
     * 2. 로그인기능에서 user객체 null 체크 -> 비즈니스 로직 영역, null일 경우 return은 exception 처리로 가능
     * 3. 비즈니스 로직을 잘 구분해서 서비스로 옮길 것. controller에 있어서는 안된다.
     * 4. 프로세스를 플로우 차트 그리면서 정리 & 공유
     */


    /**
     * 로그인
     * @param userRequest id, pw가 포함된 객체
     * ----
     * 로그인 토큰 구현 후, 로그인 성공시 토큰 발급 추가 필요 -> [feature/token]
     */
    @PostMapping("/login")
    public ResponseEntity<ResultResponse> login(@Valid @RequestBody UserRequest userRequest){
        userService.login(userRequest);
        ResultResponse response = ResultResponse.of(ResultCode.LOGIN_SUCCESS, "");
        return new ResponseEntity<>(response, response.getStatus());
    }

    /**
     * 로그아웃
     * @param userRequest userid
     */
    @PostMapping("/logout")
    public ResponseEntity<ResultResponse> logout(@RequestBody UserRequest userRequest){
        //토큰 구현시 토큰제거 로직 추가 예정
        ResultResponse response = ResultResponse.of(ResultCode.LOGOUT_SUCCESS);
        return new ResponseEntity<>(response, response.getStatus());

    }

    /**
     * 아이디 중복 확인
     * @param id 회원가입하고 싶은 아이디
     */
    @GetMapping("/signup/{id}")
    public ResponseEntity<ResultResponse> checkLoginID(@PathVariable("id") @NotBlank String id) {
        userService.checkLoginID(id);
        ResultResponse response = ResultResponse.of(ResultCode.ID_USING_AVAIABLE, id);
        return new ResponseEntity<>(response, response.getStatus());
    }

    /**
     * 회원가입
     * @param userRequest : 회원 가입을 위한 객체, id pw nickname을 포함하고 있다
     */
    @PostMapping("/signup")
    public ResponseEntity<ResultResponse> signUp(@Valid @RequestBody UserSignupRequest userRequest){
        userService.signUp(userRequest);
        ResultResponse response = ResultResponse.of(ResultCode.SIGNUP_SUCCESS, userRequest.getUserId());
        return new ResponseEntity<>(response, response.getStatus());
    }

    /**
     * 회원정보 조회
     * @param id 조회할 유저 아이디
     */
    @GetMapping("/members/{id}")
    public ResponseEntity<ResultResponse> getUserDetail(@PathVariable("id") String id){
        UserInfoRespnse userInfo = new UserInfoRespnse(userService.getUserDetail(id));
        ResultResponse response = ResultResponse.of(ResultCode.SEARCH_MYINFO_SUCCESS, userInfo);
        return new ResponseEntity<>(response, response.getStatus());
    }

    /**
     * 회원정보 수정(=비밀번호 변경)
     * @param userRequest 현재 정보와 변경할 정보가 같이 있는 객체 (아이디, 현재 비밀번호, 변경할 비밀번호, 닉네임)
     * @return 비밀번호 변경 성공 메세지
     */
    @PutMapping("/members/{id}")
    public ResponseEntity<ResultResponse> updatePw(@Valid @RequestBody UserUpdateRequest userRequest){
        userService.userUpdate(userRequest);
        ResultResponse response = ResultResponse.of(ResultCode.CHANGE_PW_SUCCESS);
        return new ResponseEntity<>(response, response.getStatus());
    }

    /**
     * 회원 탈퇴
     * @param userRequest 회원 아이디, 비밀번호가 들어있는 객체
     * @return 탈퇴 성공 메세지
     */
    @DeleteMapping("/members/{id}")
    public ResponseEntity<ResultResponse> deleteUser(@Valid @RequestBody UserRequest userRequest){
        userService.userDelete(userRequest);
        ResultResponse response = ResultResponse.of(ResultCode.DELETE_USER_SUCCESS);
        return new ResponseEntity<>(response, response.getStatus());
    }

}
