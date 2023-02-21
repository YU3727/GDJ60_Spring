//8. event

//element 가져오기
const d1 = document.getElementById("d1");
const input1 = document.getElementById("input1");
const input2 = document.getElementById("input2");
const input3 = document.getElementById("input3");
const btn = document.getElementById("btn");

// 이벤트 호출방법 3, addEventListener 함수 호출
//콜백함수로 이벤트시 실행할 함수 만들기
d1.addEventListener("click", function(){
    console.log("Click Event");
});

d1.addEventListener("mouseenter", function(){
    console.log("MouseEnter Event");
});

input1.addEventListener("keyup", function(){
    console.log("KeyUp Event");
});

input2.addEventListener("focus", function(){
    console.log("Focus Event");
});

input2.addEventListener("blur", function(){
    console.log("Blur Event");
});

input2.addEventListener("change", function(){
    console.log("Change Event");
}); //얘는 값을 입력하거나 blur 이벤트가 발생하면 저장됨

input3.addEventListener("change", function(){
    console.log(input3.value);
})

// 이벤트 호출방법2, id명.전치사 on + 이벤트 이름
btn.onclick=function(){
    input2.focus();
};