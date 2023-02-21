
const d1 = document.getElementById("d1");

//전역변수
let n = 'iu';
var old = 'iu';



//function 선언
function f1 (){
    console.log("function test");
    console.log(n);
    var old = 'winter';
}

//매개변수 선언시 let, const는 생략해야한다
function f2 (n1, n2){
    //지역변수
    let name = 'iu';
    return n1+n2;
}

//function 호출
f1();
let result = f2(1,2);
console.log(result);
console.log(name);
//console.log(n1);
console.log("old : "+old);

//익명함수는 변수에 담을수도 있다
let fun = function (){
    console.log("익명함수");
    return 1;
}

//익명함수의 호출
fun();

//매개변수의 인자값으로 타입이 아닌 함수도 받을 수 있다
//함수를 담은 변수명을 매개변수로 줘야함
function f3(f){
    //(,)는 여러개를 출력하는 기능
    console.log('f3 : ',f,1);
}

function callback1 (){
    console.log('callback1');
}

function callback2 (){
    console.log("callback2");
}

//다른곳에서도 같은 함수를 사용하고 싶을 때
//d1.addEventListener("click", callback1);
//d1.addEventListener("click", callback2);

//여기서만 사용하는 함수
d1.addEventListener("click", function(){
    callback1();
    callback2();
});
