package team.closetalk.ootd.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.closetalk.ootd.dto.OotdArticleDto;
import team.closetalk.ootd.entity.OotdArticleEntity;
import team.closetalk.ootd.service.OotdService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ootd")
@RequiredArgsConstructor
public class OotdController {
    private final OotdService ootdService;

    @GetMapping("/main")
    public String mainPage() {
        return "ootd/main";
    }

     // 메인페이지 무한 스크롤 조회
    @GetMapping("/list")
    public String getOotd(
            Model model,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "12") Integer limit
    ) {
        Page<OotdArticleDto> ootdPage = ootdService.readOotdPaged(page, limit);
        model.addAttribute("ootdEntity", ootdPage.getContent());
        model.addAttribute("ootdPage", ootdPage);

        return "/ootd/main";
    }

}

//    @GetMapping("/list")
//    @ResponseBody
//    public Page<OotdArticleDto> getOotd(
//            Model model,
//            @RequestParam(value = "cursor", required = false) Long cursor,
//            @RequestParam(value = "limit", defaultValue = "12") Integer limit
//    ) {
//        Page<OotdArticleDto> ootdPage = ootdService.readOotdPagedWithCursor(cursor, limit);
//        model.addAttribute("ootdPage", ootdPage);
//        return ootdPage;
//    }

//    @GetMapping("/list")
//    public String getOotd(
//            Model model,
//            @RequestParam(value = "cursor", required = false) Long cursor,
//            @RequestParam(value = "limit", defaultValue = "20") Integer limit
//    ) {
//        Page<OotdArticleDto> ootdPage = ootdService.readOotdPagedWithCursor(cursor, limit);
//
//        model.addAttribute("ootdPage", ootdPage);
//
//        return "/ootd/main";
//    }
//test

// 피드 생성
//    @PostMapping("/post")
//    public OotdArticleDto createOotd(
//            @RequestBody OotdArticleDto ootdArticleDto
//    ) {
//        ootdService.createOotd(ootdArticleDto);
//        return ootdArticleDto;
//    }
// 피드 이미지 추가
//    @PutMapping("/{id}/image")
//    public void updateImage(
//            @PathVariable Long id,
//            @RequestParam(value = "image", required = false) MultipartFile articleImage
//    ) {
//        ootdService.uploadOotdImage(id, articleImage);
//    }