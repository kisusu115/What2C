import React from 'react';
import { useNavigate } from 'react-router-dom';

const MainPage = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    // 세션 스토리지에서 인증 정보 제거
    sessionStorage.removeItem('accessToken');
    sessionStorage.removeItem('accessTokenExpiresAt');
    
    // 로그인 페이지로 리디렉션
    navigate('/signin');
  };

  return (
    <div className="page">
      <div className="titleWrap">
        What2C
      </div>
      <div className="inputTitle">
        WhatToSee
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
