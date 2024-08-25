package com.mop.what2c.repository;

import com.mop.what2c.domain.MemberDTO;
import com.mop.what2c.domain.Member;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DBMemberRepository implements MemberRepository{

    private final EntityManager em;
    public DBMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(MemberDTO memberDTO) {
        Member member = new Member(memberDTO);
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findByMno(Long m_no) {
        Member member = em.find(Member.class, m_no);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findById(String id) {
        List<Member> result = em.createQuery("select m from Member m where m.id = :id", Member.class)
                .setParameter("id", id)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    @Override
    public Optional<Member> updateByMno(Long m_no, MemberDTO memberDTO) {
        Member member = em.find(Member.class, m_no);
        member.setId(memberDTO.getId());
        member.setPw(memberDTO.getPw());
        member.setEmail(memberDTO.getEmail());
        return Optional.ofNullable(member);
    }

    @Override
    public void deleteByMno(Long m_no) {
        Member member = em.find(Member.class, m_no);
        em.remove(member);
    }
}
