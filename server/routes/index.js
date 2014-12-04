var express = require('express');
var router = express.Router();
var jwt = require('jsonwebtoken');
var dblayer = require('../model/dblayer.js')
var auth = require('../security/authserver.js')



/* GET home page. */
router.get('/', function(req, res) {
  res.redirect("app/index.html")
});



router.post('/authenticate', function (req, res) {
    if(req.body.username && req.body.password){
	auth.authUser(req.body.username,req.body.password,function(err,code){
	    var user;
	    var token;
	    console.log(req.body.username+"with pass "+req.body.password+"authed: "+code)
	    if(code === 200){
	    dblayer.findPlayerByName(req.body.username,function(err,person){
		user = person;
		token = jwt.sign(user,require("../security/secrets").secretTokenUser,{expiresInMinutes:60*5});
		res.json({token:token});
		return;
	    })	
	    }
	    else{
		res.status(401).send('unable to auth');
		return;
	    }
	 
	    
	})
    }
   /*
  //TODO: Go and get UserName Password from "somewhere"
  //if is invalid, return 401
   if (req.body.username === 'student' && req.body.password === 'test') {
    var profile = {
      username: 'Bo the Student',
      role: "user",
      id: 1000
    };
    // We are sending the profile inside the token
    var token = jwt.sign(profile, require("../security/secrets").secretTokenUser, { expiresInMinutes: 60*5 });
    res.json({ token: token });
    return;
  }

  if (req.body.username === 'teacher' && req.body.password === 'test') {
    var profile = {
      username: 'Peter the Teacher',
      role: "admin",
      id: 123423
    };
    // We are sending the profile inside the token
    var token = jwt.sign(profile, require("../security/secrets").secretTokenAdmin, { expiresInMinutes: 60*5 });
    res.json({ token: token });
    return;
  }
  */
  else{
    res.status(401).send('Wrong user or password');
    return;
  }
});

module.exports = router;
