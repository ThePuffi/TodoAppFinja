package com.example.todobackend.Service;

import com.example.todobackend.Entity.Member;
import com.example.todobackend.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    public Member addMember(Member member) {
        Optional<Member> memberOptional = memberRepository.findById(member.getId());
        if (memberOptional.isPresent()) {
            throw new IllegalArgumentException("Member already exists");
        }
        return memberRepository.save(member);
    }

    public Member editMember(Member member) {
        Optional<Member> memberOptional = memberRepository.findById(member.getId());
        if (memberOptional.isPresent()) {
            return memberRepository.save(member);
        }
        throw new IllegalArgumentException("Member does not exit");
    }

    public Member getMember(long id) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if (memberOptional.isPresent()) {
            return memberOptional.get();
        }
        throw new IllegalArgumentException("Member does not exit");
    }

    public void deleteMember(long id) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if (memberOptional.isPresent()) {
            memberRepository.deleteById(id);
            return;
        }
        throw new IllegalArgumentException("Member does not exit");
    }

    public List<Member> getAllMembers() {
        return  memberRepository.findAll();
    }
}
