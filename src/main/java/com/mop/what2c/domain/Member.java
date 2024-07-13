package com.mop.what2c.domain;

public class Member {
    private Long m_no;
    private String id;
    private String pw;
    private String email;

    public Member(Long m_no, String id, String pw, String email){
        this.m_no = m_no;
        this.id = id;
        this.pw = pw;
        this.email = email;
    }

    public Long getM_no() {
        return m_no;
    }

    public void setM_no(Long m_no) {
        this.m_no = m_no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
