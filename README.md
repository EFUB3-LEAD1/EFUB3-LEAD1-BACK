# 하나투어 클론 코딩 - EFUB 3기 리드 토이 프로젝트 1팀 

### ✨ 프로젝트 소개
이화여자대학교 웹 개발 동아리 EFUB 리드 개발자의 토이 프로젝트 일환으로 진행한 '하나투어' 클론코딩입니다.

🔗 <b>[API 문서 바로가기](https://api.htour.xyz/swagger-ui/index.html)</b>

📝 <b>ERD</b>

![HANA-ERD (1)](https://github.com/EFUB3-LEAD1/EFUB3-LEAD1-BACK/assets/77741296/c3ac94ac-b69a-43b2-b67d-5865075a1b0b)


### ✨ 기능
- 유저 인증 API
  -  로그인
  -  로그아웃
- Tour 관광 상품 API
  - 관광 상품 전체 조회
  - 관광 상품 개별 조회
  - 관광 상품 나라 이름 별 조회
- Spot API
  - 여행 지역 조회
- Tour 좋아요 API
  - 사용자의 좋아요 목록 반환
  - Tour에 대한 좋아요 등록
  - Tour에 대한 좋아요 삭제
  
🔗 <b>[배포 서버 바로가기](https://api.htour.xyz/)</b>

### 👩‍👩‍👧‍👦 백엔드 팀원 소개
<table border="" cellspacing="0" cellpadding="0" width="100%">
    <tr width="100%">
        <td align="center"><a href= "https://github.com/sunnyineverywhere">이선의</a></td>
        <td align="center"><a href= "https://github.com/mingulmangul">권민아</a></td>
        <td align="center"><a href= "https://github.com/aoqlsdl">김혜빈</a></td>
    </tr>
    <tr width="100%">
         <td  align="center"><img src = "https://avatars.githubusercontent.com/u/80109963?v=4" width="100px"/></td>
        <td  align="center"><img src = "https://avatars.githubusercontent.com/u/71026706?v=4" width="100px" /></td>
        <td  align="center"><img src = "https://avatars.githubusercontent.com/u/77741296?v=4" width="100px"/></td>
    </tr>
    <tr width="100%">
      <td  align="left"><li>AWS 계정 생성</li><li>CI/CD 파이프라인 구축</li><li>도메인 연결</li><li>https 배포</li><li>로그인/로그아웃 API</li><li>Swagger API Docs 세팅</li><li>데이터 구축</li></td>
      <td  align="left"><li>프로젝트 생성</li><li>엔티티 생성</li><li>Tour API</li><li>Spot API</li><li>데이터 구축</li></td>
      <td  align="left"><li>mysql script 작성</li><li>ERD 세부 내용 작성</li><li>로그인/로그아웃 API</li><li>'좋아요' API</li></td>
   </tr>
</table>

### 📅 개발 기간
- 프로젝트 세팅: 2023.05.02. - 2023.05.04.
- API 개발: 2023.05.02. - 2023.05.16.
- 배포 및 API 연결: 2023.05.17 - 2023.05.22

### 🗂️ 디렉토리 구조
```
📂 main.java.efub.clone.hanatour
└─ 📂 domain 
    ├─ 📂 heart
    ├─ 📂 image
    ├─ 📂 member
    ├─ 📂 spot
    ├─ 📂 heart
    └─ 📂 tour
└─ 📂 global
    ├─ 📂 config
    ├─ 📂 entity
    └─ 📂 jwt
```

### 🔨 기술 스택
- BackEnd : <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/docker-4479A1?style=for-the-badge&logo=docker&logoColor=white"> 
- ETC : <img src="https://img.shields.io/badge/aws-232F3E?style=for-the-badge&logo=aws&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
