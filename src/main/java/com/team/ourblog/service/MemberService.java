package com.team.ourblog.service;


import com.team.ourblog.common.MemberException;
import com.team.ourblog.common.ResourceNotFoundException;
import com.team.ourblog.dto.response.member.MemberInfoResponseDto;
import com.team.ourblog.entity.Category;
import com.team.ourblog.entity.Member;
import com.team.ourblog.repository.CategoryRepository;
import com.team.ourblog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
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


    public MemberInfoResponseDto findByIdWithCategoriesAndNickname(UserDetails userDetails) {
        Member memberInfo = memberRepository.findById(Long.valueOf(userDetails.getUsername())).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member Id", userDetails.getUsername())
        );
        List<Category> categories = memberInfo.getCategories();
        String nickname = memberInfo.getNickname();

        return MemberInfoResponseDto.fromEntity(categories, nickname);

    }

    public void findMemberInfoByEmail(String email){
        if (memberRepository.findByEmail(email).isPresent()){
            throw new MemberException("이미 사용 중인 이메일입니다.", HttpStatus.BAD_REQUEST);
        }
    }

    public  void findMemberInfoByNickname(String nickname){
        if (memberRepository.findByNickname(nickname).isPresent()){
            throw new MemberException("이미 사용 중인 닉네임입니다.", HttpStatus.BAD_REQUEST);
        }

    }


    public void createDefaultCategoriesOnJoin(Member saveMember) {
        if (saveMember.getCategories() == null) {
            saveMember.setCategories(new ArrayList<>());
        }
        for(int i = 1; i <= 4; i++ ){
            Category category = new Category();
            category.setMember(saveMember);
            category.setName("카테고리" + i);
            categoryRepository.save(category);
            saveMember.getCategories().add(category);

        }
    }


}