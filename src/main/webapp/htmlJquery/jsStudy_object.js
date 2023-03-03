

let car = {
    brand:'AUDI',
    price:3000,
    info:function(){
        console.log(this.brand, this.price);
    }
};

const btn1 = document.getElementById("btn1");
btn1.addEventListener("click", function(){
    console.log(car.brand);
    console.log(car.price);
    car.info();

    //만들어진 객체에 멤버를 추가할 수 있다(Java와는 다른점)
    car.color='RED';

    console.log("for in");
    //car는 객체이지 배열이 아니다
    for(let v in car){
        console.log(v);
        //근데 car 객체 내 멤버변수, 함수 다 나오네?
        //이건 매개변수로 무언가를 받았을 때 어떤 멤버가 있는지 모르기 때문에 확인하는 용도로도 사용한다
        console.log(car[v]);
    }
});