package com.team.ourblog.service;

import com.team.ourblog.dto.request.comment.CommentRequestDto;
import com.team.ourblog.dto.request.posting.PostingRequestDto;
import com.team.ourblog.dto.response.comment.CommentResponseDto;
import com.team.ourblog.dto.response.comment.CommentUpdateDto;
import com.team.ourblog.dto.response.posting.PostingResponseDto;
import com.team.ourblog.entity.*;
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
    private PostingResponseDto responseDto;
    private CommentResponseDto responseDto1;

    private Member testMember;
    private Member testMember2;


    @BeforeEach
    void setUp(){
        testMember = createMember(2L,"test10@naver.com", "dltjgk19950322@","테스트십","닉네임십");
        testMember2 = createMember(3L,"test11@naver.com", "dltjgk19950322@","테스트십일","닉네임십일");

        Category category = createCategory("음식", testMember);

        responseDto = createPosting(String.valueOf(category.getId()),"테스트게시물","테스트내용","닉네임십");
    }


//    @Test
//    void getAllComments() {
//        createComment("댓글 첫번째");
//        createComment("댓글 두번째");
//
//        assertThat(commentService.getAllComments(responseDto.getPostId()).size()).isEqualTo(2);
//
//    }



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

    private Member createMember(Long id, String email, String password, String userName, String nickname) {
        Member member = new Member(id,email ,nickname,userName,password,Authority.ROLE_USER);
        Image image = new Image(1L,member,"src/asset/anonymous.png");
        member.setImage(image);
        return memberRepository.save(member);

    }

    private PostingResponseDto createPosting(String categoryId,String title, String content, String autor) {
        PostingRequestDto requestDto = new PostingRequestDto();
        requestDto.setCategoryId(categoryId);
        requestDto.setTitle(title);
        requestDto.setContent(content);
        requestDto.setNickname(autor);

        return postingService.createPosting(requestDto, testMember.getId());
    }

    private Category createCategory(String categoryName, Member member) {
        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setMember(member);
        return categoryRepository.save(category);
    }

    private CommentResponseDto createComment(String reply) {
        CommentRequestDto commentRequestDto = new CommentRequestDto();
        commentRequestDto.setReply(reply);
        return commentService.create(responseDto.getPostId(), testMember2.getId(),commentRequestDto);
    }

}