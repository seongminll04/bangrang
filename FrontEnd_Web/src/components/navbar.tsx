import React from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";

import styles from './navbar.module.css'

const Navbar: React.FC = () => {
  const location = useLocation();
  const navigate = useNavigate()
  return (
    <>
      {location.pathname==='/' ? 
      <header className='navbar'>
        <Link to='/'>
          <img className={styles.logo} src="assets/logo.png" alt="" />
        </Link>
        <div className={styles.navbtn_lst}>
          <Link className={styles.navbtn} to='/'>서비스 소개</Link>
          <Link className={styles.navbtn} to='/login'>관리자 페이지</Link>
          <Link className={`${styles.navbtn} ${styles.appdown}`} to='https://play.google.com/store'>다운로드</Link>
        </div>
      </header>
      : location.pathname==='/manage' ?
      <header className='navbar'>
        <Link to='/manage'>
          <img className={styles.logo} src="assets/logo.png" alt="" />
        </Link>
        <p className={styles.navbtn} onClick={()=>navigate('/')}>로그아웃</p>
      </header>
      : null}
    </>
  );
};
export default Navbar;
