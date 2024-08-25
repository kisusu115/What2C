import React from 'react';

const MainPage = () => {
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
    </div>
  );
};

export default MainPage;
