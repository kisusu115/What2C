import React, {useEffect, useState} from "react";
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

export default function SignUpPage(){
    const navigate = useNavigate();

    const [id, setId] = useState('');
    const [pw, setPw] = useState('');
    const [email, setEmail] = useState('');

    const [idValid, setIdValid] = useState(false);
    const [pwValid, setPwValid] = useState(false);
    const [emailValid, setEmailValid] = useState(false);
    const [buttonNotAllow, setButtonNotAllow] = useState(true);

    const handleId = (e) => {
        const idValue = e.target.value;
        setId(idValue);
        const regex = /^[A-Za-z0-9]{3,}$/i;
        setIdValid(regex.test(idValue));
    }

    const handlePw = (e) => {
        const pwValue = e.target.value;
        setPw(pwValue);
        const regex = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+])(?!.*[^a-zA-Z0-9$`~!@$!%*#^?&\\(\\)\-_=+]).{8,20}$/;
        setPwValid(regex.test(pwValue));
    }

    const handleEmail = (e) => {
        const emailValue = e.target.value;
        setEmail(emailValue);
        const regex = /^(([^<>()\[\].,;:\s@"]+(\.[^<>()\[\].,;:\s@"]+)*)|(".+"))@(([^<>()[\].,;:\s@"]+\.)+[^<>()[\].,;:\s@"]{2,})$/i;
        setEmailValid(regex.test(emailValue));
    }

    const onClickConfirmButton = async () => {
        try {
            const response = await axios.post('http://localhost:8080/sign-up', {
                username: id,
                password: pw,
                email: email
            });

            if (response.status === 200) {
                alert(`회원가입에 성공했습니다. 환영합니다.
                    \n[${id}]
                    \n[${pw}]
                    \n[${email}]`);
                    navigate('/signin');
            } else {
                console.error("회원가입 실패");
                alert("회원가입에 실패했습니다. 응답이 200이 아니에요.");
            }

        } 
        catch (error) {
            console.error("회원가입 실패");
            alert("회원가입에 실패했습니다. 서버에 문제가 있나봐요.");
        }
    }

    useEffect(() => {
        if (emailValid && pwValid && idValid) {
            setButtonNotAllow(false);
        } else {
            setButtonNotAllow(true);
        }
    }, [emailValid, pwValid, idValid]);

    return (
        <div className="page">
            <div className="titleWrap">
                회원가입
            </div>
            <div className="contentWrap">
            <div className="inputTitle">아이디</div>
                <div className="inputWrap">
                    <input 
                        type='text'
                        className="input"
                        placeholder="영문, 숫자로만 3자 이상"
                        value={id}
                        onChange={handleId}
                    />
                </div>
                <div className="errorMessageWrap">
                    {!idValid && id.length > 0 && (
                        <div>영문, 숫자로만 이루어진 3자 이상의 ID를 입력해주세요.</div>
                    )}
                </div>

                <div style={{marginTop:"26px"}} className="inputTitle">비밀번호</div>
                <div className="inputWrap">
                    <input 
                        type='password'
                        className="input"
                        placeholder="영문, 숫자, 특수문자 포함 8자 이상"
                        value={pw}
                        onChange={handlePw} 
                    />
                </div>
                <div className="errorMessageWrap">
                    {!pwValid && pw.length > 0 && (
                        <div>영문, 숫자, 특수문자 포함 8자 이상 입력해주세요.</div>
                    )}
                </div>

                <div style={{marginTop:"26px"}} className="inputTitle">이메일 주소</div>
                <div className="inputWrap">
                    <input 
                        type='text'
                        className="input"
                        placeholder="example@gmail.com"
                        value={email}
                        onChange={handleEmail} 
                    />
                </div>
                <div className="errorMessageWrap">
                    {!emailValid && email.length > 0 && (
                        <div>올바른 이메일을 입력해주세요.</div>
                    )}
                </div>

                
            </div>
            <div>
                <button onClick = {onClickConfirmButton} disabled={buttonNotAllow} className="bottomButton">
                    가입하기
                </button>
            </div>
        </div>
    );
}