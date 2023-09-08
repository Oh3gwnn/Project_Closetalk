package team.closetalk.mypage.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.closetalk.mypage.service.MyArticleService;
import team.closetalk.ootd.dto.OotdArticleDto;
import team.closetalk.user.dto.CustomUserDetails;

@Slf4j
@RestController
@RequestMapping("/myarticles")
@RequiredArgsConstructor
public class MyArticleController {
    private final MyArticleService myArticleService;

    @GetMapping("/ootd")
    public Page<OotdArticleDto> readMyOotdArticlePage(Authentication authentication
            , @RequestParam(value = "page", defaultValue = "0") Integer page
            , @RequestParam(value = "limit", defaultValue = "12") Integer limit
    ){
        return myArticleService.readMyOotdArticlePage(CustomUserDetails.fromAuthentication(authentication), page, limit);
    }
}
