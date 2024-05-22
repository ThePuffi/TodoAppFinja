package com.example.todobackend.Controller;


import com.example.todobackend.DTO.MemberDTO;
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
    public ResponseEntity<MemberDTO> addMember(@RequestBody MemberDTO member) {
        return new ResponseEntity<>(this.memberService.addMember(member), HttpStatus.CREATED);
    }
    @GetMapping("/getAllMembers")
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        return new ResponseEntity<>(this.memberService.getAllMembers(), HttpStatus.FOUND);
    }
    @GetMapping("/findMemberById")
    public ResponseEntity<MemberDTO> findMemberById(@RequestParam Long memberId) {
        return new ResponseEntity<>(this.memberService.findMemberById(memberId), HttpStatus.FOUND);
    }
    @PutMapping("/updateMember")
    public ResponseEntity<MemberDTO> updateMember(@RequestBody MemberDTO member) {
        return new ResponseEntity<>(this.memberService.updateMember(member), HttpStatus.OK);
    }

    @DeleteMapping("/deleteMember")
    public ResponseEntity<HttpStatus> deleteMember(@RequestBody MemberDTO member) {
        this.memberService.deleteMember(member);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}