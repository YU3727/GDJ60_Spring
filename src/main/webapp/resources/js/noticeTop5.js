//Ajax, Home에 Notice list5개 뿌리기
let xhttp = new XMLHttpRequest();

xhttp.open('GET', './notice/listTop');

xhttp.send();

xhttp.addEventListener('readystatechange', function(){
    if(this.readyState==4 && this.status==200){
        console.log(this.responseText);
        document.getElementById("noticeList").innerHTML=this.responseText.trim();
    }
});
