<h1 align="center">📝 ourblog — 블로그 플랫폼 </h1>

<p align="center">
  <a href="#-프로젝트-개요">프로젝트 개요</a> •
  <a href="#-기간--팀-구성">기간 & 팀 구성</a> •
  <a href="#-tech-stack">Tech Stack</a> •
  <a href="#-api-설계">API 설계</a> •
  <a href="#-실행-화면">실행 화면</a> •
  <a href="#-내-담당-기능">내 담당 기능</a> •
  <a href="#-핵심-트러블슈팅">핵심 트러블슈팅</a> •
  <a href="#-느낀점--회고">느낀점 / 회고</a>
</p>

<p align="center">
  <img alt="Java" src="https://img.shields.io/badge/Java-17-007396?logo=openjdk&logoColor=white">
  <img alt="Spring" src="https://img.shields.io/badge/Spring%20Data%20JPA-3.x-6DB33F?logo=spring&logoColor=white">
  <img alt="Spring Security" src="https://img.shields.io/badge/Spring%20Security-6.x-2c3e50">
  <img alt="JWT" src="https://img.shields.io/badge/JWT-Auth-000000?logo=jsonwebtokens&logoColor=white">
  <img alt="MySQL" src="https://img.shields.io/badge/MySQL-8-4479A1?logo=mysql&logoColor=white">
</p>

---

## 📌 프로젝트 개요
**ourblog**는 개발자를 위한 블로그 서비스입니다.  
회원 인증(JWT), 게시글/댓글/좋아요, 카테고리, 검색, 관리자 기능을 제공하여  
개발 학습·공유를 위한 **가볍고 직관적인** 글쓰기 환경을 지향합니다.

---

