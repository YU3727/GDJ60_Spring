
//객체 만든것. Class 없이도 key:value로 만들 수 있다.
let car = {
    brand:'AUDI',
    price:3000,
    info:function(){
        console.log(this.brand, this.price);
    }
};


//Class 선언
class Student{
    //#은 private의 의미
    #n='';
    #kor;

    //클래스 안에 선언된 기능구현체 : 메서드 / 바깥에 선언된 것 : 함수
    set n(n){
        this.#n=n;
    }

    get n(){
        return this.#n;
    }

    set kor(kor){
        this.#kor=kor;
    }

    get kof(){
        return this.#kor;
    }

    info(){
        console.log(this.#n, this.#kor);
    }
}


const btn1 = document.getElementById("btn1");
btn1.addEventListener("click", function(){
    // console.log(car.brand);
    // console.log(car.price);
    // car.info();

    // //만들어진 객체에 멤버를 추가할 수 있다(Java와는 다른점)
    // car.color='RED';

    // console.log("for in");
    // //car는 객체이지 배열이 아니다
    // for(let v in car){
    //     console.log(v);
    //     //근데 car 객체 내 멤버변수, 함수 다 나오네?
    //     //이건 매개변수로 무언가를 받았을 때 어떤 멤버가 있는지 모르기 때문에 확인하는 용도로도 사용한다
    //     console.log(car[v]);
    // }

    //객체생성
    let s = new Student();
    s.n='iu';
    s.kor=100;
    s.eng=100;
    s.info();
    console.log(s.eng);
    //getter 호출도 형태만 보면 멤버변수의 사용과 동일함
    console.log(s.n);
    console.log(s.kor);
});