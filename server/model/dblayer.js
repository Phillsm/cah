
var model = require('./model.js');
var uuid = require('node-uuid');
//var random = require('mongoose-random');

var cards;

function rand(int){
  return Math.floor((Math.random()*int)+1);
}

exports.findPlayerByName = function(name,callback){
//    console.log('testtest')
    model.PlayerModel.find({name: name}, function(err,player){
	if(err){return callback(err)}
//	console.log("test")
	callback(false,player)
    })
}

exports.getRandomAnswer = function(callback){
 if(cards == null){
    var questions = model.CardModel.find({},function(err,rcards){
	cards = rcards
	var questioncards = cards.filter(function(card){return card.cardType === 'A'})
	var card = questioncards[rand(questioncards.length)]
	callback(card);
    })
    }
    else{
	var questioncards = cards.filter(function(card){return card.cardType === 'A'})
	var card = questioncards[rand(questioncards.length)]
	callback(card);
    }
}

exports.getRandomQuestion = function(callback){
    if(cards == null){
    var questions = model.CardModel.find({},function(err,rcards){
	cards = rcards
	var questioncards = cards.filter(function(card){return card.cardType === 'Q'})
	var card = questioncards[rand(questioncards.length)]
	callback(card);
    })
    }
    else{
	var questioncards = cards.filter(function(card){return card.cardType === 'Q'})
	var card = questioncards[rand(questioncards.length)]
	callback(card);
    }
}

exports.getHand = function(gameid,playerid,callback){
    model.HandModel.find({player:playerid,game:gameid},function(err,hand){
	if(err){
	    callback(err);
	}
	else{
	    callback(false,hand);
	}
    })
}

exports.getGame = function(gameid,callback){
    model.GameModel.find({_id:gameid},function(err,game){
	if(err){
	    callback(err);
	}
	else{
	    callback(false,game);
	}
    })
}




/*
var db = require('./db')
getRandomQuestion(function(x){console.log(x)})
*/




exports.createNewPlayer = function(name,callback){
    model.PlayerModel.find({name:name},function(err,data){
	if(data.length){
	    callback("User already exists")
	}
	else{
	    var newplayer = new model.PlayerModel({_id: uuid.v4(), name: name})
	    newplayer.save(function(err){
		callback(err,newplayer);
	    })
	}
    })
    
    
}
