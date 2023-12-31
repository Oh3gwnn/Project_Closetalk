package team.closetalk.user.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import team.closetalk.community.enumeration.StringToCategoryConverter;
import team.closetalk.user.service.CustomOAuth2UserService;
import team.closetalk.user.utils.JwtFilter;
import team.closetalk.user.utils.OAuth2SuccessHandler;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtFilter jwtFilter;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer :: disable)
                .authorizeHttpRequests(
                        authHttp -> authHttp
                                .requestMatchers(
                                        "/joinPage"
                                        , "/users/sendEmail"
                                        , "/users/register"
                                        , "/users/login"
                                        , "/users/check-id"
                                        , "/users/check-nickname"
                                        , "/ootd/main"
                                        , "/ootd/header"
                                        , "/ootd/list"
                                        , "/ootd/banner"
                                        , "/ootd/{articleId}"
                                        , "/ootd/{articleId}/comment"
                                        , "/ootd/{articleId}/writePage"
                                        , "/ootd/{articleId}/editPage"
                                        , "/ootd/rest/**"
                                        , "/static/**"
                                        , "/loginPage"
                                        , "/issue"
                                        , "/issue/{id}"
                                        , "/community/**"
                                        , "/ootd/rest/list"
                                        , "/closet/view/item"
                                        , "/closet/view/**"
                                        , "/issue/view/list"
                                        , "/issue/view/{articleId}"
                                        , "/mypage/liked/community"
                                        , "/mypage/liked/ootd"
                                        , "/find/id"
                                        , "/findId"
                                        , "/find/password"
                                        , "/findPassword"
                                        , "/tempPassword"
                                        , "/profile"
                                        ,"/profile/changePw"
                                ).permitAll()
                                .anyRequest()
                                .authenticated()
                )
                //소셜로그인 추가
                .oauth2Login( oauthHttp -> oauthHttp.loginPage("/loginPage")
                        .successHandler(oAuth2SuccessHandler)
                        .failureUrl("/loginPage")
                        .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
                        .permitAll()
                )
                .sessionManagement(
                        sessionManagement -> sessionManagement.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                ).addFilterBefore(
                        jwtFilter
                        , AuthorizationFilter.class
                );

        return http.build();
    }
}
