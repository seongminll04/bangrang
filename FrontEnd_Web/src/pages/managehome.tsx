import React from "react";

const ManageHome: React.FC = () => {
  return (
    <div>
      <div style={{width:'100vw', display:'flex', backgroundColor:'#E2F5FF',padding:'5% 0'}}>
        <div style={{width:'60%',marginLeft:'20%', backgroundColor:'white'}}>
          <h1>대충 내용있음</h1>
        </div>
        <div style={{width:'10%',margin:'0 5%',border:'1px solid black', backgroundColor:'white'}}>
          <p>공지사항</p>
          <p>축제 정보</p>
          <p>1:1 문의</p>
          <p>관리자</p>
        </div>
      </div>
    </div>
  );
};
export default ManageHome;
