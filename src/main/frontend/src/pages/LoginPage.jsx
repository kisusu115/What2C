import React, {useEffect, useState} from "react";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import googleLogo from '../pngs/google_logo.png';
import kakaoLogo from '../pngs/kakao_logo.png';
import naverLogo from '../pngs/naver_logo.png';

export default function LoginPage(){
    const [id, setId] = useState('');
    const [pw, setPw] = useState('');
    const navigate = useNavigate();

    const handleId = (e) => {
        const idValue = e.target.value;
        setId(idValue);
    }

    const handlePw = (e) => {
        const pwValue = e.target.value;
        setPw(pwValue);
    }

    const functionName = () => {
        
    }

    const login = async () => {
        try {
            const urlEncodedData = new URLSearchParams();
            urlEncodedData.append('username', id);
            urlEncodedData.append('password', pw);

            // axios를 사용하여 POST 요청 전송
            const response = await axios.post('http://localhost:8080/sign-in', urlEncodedData.toString(), {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            });

            if (response.status === 200) {
                const authInfo = response.headers['authorization'].split(' ');
                const grantType = authInfo[0];
                const token = authInfo[1];
                
                if (token) {
                    // 토큰을 sessionStorage에 저장
                    sessionStorage.setItem('accessToken', token);
                    sessionStorage.setItem('accessTokenExpiresAt', new Date().getTime() + 86420000);
            
                    // 성공적으로 인증된 요청
                    console.log('인증된 요청 성공:', response.data);
                    alert(`로그인 성공!\ngrantType: ${grantType}\naccessToken: ${token}`);
                    navigate('/home');
                } else {
                    alert('Authorization 헤더가 없습니다.');
                    console.error('Authorization 헤더가 없습니다.');
                }
            } else if (response.status === 401) {
                alert("로그인 실패: 인증 오류");
            }
        } catch (error) {
            console.error("로그인 실패", error);
            alert("로그인에 실패했습니다. 서버에 문제가 있나봐요.");
        }
    }

    const onNaverLogin = () => {
        window.location.href = "http://localhost:8080/oauth2/authorization/naver";
        
        sessionStorage.setItem('accessToken', "tmptoken");
        sessionStorage.setItem('accessTokenExpiresAt', new Date().getTime() + 86420000);
    }

    const onGoogleLogin = () => {
        window.location.href = "http://localhost:8080/oauth2/authorization/google";
        
        sessionStorage.setItem('accessToken', "tmptoken");
        sessionStorage.setItem('accessTokenExpiresAt', new Date().getTime() + 86420000);
    }

    const signup = () => {
        navigate('/signup');
    }

    useEffect(()=>{

    }, []);

    return (
        <div className="page">
            <div className="titleWrap">
                로그인
            </div>
            <div className="contentWrap">
            <div className="inputTitle">아이디</div>
                <div className="inputWrap">
                    <input 
                        type='text'
                        className="input"
                        placeholder="아이디를 입력해주세요."
                        value={id}
                        onChange={handleId}
                    />
                </div>

                <div style={{marginTop:"26px"}} className="inputTitle">비밀번호</div>
                <div className="inputWrap">
                    <input 
                        type='password'
                        className="input"
                        placeholder="비밀번호를 입력해주세요."
                        value={pw}
                        onChange={handlePw} 
                    />
                </div>
                <div>
                    <button onClick = {login} className="loginButton">
                        로그인
                    </button>
                    <div className="signupTextContainer">
                        <p onClick={signup} className="signupText">
                            회원가입
                        </p>
                        <p onClick={functionName} className="signupText">
                            아이디/비밀번호 찾기
                        </p>
                    </div>
                    <div className="separator">
                        <span className="separateText">또는</span>
                    </div>
                    <div className="socialLoginContainer">
                        <button className="socialButton googleButton" onClick={onGoogleLogin}>
                            <img src={googleLogo} alt="Google" className="socialLogo"/>
                            구글로 로그인
                        </button>
                        <button className="socialButton kakaoButton">
                            <img src={kakaoLogo} alt="Kakao" className="socialLogo"/>
                            카카오톡으로 로그인
                        </button>
                        <button className="socialButton naverButton" onClick={onNaverLogin}>
                            <img src={naverLogo} alt="Naver" className="socialLogo"/>
                            네이버로 로그인
                        </button>
                    </div>
                </div>
            </div>
            
        </div>
    );
}