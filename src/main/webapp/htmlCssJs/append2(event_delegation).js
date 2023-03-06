
const d1 = document.getElementById("d1"); //div
const add = document.getElementById("add"); //button
const d1_1 = document.getElementById("d1_1");
const result = document.getElementById("result");

let idx = 0;

// //delete 버튼에 공통적으로 클릭 이벤트 걸기. 확인은 콘솔로
// //1. 선택자로 선택
// const dels = document.getElementsByClassName("dels");
// //2. 이벤트 걸기
// for(let i=0; i<dels.length; i++){
//     dels[i].addEventListener("click", function(){
//         console.log("dels click event!");
//     });
// };

//부모element에 클릭 이벤트를 걸어줌
result.addEventListener("click", function(e){
    //자식element가 맞는지 확인하는 것
    // console.log(e.currentTarget, e.target);
    // console.log(e.target.classList.contains('dels'));
    if(e.target.classList.contains('dels')){ //자식 element중 class이름이 dels인것을 찾아줌
    //버튼을 눌렀을때만 로그가 찍히니까 버튼에 이벤트를 건 효과와 같음

    //어느 버튼을 눌렀는지 확인하려면 id를 알아내야함
    //이 버튼과 부모의 연결고리를 알아야함... global attribute - data-**: data-div-idx
    //우선 속성명 먼저 확인
    //버튼 클릭했을 때 div의 id속성명과 button의 data-div-idx 속성명 확인
        //e.target 여기까지가 button
        let id = e.target.getAttribute('data-div-idx');
        document.getElementById(id).remove();
        console.log("id : "+id);
    };
});


add.addEventListener("click", function(){
    let d = document.createElement("div"); //부모 div
    let btn = document.createElement("button"); //자식 delete 버튼
    let n = document.createTextNode("delete");
    
    let attr = document.createAttribute("class");
    attr.value="dels";
    btn.setAttributeNode(attr);

    //div에 id 속성 추가
    attr = document.createAttribute("id");
    attr.value = "del"+idx;
    d.setAttributeNode(attr);
    
    //btn에 data-div-idx 추가(다른 element와 연결고리 역할을 위해)
    attr = document.createAttribute("data-div-idx");
    attr.value = "del"+idx; 
    btn.setAttributeNode(attr);
    idx++;

    btn.appendChild(n);
    d.appendChild(btn);

    result.append(d);
});

d1.addEventListener("click", function(){
    //d1.removeChild(d1_1);

})