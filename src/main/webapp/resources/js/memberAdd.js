
//SELECTOR
const id = document.getElementById("id");
const pw = document.getElementById("pw");
const pwCheck = document.getElementById("pwCheck");
const name1 = document.getElementById("name");
const phone = document.getElementById("phone");
const email = document.getElementById("email");

const idResult = document.getElementById("idResult");
const pwResult = document.getElementById("pwResult");
const pwCheckResult = document.getElementById("pwCheckResult");
const nameResult = document.getElementById("nameResult");
const phoneResult = document.getElementById("phoneResult");
const emailResult = document.getElementById("emailResult");

//왜 button이나 input 태그가 아니라 폼에다가 걸어야 할까? 생각해보기
const frm = document.getElementById("frm");
const btn = document.getElementById("btn");

//검증 장치
let checkId = false;
let checkPw = false;
let checkPwCheck = false;
let checkName = false;
let checkPhone = false;
let checkEmail = false;

//검증 장치 ver2
// let checks = [false, false, false, false, false, false, false];
// 이러고 아래에 조건 만족하면 checks[i] = true; 이런식으로 줌
// 마지막 버튼에 반복문으로 for(i<checks.length) 돌려서 모두 확인하거나
// if(checks.includes(false))를 입력해서 false 여부를 확인하면 됨


//사용자친화적으로 개발을 해야한다. 그래야 다시 우리 제품을 쓸테니까. 불편한건 개발자의 몫

//EVENT
//1. id 검증
id.addEventListener("blur", function(){
    //조건을 넣을때 중요한 것을 조건식에 넣고 아닌것을 else에 들어가게끔
    if(id.value!=0){
        idResult.innerHTML='';
        checkId = true;
    }else{
        idResult.innerHTML='id는 필수 사항입니다';
        checkId = false;
    }
});


//2. 비밀번호 검증
pw.addEventListener("blur", function(){
    if(pw.value.length==0){
        pwResult.innerHTML='pw는 필수 사항입니다';
        checkPw = false;
    }
});
pw.addEventListener("keyup", function(){
    //비밀번호 체크 통과 후 위에서 비밀번호를 바꾸면 체크를 다시 해야한다.
    let length = pw.value.length;
    if(length >5 && length <13){
        pwResult.innerHTML='정상적인 비밀번호 입니다';
        checkPw = true;
    }else{
        pwResult.innerHTML='비밀번호는 최소 6글자 이상, 최대 12글자 이하여야 합니다';
        checkPw = false;
    }
});
pw.addEventListener("change", function(){
    //값이 바뀌면 다시 체크 하게끔 유도하는 작업
    checkPwCheck = false;
    pwCheck.value='';
    pwCheckResult.innerHTML='';
});


//3. 비밀번호 확인
pwCheck.addEventListener("blur", function(){
    //공백일때도 같다고 인식해버림 고쳐야한다
    if(pw.value.length==0 && pwCheck.value.length==0){
        pwCheckResult.innerHTML='비밀번호는 공백일 수 없습니다';
        checkPwCheck = false;
    }else if(pw.value == pwCheck.value){
        pwCheckResult.innerHTML='비밀번호가 일치합니다';
        checkPwCheck = true;  
    }else {
        pwCheckResult.innerHTML='비밀번호가 일치하지 않습니다';
        checkPwCheck = false;
    }

});


//4. 이름
name1.addEventListener("blur", function(){
    if(name1.value.length!=0){
        nameResult.innerHTML='';
        checkName = true;
    }else{
        nameResult.innerHTML='이름은 필수 사항입니다';
        checkName = false;
    }
});


//5. 전화번호
phone.addEventListener("blur", function(){
    if(phone.value.length!=0){
        phoneResult.innerHTML='';
        checkPhone = true;
    }else{
        phoneResult.innerHTML='전화번호는 필수 사항입니다';
        checkPhone = false;
    }
});


//6. 이메일
email.addEventListener("blur", function(){
    if(email.value.length!=0){
        emailResult.innerHTML='';
        checkEmail = true;
    }else{
        emailResult.innerHTML='이메일은 필수 사항입니다';
        checkEmail = false;
    }
})


//7. 버튼을 눌렀을 때 폼 전송
btn.addEventListener("click", function(){
    if(checkId && checkPw && checkPwCheck && checkName && checkPhone && checkEmail==true){
    // if(!checks.includes(false)) 이 조건식도 ok
        //검증 완료일때
        frm.submit(); //강제로 이벤트 발생시키기
    }else{
        //검증 안됐을때
        alert("조건에 맞게 입력해주세요");
    }
});


//id 중복 검사는 아직 덜 배운 기능들이 있어서, 나중에 ajax를 배우고 나면 하도록 한다