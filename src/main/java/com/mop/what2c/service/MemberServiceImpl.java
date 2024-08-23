package com.mop.what2c.service;

import com.mop.what2c.domain.MemberDTO;
import com.mop.what2c.domain.Member;
import com.mop.what2c.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;



@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    //@Autowired >> @RequiredArgsConstructor가 해줌
    private final MemberRepository memberRepository;

    @Override
    public Member join(MemberDTO memberDTO) {
        return memberRepository.save(memberDTO);
    }

    @Override
    public Optional<Member> findMemberByMno(Long m_no) {
        return memberRepository.findByMno(m_no);
    }

    @Override
    public Optional<Member> findMemberById(String id) {
        return memberRepository.findById(id);
    }

    @Override
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Optional<Member> changeMemberByMno(Long m_no, MemberDTO memberDTO) {
        return memberRepository.updateByMno(m_no, memberDTO);
    }

    @Override
    public void deleteMemberByMno(Long m_no) {
        memberRepository.deleteByMno(m_no);
    }

    @Override
    public Long tryLogin(String id, String pw) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if(optionalMember.isPresent()){
            Member member = optionalMember.get();
            if(Objects.equals(member.getPw(), pw)){
                return member.getM_no();
            }else {
                return 0L;
            }
        }else{
            return -1L;
        }
    }
}