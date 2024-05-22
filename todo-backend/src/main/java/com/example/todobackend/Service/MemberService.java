package com.example.todobackend.Service;

import com.example.todobackend.DTO.MemberDTO;
import com.example.todobackend.DTO.TodoDTO;
import com.example.todobackend.Entity.Group;
import com.example.todobackend.Entity.Member;
import com.example.todobackend.Entity.Todo;
import com.example.todobackend.Mapper.MemberMapper;
import com.example.todobackend.Mapper.TodoMapper;
import com.example.todobackend.Repository.GroupRepository;
import com.example.todobackend.Repository.MemberRepository;
import com.example.todobackend.Repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final GroupRepository groupRepository;
    private final MemberMapper memberMapper;

    private final String doesNotExist = "Member does not exit";

    public MemberDTO addMember(Member member) {
        Optional<Member> memberOptional = memberRepository.findById(member.getId());
        Optional<Group> groupOptional = groupRepository.findById(member.getGroup().getId());
        if (memberOptional.isPresent() || groupOptional.isEmpty()) {
            throw new IllegalArgumentException("Member already exists");
        }
        return memberMapper.entityToDto(memberRepository.save(member));
    }

    public MemberDTO editMember(Member member) {
        Optional<Member> memberOptional = memberRepository.findById(member.getId());
        Optional<Group> groupOptional = groupRepository.findById(member.getGroup().getId());
        if (memberOptional.isPresent() && groupOptional.isPresent()) {
            return memberMapper.entityToDto(memberRepository.save(member));
        }
        throw new IllegalArgumentException(doesNotExist);
    }

    public MemberDTO getMember(long id) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if (memberOptional.isPresent()) {
            return memberMapper.entityToDto(memberOptional.get());
        }
        throw new IllegalArgumentException(doesNotExist);
    }

    public void deleteMember(long id) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if (memberOptional.isPresent()) {
            memberRepository.deleteById(id);
            return;
        }
        throw new IllegalArgumentException(doesNotExist);
    }

    public List<MemberDTO> getAllMembers() {
        List<MemberDTO> members = new ArrayList<>();
        List<Member> membersEntity = memberRepository.findAll();
        membersEntity.forEach(member -> {
            members.add(memberMapper.entityToDto(member));
        });
        return members;
    }

    // getAllMembersByGroup
}
