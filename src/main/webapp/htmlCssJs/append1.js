const list = document.getElementById("list");
const add = document.getElementById("add");


add.addEventListener("click", function(){
    //element 추가하기
    //<li>d</li>
    //<li><button>click</button></li>

    //element 만들기
    let li = document.createElement('li');
    let button = document.createElement('button');

    //contents영역 만들기
    let text = document.createTextNode('click');

    //속성 만들기 - 여러개 만들고싶으면 createAttribute을 여러번 해야함
    let attr = document.createAttribute('class');

    //속성 값 세팅
    attr.value = 'btn';
    attr.value = attr.value + ' bg'; //변수니까 값을 가져올수 있지 않을까? 누적하기 -> ok

    //만든 속성을 button에 적용시키기
    button.setAttributeNode(attr);

    //element 내에 contents 집어넣기
    button.appendChild(text);
    li.appendChild(button);

     //append는 추가(덮어씌우기 x)
    // list.append(li);
    // list.prepend(li);

    //얘네는 선택자 외부에 추가된다(바깥에붙어서 다른태그가 된다)
    // list.before(li);
    list.after(li);
});