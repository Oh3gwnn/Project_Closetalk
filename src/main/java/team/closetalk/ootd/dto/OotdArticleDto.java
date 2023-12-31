package team.closetalk.ootd.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import team.closetalk.ootd.entity.OotdArticleEntity;
import team.closetalk.ootd.entity.OotdArticleImagesEntity;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OotdArticleDto {
    private Long id;

    private String nickname;
    private String profile;

    private String content;
    private String hashtag;

    // 좋아요
    public Long likes;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String createdAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String modifiedAt;

    private Boolean isDeleted;

    // 게시물 대표 이미지
    private String thumbnail;

    // 게시물 전체 이미지
    private List<OotdArticleImagesDto> ootdImages;




    //메소드 네이밍 수정할 것.

    // 게시물 전체 조회
    public static OotdArticleDto fromEntityForList(OotdArticleEntity entity) {
        OotdArticleDto dto = new OotdArticleDto();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setHashtag(entity.getHashtag());
        dto.setNickname(entity.getUserEntity().getNickname());
        dto.setThumbnail(entity.getThumbnail());

        return dto;
    }

    // 게시물 상세 조회
    public static OotdArticleDto fromEntity(OotdArticleEntity entity) {
        OotdArticleDto dto = new OotdArticleDto();
        dto.setId(entity.getId());
        dto.setNickname(entity.getUserEntity().getNickname());
        dto.setProfile(entity.getUserEntity().getProfileImageUrl());
        dto.setContent(entity.getContent());
        dto.setHashtag(entity.getHashtag());
        dto.setCreatedAt(entity.getCreatedAt()
                .format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
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

    public OotdArticleEntity newEntity() {
        OotdArticleEntity entity = new OotdArticleEntity();
        entity.setContent(content);
        entity.setHashtag(hashtag);
        entity.setThumbnail(thumbnail);

        return entity;
    }

    // 좋아요 수 조회
    public void setLikeCount(Long likeCount) {
        this.likes = likeCount;
    }
}
