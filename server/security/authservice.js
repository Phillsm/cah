var bcrypt = require('bcrypt');
var server = require('./authserver.js');

// handle new user
function newUser(name,password,callback){
    bcrypt.genSalt(10,function(err,salt){
        if (err){
            callback(err);
            return;
        }
	    bcrypt.hash(password,salt,function(err,hash){
	        if(err){
                callback(err);
                return;
            }
	    
            //TODO: Fix response after server file is written
            server.newUser(name,hash,function(err,response){
                callback(false,response)
            });
	    });
    });
};

// handle auth of old user
function authUser(name,password,callback){
    server.authUser(name,password,function(err,hash){
	if(err){callback(err);return}
	bcrypt.compare(password,hash,function(err,res){
	    if(err){callback(err);return}
	    callback(false,res);
	})
    })
}

// handle change password of existing user
function changePass(name,password,newpass,callback){
    server.changePass(name,password,newpass,function(err,res){
	if(err){callback(err); return}
	
	callback(res);
    })
}

exports.newUser = newUser
exports.authUser = authUser
exports.changePass = changePass
