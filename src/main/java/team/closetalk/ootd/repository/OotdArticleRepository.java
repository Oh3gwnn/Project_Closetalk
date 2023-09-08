package team.closetalk.ootd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import team.closetalk.ootd.entity.OotdArticleEntity;
import team.closetalk.user.entity.UserEntity;

public interface OotdArticleRepository extends JpaRepository<OotdArticleEntity, Long> {
    Page<OotdArticleEntity> findAllByUserEntity(Pageable pageable, UserEntity userEntity);
    Page<OotdArticleEntity> findAllByHashtagContaining(Pageable pageable, String keyword);
    Page<OotdArticleEntity> findAllByContentContaining(Pageable pageable, String keyword);
}
