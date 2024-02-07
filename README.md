## :pushpin: ourblog   
>개발자를 위한 블로그    
   
</br>   

### 1.제작기간&참여 인원   
* 2024.01.01 ~ 진행중   
* 팀 프로젝트(2명)   

</br>

### 2.사용기술   
* JAVA17   
* MySQL   
* spring Security   
* JWT   
* Spring Data Jpa   
       
 </br>     

 ### 3. API 설계 
 ---   
 ![Member](https://github.com/Seoha95/ourblog/assets/107228582/4b4056ba-bb1e-42c5-af66-823252da630d)   
 ![Admin](https://github.com/Seoha95/ourblog/assets/107228582/2a653696-106a-4faa-9998-e9cb54e94f97)   
![Profile](https://github.com/Seoha95/ourblog/assets/107228582/d9071551-c6a4-420f-930e-2c5a2c7cbd69)   
![Posting](https://github.com/Seoha95/ourblog/assets/107228582/ad9aff2b-ece7-4d1f-aa83-1f9b34e1d73e)   
![Heart](https://github.com/Seoha95/ourblog/assets/107228582/00713183-ee70-41ae-b592-9c8019c93b23)   
![Comment](https://github.com/Seoha95/ourblog/assets/107228582/acd8a181-89f0-4569-924c-e0ad96319d9a)   
![Category](https://github.com/Seoha95/ourblog/assets/107228582/997c46bf-1187-4732-b659-307f82b35831)   

</br>   

### 4. 실행화면   

</br>      

### 5.핵심기능   
  * 로그인 시 토큰 발급 : 사용자가 로그인을 하면 토큰을 발급 해줍니다.   
  * 회원 정보 수정 : 마이페이지에서 정보를 수정하거나 회원탈퇴가 가능합니다. 
  * 게시물 기능 : 게시물을 조회, 작성, 수정, 삭제가 가능합니다. 
  * 댓글 기능 : 게시물에 댓글 작성, 조회, 수정, 삭제가 가능합니다. 
  * 좋아요 기능 : 게시물을 좋아요하거나 좋아요 취소를 할 수 있습니다. 
  * 검색 기능 : 사용자가 원하는 게시물을 검색하면 조회를 해줍니다.   
  * 관리자 기능 : 관리자는 회원의 정보를 조회 및 삭제를 할 수 있습니다.     

<details>
<summary>핵심기능설명펼치기</summary>   

#### 5-1. 로그인 시 토큰 발급
* 로그인 기능 📍[코드확인](https://github.com/Seoha95/ourblog/blob/a60351ce53debd4ebff276e1bd8da6208081459d/src/main/java/com/team/ourblog/service/AuthService.java#L51-L71)   
  * 사용자가 로그인시 어세스토큰 생성과 리프레쉬 토큰 생성 후 DB에 저장합니다.
#### 5-2. 회원 정보 수정 
* 회원 정보 수정 📍[코드확인](https://github.com/Seoha95/ourblog/blob/a60351ce53debd4ebff276e1bd8da6208081459d/src/main/java/com/team/ourblog/service/ProfileService.java#L19-L77)   
    * 프로필 이미지, 닉네임, 이메일, 비밀번호를 수정할 수 있습니다.
* 회원 탈퇴 📍[코드확인]()  
    * 회원은 마이페이지에서 회원탈퇴를 할 수 있습니다.
#### 5-3. 게시물 기능 
* 전체 게시물 조회 기능 📍[코드확인](https://github.com/Seoha95/ourblog/blob/a60351ce53debd4ebff276e1bd8da6208081459d/src/main/java/com/team/ourblog/service/PostingService.java#L27-L33)   
    * 회원과 비회원 모두 메인페이지에서 전체 게시물을 볼 수 있습니다. 
* 내 블로그 게시물 조회 기능 📍[코드확인](https://github.com/Seoha95/ourblog/blob/a60351ce53debd4ebff276e1bd8da6208081459d/src/main/java/com/team/ourblog/service/PostingService.java#L35-L41)  
    * 내블로그를 클릭하면 로그인한 사용자의 포스팅한 게시물만 조회를 합니다.
* 게시물 작성 기능 📍[코드확인](https://github.com/Seoha95/ourblog/blob/a60351ce53debd4ebff276e1bd8da6208081459d/src/main/java/com/team/ourblog/service/PostingService.java#L57-L69)  
    * 회원만이 게시물을 작성할 수 있습니다. 
* 게시물 수정 기능 📍[코드확인](https://github.com/Seoha95/ourblog/blob/a60351ce53debd4ebff276e1bd8da6208081459d/src/main/java/com/team/ourblog/service/PostingService.java#L78-L86) 
    * 게시물을 작성한 사용자가 게시물을 수정할 수 있습니다. 
* 게시물 삭제 기능 📍[코드확인](https://github.com/Seoha95/ourblog/blob/a60351ce53debd4ebff276e1bd8da6208081459d/src/main/java/com/team/ourblog/service/PostingService.java#L88-L91) 
    * 게시물을 작성한 사용자가 게시물을 삭제할 수 있습니다.
#### 5-4. 댓글 기능 
* 댓글 조회 기능 📍[코드확인](https://github.com/Seoha95/ourblog/blob/a60351ce53debd4ebff276e1bd8da6208081459d/src/main/java/com/team/ourblog/service/CommentService.java#L29-L35) 
    * 회원과 비회원 모두 게시물에 대한 댓글 리스트를 볼 수 있습니다.
* 댓글 작성 기능 📍[코드확인](https://github.com/Seoha95/ourblog/blob/a60351ce53debd4ebff276e1bd8da6208081459d/src/main/java/com/team/ourblog/service/CommentService.java#L37-L54) 
    * 회원만 게시물에 댓글을 작성할 수 있습니다. 
* 댓글 삭제 기능 📍[코드확인](https://github.com/Seoha95/ourblog/blob/a60351ce53debd4ebff276e1bd8da6208081459d/src/main/java/com/team/ourblog/service/CommentService.java#L56-L67) 
    * 게시물에 댓글을 작성한 작성자만 삭제할 수 있습니다. 
#### 5-5. 좋아요 기능 
* 게시물 좋아요 기능 📍[코드확인](https://github.com/Seoha95/ourblog/blob/a60351ce53debd4ebff276e1bd8da6208081459d/src/main/java/com/team/ourblog/service/HeartService.java#L26-L46) 
    * 게시물 상세보기에 들어가서 회원만 좋아요를 누를 수 있습니다. 
* 게시물 좋아요 취소 기능 📍[코드확인](https://github.com/Seoha95/ourblog/blob/a60351ce53debd4ebff276e1bd8da6208081459d/src/main/java/com/team/ourblog/service/HeartService.java#L48-L64) 
    * 게시물 상세보기에 들어가서 좋아요를 눌렀던 회원이 좋아요 취소를 할 수 있습니다. 
#### 5-6. 검색 기능 
* 게시물 검색 기능 📍[코드확인](https://github.com/Seoha95/ourblog/blob/a60351ce53debd4ebff276e1bd8da6208081459d/src/main/java/com/team/ourblog/service/PostingService.java#L42-L48) 
    * 제목, 내용, 닉네임 중에서 한 글자만 검색해도 연관된 모든 게시물이 조회될 수 있게 구현했습니다. 
#### 5-7. 관리자 기능 
* 회원의 정보를 전체 조회 기능 📍[코드확인](https://github.com/Seoha95/ourblog/blob/a60351ce53debd4ebff276e1bd8da6208081459d/src/main/java/com/team/ourblog/service/MemberService.java#L80-L85)
   * 관리자가 전체 회원의 정보를 볼 수 있습니다.     
* 회원의 정보 관리 기능 📍[코드확인](https://github.com/Seoha95/ourblog/blob/a60351ce53debd4ebff276e1bd8da6208081459d/src/main/java/com/team/ourblog/service/MemberService.java#L86-L92) 
    * 관리자의 권한으로 회원의 정보를 삭제할 수 있습니다.
</br>
</details>   

### 6.핵심 트러블 슈팅


</br>

### 6. 느낀점

