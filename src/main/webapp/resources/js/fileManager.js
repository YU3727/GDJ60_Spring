const fileAdd = document.getElementById("fileAdd");
const fileList = document.getElementById("fileList");
let count=0;
let max=1;
let param='pic'; //기본값 pic

//나중을 위한 함수 - max값 조정
function setMax(m){
    max=m;
}

function setParam(p){
    param=p;
}

fileAdd.addEventListener("click", function(){
    //import, event 작동 test
    // console.log("test");

    if(count>=max){
        alert('첨부파일은 최대 '+max+'개 까지만 가능');
        return;
    }

    // if(count!=1){
        //element 생성
        let div = document.createElement('div'); //부모div
        let label = document.createElement('label');
        let labelText = document.createTextNode('image');
        let input = document.createElement('input');

        //element 조립
        div.appendChild(label);
        div.appendChild(input);
        label.appendChild(labelText);
        
        //Attribute 생성 적용
        //div
        let attr = document.createAttribute('class');
        attr.value='mb-3';
        div.setAttributeNode(attr);
        
        attr = document.createAttribute('id'); //삭제를 위한 id추가
        attr.value='divCheck';
        div.setAttributeNode(attr);
        
        //label - 변수 재활용하기
        //Attribute는 바로 재활용 할수는 없고 createAttribute로 새로 만들어서 변수명만 재활용 할수있다
        attr = document.createAttribute('class');
        attr.value = 'form-label';
        label.setAttributeNode(attr);

        attr = document.createAttribute('for');
        attr.value = 'files';
        label.setAttributeNode(attr);


        // let labelAttrFor = document.createAttribute('for');
        // let labelAttrClass = document.createAttribute('class');
        // let labelText = document.createTextNode('image');
        // label.appendChild(labelText);
        // labelAttrFor.value = 'files';
        // labelAttrClass.value = 'form-label';
        // label.setAttributeNode(labelAttrFor);
        // label.setAttributeNode(labelAttrClass);
        
        //input - 새로 만들기
        let inputAttrType = document.createAttribute('type');
        let inputAttrClass = document.createAttribute('class');
        let inputAttrId = document.createAttribute('id');
        let inputAttrName = document.createAttribute('name');
        inputAttrType.value = 'file';
        inputAttrClass.value = 'form-control';
        inputAttrId.value = 'files';
        // inputAttrName.value = 'pic';
        inputAttrName.value = param;
        input.setAttributeNode(inputAttrType);
        input.setAttributeNode(inputAttrClass);
        input.setAttributeNode(inputAttrId);
        input.setAttributeNode(inputAttrName);
        
        //최종 추가
        fileList.prepend(div);
        count++;


        //add버튼 -> cancel버튼으로 바꾸기(파일 1개 이상일땐 주석)
        // fileAdd.innerText='cancel';
    // }else{
    //     // window.alert('add버튼은 한번만 작동 가능');
    //     //여기에 cancel버튼 활성화(count=1)일때니까 위에 추가했던거 다 지우는 작업
    //     const conversion = document.getElementById("divCheck");
    //     fileList.removeChild(conversion);
    //     count--;
    //     fileAdd.innerText='Image Add';
    // }
});