package com.example.springboard.controller;

import com.example.springboard.dto.response.UserInfoRespnse;
import com.example.springboard.dto.request.UserSignupRequest;
import com.example.springboard.dto.request.UserUpdateRequest;
import com.example.springboard.global.auth.Token;
import com.example.springboard.global.auth.TokenProvider;
import com.example.springboard.global.response.ResultCode;
import com.example.springboard.global.response.ResultResponse;
import com.example.springboard.service.UserService;
import com.example.springboard.dto.request.UserRequest;
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


    /**
     * 로그인
     * @param userRequest id, pw가 포함된 객체
     * ----
     * 로그인 토큰 구현 후, 로그인 성공시 토큰 발급 추가 필요 -> [feature/token]
     */
    @PostMapping("/login")
    public ResponseEntity<ResultResponse<?>> login(@Valid @RequestBody UserRequest userRequest){
        Token token = userService.login(userRequest);
        ResultResponse<Token> response = ResultResponse.of(ResultCode.LOGIN_SUCCESS, token);
        return new ResponseEntity<>(response, response.getStatus());
    }

    /**
     * 로그아웃
     */
    @PostMapping("/logout")
    public ResponseEntity<ResultResponse<?>> logout(@RequestHeader(value = TokenProvider.ACCESS_PREFIX_STRING, required = false) String accessToken,
                                                 @RequestHeader(value = TokenProvider.REFRESH_HEADER_STRING, required = false) String refreshToken){
        userService.logout(accessToken,refreshToken);
        ResultResponse<?> response = ResultResponse.of(ResultCode.LOGOUT_SUCCESS);
        return new ResponseEntity<>(response, response.getStatus());
    }

    /**
     * 아이디 중복 확인
     * @param id 회원가입하고 싶은 아이디
     */
    @GetMapping("/signup/{id}")
    public ResponseEntity<ResultResponse<?>> checkLoginID(@PathVariable("id") @NotBlank String id) {
        userService.checkLoginID(id);
        ResultResponse<String> response = ResultResponse.of(ResultCode.ID_USING_AVAIABLE, id);
        return new ResponseEntity<>(response, response.getStatus());
    }

    /**
     * 회원가입
     * @param userRequest : 회원 가입을 위한 객체, id pw nickname을 포함하고 있다
     */
    @PostMapping("/signup")
    public ResponseEntity<ResultResponse<?>> signUp(@Valid @RequestBody UserSignupRequest userRequest){
        userService.signUp(userRequest);
        ResultResponse<String> response = ResultResponse.of(ResultCode.SIGNUP_SUCCESS, userRequest.getUserId());
        return new ResponseEntity<>(response, response.getStatus());
    }

    /**
     * 회원정보 조회
     * @param id 조회할 유저 아이디
     */
    @GetMapping("/members/{id}")
    public ResponseEntity<ResultResponse<?>> getUserDetail(@PathVariable("id") String id){
        UserInfoRespnse userInfo = new UserInfoRespnse(userService.getUserDetail(id));
        ResultResponse<UserInfoRespnse> response = ResultResponse.of(ResultCode.SEARCH_MYINFO_SUCCESS, userInfo);
        return new ResponseEntity<>(response, response.getStatus());
    }

    /**
     * 회원정보 수정(=비밀번호 변경)
     * @param userRequest 현재 정보와 변경할 정보가 같이 있는 객체 (아이디, 현재 비밀번호, 변경할 비밀번호, 닉네임)
     * @return 비밀번호 변경 성공 메세지
     */
    @PutMapping("/members/{id}")
    public ResponseEntity<ResultResponse<?>> updatePw(@Valid @RequestBody UserUpdateRequest userRequest){
        userService.userUpdate(userRequest);
        ResultResponse<?> response = ResultResponse.of(ResultCode.CHANGE_PW_SUCCESS);
        return new ResponseEntity<>(response, response.getStatus());
    }

    /**
     * 회원 탈퇴
     * @param userRequest 회원 아이디, 비밀번호가 들어있는 객체
     * @return 탈퇴 성공 메세지
     */
    @DeleteMapping("/members/{id}")
    public ResponseEntity<ResultResponse<?>> deleteUser(@Valid @RequestBody UserRequest userRequest){
        userService.userDelete(userRequest);
        ResultResponse<?> response = ResultResponse.of(ResultCode.DELETE_USER_SUCCESS);
        return new ResponseEntity<>(response, response.getStatus());
    }

}
