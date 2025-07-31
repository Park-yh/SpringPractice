package com.example.springpractice.controller;

import com.example.springpractice.dto.MemberRequestDto;
import com.example.springpractice.dto.MemberResponseDto;
import com.example.springpractice.entity.Member;
import com.example.springpractice.repository.MemberRepository;
import com.example.springpractice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @PostMapping("/members")
    public MemberResponseDto createMember(@RequestBody MemberRequestDto memberRequestDto) {
        return memberService.save(memberRequestDto);
    }

    @GetMapping("/members")
    public List<MemberResponseDto> getMembers(){
        return memberService.findMembers();
    }

    @GetMapping("/members/{memberId}")
    public MemberResponseDto getMember(
            @PathVariable Long memberId
            ){
        return memberService.findMember(memberId);
    }

    @PutMapping("/members/{memberId}")
    public MemberResponseDto updateMember(
            @PathVariable Long memberId,
            @RequestBody MemberRequestDto memberRequestDto
    ){
        return memberService.updateMember(memberId, memberRequestDto);
    }

    @DeleteMapping("/members/{memberId}")
    public void deleteMember(
            @PathVariable Long memberId
    ){
        memberService.deleteMember(memberId);
    }


}
