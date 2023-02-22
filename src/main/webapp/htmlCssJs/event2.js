const b1 = document.getElementById("b1"); //button
const p1 = document.getElementById("p1"); //div

const b2 = document.getElementById("b2"); //button
const p2 = document.getElementById("p2"); //div

const naver = document.getElementById("naver"); //a


naver.addEventListener('click', function(event){
    console.log('naver click');
    //element에 내장되어 있는 동작을 막아줄 때 사용. 버블링, 캡쳐링과 무관
    event.preventDefault;
    // return false;
});


//캡쳐링과 위임중단
b1.addEventListener('click', function(e){
    //실행했을때 수행되어야 할 함수 : 콜백함수
    console.log('button1 click');
    console.log(e);
}, true);

//매개변수의 이름은 고정되어있지 않음. 개발자마음
p1.addEventListener('click', function(event){
    console.log('div1 click');
    console.log(event);
    event.stopPropagation();
}, true);


//버블링과 위임중단 - 기본옵션이므로 false 생략가능
b2.addEventListener('click', function(e){
    //실행했을때 수행되어야 할 함수 : 콜백함수
    console.log('button2 click');
    console.log(e);
    // e.stopPropagation();
    console.log("Button This : "+this);
}, false);

p2.addEventListener('click', function(event){
    console.log('div2 click');
    console.log(event);
    console.log("currentTarget : ",event.currentTarget);
    console.log("Target : ",event.target);
    console.log("This : "+this);
}, false);