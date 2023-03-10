
//this가 필요없으면 화살표 함수를 써도 됨
$("#btn").click(()=>{
    //https://dummyjson.com/ 사이트를 이용해서 더미JSON 받아오기

    let productId = $("#productId").val();

    $.ajax({
        type:'GET',
        url:"https://dummyjson.com/products/"+productId,
        success:function(response){
            console.log(response);
            console.log(typeof response);

            response=JSON.parse(response);
        }
    });

    //REST Template 형식(url 자체가 파라미터가 된다)
    // $.get('https://dummyjson.com/products/'+productId, (response)=>{
    //     //응답이 오는걸 매개변수로 받음
    //     console.log(response);
    //     console.log(response.title);
    //     console.log(typeof response);
    //     alert(response); //object 타입이 뜨면 JSON 객체
    //     let result = JSON.stringify(response); //result로 response는 문자열이 됨
    //     console.log(result);
    //     alert(result);
    //     console.log(typeof result);
    //     console.log(result.title); //문자열에는 title이라는 속성이 없기때문에 undefined
    // });
})


$.get('https://dummyjson.com/products', (response)=>{
    console.log(response.products);
    // let result = '';

    for(let item of response.products){
        console.log(item);

        let result = '<div class="card" style="width: 18rem;">';
        result = result + '<img src="'+item.thumbnail+'" class="card-img-top" alt="...">';
        result = result + '<div class="card-body">';
        result = result + '<h5 class="card-title">'+item.title+'</h5>';
        result = result + '<p class="card-text">'+item.description+'</p>';
        result = result + '<a href="#" class="btn btn-primary detail" data-productId="'+item.id+'">'+'$ '+item.price+'</a>';
        result = result + '</div></div>';

        //하나씩 추가하는 방법도 있다
        $("#productList").append(result);
    }
    // $("#productList").html(result);
})

//jQuery 이벤트 위임
$("#productList").on("click", ".detail", function(e){
    //확인
    console.log("id :"+$(this).attr("data-productId"));

    //detail
    //상세페이지에서 뿌릴거면 동기방식으로 넘기고, 상세페이지에서 Ajax로 받아와야함
    let productId = $(this).attr("data-productId");




    $.get('https://dummyjson.com/products/'+productId, (response)=>{
        //응답이 오는걸 매개변수로 받음
        console.log(response);
        console.log(response.title);
        console.log(typeof response);
        alert(response); //object 타입이 뜨면 JSON 객체
        let result = JSON.stringify(response); //result로 response는 문자열이 됨
        console.log(result);
        alert(result);
        console.log(typeof result);
        console.log(result.title); //문자열에는 title이라는 속성이 없기때문에 undefined
    });

    //기본기능 무력화
    e.preventDefault();
})


