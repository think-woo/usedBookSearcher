## 프로그램 개요
중고서적을 대량으로 처분할 때, 인터넷 도서판매점에서 일일이 해당 서적의 ISBN을 입력하려면 매우 많은 시간이 소요된다. 그래서 목록을 한 번만 작성한다면, 해당 목록을 가지고 언제든지 매입가격을 확인할 수 있도록 프로그램을 작성했다.

## 사용 라이브러리
jsoup (https://jsoup.org/)

## 사용방법
1. <제목 ISBN>형식으로 txt파일에서 서적 목록을 작성한다.
2. 소스코드에서 해당 txt파일의 경로를 설정해준다.
3. 소스코드를 실행하여 원하는 도서판매점을 선택하면, 매입가격이 GUI를 통해 출력된다.

<div>
  <img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fk.kakaocdn.net%2Fdn%2FNp2T1%2FbtqBoPBT6kZ%2FDFv4rnZfghaVq9s1QQZFg0%2Fimg.png">
  <p><제목 ISBN>형식으로 작성된 txt파일. 중간에 꼭 띄어쓰기를 한 번 해야한다.</p>
    
  <img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fk.kakaocdn.net%2Fdn%2FLD3z6%2FbtqBpgF2KXP%2FNQN5ZWarGW11kvzp8GqeO1%2Fimg.png">
  <p>GUI를 통해 출력된 서적 목록. 가격이 없는 서적은 해당 도서판매점에서 취급하지 않는 서적이다.</p>
</div>
