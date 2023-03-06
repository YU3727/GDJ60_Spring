const replyAdd = document.getElementById("replyAdd");
const replyContents = document.getElementById("replyContents");
const replyList = document.getElementById("replyList");
// const pageLink = document.querySelectorAll(".page-link"); //css선택자처럼
//elementsByClassName = HTML~형태, querySelectorAll = NodeList

let updatePhase = 0;

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
                getList(1);
            }else {
                alert("등록에 실패했습니다");
            }  
        }
    });
});


getList(1);

//Ajax, bankBookDetail에 reply list 5개 뿌리기
//JavaScript에서 매개변수 선언할때는 let 안붙임
function getList(page){

    let count=0;

    let xhttp = new XMLHttpRequest();
    xhttp.open('GET', '../bankBookComment/list?&bookNumber='+replyAdd.getAttribute('data-idx-bookNumber')+'&page='+page);
    xhttp.send();

    xhttp.addEventListener('readystatechange', function(){
        if(this.readyState==4 && this.status==200){
            // console.log(this.responseText);
            replyList.innerHTML=this.responseText.trim();
            count++;
        }
        // console.log("count1 : "+count);
    });

    //여기서는 0일수도 1일수도 있다. 동기방식이면 1이 나오는데, 비동기방식이기 때문에 응답을 대기하지않고 자신의 할일을 한다.
    //이벤트가 걸려있는부분은 이벤트가 발생할 때 응답을 처리하고, 나머지 코드를 진행한다. 비동기방식에서 응답을 받은후에 일처리를 하고싶으면
    //이벤트발생 코드 내에 작성을 해야한다
    // console.log("count2 : "+count);
}

//pagination
//Event위임
//자식영역에 클릭 -> 부모영역(replyList)의 이벤트 발생
replyList.addEventListener("click", function(e){
    let pageLink = e.target;
    if(pageLink.classList.contains("page-link")){
        //리스트 보낼때 파라미터로 페이지번호 보낼려고 속성 가져옴
        let page = pageLink.getAttribute("data-board-page");
        getList(page);
    }

    //눌렀을 때 #뒤에 id가 오면 해당 id쪽으로 가게 된다. 아무것도 안붙여주면 맨위로 가짐(맨 위로 버튼 기능 구현)
    e.preventDefault();
})


//delete - css 디자인을 위해 만든 클래스명 말고 다른걸 쓰자
//위에거랑 클릭 이벤트가 같은데 되나 확인해야함.
//잘 작동된다. 이벤트는 부모한테 거는게 아니라 자식한테 거는거처럼 하기때문에
replyList.addEventListener("click", function(e){
    let deleteButton = e.target;
    if(deleteButton.classList.contains("del")){
        // console.log("delete");
        let xhttp = new XMLHttpRequest();
        xhttp.open('POST', '../bankBookComment/delete');
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("num="+deleteButton.getAttribute("data-comment-num"));

        xhttp.addEventListener('readystatechange', function(){
            if(this.readyState==4 && this.status==200){
                let result = this.responseText.trim();
                if(result>0){
                    alert('댓글이 삭제되었습니다');
                    //얘는 여기에 써야 제대로 작동함, 이벤트리스너 끝나고 쓰면 이벤트 실행 전의 리스트를 불러와서 의미가없다
                    getList(1);
                }else{
                    alert('삭제 실패');
                }

            }
        });
    }

    e.preventDefault();
})


//update - 내가 만든거. 내용이 안들어가는거만 수정하면 될거같음 ㅠㅠ
//입력창 뜨고, 입력되면 contents 수정이 되어야 하는데 입력을 어떻게 받을까
// replyList.addEventListener("click", function(e){

//     let updateButton = e.target;
//     if(updateButton.classList.contains("update")){
//         // console.log('update');

//         let num = updateButton.getAttribute("data-comment-num");

//         const fixComment = document.getElementById("contents"+num);
//         let comment = fixComment.innerText;
//         // console.log("fixComment before: "+fixComment);
//         // console.log("comment: "+comment);

//         //업데이트 버튼이 눌려지면, 내용입력창이 먼저 떠야함
//         // fixComment.innerHTML='<td><input type="text" value='+comment+'></td>';
        
//         //이거 말고 createElement로 하는게 나을지도 모르겠다
//         let inputForm = document.createElement("input");
//         let attr = document.createAttribute("type");
//         attr.value='text';
//         inputForm.setAttributeNode(attr);

//         attr = document.createAttribute("value");
//         attr.value=comment;
//         inputForm.setAttributeNode(attr);

//         attr = document.createAttribute("id");
//         attr.value='content'+num;
//         inputForm.setAttributeNode(attr);
        
//         console.log(inputForm);
//         console.log("updatephase1: "+updatePhase);

