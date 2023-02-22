const fileAdd = document.getElementById("fileAdd");
const fileList = document.getElementById("fileList");


fileAdd.addEventListener("click", function(){
    //import, event 작동 test
    // console.log("test");

    //element 생성
    let div = document.createElement('div'); //부모div
    let label = document.createElement('label');
    let input = document.createElement('input');

    //element 조립
    div.appendChild(label);
    div.appendChild(input);
    // label.appendChild(labelText);
    
    //Attribute 생성 적용
    //div
    let attr = document.createAttribute('class');
    attr.value='mb-3';
    div.setAttributeNode(attr);
    
    //label - 변수 재활용하기
    //attribute를 바로 재활용 할수는 없고 새로 만들어서 변수명만 재활용 할수있다
    attr = document.createAttribute('class');
    attr.value = 'form-label';
    label.setAttributeNode(attr);

    attr = document.createAttribute('for');
    attr.value = 'files';
    label.setAttributeNode(attr);

    let labelText = document.createTextNode('image');
    label.appendChild(labelText);

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
    inputAttrName.value = 'pic';
    input.setAttributeNode(inputAttrType);
    input.setAttributeNode(inputAttrClass);
    input.setAttributeNode(inputAttrId);
    input.setAttributeNode(inputAttrName);
    
    //최종 추가
    fileList.prepend(div);
});