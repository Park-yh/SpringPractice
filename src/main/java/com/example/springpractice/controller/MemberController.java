package com.example.springpractice.controller;

import com.example.springpractice.dto.MemberRequestDto;
import com.example.springpractice.dto.MemberResponseDto;
import com.example.springpractice.entity.Member;
import com.example.springpractice.repository.MemberRepository;
import com.example.springpractice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<MemberResponseDto> createMember(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(memberService.save(memberRequestDto));
    }

    @GetMapping("/members")
    public ResponseEntity<List<MemberResponseDto>> getMembers(){
        return ResponseEntity.ok(memberService.findMembers());
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<MemberResponseDto> getMember(
            @PathVariable Long memberId
            ){
        return ResponseEntity.ok(memberService.findMember(memberId));
    }

    @PutMapping("/members/{memberId}")
    public ResponseEntity<MemberResponseDto> updateMember(
            @PathVariable Long memberId,
            @RequestBody MemberRequestDto memberRequestDto
    ){
        return ResponseEntity.ok(memberService.updateMember(memberId, memberRequestDto));
    }

    @DeleteMapping("/members/{memberId}")
    public void deleteMember(
            @PathVariable Long memberId
    ){
        memberService.deleteMember(memberId);
    }


}
