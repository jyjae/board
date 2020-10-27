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
<img src="https://github.com/Jeongwonseok/Portfolio_JWS/blob/master/image/smart/구성.png" width="300" height="300"><br>

****
# 3. 제공 기능
<img src="https://github.com/Jeongwonseok/Portfolio_JWS/blob/master/image/smart/메인.png" width="226" height="396"><br>


* **로그인**
```
* 사용자에게 수면 모드의 강, 약 제어를 제공한다.
* 클릭 시, 수면 모드 가동 시간을 입력받는다.
* 가동 시간 입력 후, 딜레이를 통해 타이머 기능을 제공한다.
```

* **로그아웃**
```
* 사용자에게 집중 모드의 강, 약 제어를 제공한다.
* 클릭 시, 집중 모드 가동 시간을 입력받는다.
* 가동 시간 입력 후, 딜레이를 통해 타이머 기능을 제공한다.
```

* **가습 모드**
```
* 사용자에게 가습 모드의 강, 약 제어를 제공한다.
* 클릭 시, 가습 모드 가동 시간을 입력받는다.
* 가동 시간 입력 후, 딜레이를 통해 타이머 기능을 제공한다.
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
    
    
 
