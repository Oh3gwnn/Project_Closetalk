package team.closetalk.ootd.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import team.closetalk.ootd.entity.OotdArticleEntity;
import team.closetalk.ootd.entity.OotdArticleImagesEntity;
import team.closetalk.user.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OotdArticleDto {
    private Long id;

    private String nickname;

    private String content;
    private String hashtag;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime createdAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime modifiedAt;

    // 게시물 대표 이미지
    private String thumbnail;

    // 게시물 전체 이미지
    private List<OotdArticleImagesDto> ootdImages;

    // 게시물 전체 조회
    public static OotdArticleDto readOotdAll(OotdArticleEntity entity) {
        OotdArticleDto dto = new OotdArticleDto();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setHashtag(entity.getHashtag());

        // 첫번째 이미지를 thumbnail 저장
        // service 단계에서 저장해주면 굳이 안써도됨
        List<OotdArticleImagesEntity> ootdImages = entity.getOotdImages();
        if (ootdImages != null && !ootdImages.isEmpty()) {
            dto.setThumbnail(ootdImages.stream()
                    .map(OotdArticleImagesDto::fromEntity)
                    .toList().get(0).getImageUrl());
        }
        System.out.println(dto.getThumbnail());

        return dto;
    }

    // 게시물 상세 조회
    public static OotdArticleDto readOotdOne(OotdArticleEntity entity) {
        OotdArticleDto dto = new OotdArticleDto();
        dto.setId(entity.getId());
        dto.setNickname(entity.getUserId().getNickname());
        dto.setContent(entity.getContent());
        dto.setHashtag(entity.getHashtag());
        // 이미지 전체 불러오기
        List<OotdArticleImagesEntity> ootdImages = entity.getOotdImages();
        if (ootdImages != null && !ootdImages.isEmpty()) {
            dto.setOotdImages(ootdImages.stream()
                    .map(OotdArticleImagesDto::fromEntity)
                    .collect(Collectors.toList()));
        }
        // 댓글 전체 불러오기

        return dto;
    }
}