## ⏱ 기간 & 팀 구성
- **제작 기간:** 2024.01.01 ~ 2024.02.09  
- **참여 인원:** **2명** (팀 프로젝트)  
  - **Backend:** 본인 — 인증/인가, 도메인/레포지토리/서비스, 검색·카테고리·관리자 기능  
  - **Frontend:** 팀원 ([frontend-Repository](https://github.com/kimnya/ourblog)) — 화면 설계, UI/UX, API 연동

---

## 🧰 Tech Stack
| 분류 | 기술 |
|---|---|
| Language | **Java** |
| Backend | **JPA**, **Spring Security** |
| Auth | **JWT** |
| DB | **MySQL** |

---

## 🗂 API 설계
> 주요 도메인(회원/관리자/프로필/게시글/좋아요/댓글/카테고리)의 엔드포인트 구조

![Member](https://github.com/Seoha95/ourblog/assets/107228582/4b4056ba-bb1e-42c5-af66-823252da630d)
![Admin](https://github.com/Seoha95/ourblog/assets/107228582/4e0ae996-98e3-4bb6-a852-05b56d8881c4)
![Profile](https://github.com/Seoha95/ourblog/assets/107228582/d9071551-c6a4-420f-930e-2c5a2c7cbd69)
![Posting](https://github.com/Seoha95/ourblog/assets/107228582/fb06c28c-c29a-4bdd-98e1-9cb257cadaf9)
![Heart](https://github.com/Seoha95/ourblog/assets/107228582/00713183-ee70-41ae-b592-9c8019c93b23)
![Comment](https://github.com/Seoha95/ourblog/assets/107228582/acd8a181-89f0-4569-924c-e0ad96319d9a)
![Category](https://github.com/Seoha95/ourblog/assets/107228582/767dc16f-a054-4eaf-8e46-fd7a4704c88a)

---

## 🖥 실행 화면
- **로그인**  
  ![로그인](https://github.com/Seoha95/ourblog/assets/107228582/ac07562d-29f9-493c-8ea8-83140e1d281c)
- **회원가입**  
  ![회원가입](https://github.com/Seoha95/ourblog/assets/107228582/e903a5e4-36ea-47ac-a358-bda03eb50a08)
- **전체 게시물 보기**  
  ![전체게시물](https://github.com/Seoha95/ourblog/assets/107228582/424bb69d-8c04-4b37-9837-e91af7fd1de6)
- **나의 블로그**  
  ![나의 블로그](https://github.com/Seoha95/ourblog/assets/107228582/600107b9-d8d5-48e2-a24c-ba0883d9494f)
- **그림자 효과**  
  ![그림자효과](https://github.com/Seoha95/ourblog/assets/107228582/4e903ed5-180e-4c0c-8ea2-cd820be611e1)
- **게시물 작성**  
  ![게시물 작성 화면](https://github.com/Seoha95/ourblog/assets/107228582/bf9221d3-570b-4184-a3d4-d9ed0b9e917b)
- **댓글 작성**  
  ![댓글작성](https://github.com/Seoha95/ourblog/assets/107228582/6b9f8374-c7df-44e4-b650-5470c00fb645)
- **게시물 상세보기**  
  ![상세보기](https://github.com/Seoha95/ourblog/assets/107228582/40f44fee-2c0a-46d9-99dc-60ad051c75aa)
- **마이페이지**  
  ![마이페이지](https://github.com/Seoha95/ourblog/assets/107228582/90c97972-fdb1-4dd2-b892-4c5f218c24d6)
- **관리자 페이지**  
  ![관리자 페이지](https://github.com/Seoha95/ourblog/assets/107228582/a1edcd45-cdf7-4c25-b8ae-0b3ab625a3a4)

---

## 👤 내 담당 기능
- **인증/인가**: 로그인 시 **Access/Refresh 토큰** 발급 및 갱신, 보호 리소스 접근 제어  
- **도메인 로직**: 게시글/댓글/좋아요/카테고리 서비스 구현  
- **검색**: **제목/내용/닉네임** 기준 통합 검색 (정렬·페이지네이션 적용)  
- **관리자 기능**: 회원 정보 조회/삭제 API 설계 및 권한 정책 설정  
- **보안/설정**: Spring Security 경로 권한(permitAll/인증 필요) 및 예외 처리 규약 정리

---

## 🧪 핵심 트러블슈팅
### 이슈: **검색이 동작하지 않음**  
초기 구현에서 `searchText` 하나만 받아 **제목/내용/닉네임**에 동시에 적용했지만, 메서드 시그니처가 일치하지 않아 검색이 실패.  
각 필드에 **독립적으로** 검색어를 전달하도록 **파라미터를 3개**로 분리하여 해결.
   
<details>      
<summary>기존코드</summary>      
<pre>
<code>
    PostingRepository.java   
   public interface PostingRepository extends JpaRepository<Posting,Long> {   
    List<Posting> findByTitleContainingOrContentContainingOrNickNameContainingOrderByCreateDateDesc(String searchText);   
       }
  
    PostingService.java    
     public List<PostingListResponseDto> getPostingList(String searchText){   
        List<Posting> postingList = postingRepository.findByTitleContainingOrContentContainingOrNickNameContainingOrderByCreateDateDesc(searchText);   
         
        return postingList.stream()   
                .map(PostingListResponseDto::fromEntity)   
                .collect(Collectors.toList());   
</code>
</pre>
   
</details>   

<details>
<summary>개선된 코드</summary>
<pre>
<code>   
     PostingRepository.java   
     public interface PostingRepository extends JpaRepository<Posting,Long> {  
     List<Posting> findByTitleContainingOrContentContainingOrNickNameContainingOrderByCreateDateDesc(String title, String content, String nickname);   
     }   
        
     PostingService.java    
     public List<PostingListResponseDto> getPostingList(String searchText){    
     List<Posting> postingList = postingRepository.findByTitleContainingOrContentContainingOrNickNameContainingOrderByCreateDateDesc(searchText, searchText, searchText);     
     return postingList.stream()   
                .map(PostingListResponseDto::fromEntity)   
                .collect(Collectors.toList()); 
</code>    
</pre>   

</details>   
</br>    

---

### 💡 느낀점 / 회고

- 협업의 민감도: 프론트/백엔드가 포트 분리된 환경에서, 필드명 하나만 바뀌어도 상호 영향이 큼을 체감했습니다.
- 보안 설정 학습: Spring Security에서 상세 페이지 /posting/detail/{postId} 접근 허용은
"/posting/list", "/posting/detail/**"처럼 **와일드카드 /****를 사용해야 함을 이해했습니다.
- 케이스 합의의 가치: 인증/인가, 공개 엔드포인트, 에러 응답 규약 등을 사전에 문서화하면 디버깅 비용이 크게 줄어듭니다.

