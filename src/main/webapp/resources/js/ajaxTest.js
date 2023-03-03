const btn = document.getElementById("btn");

btn.addEventListener("click", function(){

    //요청을 보내기 위한 빈 객체 생성.
    //요청은 URL, Method형식, [parameter]로 구성되어있다. 얘들을 넣어주면 된다
    let xhttp = new XMLHttpRequest();

    //noticelist 호출
    xhttp.open('GET', './notice/list?page=1');

    //요청 header 정보(POST방식으로 전송시)

    //parameter 전송(POST방식으로 전송시)

    //요청을 여기서 보낸다
    xhttp.send();

    //이벤트 거는거라 xhttp.onreadyState로 써도 됨
    xhttp.addEventListener('readystatechange', function(){
        if(this.readyState==4 && this.status==200){
            console.log('정상 요청 응답 종료');
            //responseText에 응답이 담겨져 온다
            console.log(this.responseText);
        }
    })
})