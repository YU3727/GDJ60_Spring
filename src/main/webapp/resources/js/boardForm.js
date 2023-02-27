//boardForm.js


const frm = document.getElementById("frm");
const bu = document.getElementById("update");
const bd = document.getElementById("delete");

//update 버튼을 클릭하면 form이벤트(submit)가 발생하면 됨. 메서드 형식 post
bu.addEventListener("click", function(){
    frm.setAttribute("method", 'post');

})

//delete 버튼을 클릭하면 form이벤트(submit)가 발생하면 됨. 단 action값 변경해서. 메서드 형식 get
bd.addEventListener("click", function(){
    let check = window.confirm("정말 삭제하시겠습니까?");
    if(check){
        frm.setAttribute("action", "./delete");
        frm.setAttribute('button', 'submit');
        frm.submit();
    }

})