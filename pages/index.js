
const express = require('express')
const app = express()
const port = 8080
app.use(express.static('public'))

app.set('view engine', 'ejs')

app.get("/", (request, response)=> {
    response.render("index")
})

app.get("/inventory", (request, response) => {
    response.render("productList")
})

app.get("/login", (request, response) => {
    response.render("login")
})

app.get("/Test", (request, response) => {
    response.render("Test", {testData :{ data:"This is my test data", data2:"hej" } })
})

app.listen(port, () => console.log("I'm a alive"))