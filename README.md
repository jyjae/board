# board
======================

# 1. 개요
## 1.1. 주제
웹사이트 게시판

## 1.2. 목적 / 목표
웹 사이트 로그인, 로그아웃 게시판 글쓰기, 삭제, 수정, 답글 쓰기 기능을 제공하고<br>
관리자(admin)과 일반사용자를 분리하여 관리자는 회원 관리를 할 수 있게 한다.

## 1.3. 개발 범위
*  **로그인**<br>
-입력한 아이디와 비밀번호가 테이블에 저장되어있다면 로그인된다.<br>

*  **로그아웃**<br>
-세션을 무효화시킨다.

*  **회원가입**<br>
-아이디, 비밀번호, 이름, 나이, 성별, 이메일주소를 등록한다<br>

*  **게시판 글쓰기**<br>
-글 비밀번호, 제목, 내용 작성과 파일첨부 후 글을 등록한다.<br>

*  **게시판 글수정**<br>
-글 비밀번호를 입력한후 맞으면 제목과 내용, 파일첨부를 수정할 수 있다.<br>

*  **게시판 글삭제**<br>
-글을 삭제한다.<br>

*  **답변 달기**<br>
-다른 사람의 원문글에 답변을 달 수 있다.<br>

## 1.4. 개발 환경
* eclipse

****
# 2. 구성

## 2.1. SW 구성
![board_sw_struct](https://user-images.githubusercontent.com/52684942/97268033-97f7ca00-186e-11eb-9b4b-00a4162d9dcb.PNG)<br>

## 2.2. DB 구성
### 2.2.1 member 테이블
![member_table](https://user-images.githubusercontent.com/52684942/97269923-a1cefc80-1871-11eb-9079-4166b3465b87.PNG)
<br>

### 2.2.2 board 테이블
![board_table](https://user-images.githubusercontent.com/52684942/97269948-aabfce00-1871-11eb-896b-bd89b33cc8a6.PNG)<br>


****
# 3. 제공 기능
![login](https://user-images.githubusercontent.com/52684942/97276574-b95eb300-187a-11eb-855d-b373509d8505.PNG)

* **로그인**
```
* 잘 못된 아이디를 입력하면 '아이디가 존재하지 않습니다'라는 경고창이 뜬다.
* 잘 못된 비밀번호를 입력하면 '비밀번호가 일치하지 않습니다'라는 경고창이 뜬다.
* '아이디 기억하기' 체크박스를 누르고 로그인하면 로그아웃을 해도 로그인 화면 아이디 text부분에 아이디가 입력되어져 있다.
* 옳바른 아이디와 비밀번호를 입력하면 로그인 된다.
```
* **관리자와 일반사용자**
```
* 관리자(admin)의 header에는 회원정보, 게시판 관리도 포함되어있다.
* 일반 사용자의 header에는 정보수정만 있다.
```

* **로그아웃**
```
* 로그아웃 앵커를 누르면 세션이 무효화되면서 로그아웃 된다.
```
![join](https://user-images.githubusercontent.com/52684942/97276599-c2e81b00-187a-11eb-9483-e53027b53f95.PNG)<br>

* **회원가입**
```
* 아이디를 입력하지 않고 회원가입 버튼을 누르면 경고창이 뜬다.
* 비밀번호를 입력하지 않고 회원가입 버튼을 누르면 경고창이 뜬다.
* 이름을 입력하지 않고 회원가입 버튼을 누르면 경고창이 뜬다.
* 나이을 입력하지 않고 회원가입 버튼을 누르면 경고창이 뜬다.
* 성별을 선택하지 않고 회원가입 버튼을 누르면 경고창이 뜬다.
* 이메일 주소를 입력하지 않고 회원가입 버튼을 누르면 경고창이 뜬다.
* 다시작성 버튼을 누르면 리셋된다.
* 회원가입 버튼을 누르면 회원가입된다.
```
![board_write](https://user-images.githubusercontent.com/52684942/97395983-7f98b580-1929-11eb-906d-771a5a2a92c7.PNG)

* **글쓰기**
```
* 게시판에서 글쓰기 버튼이 누르면 글 작성 화면이 나온다.
* 글쓴이 text에는 로그인되어져있는 사용자의 아이디가 출력되어져 나탄난다.
* 비밀번호와, 제목, 내용, 파일을 첨부할 수 있다.
```
![board_view](https://user-images.githubusercontent.com/52684942/97395991-8293a600-1929-11eb-812a-3b57c1df6bad.PNG)

* **글 수정**
```
* 수정 버튼을 누르고 글 비밀번호와 일치하는 비밀번호를 입력하면 글을 수정할 수 있다.
```

* **글 삭제**
```
* 삭제 버튼을 누르고 글 비밀번호와 일치하는 비밀번호를 입력하면 글을 삭제할 수 있다.
```

* **답변 달기 **
```
* 답변을 쓰고 등록 버튼을 누르면 댓글 버튼 위에 숫자가 증가한다.
```
****
# 4. 사용 기술
## 4.1. 블루투스 통신 (BlutoothSPP)
![통신1](https://github.com/Jeongwonseok/Portfolio_JWS/blob/master/image/smart/통신1.png)
```
설명 : 블루투스 변수를 선언하고, 생성자를 정의한다.
```
![통신2](https://github.com/Jeongwonseok/Portfolio_JWS/blob/master/image/smart/통신2.png)
```
설명 : 아두이노에서 온 데이터를 data 배열에 넣어 합친 후, message를 통해 Toast 메시지를 출력한다.
```
![통신3](https://github.com/Jeongwonseok/Portfolio_JWS/blob/master/image/smart/통신3.png)
```
설명 : 블루투스 연결 버튼 변수를 선언하고, 페어링 되어있는 기기 목록 출력 화면을 생성한다.
```
![통신4](https://github.com/Jeongwonseok/Portfolio_JWS/blob/master/image/smart/통신4.png)
```
설명 : 블루투스 중지 및 시작 함수를 선언한다.
```

## 4.2. 핸들러
![핸들러1](https://github.com/Jeongwonseok/Portfolio_JWS/blob/master/image/smart/핸들러1.png)
```
설명
- 블루투스 연결 완료 후, 사용 준비가 되면 자동으로 실행되는 setup() 함수를 정의한다.
- 종료 버튼 클릭 시, 이전에 진행중이던 예약를 전부 지운다. (Handler 사용)
```
![핸들러2](https://github.com/Jeongwonseok/Portfolio_JWS/blob/master/image/smart/핸들러2.png)
![핸들러3](https://github.com/Jeongwonseok/Portfolio_JWS/blob/master/image/smart/핸들러3.png)
```
설명
- 메인스레드와 서브스레드 간에 Handler를 통해 메시지를 전달하여 메시지 큐에 저장하는 방식의 통신을 사용한다.
- FIFO(First In First OUT) 방식으로 먼저 전달 받은 메시지를 먼저 처리한다.
```

****
# 5. 부록
## 5.1. 참여 목록
* 
    
    
 
