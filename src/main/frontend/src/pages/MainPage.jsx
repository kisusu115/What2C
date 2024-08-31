import React, { useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const MainPage = () => {
  const navigate = useNavigate();

  useEffect(() => {
    onPageAvail();
  }, []);

  const onPageAvail = () => {
    const token = getCookie('Authorization');
    if(token){
      sessionStorage.setItem('accessToken', token);
      sessionStorage.setItem('accessTokenExpiresAt', new Date().getTime() + 86420000);
    }
  }

  const handleLogout = () => {
    // 세션 스토리지에서 인증 정보 제거
    sessionStorage.removeItem('accessToken');
    sessionStorage.removeItem('accessTokenExpiresAt');
    
    // 클라이언트 측에서 쿠키 삭제
    document.cookie = "Authorization=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    
    // 로그인 페이지로 리디렉션
    navigate('/signin');
  };

  const getCookie = (name) => {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
    return null;
  };

  const makeAuthenticatedRequest = async () => {
    const token = sessionStorage.getItem('accessToken');
    if (token) {
        try {
            const response = await axios.get('http://localhost:8080/user', {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            alert(response.data)
            console.log('인증된 요청 성공:', response.data);
        } catch (error) {
            console.error('인증된 요청 실패:', error);
        }
    } else {
        console.error('인증 토큰이 없습니다.');
    }
  };

  return (
    <div className="page">
      <div className="titleWrap">
        What2C
      </div>
      <div className="inputTitle">
        WhatToSee
        <button onClick={makeAuthenticatedRequest}>UsrInfo</button>
      </div>
      
      <div className="contentWrap">
        <div style={{ display: 'flex', justifyContent: 'space-between' }}>
          {/* 웹툰 Section */}
          <div style={{ flex: 1, marginRight: '20px' }}>
            <div className="inputTitle">웹툰</div>
            <div className="inputWrap" style={{ display: 'flex', flexDirection: 'row', flexWrap: 'wrap' }}>
              {Array.from({ length: 10 }, (_, index) => (
                <div key={index} style={{ margin: '8px', width: 'calc(100% / 10 - 16px)' }}>
                  <div style={{ fontSize: '14px', fontWeight: '600' }}>웹툰 제목 {index + 1}</div>
                  <div style={{ fontSize: '12px', color: '#999' }}>좋아요 {Math.floor(Math.random() * 100)}</div>
                </div>
              ))}
            </div>
          </div>

          {/* 소설 Section */}
          <div style={{ flex: 1, marginLeft: '20px' }}>
            <div className="inputTitle">소설</div>
            <div className="inputWrap" style={{ display: 'flex', flexDirection: 'row', flexWrap: 'wrap' }}>
              {Array.from({ length: 10 }, (_, index) => (
                <div key={index} style={{ margin: '8px', width: 'calc(100% / 10 - 16px)' }}>
                  <div style={{ fontSize: '14px', fontWeight: '600' }}>소설 제목 {index + 1}</div>
                  <div style={{ fontSize: '12px', color: '#999' }}>좋아요 {Math.floor(Math.random() * 100)}</div>
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>

      <div style={{ marginTop: '20px' }}>
        <div className="inputTitle">기타 기능</div>
        <div style={{ display: 'flex', justifyContent: 'space-around', marginTop: '10px' }}>
          <div className="inputWrap">출석체크</div>
          <div className="inputWrap">추첨</div>
          <div className="inputWrap">기타 기능 1</div>
          <div className="inputWrap">기타 기능 2</div>
        </div>
      </div>

      <footer style={{ marginTop: '20px', backgroundColor: '#e2e0e0', padding: '10px', textAlign: 'center', fontSize: '12px', color: '#262626' }}>
        footer
      </footer>
      
      {/* 로그아웃 버튼 */}
      <button 
        onClick={handleLogout} 
        style={{
          position: 'absolute',
          top: '10px',
          right: '10px',
          padding: '10px 20px',
          fontSize: '14px',
          backgroundColor: '#007bff',
          color: '#fff',
          border: 'none',
          borderRadius: '5px',
          cursor: 'pointer'
        }}
      >
        로그아웃
      </button>
    </div>
  );
};

export default MainPage;
