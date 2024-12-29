const text = document.querySelector(".title");
const changeColor = document.querySelector(".changeColor");


const makeReq = (method,url,data) =>{
    const xhr = new XMLHttpRequest();

    xhr.open(method,url);
    xhr.setRequestHeader('Content-Type', 'application/json')

    xhr.onload = ()=>{
        let data = xhr.response;
        console.log(JSON.parse(data))
    }
    xhr.onerror = () =>{
        console.log("error is here bro")
    }
    // xhr.send();
    xhr.send(JSON.stringify(data));
}
const getData = (method, url) =>{
    makeReq('GET','https://jsonplaceholder.typicode.com/posts');
}

const sendDate = (method, url) =>{
    makeReq('POST','https://jsonplaceholder.typicode.com/posts',{
        title: 'foo',
        body: 'bar',
        userId: 1,
    });
}

const updateDate = (method, url) =>{
    makeReq('PUT','https://jsonplaceholder.typicode.com/posts/1',{
        id: 1,
        title: 'fooamama',
        body: 'baaarrrrr',
        userId: 1,
    });
}
const updateSingleDate = (method, url) =>{
    makeReq('PATCH','https://jsonplaceholder.typicode.com/posts/1',{
        title: 'hello guysz',
    });
}
const deleteDate = (method, url) =>{
    makeReq('DELETE','https://jsonplaceholder.typicode.com/posts/1',{
    });
    console.log("ID HAS BEEN REMOVED.");
}

deleteDate();
