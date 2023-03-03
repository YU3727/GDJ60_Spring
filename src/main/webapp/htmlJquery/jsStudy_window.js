const btn1 = document.getElementById("btn1");


btn1.addEventListener("click", function(){
    //jsp 띄울거면 Controller로 가는 주소 써야한다
    // window.open("./new.html", 'test', 'width=400, height=100, top=100, left=200');
    console.log('interval finish');
    clearInterval(st);
})

//setTimeout : 일정한 시간후에 한번 실행할 것
//setTimeout(실행해야할 콜백함수, 지나야할 시간)
setTimeout(function(){
    alert("timeout");
}, 3000);

//setInterval : 일정한 시간 간격으로 반복적으로 시행하는 것
let st = setInterval(function(){
    console.log('interval');
}, 1000);