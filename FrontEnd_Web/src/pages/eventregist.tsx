import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axiosInstance from "../axiosinstance";
import styles from "./eventregist.module.css";
import { divide } from "lodash";

interface EventList<T> {
  data: T[];
}

interface Event {
  eventIdx: number;
  title: string;
  subTitle: string | null;
  content: string;
  startDate: string;
  endDate: string;
  address: string;
  longitude: number;
  latitude: number;
  eventUrl: string | null;
  likeCount: number;
}

interface DetailEvent {
  eventIdx: number;
  title: string;
  subTitle: string | null;
  content: string;
  startDate: string;
  endDate: string;
  address: string;
  longitude: number;
  latitude: number;
  eventUrl: string | null;
  likeCount: number;
}

interface inquiry {
  inquiryIdx: Number;
  title: string;
  event: string;
  createdAt: Date;
}
interface Detailinquiry {
  inquiryIdx: Number;
  title: string;
  content: string;
  createdAt: Date;
  event: string | null;
  nickname: string;
  comment: {
    commentIdx: Number;
    content: string;
    updatedAt: Date;
  } | null;
}

const EventRegist: React.FC = () => {
  const [isInquiry, setInquiry] = useState<inquiry[]>([
    {
      inquiryIdx: 1,
      title: "OO축제 운영 시간 문의",
      event: "<부산 불꽃놀이 축제>",
      createdAt: new Date(),
    },
    {
      inquiryIdx: 2,
      title: "OO축제 운영 시간 문의",
      event: "<부산 불꽃놀이 축제>",
      createdAt: new Date(),
    },
    {
      inquiryIdx: 3,
      title: "OO축제 운영 시간 문의ddddddddddddddddddddddddddddddddd",
      event: "<부산 불꽃놀이 축제>",
      createdAt: new Date(),
    },
  ]);
  const [isDetail, setDetail] = useState<DetailEvent | null>({
    eventIdx: 1,
    title: "OO축제 운영 시간 문의",
    subTitle: "<부산 불꽃놀이 축제>",
    startDate: "23",
    endDate: "23",
    content: "asdfads",
    address: "주소",
    longitude: 126,
    latitude: 152,
    eventUrl: null,
    likeCount: 0,
  });

  const [isEdit, setEdit] = useState(false); // comment 수정 on/off
  const [isComment, setComment] = useState(""); // 수정할 comment 내용 담아두는 변수
  const days = ["일", "월", "화", "수", "목", "금", "토"];
  const [eventList, setEventList] = useState<Event[]>([]);

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

  const loaddata = () => {
    axiosInstance({
      method: "get",
      url: `${process.env.REACT_APP_API}/api/web/inquiry`,
    })
      .then((res) => {
        setInquiry(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const detaildata = (idx: Number) => {
    axiosInstance({
      method: "get",
      url: `${process.env.REACT_APP_API}/api/web/inquiry/${idx}`,
    })
      .then((res) => {
        setDetail(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <div
      style={{
        width: "100%",
        height: window.innerHeight - 80,
        backgroundColor: "#E2F5FF",
        padding: "3% 10%",
        boxSizing: "border-box",
      }}
    >
      <p style={{ border: "1px solid black", margin: 0, width: "100%" }}>
        이벤트
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
          <div style={{ width: "100%", height: "5%", backgroundColor: "red" }}>
            이벤트 리스트
          </div>

          <div style={{ width: "100%", height: "95%", overflowY: "scroll" }}>
            {eventList.map((item, index) => (
              <div
                key={index}
                className={`${
                  isDetail?.eventIdx === item.eventIdx ? styles.sel_inquiry : ""
                } ${styles.inquiry_lst}`}
                onClick={() => detaildata(item.eventIdx)}
              >
                <div className={styles.inquiry_idx}>{index + 1}</div>
                <div className={styles.inquiry_info}>
                  <span>{item.title}</span>
                  <span>{item.subTitle}</span>
                </div>
              </div>
            ))}
          </div>
        </div>
        <div
          style={{
            width: "70%",
            backgroundColor: "white",
            border: "1px solid black",
          }}
        >
          <div
            style={{
              height: "10%",
              fontSize: 40,
              borderBottom: "1px solid black",
            }}
          >
            이벤트
          </div>
          {isDetail ? (
            <div style={{ height: "90%" }}>
              <div
                style={{
                  height: "60%",
                  padding: "2%",
                  boxSizing: "border-box",
                }}
              >
                <div
                  style={{
                    display: "flex",
                    justifyContent: "space-between",
                    height: "10%",
                  }}
                >
                  <span
                    onClick={() => {
                      setDetail(null);
                    }}
                  >
                    닫기
                  </span>
                </div>

                <div style={{ margin: "2% 10%", height: "90%" }}>
                  <div
                    style={{
                      display: "flex",
                      textAlign: "start",
                      height: "15%",
                    }}
                  >
                    <div
                      style={{
                        width: "20%",
                        border: "1px solid black",
                        display: "flex",
                        alignItems: "center",
                        paddingLeft: 10,
                        boxSizing: "border-box",
                      }}
                    >
                      이벤트 제목
                    </div>
                    <div
                      style={{
                        width: "80%",
                        border: "1px solid black",
                        display: "flex",
                        alignItems: "center",
                        paddingLeft: 10,
                        boxSizing: "border-box",
                      }}
                    >
                      {isDetail.title}
                    </div>
                  </div>
                  <div
                    style={{
                      display: "flex",
                      textAlign: "start",
                      height: "15%",
                    }}
                  >
                    <div
                      style={{
                        width: "20%",
                        border: "1px solid black",
                        display: "flex",
                        alignItems: "center",
                        paddingLeft: 10,
                        boxSizing: "border-box",
                      }}
                    >
                      이벤트 부제목
                    </div>
                    <div
                      style={{
                        width: "80%",
                        border: "1px solid black",
                        display: "flex",
                        alignItems: "center",
                        paddingLeft: 10,
                        boxSizing: "border-box",
                      }}
                    >
                      {isDetail.subTitle}
                    </div>
                  </div>
                  <div
                    style={{
                      display: "flex",
                      textAlign: "start",
                      height: "15%",
                    }}
                  >
                    <div
                      style={{
                        width: "20%",
                        border: "1px solid black",
                        display: "flex",
                        alignItems: "center",
                        paddingLeft: 10,
                        boxSizing: "border-box",
                      }}
                    >
                      이벤트 주소
                    </div>
                    <div
                      style={{
                        width: "80%",
                        border: "1px solid black",
                        display: "flex",
                        alignItems: "center",
                        paddingLeft: 10,
                        boxSizing: "border-box",
                      }}
                    >
                      {isDetail.address}
                    </div>
                  </div>
                  <div
                    style={{
                      display: "flex",
                      textAlign: "start",
                      height: "15%",
                    }}
                  >
                    <div
                      style={{
                        width: "20%",
                        border: "1px solid black",
                        display: "flex",
                        alignItems: "center",
                        paddingLeft: 10,
                        boxSizing: "border-box",
                      }}
                    >
                      시작/완료
                    </div>
                    <div
                      style={{
                        width: "80%",
                        border: "1px solid black",
                        display: "flex",
                        alignItems: "center",
                        paddingLeft: 10,
                        boxSizing: "border-box",
                      }}
                    >
                      {isDetail.startDate} ~ {isDetail.endDate}
                    </div>
                  </div>

                  <div
                    style={{
                      display: "flex",
                      textAlign: "start",
                      height: "70%",
                    }}
                  >
                    <div
                      style={{
                        width: "20%",
                        border: "1px solid black",
                        display: "flex",
                        padding: "5px 10px",
                        boxSizing: "border-box",
                      }}
                    >
                      이벤트 내용
                    </div>
                    <div
                      style={{
                        width: "80%",
                        border: "1px solid black",
                        display: "flex",
                        padding: "5px 10px",
                        boxSizing: "border-box",
                      }}
                    >
                      {isDetail.content}
                    </div>
                  </div>
                  {isDetail.eventUrl ? (
                    <img src={isDetail.eventUrl} alt="sfdsd" />
                  ) : (
                    <div
                      style={{
                        width: "80%",
                        border: "1px solid black",
                        display: "flex",
                        padding: "5px 10px",
                        boxSizing: "border-box",
                      }}
                    >
                      이벤트 이미지가 없습니다.
                    </div>
                  )}
                </div>
              </div>
            </div>
          ) : null}
        </div>
      </div>
    </div>
  );
};
export default EventRegist;
