const replyAdd = document.getElementById("replyAdd");
const replyContents = document.getElementById("replyContents");
const replyList = document.getElementById("replyList");


//Ajax, 댓글 등록 이벤트
replyAdd.addEventListener("click", function(){
    console.log("ok");
    
    let xhttp = new XMLHttpRequest();
    xhttp.open('POST', '../bankBookComment/add');
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    //요청 발생, Post일 경우 parameter 전송
    //parameter 이름과 memberDTO의 setter 이름이 같게 세팅
    xhttp.send("contents="+replyContents.value+"&bookNumber="+replyAdd.getAttribute('data-idx-bookNumber'));

    xhttp.addEventListener('readystatechange', function(){
        if(this.readyState==4 && this.status==200){
            //성공했을 때 입력할 내용 실행
            if(this.responseText.trim()==1){
                alert("댓글이 등록 되었습니다");
                replyContents.value='';
                //다시 댓글리스트를 불러오기(갱신을 위해)
                getList();
            }else {
                alert("등록에 실패했습니다");
            }  
        }
    });
});


getList();

//Ajax, bankBookDetail에 reply list 5개 뿌리기
function getList(){
    let xhttp = new XMLHttpRequest();
    xhttp.open('GET', '../bankBookComment/list?&bookNumber='+replyAdd.getAttribute('data-idx-bookNumber'));
    xhttp.send();

    xhttp.addEventListener('readystatechange', function(){
        if(this.readyState==4 && this.status==200){
            // console.log(this.responseText);
            replyList.innerHTML=this.responseText.trim();
        }
    });
}