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
            const response = await axios.post('./member/sign-in', {
                username: id,
                password: pw
            });
            if (response.status === 200) {
                const grantType = response.data.grantType;
                const accessToken = response.data.accessToken;
                const refreshToken = response.data.refreshToken;

                alert(`로그인 성공!\ngrantType: ${grantType}\naccessToken: ${accessToken}\refreshToken: ${refreshToken}`);
                navigate('/home');
            } else if (response.status === 401) {
                alert("로그인 실패: 인증 오류");
            }
        } catch (error) {
            console.error("로그인 실패", error);
            alert("로그인에 실패했습니다. 서버에 문제가 있나봐요.");
        }
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
                        <button className="socialButton googleButton">
                            <img src={googleLogo} alt="Google" className="socialLogo"/>
                            구글로 로그인
                        </button>
                        <button className="socialButton kakaoButton">
                            <img src={kakaoLogo} alt="Kakao" className="socialLogo"/>
                            카카오톡으로 로그인
                        </button>
                        <button className="socialButton naverButton">
                            <img src={naverLogo} alt="Naver" className="socialLogo"/>
                            네이버로 로그인
                        </button>
                    </div>
                </div>
            </div>
            
        </div>
    );
}