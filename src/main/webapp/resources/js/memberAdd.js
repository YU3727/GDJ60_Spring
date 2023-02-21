
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

//EVENT
//1. id 검증
id.addEventListener("blur", function(){
    if(id.value==''){
        idResult.innerHTML='id는 필수 사항입니다';
        checkId = false;
        // idh.type="text";
        // idh.value='id는 필수 입력값입니다';
        // idh.setAttribute("readonly");
        // console.log('id는 필수 입력값입니다');
    }else{
        idResult.innerHTML='';
        checkId = true;
        // idh.type="hidden";
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
    let length = pw.value.length;
    if(length >5 && length <13){
        pwResult.innerHTML='정상적인 비밀번호 입니다';
        checkPw = true;
    }else{
        pwResult.innerHTML='비밀번호는 최소 6글자 이상, 최대 12글자 이하여야 합니다';
        checkPw = false;
    }
});


//3. 비밀번호 확인
pwCheck.addEventListener("blur", function(){
    //공백일때도 같다고 인식해버림 고쳐야한다
    if(pw.value == pwCheck.value){
        pwCheckResult.innerHTML='비밀번호가 일치합니다';
        checkPwCheck = true;
    }else {
        pwCheckResult.innerHTML='비밀번호가 일치하지 않습니다';
        checkPwCheck = false;
    }

});


//4. 이름
name1.addEventListener("blur", function(){
    if(name1.value.length==0){
        nameResult.innerHTML='이름은 필수 사항입니다';
        checkName = false;
    }else{
        nameResult.innerHTML='';
        checkName = true;
    }
});


//5. 전화번호
phone.addEventListener("blur", function(){
    if(phone.value.length==0){
        phoneResult.innerHTML='전화번호는 필수 사항입니다';
        checkPhone = false;
    }else{
        phoneResult.innerHTML='';
        checkPhone = true;
    }
});


//6. 이메일
email.addEventListener("blur", function(){
    if(email.value.length==0){
        emailResult.innerHTML='이메일은 필수 사항입니다';
        checkEmail = false;
    }else{
        emailResult.innerHTML='';
        checkEmail = true;
    }
})


//7. 버튼을 눌렀을 때 폼 전송
btn.addEventListener("click", function(){
    if(checkId==true && checkPw==true && checkPwCheck==true && checkName==true && checkPhone==true && checkEmail==true){
        //검증 완료일때
        frm.submit(); //강제로 이벤트 발생시키기
    }else{
        //검증 안됐을때
        alert("조건에 맞게 입력해주세요");
    }
});

