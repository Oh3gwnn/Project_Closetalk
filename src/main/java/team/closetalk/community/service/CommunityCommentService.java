package team.closetalk.community.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import team.closetalk.closet.service.EntityRetrievalService;
import team.closetalk.community.dto.CommunityCommentListDto;
import team.closetalk.community.dto.CommunityCommentReplyDto;
import team.closetalk.community.entity.CommunityArticleEntity;
import team.closetalk.community.entity.CommunityCommentEntity;
import team.closetalk.community.repository.CommunityArticleRepository;
import team.closetalk.community.repository.CommunityCommentRepository;
import team.closetalk.user.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommunityCommentService {
    private final CommunityArticleRepository communityArticleRepository;
    private final CommunityCommentRepository communityCommentRepository;
    private final EntityRetrievalService entityRetrievalService;

    public List<CommunityCommentListDto> readCommentList(Long articleId) {
        // 해당 게시물의 모든 댓글 불러오기(CommentId == null -> 대댓글이 아님)
        List<CommunityCommentEntity> commentEntities =
                communityCommentRepository.findAllByCommunityArticle_IdAndCommentId(articleId, null);

        // commentList -> 반환을 위한 전체 댓글 목록 생성
        List<CommunityCommentListDto> commentList = new ArrayList<>();
        // 해당 게시물의 모든 댓글 중 대댓글을 찾는 반복문
        for (CommunityCommentEntity commentEntity : commentEntities) {
            // commentEntities -> 이미 해당 게시물인 것을 확인
            // commentEntity.getId() -> 대댓글이 없으면 빈 리스트, 있으면 대댓글 리스트가 완성
            List<CommunityCommentEntity> replies =
                    communityCommentRepository.findAllByCommentId_Id(commentEntity.getId());

            // 대댓글의 Entity -> Dto 변환을 위한 과정
            List<CommunityCommentReplyDto> replyList = new ArrayList<>();
            for (CommunityCommentEntity replyEntity : replies) {
                CommunityCommentReplyDto replyDto = CommunityCommentReplyDto.toReplyDto(replyEntity);
                replyList.add(replyDto);
            }
            // (해당 댓글, 해당 댓글의 대댓글 Dto 리스트)를 처음에 반환을 위해 만들었던 전체 댓글 목록에 추가
            commentList.add(CommunityCommentListDto.toCommentDto(commentEntity, replyList));
        }
        log.info("[{}]번 게시물의 댓글 목록 조회", articleId);
        return commentList;
    }

    // 1. 댓글 생성
    public void createComment(Long articleId, String content,
                              Authentication authentication) {
        CommunityArticleEntity article = getArticle(articleId);
        UserEntity user = getUserEntity(authentication.getName());
        communityCommentRepository.save(new CommunityCommentEntity(content, user, article, null));
        log.info("게시물 [{}] 댓글 등록 완료", article.getTitle());
    }

    // 1-1. 리플 생성
    public void createReply(Long articleId, Long commentId,
                            String content,
                            Authentication authentication) {
        CommunityArticleEntity article = getArticle(articleId);
        CommunityCommentEntity comment = getByComment(commentId);
        UserEntity user = getUserEntity(authentication.getName());
        communityCommentRepository.save(new CommunityCommentEntity(content, user, article, comment));
        log.info("게시물 [{}]의 댓글 [{}]에 댓글 등록 완료", article.getTitle(), commentId);
    }

    // 2. 댓글 수정
    public void updateComment(Long articleId, Long commentId, Authentication authentication) {
    }

    // 3. 댓글 삭제
    public void deleteComment(Long articleId, Long commentId, Authentication authentication) {
        // 게시글 찾기
        Optional<CommunityArticleEntity> optionalCommunity = communityArticleRepository.findById(articleId);
        if (optionalCommunity.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // 댓글 찾기
        Optional<CommunityCommentEntity> optionalCommunityComment = communityCommentRepository.findById(commentId);
        if (optionalCommunityComment.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // 삭제
        communityCommentRepository.deleteById(commentId);
    }

    // 대댓글 삭제 -- 수정필요
    public void deleteReComment(Long articleId, Long commentId, Long reCommentId) {
        // 게시글 찾기
        Optional<CommunityArticleEntity> optionalCommunity = communityArticleRepository.findById(articleId);
        if (optionalCommunity.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // 댓글 찾기
        Optional<CommunityCommentEntity> optionalCommunityComment = communityCommentRepository.findById(commentId);
        if (optionalCommunityComment.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // 대댓글 찾기
        Optional<CommunityCommentEntity> optionalCommunityReComment = communityCommentRepository.findById(reCommentId);
        if (optionalCommunityReComment.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // 삭제
        communityCommentRepository.deleteById(reCommentId);
    }
    // LoginId == authentication.getName() 사용자 찾기
    private UserEntity getUserEntity(String LoginId) {
        return entityRetrievalService.getUserEntity(LoginId);
    }

    // article 찾기
    private CommunityArticleEntity getArticle(Long articleId) {
        return communityArticleRepository.findById(articleId)
                .orElseThrow(() -> {
                    log.error("{}번 게시물을 찾을 수 없음", articleId);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND);
                });
    }

    // comment 찾기
    private CommunityCommentEntity getByComment(Long commentId) {
        return communityCommentRepository.findById(commentId)
                .orElseThrow(() -> {
                    log.error("해당 댓글 찾을 수 없음");
                    return new ResponseStatusException(HttpStatus.NOT_FOUND);
                });
    }
}
