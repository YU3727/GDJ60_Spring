//boardForm.js


const frm = document.getElementById("frm");
const bu = document.getElementById("update");
const bd = document.getElementById("delete");

//update 버튼을 클릭하면 form이벤트(submit)가 발생하면 됨. 메서드 형식 post
bu.addEventListener("click", function(){
    // console.log('update button');



    // let attr = document.createAttribute("type");
    // attr.value='submit';
    // bu.setAttributeNode(attr);

    // attr = document.createAttribute("method");
    // attr.value="POST";
    // console.log(attr);
})

//delete 버튼을 클릭하면 form이벤트(submit)가 발생하면 됨. 단 action값 변경해서. 메서드 형식 get
bd.addEventListener("click", function(){
    // console.log("delete button");


})