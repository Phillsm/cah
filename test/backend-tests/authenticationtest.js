/*
var should = require("should");
var auth = require('../../server/security/authserver.js');
var assert = require('assert'); // derudover add done

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

    it('should return 200 when I save a new user',function(done){
	auth.newUser('puttest','putpass',function(err,code){
	    assert.equal(200,code);
        done();
    });

	it('should return 200 when I delete a user',function(done){
	    auth.deleteUser('puttest','putpass',function(err,code){
		assert.equal(200,code);
        done();
;	    });
	});
    });

    it('should return 200 when I authenticate with correct password',function(done){
	auth.authUser(username,password,function(err,code){
	    assert.equal(200,code);
        done();
	})
    })

    it('should return 400 when I create a user with a name that already exists',function(done){
	auth.newUser(username,password,function(err,code){
	    assert.equal(400,code);
        done();
	})
    })
    it('should return 400 when I authenticate with wrong password',function(done){
	auth.authUser(username,"notthepass",function(err,code){
	    assert.equal(400,code);
        done();
	})
    })


});
*/