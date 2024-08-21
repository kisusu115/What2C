import React, {useEffect, useState} from "react";
import { useNavigate } from 'react-router-dom';
import googleLogo from './pngs/google_logo.png';
import kakaoLogo from './pngs/kakao_logo.png';
import naverLogo from './pngs/naver_logo.png';

export default function Login(){
    const [id, setId] = useState('');
    const [pw, setPw] = useState('');
    const navigate = useNavigate();
    const [boolVal, setBoolVal] = useState(false);

    const handleInputChange = (e) => {
        const {name, value} = e.target;
        if (name === 'id') {
            setId(value);
        } else if (name === 'pw') {
            setPw(value);
        }
    };

    const functionName = () => {
        
    }

    const login = () => {
        const json_data = [{"id" : id}, {"pw" : pw}]
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
                        onChange={functionName}
                    />
                </div>

                <div style={{marginTop:"26px"}} className="inputTitle">비밀번호</div>
                <div className="inputWrap">
                    <input 
                        type='password'
                        className="input"
                        placeholder="비밀번호를 입력해주세요."
                        value={pw}
                        onChange={functionName} 
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
                        <p className="signupText">
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