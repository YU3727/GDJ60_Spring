// const fileAdd = document.getElementById("fileAdd");
const fileList = document.getElementById("fileList");

let count=0;
let max=1;
let param='files'; //기본값 pic
let idx=0; //버튼정보를 구분할 인덱스번호

function setCount(c){
    count=c;
}

//나중을 위한 함수 - max값 조정
function setMax(m){
    max=m;
}

//파일의 name속성명을 바꿔주려고
function setParam(p){
    param=p;
}


// fileAdd.addEventListener("click", function(){

//     if(count>=max){
//         alert('첨부파일은 최대 '+max+'개 까지만 가능');
//         return;
//     }

//     count++;

//     //element 생성
//     let div = document.createElement('div'); //부모div
//     // let label = document.createElement('label');
//     // let labelText = document.createTextNode('Attached File');
//     let input = document.createElement('input');
//     let del = document.createElement('button'); //idx번호가 다른 element를 각각 생성, 고르기

//     //element 조립
//     // div.appendChild(label);
//     div.appendChild(input);
//     // label.appendChild(labelText);
//     div.appendChild(del);
    
//     //Attribute 생성 적용
//     //div
//     let attr=document.createAttribute('class');
//     attr.value='mb-3';
//     div.setAttributeNode(attr);
//     div.classList.add('input-group');
    
//     attr=document.createAttribute('id'); //삭제를 위한 id추가
//     attr.value='del'+idx;
//     div.setAttributeNode(attr);
    
//     //label - 변수 재활용하기
//     //Attribute는 바로 재활용 할수는 없고 createAttribute로 새로 만들어서 변수명만 재활용 할수있다
//     // attr=document.createAttribute('class');
//     // attr.value='form-label';
//     // label.setAttributeNode(attr);

//     // attr=document.createAttribute('for');
//     // attr.value='files';
//     // label.setAttributeNode(attr);
    
//     //input - 새로 만들기
//     let inputAttrType=document.createAttribute('type');
//     let inputAttrClass=document.createAttribute('class');
//     let inputAttrId=document.createAttribute('id');
//     let inputAttrName=document.createAttribute('name');
//     inputAttrType.value='file';
//     inputAttrClass.value='form-control';
//     inputAttrId.value='files';
//     // inputAttrName.value='pic';
//     inputAttrName.value = param;
//     input.setAttributeNode(inputAttrType);
//     input.setAttributeNode(inputAttrClass);
//     input.setAttributeNode(inputAttrId);
//     input.setAttributeNode(inputAttrName);

//     //X버튼 추가(첨부파일 추가란 삭제용도), attr 변수 재활용
//     attr=document.createAttribute('type');
//     attr.value='button';
//     del.setAttributeNode(attr);

//     attr=document.createAttribute('class');
//     attr.value='dels btn btn-outline-secondary';
//     del.setAttributeNode(attr);

//     attr=document.createAttribute('data-del-idx');
//     attr.value='del'+idx;
//     idx++;
//     del.setAttributeNode(attr);

//     let delText=document.createTextNode("X");
//     del.appendChild(delText);


//     //최종 추가
//     fileList.prepend(div);
// });


// //새로 만들어진 delete 버튼에 이벤트 전달하기(캡쳐링)
// //부모 -> 자식 방향으로; 부모는 div태그(id=fileList), 자식은 add버튼 눌러서 만들어진 버튼
// //e.target은 div의 자식을 선택하는 선택자.
// fileList.addEventListener("click", function(e){
//     //확인용
//     // console.log(e.currentTarget, e.target);
//     // console.log(e.target.classList.contains('dels'));

//     if(e.target.classList.contains('dels')){
//         // let id = e.target.getAttribute('data-del-idx');
//         // document.getElementById(id).remove();
//         // count--;

//         //버튼을 클릭했을 때 부모를 찾는 상대선택자(.parentNode)
//         e.target.parentNode.remove();
//         count--;
//     }
// })



//fileAdd 기능 구현 - Ajax(Jquery)
$("#fileAdd").click(()=>{ //화살표함수 function(){} -> ()=>{}

    if(count>=max){
        //최대파일개수 = 기존파일개수(count) + 추가할 수 있는 파일 개수(max)
        alert('첨부파일은 최대 '+max+'개 까지만 가능');
        return;
    }
    count++;

    let child = '<div class="input-group my-3" id="del'+idx+'">';
        child = child + '<input type="file" class="form-control" name="'+param+'">';
        child = child + '<button type="button" class="dels btn btn-outline-secondary" data-del-idx="del'+idx+'">X</button>';
        child = child + '</div>';
        $("#fileList").append(child);

    idx++;
})


//이벤트 위임 - Ajax(Jquery)
$("#fileList").on("click", ".dels", function(){ //".dels"
    // let id = $(this).attr("data-del-idx");
    // $("#"+id).remove();

    //상대선택자 사용(부모 찾는 것), Jquery에서는 상대선택자로 element만 찾아준다(textNode는 취급x)
    $(this).parent().remove();
    count--;
})
