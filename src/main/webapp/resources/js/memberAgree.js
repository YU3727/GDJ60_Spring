//전체동의 -> 모두체크, 전체동의해제 모두해제
//1. js로 element를 가져옴
//2. element에 event를 걸던가 contents를 꺼내든 손보든, attribute를 꺼내든 손보든

//전체동의, 개별동의
const checkAll = document.getElementById("checkAll"); //1개
const checks = document.getElementsByClassName("ch"); //2개이상, 배열(element를 모아놓은)

//가입버튼
const agree = document.getElementById("agree");

//전체동의
checkAll.addEventListener("click", function(){
    // console.log(checkAll.value);
    for(let i=0; i<checks.length; i++){
        //checks는 check를 모아둔 배열이라 .checked 속성이 없다 -> 꺼내야함
        checks[i].checked = checkAll.checked;
    }
});


//개별동의 버튼
//3개 모두 checked 값이 true 라면 전체동의 checked, 하나라도 false라면 false
//checks는 배열이라 element를 꺼내기 위한 반복문
for(let i=0; i<checks.length; i++){
    checks[i].addEventListener("click", function(){
        //아래는 클릭 할 때 마다
        //자기 자신을 포함해서 모두와 비교하기위한 반복문
        let result = true;
        for(let j=0; j<checks.length; j++){
            //if문에 한번이라도 들어갔다 나오면 result값이 false가 됨
            if(!checks[j].checked){
                // result = checks[j].checkd;
                // result = false;
                result = !result;
                break;
            }
        }
        checkAll.checked = result;

    })
};


// (여기까지가 잘 체크버튼에 불 들어오는지 테스트)
// for(let i=0; i<checks.length; i++){
//     checks[i].addEventListener("click", function(){
//         console.log(this.checked);     
//      });
// };


//회원가입 버튼 누르면 전체 동의 되어있어야 넘어가지고
//아닌경우에는 alert창 뜨면서 약관에 동의하셔야 합니다 메시지 뜨고 페이지 그대로
agree.addEventListener("click", function(){
    console.log(agree);
    if(checkAll.checked){
        // window.alert('다음 페이지 넘어가기');
        location.href="./memberAdd"; //이건 보안이 필요하지 않은 페이지에서 사용
    }else{
        window.alert('전체 약관에 동의해야 합니다');
    }

});