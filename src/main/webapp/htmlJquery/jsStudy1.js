//querySelector를 쓸때는 css 선택자 쓰는것과 동일하게

const btn1 = document.querySelector("#btn1")
// const btn1 = document.getElementById("btn1"); 얘와 같은 의미

const buttons = document.querySelectorAll(".buttons");
// const buttons = document.getElementsByClassName("buttons");

//querySelector == querySelectorAll[0];

//이런 유형도 가능하다
const d1 = document.querySelector("#d1 > .buttons li");


btn1.addEventListener("click", function(){
    console.log("btn1");
});


for(let b of buttons){
    b.addEventListener("click", function(){
        console.log("buttons");
    })
}