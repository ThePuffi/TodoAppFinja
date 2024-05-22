package com.example.todobackend.Controller;


import com.example.todobackend.Entity.Member;
import com.example.todobackend.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("api/member")
@CrossOrigin
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/addMember")
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        return new ResponseEntity<>(this.memberService.addMember(member), HttpStatus.CREATED);
    }
    @GetMapping("/getAllMembers")
    public ResponseEntity<List<Member>> getAllMembers() {
        return new ResponseEntity<>(this.memberService.getAllMembers(), HttpStatus.FOUND);
    }
    @GetMapping("/findMemberById")
    public ResponseEntity<Member> findMemberById(@RequestParam("MemberId") Long memberId) {
        return new ResponseEntity<>(this.memberService.getMember(memberId), HttpStatus.FOUND);
    }
    @PutMapping("/updateMember")
    public ResponseEntity<Member> updateMember(@RequestBody Member member) {
        return new ResponseEntity<>(this.memberService.editMember(member), HttpStatus.OK);
    }

    @DeleteMapping("/deleteMember")
    public ResponseEntity<HttpStatus> deleteMember(@RequestParam("MemberId") long memberId) {
        this.memberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}