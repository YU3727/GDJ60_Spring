//web browser는 개발자가 문법적으로 조금 틀리게 쓰더라도 실행시켜주려 노력함.
// 뭔가 확인하려면 출력을 해봐야한다
console.log("Hello, world!");
//얘는 단독으로는 못쓴다 html이 있어야함

//JavaScript는 인터프리터 언어(interpretive language)이다

//변수선언과 대입
//데이터타입 변수명;
let num1 = 3;
num1 = "abc";

//옛날 버전, Hoisting에 문제가 발생한다. 그래서 지금은 쓰지 말것을 권장한다
//그래서 지금은 let을 사용
var num2 = 4;

//const는 상수형 변수. 한번 대입하면 값을 변경할 수 없다.(java에서 final이 붙은것과 같은 역할)
const num3 = 5;
// num3 = 6;


//연산자 - java와 유사함
let num4 = null;
num4 = num1+num2; //-, *, /, %
num4 = num1 > num2; //<, >=, <=, ==, !=, ===, !==

//증감연산자
num4 = 1;
num4 = num4 + 1;
num4++;

num1 = 3;
num2 = '3';

//타입연산자
///변수 선언을 let으로 하기 때문에 어떤 데이터타입인지 확인하고싶을 때 사용하는 연산자이다
num4 = 'abc';
num4 = null;
console.log(typeof num4);

//같은 값이면 타입과 상관없이 true를 반환
console.log(num1 == num2);
///equal 연산자 3개는 값과 데이터타입 모두 같은지를 비교하는 연산자
console.log(num1 === num2);


console.log("num3 : "+num3);
console.log('num1 : '+num1);