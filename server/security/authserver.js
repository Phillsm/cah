var http = require('http');
function newUser(name,pass,callback){
var options = {
    hostname: '178.62.70.7',
    port: 57000,
    path: '/auth?player='+name+'&pass='+pass,
    method: 'PUT'
}

var req = http.request(options,function(res){
    console.log(res.statusCode);
    res.on('data',function(chunk){
	
    })
    callback(false,res.statusCode);

})

req.on('error',function(e){
    console.log("error in authserver newUser" + e.message);
})

req.end();

}

function authUser(name,pass,callback){
var options = {
    hostname: '178.62.70.7',
    port: 57000,
    path: '/auth?player='+name+'&pass='+pass,
    method: 'GET'
}
var req = http.request(options,function(res){
    console.log(res.statusCode);
    res.on('data',function(chunk){
	
    })
    callback(false,res.statusCode);

})

req.on('error',function(e){
    console.log("error in authserver newUser" + e.message);
})

req.end();

}


function changePass(name,pass,newpass,callback){
var options = {
    hostname: '178.62.70.7',
    port: 57000,
    path: '/auth?player='+name+'&pass='+pass+'&newpass='+newpass,
    method: 'POST'
}
var req = http.request(options,function(res){
    console.log(res.statusCode);
    res.on('data',function(chunk){
	
    })
    callback(false,res.statusCode);

})

req.on('error',function(e){
    console.log("error in authserver newUser" + e.message);
})

req.end();

}

function deleteUser(name,pass,callback){
var options = {
    hostname: '178.62.70.7',
    port: 57000,
    path: '/auth?player='+name+'&pass='+pass,
    method: 'DELETE'
}
var req = http.request(options,function(res){
    console.log(res.statusCode);
    res.on('data',function(chunk){
	
    })
    callback(false,res.statusCode);

})

    req.on('error',function(e){
	console.log("error in authserver newUser" + e.message);
})

    req.end();
    
}


exports.newUser = newUser
exports.authUser = authUser
exports.changePass = changePass
exports.deleteUser = deleteUser
