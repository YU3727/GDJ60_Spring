const btn = document.getElementById("btn");

//1.테이블
const btn2 = document.getElementById("btn2");
let r1 = document.getElementById("r1");
let c1 = document.getElementById("c1");
let tbl1 = document.getElementById("tbl1");

//2.select-option 체크
const s1 = document.getElementById("s1");
//options는 실제 배열은 아니고 유사배열(배열이라고 생각)
const options = document.getElementsByClassName("options");

//3.radio button 체크 / 버튼, element를 가져오기
const s2 = document.getElementById("s2");
const meals = document.getElementsByClassName("meals");

//4.체크박스
const ch = document.getElementsByClassName("ch");



btn.addEventListener("click", function(){

    let r1 = document.getElementById("r1");
    console.log(r1.value*1+5);
    //javascript는 value값이 문자열로 옴. +는 연결연산자의 의미도 있다
});

//value를 불러오는게 언제인지 잘 생각해야함. 입력하고 버튼을 눌렀을 때 해야하지, 미리 받아와버리면 Null이 들어감
//js 코드를 </body>위에 쓰는 이유도 html에 element, attribute, contents, id 등이 미리 선언되어있어야 쓸 수 있기 때문이다


//1.테이블 만들기. innerHTML은 명령어가 들어갈때마다 덮어쓰기가 된다
//그러므로 table을 만드는 tag를 특정변수에 다 입력해놓고 한번에 innerHTML으로 넣어야한다
btn2.addEventListener("click", function(){
    let result = '';

        for(let i=0; i<r1.value; i++){
            result = result+"<tr>";
                for(let j=0; j<c1.value; j++){
                    result = result+"<td>";
                    result = result+"</td>";
                }
            result = result+"</tr>";
        }
        tbl1.innerHTML = result;
});


//2.선택한 value를 가지고 오고싶을때 id가 아닌 class로도 가져올 수 있다.
//select-option에 option마다 값 체크하기
//버튼 s1을 클릭하면 option에 있는 value를 받아오기, 옵션마다 selected가 있는지 물어보기
s1.addEventListener("click", function(){
    for(let i=0; i<options.length; i++){
        if(options[i].selected){
            console.log(options[i].value);
            break;
        }
    }
});


//3.radio 버튼을 선택하고 check버튼을 누르면 고른 속성값 반환받기
//radio 버튼은 세트로 움직일 때 이름값이 같아야함. 클래스이름으로 데이터를 가져오자
s2.addEventListener("click", function(){
    for(let i=0; i<meals.length; i++){
        if(meals[i].checked){
            console.log(meals[i].value);
            break;
        }
    } 
});


//4.checkbox
//이벤트는 element에만 걸수 있는데, ch는 element를 모아놓은 배열이라 이벤트를 걸 수 없다.
//이벤트를 걸려면 반복문 돌려서 꺼내고 하나씩 걸어줘야한다
for(let i=0; i<ch.length; i++){
    ch[i].addEventListener("click", function(){
        console.log(this.checked);
        for(let j=0; j<ch.length; j++){
            ch[j].checked = this.checked;
        }
    });
};