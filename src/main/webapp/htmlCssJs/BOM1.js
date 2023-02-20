//가지고 오기만 한것
const btn1 = document.getElementById("btn1");
//눌렀을 때(~ 했을 때 는 event라고 부름)
//이 이벤트(click)를 등록한다
btn1.addEventListener("click", function(){
    
    //c2를 선택하고 그 안에 text를 출력
    let c2 = window.document.getElementById("c2");
    console.log(c2.innerText);
    console.log(c2.innerHTML);
    // c2.innerText = 'test';
    //c2의 태그변경까지 가능. innerText는 text를 변경
    c2.innerHTML='<img src="../resources/images/s1.jpg">';
});

//btn2를 눌렀을 떄 id가 c3인 element의 title 이름을 가져오기
const btn2 = document.getElementById("btn2");

btn2.addEventListener("click", function(){
    let c3 = document.getElementById("c3");
    console.log(c3.title);
    console.log(c3.getAttribute("title"));
});

//내가 입력한 글자가 확인 버튼을 눌렀을 때 console에 찍히게 하기
const btn3 = document.getElementById("btn3");

btn3.addEventListener("click", function(){
    let in1 = document.getElementById("in1");
    let v = in1.value;
    console.log(v);

});


let c1 = window.document.getElementById("c1");
//get: 가지고오자, element: 엘리먼트 자체를, by id: id로 구분해서

//alert, console. 앞에는 window.가 생략되어있다.
// alert(c1);
console.log(c1);

//엘리먼트를 꺼내와서 어떤걸 바꿀지 결정(tag, 속성, contetns 변경가능)

//c1의 contents를 확인
console.log(c1.innerText);
console.log(c1.innerHTML);

//c1의 contents 글자를 바꾸고싶다
// c1.innerText = 'change Text';


//무조건 실행하는게 아니라 '~했을 때' 실행시키고 싶다. -> 조건을 줌
