package com.team.ourblog.service;

import com.team.ourblog.dto.request.posting.PostingRequestDto;
import com.team.ourblog.dto.request.posting.PostingUpdateDto;
import com.team.ourblog.dto.response.posting.DetailResponseDto;
import com.team.ourblog.dto.response.posting.PostingListResponseDto;
import com.team.ourblog.dto.response.posting.PostingResponseDto;
import com.team.ourblog.entity.Authority;
import com.team.ourblog.entity.Category;
import com.team.ourblog.entity.Member;
import com.team.ourblog.repository.CategoryRepository;
import com.team.ourblog.repository.MemberRepository;
import com.team.ourblog.repository.PostingRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class PostingServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PostingRepository postingRepository;

    @Autowired
    PostingService postingService;

    @Autowired
    CategoryRepository categoryRepository;
    private Member testMember;

    @Test
    void createPosting() {
        testMember = createMember("test10@naver.com", "dltjgk19950322@","테스트십","닉네임십");

        Category category = createCategory("음식", testMember);

        PostingResponseDto responseDto = createPosting("테스트제목", "테스트내용", "닉네임십", category.getId());

        assertThat(responseDto.getWriter()).isEqualTo("닉네임십");
    }

    @Test
    void getPostingDetail() {
        testMember = createMember("test10@naver.com", "dltjgk19950322@","테스트십","닉네임십");

        Category category = createCategory("음식", testMember);

        PostingResponseDto responseDto = createPosting("테스트제목", "테스트내용", testMember.getNickname(), category.getId());

        List<DetailResponseDto> postingDetails = postingService.getPostingDetail(responseDto.getPostId());

        for(DetailResponseDto postingDetail : postingDetails){
            assertThat(postingDetail.getTitle()).isEqualTo("테스트제목");
            assertThat(postingDetail.getContent()).isEqualTo("테스트내용");
            assertThat(postingDetail.getWriter()).isEqualTo("닉네임십");
        }
    }
    @Test
    void update() {

        testMember = createMember("test10@naver.com", "dltjgk19950322@","테스트십","닉네임십");

        Category category = createCategory("음식", testMember);

        PostingResponseDto responseDto = createPosting("테스트제목", "테스트내용", testMember.getNickname(), category.getId());

        assertThat(responseDto.getWriter()).isEqualTo("닉네임십");

        PostingUpdateDto updateDto = new PostingUpdateDto();
        updateDto.setTitle("수정한 제목");
        updateDto.setContent("수정한 내용");

        List<DetailResponseDto> detailResponseDtos = postingService.update(responseDto.getPostId(), updateDto);

        for(DetailResponseDto dto1 : detailResponseDtos){
            assertThat(dto1.getTitle()).isEqualTo("수정한 제목");
            assertThat(dto1.getContent()).isEqualTo("수정한 내용");
        }
    }

    @Test
    void delete() {

        testMember = createMember("test10@naver.com", "dltjgk19950322@","테스트십","닉네임십");

        Category category = createCategory("음식", testMember);

        PostingResponseDto responseDto = createPosting("테스트제목", "테스트내용", testMember.getNickname(), category.getId());

        postingService.delete(responseDto.getPostId());


        assertThat(postingRepository.findById(responseDto.getPostId()).isEmpty());

    }

    @Test
    void getPostingList() {

        testMember = createMember("test10@naver.com", "dltjgk19950322@","테스트십","닉네임십");
        Member testMember2 = createMember("test11@naver.com", "dltjgk19950322@","테스트십일","닉네임십일");

        Category category = createCategory("음식", testMember);
        createPosting("테스트제목", "테스트내용", testMember.getNickname(), category.getId());
        createPosting("테스트제목2","테스트내용2",testMember2.getNickname(), category.getId());

        List<PostingListResponseDto> postingList =  postingService.getPostingList();

        assertThat(postingList.size()).isEqualTo(2);
    }

    @Test
    void getUserPostingList() {
        testMember = createMember("test10@naver.com", "dltjgk19950322@","테스트십","닉네임십");

        Category category = createCategory("음식", testMember);
        createPosting("테스트제목", "테스트내용", testMember.getNickname(), category.getId());
        createPosting("테스트제목2","테스트내용2", testMember.getNickname(), category.getId());

        List<PostingListResponseDto> userPostingList  = postingService.getUserPostingList(testMember.getId());
        assertThat(userPostingList.size()).isEqualTo(2);
    }

    @Test
    void getPostingListCategory() {

        testMember = createMember("test10@naver.com", "dltjgk19950322@","테스트십","닉네임십");

        Category category = createCategory("음식", testMember);

        createPosting("테스트제목", "테스트내용", testMember.getNickname(), category.getId());


        List<PostingListResponseDto> postingList = postingService.getPostingListCategory(category.getId());
        for (PostingListResponseDto responseDto : postingList){
            assertThat(responseDto.getWriter()).isEqualTo("닉네임십");
            assertThat(responseDto.getTitle()).isEqualTo("테스트제목");
            assertThat(responseDto.getContent()).isEqualTo("테스트내용");
        }
    }

    private Member createMember(String email, String password, String userName, String nickname) {
        Member member = new Member();
        member.setEmail(email);
        member.setPassword(password);
        member.setName(userName);
        member.setNickname(nickname);
        member.setAuthority(Authority.ROLE_USER);
        return memberRepository.save(member);

    }
    private Category createCategory(String categoryName, Member member) {
        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setMember(member);
        return categoryRepository.save(category);
    }
    private PostingResponseDto createPosting(String title, String content, String autor, Long categoryId) {
        PostingRequestDto requestDto = new PostingRequestDto();
        requestDto.setTitle(title);
        requestDto.setContent(content);
        requestDto.setNickName(autor);
        requestDto.setCategoryId(categoryId);
        return postingService.createPosting(requestDto, testMember.getId());
    }
}