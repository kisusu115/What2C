package com.mop.what2c.repository;

//@Component
public class DBMemberRepository { //implements MemberRepository{

    /* JpaRepository 사용으로 인한 미사용 주석처리
    
    private final EntityManager em;
    public DBMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(MemberDto memberDTO) {
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
        List<Member> result = em.createQuery("select m from members m where m.id = :id", Member.class)
                .setParameter("id", id)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from members m", Member.class).getResultList();
    }

    @Override
    public Optional<Member> updateByMno(Long m_no, MemberDto memberDTO) {
        Member member = em.find(Member.class, m_no);
        member.setId(memberDTO.getId());
        member.setPassword(memberDTO.getPw());
        member.setEmail(memberDTO.getEmail());
        return Optional.ofNullable(member);
    }

    @Override
    public void deleteByMno(Long m_no) {
        Member member = em.find(Member.class, m_no);
        em.remove(member);
    }
    */
}
