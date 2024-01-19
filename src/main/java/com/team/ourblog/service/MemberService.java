package com.team.ourblog.service;

import com.team.ourblog.common.MemberException;
import com.team.ourblog.common.ResourceNotFoundException;
import com.team.ourblog.dto.response.member.MemberInfoResponseDto;
import com.team.ourblog.dto.response.member.MemberPageResponseDto;
import com.team.ourblog.entity.Category;
import com.team.ourblog.entity.Image;
import com.team.ourblog.entity.Member;
import com.team.ourblog.repository.CategoryRepository;
import com.team.ourblog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    // 회원의 정보 조회 기능
    public MemberPageResponseDto userInfo(Long memberId){
        Member memberInfo = memberRepository.findById(memberId).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member Id", String.valueOf(memberId))
        );
        Image image = memberInfo.getImage();
        return MemberPageResponseDto.fromEntity(memberInfo,image);
    }


    // 회원의 카테고리와 닉네임 가져오는 기능
    public MemberInfoResponseDto findByIdWithCategoriesAndNickname(Long memberId) {
        Member memberInfo = memberRepository.findById(memberId).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member Id", String.valueOf(memberId))
        );
        List<Category> categories = memberInfo.getCategories();
        String nickname = memberInfo.getNickname();

        return MemberInfoResponseDto.fromEntity(categories, nickname);

    }
    // 이메일 중복체크
    public void findMemberInfoByEmail(String email){
        if (memberRepository.findByEmail(email).isPresent()){
            throw new MemberException("이미 사용 중인 이메일입니다.", HttpStatus.BAD_REQUEST);
        }
    }

    // 닉네임 중복체크
    public  void findMemberInfoByNickname(String nickname){
        if (memberRepository.findByNickname(nickname).isPresent()){
            throw new MemberException("이미 사용 중인 닉네임입니다.", HttpStatus.BAD_REQUEST);
        }

    }
    // 회원가입시 카테고리 4개 생성 기능
    public void createDefaultCategoriesOnJoin(Member saveMember) {
        if (saveMember.getCategories() == null) {
            saveMember.setCategories(new ArrayList<>());
        }
        for(int i = 1; i <= 4; i++ ){
            Category category = new Category();
            category.setMember(saveMember);
            category.setCategoryName("카테고리" + i);
            categoryRepository.save(category);
            saveMember.getCategories().add(category);

        }
    }
}