
//element 가져오기
const pl = document.getElementsByClassName("page-link");
const searchForm = document.getElementById("searchForm");
const page = document.getElementById("page");

// for(let i=0; i<pl.length; i++){
//     pl[i].addEventListener("click", function(){
//         console.log(i+"번째 pl");
//     })
// }

//이벤트 걸기
//반복문 for(변수명 of 컬렉션변수명){ } << java의 향상된 for문과 유사. :대신 of
for(let p of pl){
    p.addEventListener("click", function(e){
        //page 번호를 알고싶은데 여기서는 알수가 없다. jsp에 data-**로 정보를 적어두자
        //어느 element인지 알고싶으면 각 버튼을 클릭했을 때 data-** 속성을 가져 오면 된다.
       let v = p.getAttribute("data-board-page");
       console.log(v);

       //page 번호를 바꾸고 싶으니까 page 번호가 들어가있는 input 태그에 id를 줌
       page.value = v;

       //form을 전송하고싶으면 submit 이벤트가 발생해야한다. > form에 id를 줌
       searchForm.submit();//강제발생
    })
}