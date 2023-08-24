package team.closetalk.community.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import team.closetalk.community.dto.CommunityArticleDto;
import team.closetalk.community.entity.CommunityArticleEntity;
import team.closetalk.community.entity.CommunityCommentEntity;
import team.closetalk.community.repository.CommunityArticleRepository;
import team.closetalk.community.repository.CommunityCommentRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CommunityArticleService {
    private final CommunityArticleRepository communityArticleRepository;
    private final CommunityCommentRepository communityCommentRepository;

    // READ
    // 페이지 단위로 조회
    public Page<CommunityArticleDto> readCommunityPaged(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(
                pageNum, pageSize, Sort.by("id").ascending());
        Page<CommunityArticleEntity> communityEntityPage = communityArticleRepository.findAll(pageable);
        return communityEntityPage.map(CommunityArticleDto::fromEntity);
    }

    // 상세 페이지 조회
    public CommunityArticleDto readArticleOne(Long id) {
        Optional<CommunityArticleEntity> optionalArticle = communityArticleRepository.findById(id);

        if (optionalArticle.isPresent()) {
            return CommunityArticleDto.fromEntity2(optionalArticle.get());
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // DELETE
    // 게시글 삭제
    public void deleteArticle(Long articleId) {
        // 게시글 찾기
        Optional<CommunityArticleEntity> optionalCommunity = communityArticleRepository.findById(articleId);
        if (optionalCommunity.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // 삭제
        communityArticleRepository.deleteById(articleId);
    }

    // 댓글 삭제
    public void deleteComment(Long articleId, Long commentId) {
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
}