import React from "react";
import styles from './navbar.module.css'
import { Link } from "react-router-dom";

const Navbar: React.FC = () => {
  return (
    <header className='navbar'>
      <Link to='/'>
        <img className={styles.logo} src="assets/logo.png" alt="" />
      </Link>
      <div className={styles.navbtn_lst}>
        <Link className={styles.navbtn} to='/intro'>서비스 소개</Link>
        <Link className={styles.navbtn} to='/requestmanager'>관리자 신청</Link>
        <Link className={styles.navbtn} to='/manage'>관리자 페이지</Link>
        <Link className={`${styles.navbtn} ${styles.appdown}`} to='https://play.google.com/store'>다운로드</Link>
      </div>
    </header>
  );
};
export default Navbar;
