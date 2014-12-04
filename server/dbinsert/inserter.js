var fs = require('fs');
var mongoose = require('mongoose');
var models = require('../model/model.js')
var cardsjs = require('./cards.js');

var cards = cardsjs.cards

console.log(cards.length);
console.log(cards[2]);



var cardModels = cards.map(function(card){
    return new models.CardModel({
	_id: card.id,
	cardType: card.cardType,
	text: card.text,
	numAnswers: card.numAnswers,
	expansion: card.expansion,
	owner: null
    })
})

var db = mongoose.connect('mongodb://localhost/cahdb')

db.connection.once('open',function(err){
    console.log('connected')
})

db.connection.on('error',function(err){
    console.log(err);
})

function insert(){
    cardModels.forEach(function(cmodel){
	cmodel.save(function(err,ok){
	    if(err){console.log(err)}
	})
    })
    db.connection.close();
}


insert();

