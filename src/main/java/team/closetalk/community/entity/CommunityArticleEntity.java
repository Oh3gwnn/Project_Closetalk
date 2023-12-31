package team.closetalk.community.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import team.closetalk.community.enumeration.Category;
import team.closetalk.user.entity.UserEntity;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@Entity
@Table(name = "community_article")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;    // 카테고리
    @Column(nullable = false)
    private String title;       // 제목
    @Column(nullable = false)
    private String content;     // 내용
    @ColumnDefault(value = "0")
    private Long hits;          // 조회수
    @ColumnDefault(value = "0")
    private Long likeCount;     // 좋아요 수
    private String thumbnail;   // 대표이미지
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    public CommunityArticleEntity(Category category, String title,
                                  String content, UserEntity user) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        this.userId = user;
        this.hits = 0L;
        this.likeCount = 0L;
    }

    // 조회수 증가
    public CommunityArticleEntity increaseHit() {
        this.hits = hits + 1;
        return this;
    }

    // 좋아요 수 증가
    public void increaseLikeCount() {
        this.likeCount = likeCount + 1;
    }

    // 좋아요 수 감소
    public void decreaseLikeCount() {
        this.likeCount = likeCount - 1;
    }

    // 게시글 삭제
    public CommunityArticleEntity deleteArticle() {
        this.deletedAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        return this;
    }

    // 썸네일 저장
    public CommunityArticleEntity saveThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }
}
