import React, {useEffect, useState} from "react";

export default function Login(){
    const Admin = {
        email : 'admin@example.com',
        pw : 'pw1234!!!'
    }

    const [email, setEmail] = useState('');
    const [pw, setPw] = useState('');

    const [emailValid, setEmailValid] = useState(false);
    const [pwValid, setPwValid] = useState(false);
    const [buttonNotAllow, setButtonNotAllow] = useState(true);

    const handleEmail = (e)=>{
        setEmail(e.target.value);
        const regex = 
            /^(([^<>()\[\].,;:\s@"]+(\.[^<>()\[\].,;:\s@"]+)*)|(".+"))@(([^<>()[\].,;:\s@"]+\.)+[^<>()[\].,;:\s@"]{2,})$/i;
        if(regex.test(email)){
            setEmailValid(true)
        }else{
            setEmailValid(false)
        }
    }
    const handlePw = (e)=>{
        setPw(e.target.value);
        const regex = 
            /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+])(?!.*[^a-zA-z0-9$`~!@$!%*#^?&\\(\\)\-_=+]).{8,20}$/;
        if(regex.test(pw)){
            setPwValid(true)
        }else{
            setPwValid(false)
        }
    }

    const onClickConfirmButton = () => {
        if(email === Admin.email && pw === Admin.pw){
            alert(`로그인에 성공했습니다. 환영합니다.
                \n[${email}]
                \n[${pw}]`)
        }else{
            alert("동록되지 않은 회원입니다.")
        }
    }

    useEffect(()=>{
        if(emailValid && pwValid){
            setButtonNotAllow(false);
            return;
        }
        setButtonNotAllow(true);
    }, [emailValid, pwValid]);

    return (
        <div className="page">
            <div className="titleWrap">
                이메일과 비밀번호를
                <br/>
                입력해주세요
            </div>
            <div className="contentWrap">
                <div className="inputTitle">이메일 주소</div>
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
            </div>
            <div>
                <button onClick = {onClickConfirmButton} disabled={buttonNotAllow} className="bottomButton">
                    확인
                </button>
            </div>
        </div>
    );
}