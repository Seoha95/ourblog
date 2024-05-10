## :pushpin: ourblog   
>개발자를 위한 블로그    
   
</br>   

### 1.제작기간&참여 인원   
* 2024.01.01 ~ 2024.02.09   
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
![Admin](https://github.com/Seoha95/ourblog/assets/107228582/4e0ae996-98e3-4bb6-a852-05b56d8881c4)
![Profile](https://github.com/Seoha95/ourblog/assets/107228582/d9071551-c6a4-420f-930e-2c5a2c7cbd69)   
![Posting](https://github.com/Seoha95/ourblog/assets/107228582/fb06c28c-c29a-4bdd-98e1-9cb257cadaf9)
![Heart](https://github.com/Seoha95/ourblog/assets/107228582/00713183-ee70-41ae-b592-9c8019c93b23)   
![Comment](https://github.com/Seoha95/ourblog/assets/107228582/acd8a181-89f0-4569-924c-e0ad96319d9a)   
![Category](https://github.com/Seoha95/ourblog/assets/107228582/767dc16f-a054-4eaf-8e46-fd7a4704c88a)

</br>   

### 4. 실행화면   
#### 로그인 화면   
![로그인](https://github.com/Seoha95/ourblog/assets/107228582/ac07562d-29f9-493c-8ea8-83140e1d281c)    
#### 회원가입 화면   
![회원가입](https://github.com/Seoha95/ourblog/assets/107228582/e903a5e4-36ea-47ac-a358-bda03eb50a08)     
#### 전체 게시물 보기   
![전체게시물](https://github.com/Seoha95/ourblog/assets/107228582/424bb69d-8c04-4b37-9837-e91af7fd1de6)    
#### 나의 블로그 화면   
![나의 블로그](https://github.com/Seoha95/ourblog/assets/107228582/600107b9-d8d5-48e2-a24c-ba0883d9494f)     
#### 그림자 효과   
![그림자효과](https://github.com/Seoha95/ourblog/assets/107228582/4e903ed5-180e-4c0c-8ea2-cd820be611e1)      
#### 4-6. 게시물 작성 화면   
![게시물 작성 화면](https://github.com/Seoha95/ourblog/assets/107228582/bf9221d3-570b-4184-a3d4-d9ed0b9e917b)    
#### 댓글작성 화면   
![댓글작성](https://github.com/Seoha95/ourblog/assets/107228582/6b9f8374-c7df-44e4-b650-5470c00fb645)   
#### 게시물 상세보기  
![상세보기](https://github.com/Seoha95/ourblog/assets/107228582/40f44fee-2c0a-46d9-99dc-60ad051c75aa)  
#### 마이페이지 화면   
![마이페이지](https://github.com/Seoha95/ourblog/assets/107228582/90c97972-fdb1-4dd2-b892-4c5f218c24d6)   
#### 관리자 페이지 화면   
![관리자 페이지](https://github.com/Seoha95/ourblog/assets/107228582/a1edcd45-cdf7-4c25-b8ae-0b3ab625a3a4)   

</br>      

### 5.핵심기능   
  * 로그인 시 토큰 발급 : 사용자가 로그인을 하면 토큰을 발급 해줍니다.   
  * 회원 정보 수정 : 마이페이지에서 정보를 수정하거나 회원탈퇴가 가능합니다. 
  * 게시물 기능 : 게시물을 조회, 작성, 수정, 삭제가 가능합니다. 
  * 댓글 기능 : 게시물에 댓글 작성, 조회, 수정, 삭제가 가능합니다. 
  * 좋아요 기능 : 게시물을 좋아요하거나 좋아요 취소를 할 수 있습니다. 
  * 검색 기능 : 사용자가 원하는 게시물을 검색하면 조회를 해줍니다.
  * 카테고리 기능 : 사용자가 카테고리를 추가, 수정, 삭제를 할 수 있고, 게시물을 카테고리별로 조회할 수 있습니다.
  * 관리자 기능 : 관리자는 회원의 정보를 조회 및 삭제를 할 수 있습니다.     

<details>
<summary>핵심기능설명펼치기</summary>   

#### 5-1. 로그인 시 토큰 발급
* 로그인 기능 📍[코드확인](https://github.com/Seoha95/ourblog/blob/a60351ce53debd4ebff276e1bd8da6208081459d/src/main/java/com/team/ourblog/service/AuthService.java#L51-L71)   
  * 사용자가 로그인시 어세스토큰 생성과 리프레쉬 토큰 생성 후 DB에 저장합니다.
#### 5-2. 회원 정보 수정 
* 회원 정보 수정 📍[코드확인](https://github.com/Seoha95/ourblog/blob/a60351ce53debd4ebff276e1bd8da6208081459d/src/main/java/com/team/ourblog/service/ProfileService.java#L19-L77)   
    * 프로필 이미지, 닉네임, 이메일, 비밀번호를 수정할 수 있습니다.
* 회원 탈퇴 📍[코드확인](https://github.com/Seoha95/ourblog/blob/9cf13fbbbc2b31ef474f6c1894a0ba55f8e01a50/src/main/java/com/team/ourblog/service/AuthService.java#L102-L110)  
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
#### 5-7 카테고리 기능
* 카테고리 기본 4개 생성 기능 📍[코드확인](https://github.com/Seoha95/ourblog/blob/94c313fbbdd3c273c04f4fdc667031519ee0204b/src/main/java/com/team/ourblog/service/MemberService.java#L65-L78)
   * 회원가입시 기본적으로 4개의 카테고리가 생성되도록 구현 했습니다.
* 카테고리 추가 기능 📍[코드확인](https://github.com/Seoha95/ourblog/blob/94c313fbbdd3c273c04f4fdc667031519ee0204b/src/main/java/com/team/ourblog/service/CategoryService.java#L22-L33)
   * 사용자가 카테고리를 추가해서 원하는 이름으로 저장할 수 있습니다.   
* 카테고리 수정 기능 📍[코드확인](https://github.com/Seoha95/ourblog/blob/94c313fbbdd3c273c04f4fdc667031519ee0204b/src/main/java/com/team/ourblog/service/CategoryService.java#L35-L44)
   * 사용자가 기본적으로 제공되는 카테고리나 새로 추가한 카테고리의 이름을 수정할 수 있습니다.    
* 카테고리 삭제 기능 📍[코드확인](https://github.com/Seoha95/ourblog/blob/94c313fbbdd3c273c04f4fdc667031519ee0204b/src/main/java/com/team/ourblog/service/CategoryService.java#L46-L50)
   * 사용자가 선택한 카테고리를 삭제할 수 있습니다.    
#### 5-7. 관리자 기능 
* 회원의 정보를 전체 조회 기능 📍[코드확인](https://github.com/Seoha95/ourblog/blob/a60351ce53debd4ebff276e1bd8da6208081459d/src/main/java/com/team/ourblog/service/MemberService.java#L80-L85)
   * 관리자가 전체 회원의 정보를 볼 수 있습니다.     
* 회원의 정보 관리 기능 📍[코드확인](https://github.com/Seoha95/ourblog/blob/a60351ce53debd4ebff276e1bd8da6208081459d/src/main/java/com/team/ourblog/service/MemberService.java#L86-L92) 
    * 관리자의 권한으로 회원의 정보를 삭제할 수 있습니다.
</br>
</details>   

### 6.핵심 트러블 슈팅
#### 6-1. 검색이 안되는 버그   
제목, 내용, 닉네임으로 게시물을 검색할 수 있도록 구현하였으나 검색이 되지 않는 버그가 발생했습니다.   
코드를 적어줄 때 검색어가 들어오는 것을 구현할 때 필터링 하는 주제만큼 searchText 매개변수를 넣어줘야 했었는데 1개의 매개변수의 값만 적어주어   
제대로 기능이 돌아가지 않는 문제가 발생하여 매개변수를 3개를 작성해서 제 기능을 할 수 있었습니다.   
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

### 6. 느낀점   
처음으로 프론트엔드 개발자와의 협업이라서 서로 다른 포트를 사용하고 요청받은 데이터를 통해서 데이터를 응답하는 과정이 어려웠습니다.    
그러나 이렇게 협업을 통해서 변수 하나도 변경할 때에도 프론트 쪽에 영향이 간다는 것을 알 수 있게 되었습니다.   
그리고 Security Config에서 api url이 /posting/detail/{postId}로 받아야 하는 곳은 .requestMatchers("/posting/list","posting/detail/**").permitAll() {postId}로 들어오는 자리는    
/**로 코드를 적어줘야 제대로 돌아간다는 것을 알게 되었습니다. 이 프로젝트를 통해서 협업을 할 때는 팀원과의 소통이 중요하다는 것을 배울 수 있었습니다.        

