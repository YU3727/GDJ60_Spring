
//Jquery - add
$("#replyAdd").click(function(){

    const form = new FormData();
    form.append("contents", $("#replyContents").val());
    form.append("bookNumber", $("#replyAdd").attr("data-idx-bookNumber"));


    //fetch - 사용은 이걸로 해야한다
    //JS에서 중괄호는 객체를 만든다는 의미. 멤버변수를 자기마음대로 추가할 수 있다
    //값을 구분할때는 쉼표(,)를 쓴다
    //fetch는 XMLHttpRequest의 open ~ send까지 다 한것
    fetch('../bankBookComment/add', {
        method:'POST',
        headers:{}, //formData 자체의 header가 넘어가는거같다
        body:form //"contents="+replyContents.value+"&bookNumber="+replyAdd.getAttribute('data-idx-bookNumber')
    })
    .then((response)=>response.text()) //response는 응답객체라고 생각. response.text는 응답에서 text 꺼내기. 중괄호 생략시 return 자동으로 해줌
    .then((res)=>{
        if(res.trim()==1){
            alert("댓글이 등록 되었습니다");
            replyContents.value='';
            $("replyContents").val("");
            getList(1);
        }else {
            alert("등록에 실패했습니다");
        }  
    })
    .catch(()=>{
        //예외처리 기능
        console.log('에러 발생');
    });

});


//list
getList(1);

//Ajax, bankBookDetail에 reply list 5개 뿌리기
//JavaScript에서 매개변수 선언할때는 let 안붙임
function getList(page){

    //Ajax - fetch
    fetch('/bankBookComment/list?&bookNumber='+replyAdd.getAttribute('data-idx-bookNumber')+'&page='+page, {
        method:'GET'
        //GET과 HEAD 메서드는 body속성을 가질수 없다(에러 발생)
        // headers:{},
        // body:()
    })
    .then((response)=>response.text())
    .then((res)=>{
        // replyList.innerHTML=res.trim();
        //Jquery로 표현
        $("#replyList").html(res.trim());
    })

}


//Jquery - pagination
//Event위임(자식영역에 클릭 -> 부모영역(replyList)의 이벤트 발생)
$("#replyList").on("click", ".page-link", function(e){
    let page = $(this).attr("data-board-page")
    getList(page);

    e.preventDefault();
})


//Jquery - delete
//click event를 클래스명이 del인 곳으로 위임하기
$("#replyList").on("click", ".del", function(e){

        //fetch 함수 호출
        fetch('../bankBookComment/delete', {
            method:'POST',
            headers:{
                "Content-type":"application/x-www-form-urlencoded"
            },
            body:"num="+$(this).attr("data-comment-num")
            //응답객체에서 data를 추출하는 과정
        }).then((response)=>response.text())  //.then(function(response){return response.text()})
            //추출한 data를 사용하는 단계
        .then((res)=>{
            if(res.trim()>0){
                alert('댓글이 삭제되었습니다');
                getList(1);
            }else{
                alert('삭제 실패');   
            }
            //Exception 처리
        }).catch(()=>{
            alert('삭제 실패');
        });
    e.preventDefault();
})


//Jquery - update form
$("#replyList").on("click", ".update", function(e){ //this 쓸때는 화살표함수 사용 안하는걸로
    let num = $(this).attr("data-comment-num");
    $("#contents").val($("#contents"+num).text().trim()); //선택자로 선택되는 ()괄호 내는 문자열이기만 하면 ok
    $("#contentsConfirm").attr("data-comment-num", num);
    e.preventDefault();
})


//Jquery - update submit
$("#contentsConfirm").click(function(){

    let num = $(this).attr("data-comment-num");

    //Ajax - fetch
    fetch('../bankBookComment/update', {
        method:'POST',
        headers:{"Content-type":"application/x-www-form-urlencoded"},
        body:"num="+num+"&contents="+$("#contents").val()
    })
    .then((response)=>{
        return response.text() //중괄호를 넣고싶으면 return을 넣어주자. 생략시 자동 return
    })
    .then((res)=>{
        if(res.trim()>0){
            alert('댓글이 수정되었습니다');
            $("#closeModal").click();
            getList(1);
        }else{
            alert('수정 실패');
        }
    })
    .catch(()=>{
        alert('수정 실패');
    })
})



//test 후 삭제할 영역
    //1. 선택
    // const b1 = document.getElementById("b1");
    // const b1 = document.querySelector("#b1"); //css 선택자로 사용
    // b1.addEventListener('click', function(){});
    // document.getElementById("t1").value;

    //Jquery, css선택자로 사용
    //$('#b1') 이게 document.getElementById("b1"); / .click() 이게 b1.addEventListener('click', function(){})에 해당
    // $('#b1').click(()=>{
    //     // console.log('button click');
    //     let v = $('#t1').val();
    //     console.log(v);
    // }); 

    //Event 걸기
    //$('#t1').blur(function(){})
    // $('#t1').blur(()=>{
    //     console.log('blur event');
    // })

    
    //on을 쓰는 event 방식을 사용하는 이유
    //1. 하나의 element에 여러개의 이벤트를 걸고싶을때
    //2. **event 위임을 하고자 할 때 (중요)
    
    // //1
    // $('#t1').on({
    //     click:function(){
    //         console.log('t1 click');
    //     },
    //     blur:()=>{
    //         console.log('t1 blur');
    //     }
    // })
    
    // $('#b1').on("click", ()=>{})

    // //2
    // //replyList에서 update로 이벤트 위임을 하고자 할 때
    // $('#replyList').on("click", ".update", ()=>{});


    //checkbox 약관동의
    // document.getElementsByClassName("ch");
    // for(let c of ch){
    //     c.addEventListener("click", ()=>{

    //     })
    // }

    //Jqeury checkbox 약관동의
    //클래스명 ch를 가진 element는 여러개지만, Jquery에서는 반복문을 돌리지 않아도 이벤트가 다 걸린다
    //화살표함수
    // $(".ch").click((e)=>{
    //     console.log("e: ",e);
    //     console.log(this); //화살표 함수에서는 이렇게 찍으면 window(화면 전체) 객체가 나온다
    //     // console.log(this.value); //JS
    //     // console.log($(this).val()); //Jquery
    // })

    //익명함수
    // $(".ch").click(function(e){
    //     console.log("e: ",e);
    //     console.log(this); //익명 함수에서는 해당 태그를 가리킨다
    //     console.log($(this).val());
    // })

    //**어떤 함수에서 사용하냐에 따라서 this가 가리키는게 달라진다
    //자기자신을 선택할 일이 있다면 익명함수와 화살표함수에서 어떻게 사용할지 고민을 해봐야한다.
    //화살표함수에서 this를 제대로 쓰고싶다면? JS에서 사용했던것 처럼 e.target을 사용
    // $(".ch").click((e)=>{
    //     console.log("e: ",e);
    //     console.log(e.target); //익명 함수에서의 해당 태그를 가리키는것 처럼 제대로 선택함
    //     console.log($(e.target).val()); //Jquery
    // })




////////////////////