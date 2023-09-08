package team.closetalk.mypage.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team.closetalk.mypage.repository.MyArticleRepository;
import team.closetalk.ootd.dto.OotdArticleDto;
import team.closetalk.ootd.entity.OotdArticleEntity;
import team.closetalk.ootd.repository.OotdArticleRepository;
import team.closetalk.user.dto.CustomUserDetails;
import team.closetalk.user.entity.UserEntity;
import team.closetalk.user.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyArticleService {
    private final UserRepository userRepository;
    private final OotdArticleRepository ootdArticleRepository;

    public Page<OotdArticleDto> readMyOotdArticlePage(CustomUserDetails customUserDetails, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("id").descending());

        UserEntity userEntity = userRepository.findByLoginId(customUserDetails.getLoginId()).get();
        Page<OotdArticleEntity> ootdEntityPage = ootdArticleRepository.findAllByUserEntity(pageable, userEntity);
        return ootdEntityPage.map(OotdArticleDto :: fromEntityForList);
    }
}
