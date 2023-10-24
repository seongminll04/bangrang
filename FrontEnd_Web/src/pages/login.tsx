import React, { useEffect } from "react";
import { useNavigate } from 'react-router-dom';
const Login: React.FC = () => {
    const navigate = useNavigate()
    useEffect(()=>{
      const AccessToken = localStorage.getItem('AccessToken')
      if (AccessToken) {
        navigate('/manage')
      }
    },[navigate])
  return (
    <div>
        <img src="assets/logo.png" alt="" />
        <h1>관리자 로그인</h1>
        <form>
            <label>아이디</label>
            <input type="email" name="" id="" />
            <label>비밀번호</label>
            <input type="password" name="" id="" />
            <button type="submit">로그인</button>
        </form>
        <button type="button" onClick={()=>navigate('/manage')}>일단 관리자 페이지 이동하기</button>
    </div>
  );
};
export default Login;
