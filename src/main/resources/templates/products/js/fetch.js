fetch("https://jsonplaceholder.typicode.com/posts/1",{
    method: 'PUT',
    body : JSON.stringify({
        id: 1,
        title: "olaaa",
        body: "hohoww",
        userId: 1
    }),
    headers: {
        'Content-type': 'application/json; charset=UTF-8',
    },
})
    .then((res) =>{
        if(!res.ok){
            const msg = `Error : ${res.status}`;
            throw new Error(msg);
        }
        return res.json();
    })
    .then((res) => console.log(res))
    .catch((err) => console.log(err))