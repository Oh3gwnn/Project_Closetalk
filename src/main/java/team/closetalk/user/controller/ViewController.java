package team.closetalk.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.closetalk.user.dto.CustomUserDetails;
import team.closetalk.user.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ViewController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/loginPage")
    public String viewLoginForm(){
        return "user/login-form";
    }

    @GetMapping("/joinPage")
    public String viewJoinForm() {
        return "user/join-form";
    }

    @GetMapping("/test")
    public String testForm() {
        return "user/test";
    }

    // 로그아웃
    @PostMapping("/logout")
    public String logout(Authentication authentication) {
        CustomUserDetails responseUser = userService.loadUserByUsername(authentication.getName());
        userService.logout(responseUser);
        return "ootd/ootdMain";
    }

    // 아이디찾기 UI
    @GetMapping("/find/id")
    public String findUserIdForm() {
        return "user/findId-form";
    }

    // 아이디찾기
    @GetMapping("/findId")
    @ResponseBody
    public Map<String, String> findUserId( @RequestParam("email")String email) {
        String message = userService.findUserId(email);
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }

    // 비밀번호찾기 UI
    @GetMapping("/find/password")
    public String findPwForm() {
        return "user/findPw-form";
    }

    // 비밀번호찾기 (계정 확인)
    @GetMapping("/findPassword")
    @ResponseBody
    public Map<String, String> findPassword(@RequestParam("loginId")String loginId, @RequestParam("email")String email) {
        String message = userService.findPassword(loginId, email);
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }

    // 비밀번호찾기 (임시 비밀번호 발급)
    @PostMapping("/tempPassword")
    @ResponseBody
    public String sendTempPassword(String email) {
        return userService.sendTempPassword(email);
    }

    // 마이페이지 조회
    @GetMapping("/profile")
    public String userProfile() {
        return "mypage/myProfile";
    }

    // 유저 정보 불러오기
    @GetMapping("/profile/user")
    public String readUserProfile(Model model, Authentication authentication) {
        CustomUserDetails user = userService.loadUserByUsername(CustomUserDetails.fromAuthentication(authentication).getLoginId());
        model.addAttribute("user", user);

        return "mypage/myProfile";
    }

    // 비밀번호 변경
    @RequestMapping(value = "/profile/changePw", method = RequestMethod.PUT)
    public void updateUserOne(Authentication authentication, @RequestBody CustomUserDetails customUserDetails){
        customUserDetails.setLoginId(CustomUserDetails.fromAuthentication(authentication).getLoginId());
        customUserDetails.setPassword(passwordEncoder.encode(customUserDetails.getPassword()));
        userService.updateUser(customUserDetails);
    }
}