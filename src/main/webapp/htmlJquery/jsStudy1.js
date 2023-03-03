//querySelector를 쓸때는 css 선택자 쓰는것과 동일하게

const btn1 = document.querySelector("#btn1")
// const btn1 = document.getElementById("btn1"); 얘와 같은 의미

const buttons = document.querySelectorAll(".buttons");
// const buttons = document.getElementsByClassName("buttons");

//querySelector == querySelectorAll[0];

//이런 유형도 가능하다
const d1 = document.querySelector("#d1 > .buttons li");

//여러 유형의 for문
let ar = [1, 2, 3];
console.log("for old");
for(let i=0; i<ar.length; i++){
    console.log(ar[i]);
}

console.log("for of"); //일반적으로 많이 쓰는 반복문
for(let i of ar){
    console.log(i);
}

console.log("for in");
for(let j in ar){
    //주로 JavaScript에서 객체의 멤버들을 순환해서 다 찍어낼 때 사용
    console.log(j);
    console.log(ar[j]);
}

console.log("for each"); //배열인 경우 많이 쓰는 반복문
ar.forEach(function(v, i, list){
    console.log("v: ",v, "i: ",i, "ar: ",list);
});

btn1.addEventListener("click", function(){
    console.log("btn1");
});


for(let b of buttons){
    b.addEventListener("click", function(){
        console.log("buttons");
    })
}

// alert(buttons);
// alert(ar);
console.log(buttons);
console.log(ar);

buttons.forEach(function(v, i){
    v.addEventListener("click", function(){
        console.log("click");
    })
});

//숫자로 바꾸는법(parseInt, 문자열*1)
console.log(parseInt("1")+1);
console.log("1"*1 +1);