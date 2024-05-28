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

    /**
     * Überprüft, ob ein Member mit dem Username vorhanden ist
     * Wenn ja: Login erfolgreich
     * Wenn nein: Login nicht erfolgreich => Registrierung notwendig
     *
     * @param member
     * @return member or null
     */
    public Member login(Member member) {
        Optional<Member> memberOptional = memberRepository.findMemberByUsername(member.getUsername());
        if(memberOptional.isPresent() && member.getPassword().equals(memberOptional.get().getPassword())) {
            return memberOptional.get();
        }
        return null;
    }

    /**
     * Überprüft, ob ein Member mit dem Username vorhanden ist
     * Wenn ja: Registrierung nicht erfolgreich => Login notwendig
     * Wenn nein: Registrierung erfolgreich
     *
     * @param member
     * @return member or null
     */
    public Member registration(Member member) {
        Optional<Member> memberOptional = memberRepository.findMemberByUsername(member.getUsername());
        if(memberOptional.isPresent()) {
            return null;
        }
        return memberRepository.save(member);

    }

    /**
     * Überprüft, ob ein Member mit dem Username vorhanden ist
     * Wenn ja: Member Objekt aus der Datenbank wird mit dem mitgegebenen Member überschrieben
     * Wenn nein: Fehlerausgabe
     *
     * @param member
     * @return member
     */
    public Member editMember(Member member) {
        Optional<Member> memberOptional = memberRepository.findById(member.getId());
        if (memberOptional.isPresent()) {
            return memberRepository.save(member);
        }
        throw new IllegalArgumentException("Member does not exit");
    }

    /**
     * Überprüft, ob ein Member mit der Id vorhanden ist
     * Wenn ja: Ausgabe Member Objekt aus der Datenbank
     * Wenn nein: Fehlerausgabe
     *
     * @param id
     * @return member
     */
    public Member getMember(long id) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if (memberOptional.isPresent()) {
            return memberOptional.get();
        }
        throw new IllegalArgumentException("Member does not exit");
    }

    /**
     * Überprüft, ob ein Member mit der Id vorhanden ist
     * Wenn ja: Löscht Member aus Datenbank
     * Wenn nein: Fehlerausgabe
     *
     * @param id
     * @return member
     */
    public void deleteMember(long id) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if (memberOptional.isPresent()) {
            memberRepository.deleteById(id);
            return;
        }
        throw new IllegalArgumentException("Member does not exit");
    }

    /**
     * Rückgabe aller Member aus der Datenbank
     *
     * @return List<Member>
     */
    public List<Member> getAllMembers() {
        return  memberRepository.findAll();
    }
}
