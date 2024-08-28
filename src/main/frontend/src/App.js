import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import SignUpPage from './pages/SignUpPage';
import MainPage from './pages/MainPage';

// accessToken 만료 여부 확인 함수
const isAccessTokenExpired = () => {
  const accessTokenExpiresAt = sessionStorage.getItem('accessTokenExpiresAt');
  if(new Date().getTime() <= accessTokenExpiresAt){
    sessionStorage.removeItem("accessToken");
    sessionStorage.removeItem("accessTokenExpiresAt");
    return true;
  }
  return !accessTokenExpiresAt;
};

// 기본 라우팅은 토큰 정보 여부에 따라 redirect
const DefaultRoute = ({ element }) => {
  return !isAccessTokenExpired() ? <Navigate to="/home" /> : <Navigate to="/signin" />;
};

// 로그인된 사용자가 접근할 수 없는 페이지를 위한 컴포넌트
const RedirectIfLoggedIn = ({ element }) => {
  return !isAccessTokenExpired() ? <Navigate to="/home" /> : element;
};

// 인증된 페이지를 렌더링하거나 리디렉션하는 컴포넌트
const ProtectedRoute = ({ element }) => {
  return isAccessTokenExpired() ? <Navigate to="/signin" /> : element;
};

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<DefaultRoute />} />
        <Route path="/signin" element={<RedirectIfLoggedIn element={<LoginPage />} />} />
        <Route path="/signup" element={<RedirectIfLoggedIn element={<SignUpPage />} />} />
        <Route path="/home" element={<ProtectedRoute element={<MainPage />} />} />
      </Routes>
    </Router>
  );
}

export default App;
