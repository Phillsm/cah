var should = require("should");
var auth = require('../../server/security/authserver.js');

var username;
var password;
describe('Authentication', function(){
    before(function(done){
	username = "shouldtest";
	password = "password";
	done();
    });
    after(function(done){
	auth.deleteUser(username,password,function(err,cb){});
	done();
    })

    it('should return 200 when I save a new user',function(){
	auth.newUser('puttest','putpass',function(err,code){
	    assert.equal(200,code);
	});
	it('should return 200 when I delete a user',function(){
	    auth.deleteUser('puttest','putpass',function(err,code){
		assert.equal(200,code);
	    });
	});
    });

    it('should return 200 when I authenticate with correct password',function(){
	auth.authUser(username,password,function(err,code){
	    assert.equal(200,code)
	})
    })

    it('should return 400 when I create a user with a name that already exists',function(){
	auth.newUser(username,password,function(err,code){
	    assert.equal(400,code);
	})
    })
    it('should return 400 when I authenticate with wrong password',function(){
	auth.authUser(username,"notthepass",function(err,code){
	    assert.equal(400,code);
	})
    })


});
