username: John
password: john123

registerStudent:
url(http://localhost:9090/api/v1/student/)
json:{
    "id":1,
    "name":"Peter",
    "email":"peter123@gmail.com",
    "semester":"4th",
    "address":"Kathmandu"
}

getStudentById:
url(http://localhost:9090/api/v1/student/1)

getAllStudent:
url(http://localhost:9090/api/v1/student/)

updateStudent:
url(http://localhost:9090/api/v1/student/1)
json: {
	 "name":"John Doe",
    "email":"johndoe123@gmail.com",
    "semester":"6th",
    "address":"Pokhara"
}

deleteStudent:
url(http://localhost:9090/api/v1/student/1)