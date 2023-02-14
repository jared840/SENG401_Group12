const app = require('express')();

const port = 8080;

app.set('view engine', 'ejs')


app.get("/", (request, response)=> {
    response.render("index")
    // this is how u pass data:
    //response.render("index" ,{ data : "hej"})
})

app.get("/login", (request, response) => {
    response.render("login")
})

app.get("/Test", (request, response) => {
    response.render("Test", {testData :{ data:"This is my test data", data2:"hej" } })
})

app.listen(port, ()=> console.log("I'm a alive"))