package com.example.todobackend.Controller;


import com.example.todobackend.Entity.Member;
import com.example.todobackend.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<Member> login(@RequestBody Member member) {
        Member loggedInMember = memberService.login(member);
        if (loggedInMember != null) {
            return new ResponseEntity<>(loggedInMember, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/registrate")
    public ResponseEntity<Member> registrate(@RequestBody Member member) {
        Member registeredMember = memberService.registration(member);
        if (registeredMember != null) {
            return new ResponseEntity<>(registeredMember, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
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