package com.example.springpractice.service;

import com.example.springpractice.dto.MemberRequestDto;
import com.example.springpractice.dto.MemberResponseDto;
import com.example.springpractice.entity.Member;
import com.example.springpractice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto save(MemberRequestDto memberRequestDto){
        Member member = new Member(memberRequestDto.getName());
        Member savedMember = memberRepository.save(member);
        return new MemberResponseDto(
                savedMember.getId(),
                savedMember.getName()
        );
    }

    @Transactional (readOnly = true)
    public List<MemberResponseDto> findMembers() {
        List<Member> members = memberRepository.findAll();

        List<MemberResponseDto> dtos = new ArrayList<>();
        for(Member member : members) {
            dtos.add(new MemberResponseDto(
                    member.getId(),
                    member.getName()
            ));
        }
        return dtos;
    }

    @Transactional (readOnly = true)
    public MemberResponseDto findMember(Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("Member Id Not Found")
        );
        return new MemberResponseDto(
                member.getId(),
                member.getName()
        );
    }

    @Transactional
    public MemberResponseDto updateMember(Long memberId, MemberRequestDto memberRequestDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("Member Id Not Found")
        );
        member.updateName(memberRequestDto.getName());
        return new MemberResponseDto(
                member.getId(),
                member.getName()
        );
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("Member Id Not Found")
        );
        memberRepository.deleteById(memberId);
    }
}
