package com.team.ourblog.service;

import com.team.ourblog.common.ResourceNotFoundException;
import com.team.ourblog.dto.request.posting.PostingRequestDto;
import com.team.ourblog.dto.response.posting.PostingResponseDto;
import com.team.ourblog.entity.Member;
import com.team.ourblog.entity.Posting;
import com.team.ourblog.repository.MemberRepository;
import com.team.ourblog.repository.PostingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class PostingService {
    private final PostingRepository postingRepository;
    private final MemberRepository memberRepository;

    public PostingResponseDto createPosting(PostingRequestDto requestDto, Member member){

        Posting posting = PostingRequestDto.ofEntity(requestDto);
        Member nickName = memberRepository.findByNickname(member.getUsername()).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member nickname", member.getUsername())
        );
        posting.setMappingMember(nickName);
        Posting savePosting =  postingRepository.save(posting);


        return PostingResponseDto.fromEntity(savePosting, member.getUsername());
    }

//    public List<ResBlogListDto> getBlogList(){
//        List<Blog> blogList = blogRepository.findAllDesc();
//        return blogList.stream()
//                .map(ResBlogListDto::fromEntity)
//                .collect(Collectors.toList());
//    }
}
