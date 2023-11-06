import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axiosInstance from "../axiosinstance";

interface EventList<T> {
  data: T[];
}
const EventRegist: React.FC = () => {
  const [eventList, setEventList] = useState<any>([]);

  useEffect(() => {
    axiosInstance
      .get(`${process.env.REACT_APP_API}/event`)
      .then((res) => setEventList([...res.data]))
      .catch((err) => console.log(err));
  }, []);

  console.log(eventList);

  const navigate = useNavigate();
  useEffect(() => {
    if (localStorage.getItem("AccessToken")) {
      if (localStorage.getItem("UserName") === "admin@bangrang") {
        navigate("/admin");
      }
    } else {
      navigate("/login");
    }
  }, [navigate]);

  return (
    <div
      style={{
        width: "100%",
        height: window.innerHeight - 80,
        backgroundColor: "#E2F5FF",
        padding: "2%",
        boxSizing: "border-box",
      }}
    >
      <p style={{ border: "1px solid black", margin: 0, width: "100%" }}>
        이벤트 등록 및 관리
      </p>
      <div
        style={{
          width: "100%",
          height: "95%",
          border: "1px solid black",
          display: "flex",
        }}
      >
        <div
          style={{
            width: "30%",
            backgroundColor: "white",
            border: "1px solid black",
          }}
        >
          <p>받은 문의 리스트 출력</p>
          <p>1</p>
          <p>1</p>
          <p>1</p>
          <p>1</p>
          <p>1</p>
        </div>
        <div
          style={{
            width: "70%",
            backgroundColor: "white",
            border: "1px solid black",
          }}
        >
          <h1>여기에 문의 내용, 답변달기 기능</h1>
        </div>
      </div>
    </div>
  );
};
export default EventRegist;
