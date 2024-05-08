Teamtime 팀타임
============================
<img width="1237" alt="Screenshot 2024-05-07 at 20 54 14" src="https://github.com/2311PublicDataWebApp/Teamtime/assets/152952334/09301a69-3251-4223-a9c2-c236dc033bae">

## 💬 프로젝트 소개
> 팀타임은 소규모 팀의 간편한 일정 관리 및 소통을 지원합니다.

<br/>

## 🔥 팀원 소개 및 담당 기능
![image](https://github.com/2311PublicDataWebApp/Teamtime/assets/152952334/98b84fdf-4429-4d44-81b4-a645bc9fd810)

<br/>

## 📅 개발 기간
> 2024/04/02 ~ 2024/04/30

<br/>

## 🔧 개발 환경
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white"/> <img src="https://img.shields.io/badge/Oracle-F80000?style=flat-square&logo=oracle&logoColor=white"/> <img src="https://img.shields.io/badge/VISUAL STUDIO CODE-007ACC?style=flat-square&logo=visualstudiocode&logoColor=white"/>
<br/><img src="https://img.shields.io/badge/JAVA-F80000?style=flat-square&logo=&logoColor=white"/> <img src="https://img.shields.io/badge/MyBatis-111111?style=flat-square&logo=&logoColor=white"/> <img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=flat-square&logo=thymeleaf&logoColor=white"/> <img src="https://img.shields.io/badge/HTML5-E34F26?style=flat-square&logo=html5&logoColor=white"/> <img src="https://img.shields.io/badge/CSS3-1572B6?style=flat-square&logo=css3&logoColor=white"/> <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=javascript&logoColor=white"/> <img src="https://img.shields.io/badge/Apache Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white"/>
<br/><img src="https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=github&logoColor=white"/> <img src="https://img.shields.io/badge/Bootstrap-7952B3?style=flat-square&logo=bootstrap&logoColor=white"/>

<br/>

## 📌 ERD 구조
![image](https://github.com/2311PublicDataWebApp/Teamtime/assets/152952334/6f478993-a058-40ec-93de-8e33530aab35)

## ✏ 주요기능

### 회원
|기능명|상세|
| ---- | -- |
| 로그인 | 사용자는 등록된 아이디와 비밀번호를 사용하여 로그인할 수 있습니다. 사용자의 로그인 상태는 세션을 통해 유지하고 인증된 요청만을 처리합니다. |
| 회원가입 | 사용자는 자신의 개인정보(아이디, 비밀번호, 이름, 닉네임, 이메일, 휴대폰 번호)를 입력하여 새로운 계정을 생성할 수 있습니다. |
| 정보 수정 | 사용자는 등록된 자신의 개인정보(프로필 사진, 닉네임, 이메일, 휴대폰 번호)를 수정할 수 있습니다. |
| 회원 삭제 | 사용자가 회원 탈퇴를 요청할 경우 해당 계정과 관련된 모든 정보를 안전하게 삭제합니다. |

### 알람
|기능명|상세|
| ---- | -- |
| 알람 목록 조회 | 사용자는 탑바에서 읽지 않은 알람 목록을 조회할 수 있습니다. |
| 알람 삭제 | 사용자는 읽은 알람을 삭제할 수 있습니다. |

### 채팅
|기능명|상세|
| ---- | -- |
| 채팅방 목록 | 사용자는 사이드바에서 자신이 속한 팀 채팅방 목록을 조회할 수 있습니다. |
| 채팅 입력 | 사용자는 채팅방 안에서 채팅을 입력할 수 있습니다. |

### 팀
|기능명|상세|
| ---- | -- |
| 팀 생성 | 사용자는 자신의 팀을 만들 수 있습니다. |
| 팀원 관리 | 팀장은 사용자를 자신의 팀에 추가할 수 있습니다. |
| 팀 투표 | 팀장은 자신의 팀에서 투표를 등록할 수 있습니다. |

### 팀 게시판
|기능명|상세|
| ---- | -- |
| 게시글 조회 | 사용자는 자신이 속한 팀 게시판의 게시글(공지사항, 일반글)을 조회할 수 있습니다.  |
| 게시글 생성 | 사용자는 자신이 속한 팀 게시판에 게시글(내용, 카테고리)을 에디터를 통해 생성할 수 있습니다. |
| 게시글 삭제 | 사용자는 자신이 등록한 게시글을 삭제할 수 있습니다. |
| 게시글 수정 | 사용자는 자신이 등록한 게시글을 삭제할 수 있습니다. |

### 팀 게시판 댓글
|기능명|상세|
| ---- | -- |
| 댓글 등록 | 사용자는 등록된 게시글에 댓글을 등록할 수 있습니다. |
| 댓글 조회 | 사용자는 게시글에 등록된 댓글을 조회할 수 있습니다. |
| 댓글 수정 | 사용자는 자신이 등록한 댓글을 수정할 수 있습니다. |
| 댓글 삭제 | 사용자는 자신이 등록한 댓글을 삭제할 수 있습니다. |

### 팀 캘린더
|기능명|상세|
| ---- | -- |
| 일정 조회 | 사용자는 자신이 속한 팀의 일정을 조회할 수 있습니다.  |
| 일정 생성 | 사용자는 자신이 속한 팀의 일정을 캘린더를 통해 등록할 수 있습니다. |
| 일정 수정 | 사용자는 자신이 등록한 일정을 수정할 수 있습니다. |
| 일정 삭제 |사용자는 자신이 등록한 일정을 삭제할 수 있습니다. |

### 오늘 할 일
|기능명|상세|
| ---- | -- |
| 할 일 조회 | 사용자는 자신이 등록한 투두 리스트를 오늘, 내일, 해당 주차의 요일별 할 일을 조회할 수 있습니다.  |
| 할 일 생성 | 사용자는 모달과 캘린더를 통해 할 일을 등록할 수 있습니다. |
| 할 일 수정 | 사용자는 등록된 할 일을 클릭하여 수정할 수 있고 드래그해서 날짜를 변경할 수 있습니다. |
| 할 일 삭제 | 사용자는 등록된 할 일을 삭제할 수 있습니다. |

### 일대일 문의
|기능명|상세|
| ---- | -- |
| 문의 조회 | 작성한 문의를 조회할 수 있습니다. |
| 문의 생성 | 사용자가 새로운 문의를 작성할 수 있습니다. 작성 시에는 CKEditor를 사용하여 텍스트 형식으로 작성할 수 있으며,필요 시에는 첨부 파일을 추가할 수 있습니다. |
| 문의 삭제 | 사용자가 작성한 문의를 삭제할 수 있습니다. |
| 문의 수정 | 사용자가 작성한 문의를 수정할 수 있습니다. |
| 문의 답변 | 관리자가 사용자의 문의에 답변을 작성할 수 있습니다. 답변은 댓글 형식으로 작성됩니다. 수정, 삭제도 가능합니다 |

<br/>

## 🖥 실행화면

### ✔ 메인화면
<br/>

<img width="1470" alt="Screenshot 2024-05-07 at 20 52 34" src="https://github.com/2311PublicDataWebApp/Teamtime/assets/152952334/f3cb00ba-18d4-4a5f-bd32-97c1c34b3c35">

### ✔ 회원
<br/>

**1. 로그인** <br/><br/>
![KakaoTalk_Photo_2024-05-07-21-01-33 011](https://github.com/2311PublicDataWebApp/Teamtime/assets/152952334/a10ebc50-d504-4ade-84eb-d63fb54d4097)

**2. 회원가입** <br/><br/>
![KakaoTalk_Photo_2024-05-07-21-01-33 009](https://github.com/2311PublicDataWebApp/Teamtime/assets/152952334/413d1c86-cd61-4208-a2cd-dbaac1763e1d)

**3. 내 정보 조회 및 수정** <br/><br/>
<img width="1470" alt="image" src="https://github.com/2311PublicDataWebApp/Teamtime/assets/152952334/44e9c01d-29ea-4b83-9b36-1c0d95c436f2">

### ✔ 채팅
<br/>

**1. 채팅 조회 및 입력** <br/><br/>
<img width="1469" alt="image" src="https://github.com/2311PublicDataWebApp/Teamtime/assets/152952334/90dcf290-8c33-4115-887e-e57aae541967">

### ✔ 팀
<br/>

**1. 팀 생성** <br/><br/>
![KakaoTalk_Photo_2024-05-07-21-01-33 007](https://github.com/2311PublicDataWebApp/Teamtime/assets/152952334/d1726d52-0b19-4f9c-b649-af18c4df774e)

**2. 팀 관리** <br/><br/>
![KakaoTalk_Photo_2024-05-07-21-01-33 006](https://github.com/2311PublicDataWebApp/Teamtime/assets/152952334/6aa48f46-9b7a-4a5b-b401-0b3595a42cad)

**3. 팀 투표** <br/><br/>
![KakaoTalk_Photo_2024-05-07-21-01-32 005](https://github.com/2311PublicDataWebApp/Teamtime/assets/152952334/e0320231-d2be-4abc-8974-f3b174a01684)

### ✔ 마이 투두
<br/>

**1. 투두 조회** <br/><br/>
![KakaoTalk_Photo_2024-05-07-21-01-32 004](https://github.com/2311PublicDataWebApp/Teamtime/assets/152952334/797c4585-7833-4efb-a5ab-771a55f27001)

**2. 투두 생성** <br/><br/>
![KakaoTalk_Photo_2024-05-07-21-01-32 003](https://github.com/2311PublicDataWebApp/Teamtime/assets/152952334/56afe30c-c4ff-4417-9d6d-c72a78f570d0)

**3. 투두 수정** <br/><br/>
![KakaoTalk_Photo_2024-05-07-21-01-32 002](https://github.com/2311PublicDataWebApp/Teamtime/assets/152952334/9d579d31-fa2e-4e15-9ff2-dc47c1abe27a)

### ✔ 일대일 문의
<br/>

**1. 문의 조회** <br/><br/>
![KakaoTalk_Photo_2024-05-07-21-01-32 001](https://github.com/2311PublicDataWebApp/Teamtime/assets/152952334/a78d4c7e-9c3c-4569-8ddc-cd4b271646fc)

**2. 문의 생성** <br/><br/>
<img width="1470" alt="image" src="https://github.com/2311PublicDataWebApp/Teamtime/assets/152952334/45765542-4889-4cce-b117-158cdbb778d0">

<br/>
