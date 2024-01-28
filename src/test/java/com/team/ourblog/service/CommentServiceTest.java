package com.team.ourblog.service;

import com.team.ourblog.dto.request.comment.CommentRequestDto;
import com.team.ourblog.dto.request.posting.PostingRequestDto;
import com.team.ourblog.dto.response.comment.CommentResponseDto;
import com.team.ourblog.dto.response.comment.CommentUpdateDto;
import com.team.ourblog.dto.response.posting.PostingResponseDto;
import com.team.ourblog.entity.Authority;
import com.team.ourblog.entity.Category;
import com.team.ourblog.entity.Comment;
import com.team.ourblog.entity.Member;
import com.team.ourblog.repository.CategoryRepository;
import com.team.ourblog.repository.CommentRepository;
import com.team.ourblog.repository.MemberRepository;
import com.team.ourblog.repository.PostingRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class CommentServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    PostingRepository postingRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostingService postingService;
    @Autowired
    CommentService commentService;

    private PostingResponseDto dto;
    private Member testMember;
    private Member testMember2;


    @BeforeEach
    void setUp(){
        testMember = createMember("test10@naver.com", "dltjgk19950322@","테스트십","닉네임십");
        testMember2 = createMember("test11@naver.com", "dltjgk19950322@","테스트십일","닉네임십일");

        Category category = createCategory("음식", testMember);

        PostingRequestDto requestDto = new PostingRequestDto();
        requestDto.setTitle("테스트제목");
        requestDto.setContent("테스트내용");
        requestDto.setNickName("닉네임십");
        requestDto.setCategoryId(category.getId());

        dto = postingService.createPosting(requestDto, testMember.getId());
    }

    @Test
    void getAllComments() {
        createComment("댓글 첫번째");
        createComment("댓글 두번째");

        assertThat(commentService.getAllComments(dto.getPostId()).size()).isEqualTo(2);

    }



    @Test
    void create() {

        CommentResponseDto commentResponseDto = createComment("댓글 첫번째");

        assertThat(commentResponseDto.getAuthor()).isEqualTo("닉네임십일");
    }

    @Test
    void delete() {

        CommentResponseDto commentResponseDto = createComment("댓글 첫번째");

        commentService.delete(commentResponseDto.getCommentId());

        Optional<Comment> id = commentRepository.findById(commentResponseDto.getCommentId());
        assertThat(id).isEmpty();
    }

    @Test
    void update() {

        CommentResponseDto commentResponseDto = createComment("댓글 첫번째");

        assertThat(commentResponseDto.getReply()).isEqualTo("댓글 첫번째");

        CommentRequestDto commentRequestDto = new CommentRequestDto();
        commentRequestDto.setReply("댓글 업데이트");

        CommentUpdateDto updateDto = commentService.update(commentResponseDto.getCommentId(), commentRequestDto);

        assertThat(updateDto.getReply()).isEqualTo("댓글 업데이트");

    }

    private Category createCategory(String categoryName, Member member) {
        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setMember(member);
        return categoryRepository.save(category);
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

    private CommentResponseDto createComment(String reply) {
        CommentRequestDto commentRequestDto = new CommentRequestDto();
        commentRequestDto.setReply(reply);
        return commentService.create(dto.getPostId(), testMember2.getId(),commentRequestDto);
    }

}