//         const uButton = document.getElementById("uButton");
//         uButton.innerText='Done';
//         updatePhase++;

//         if(updatePhase==1){
//             fixComment.appendChild(inputForm);
//         }


//         //일단 수정 텍스트폼까진 나오게 했음
//         //기존 글 내용 없애고 수정텍스트가 나오게 하면 좋겠다


//         //내용이 입력되면 contents랑 같이 num이 넘어가야함
//         //update 버튼 누르자마자 이렇게 되는데 대기하는 장치가 필요하다
//         if(updatePhase==2){
//             console.log("updatephase2: "+updatePhase);
            
//             //blur 이벤트로 input 태그에 value를 변경시켜줘야할거같음
//             inputForm.addEventListener("blur", function(){
//                 let text = document.getElementById("content"+num);
//                 console.log(text);

//                 // console.log(inputForm.getAttribute("id"));
//                 // let inputValue = document.getElementById(inputForm.getAttribute("id")).value;
//                 // let attri = document.createAttribute("value");
//                 // attri.value=inputValue;
//                 // inputForm.setAttributeNode(attri);
//             })

//             let xhttp = new XMLHttpRequest();
//             xhttp.open('POST', '../bankBookComment/update');
//             xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//             xhttp.send('num='+updateButton.getAttribute("data-comment-num")+'&contents='+inputForm.getAttribute("value"));
    
    
//             xhttp.addEventListener('readystatechange', function(){
//                 if(this.readyState==4 && this.status==200){
//                     let result = this.responseText.trim();
//                     if(result>0){
//                         alert('댓글이 수정되었습니다');
//                         //얘는 여기에 써야 제대로 작동함, 이벤트리스너 끝나고 쓰면 이벤트 실행 전의 리스트를 불러와서 의미가없다
//                         getList(1);
//                         uButton.innerText='UPDATE';
//                         console.log("updatephase3: "+updatePhase);
//                         updatePhase=0;
//                     }else{
//                         alert('수정 실패');
//                         uButton.innerText='UPDATE';
//                         console.log("updatephase3: "+updatePhase);
//                         updatePhase=0;
//                     }
    
//                 }
//             });
//         }
//     }

//     e.preventDefault();
// })




//update
//강사님꺼
replyList.addEventListener("click", function(e){
    let updateButton = e.target;
    if(updateButton.classList.contains("update")){
        //상대선택자 - 상대경로로 찾아가기. 클릭한 버튼의 부모<td>, 그 부모의 형제 찾아가기
        // console.log(updateButton.parentNode.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling);
        let num = updateButton.getAttribute("data-comment-num");
        let contents = document.getElementById('contents'+num);
        // console.log(contents);

        //text-area 넣는방법 두가지
        //1. innerHTML로 넣기
        // contents.innerHTML="<textarea>"+contents.innerText+"</textarea>";

        //2. 이미 뿌려질때 <textarea></textarea>를 감싸서 보내기 + readonly 속성부여, 테두리는 없애고
        //원하는대로 선택이 되는지 확인
        // console.log(document.querySelector("#contents"+num).firstChild);
        contents.firstChild.removeAttribute("readonly");

        //수정버튼 생성과 추가
        let btn = document.createElement("button");
        let attr = document.createAttribute("class");
        attr.value='btn btn-primary';
        btn.setAttributeNode(attr);
        contents.appendChild(btn);

        attr = document.createTextNode('확인');
        btn.appendChild(attr);

        //취소버튼 추가
        //입력하다가 취소 누르면 입력전의 데이터를 넣어줘야하니까 입력전 내용을 변수에 담아두기
        //입력후 확인 : 변수에 담긴 데이터 대신 새 데이터 집어넣고 수정 / 입력후 취소 : 변수에 담겨져있던 데이터를 그대로 업데이트(값 변화없이)

        //버튼을 만들고 이벤트를 걸면서 보내주는걸로 해도 된다.(위임 받아도 되고)
        btn.addEventListener("click", function(){
            //업데이트 보내려면 num, contents 두개만 보내주면 된다
            console.log(num);
            console.log(contents.firstChild.value);

            //위 데이터 2개를 서버로 보내기만 하면 된다
            let xhttp = new XMLHttpRequest();
            xhttp.open('POST', '../bankBookComment/update');
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send('num='+num+'&contents='+contents.firstChild.value);

            xhttp.addEventListener('readystatechange', function(){
                if(this.readyState==4 && this.status==200){
                    let result = this.responseText.trim();
                    if(result>0){
                        alert('댓글이 수정되었습니다');
                        getList(1);
                    }else{
                        alert('수정 실패');
                    }
                }
            });
        })
    }
    e.preventDefault();
})
