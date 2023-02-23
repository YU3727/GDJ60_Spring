



//element 가져오기
const pl = document.getElementsByClassName("page-link");


// for(let i=0; i<pl.length; i++){
//     pl[i].addEventListener("click", function(){
//         console.log(i+"번째 pl");
//     })
// }

//이벤트 걸기
//반복문 for(변수명 of 컬렉션변수명){ } << java의 향상된 for문과 유사. :대신 of
for(let p of pl){
    p.addEventListener("click", function(e){
        e.preventDefault();
    })
}