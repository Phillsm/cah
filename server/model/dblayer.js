var mongoose = require('mongoose');
var model = require('./model.js');
var uuid = require('node-uuid');

exports.findPlayerByName = function(name,callback){
//    console.log('testtest')
    model.PlayerModel.find({name: name}, function(err,player){
	if(err){return callback(err)}
//	console.log("test")
	callback(false,player)
    })
}

/*
exports.createNewPlayer = function(name,callback){
    model.PlayerModel.find({name:name},function(err,data){
	if(data.length){
	    callback("User already exists")
	}
	else{
	    var newplayer = new model.PlayerModel({_id: uuid.v1(), name: name})
	    newplayer.save(function(err){
		callback(err,newplayer);
	    })
	}
    })
    
    
}
*/
