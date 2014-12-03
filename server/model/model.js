var mongoose = require('mongoose');

var PlayerSchema = mongoose.Schema({
    _id: Number,
    name: String,
    customCards: [{type: Number, ref: 'cards'}],
    games: [{type: Number, ref: 'games'}]
});

exports.PlayerModel = mongoose.model('players',PlayerSchema);

var CardSchema = mongoose.Schema({
    _id: Number,
    cardType: String,
    text: String,
    numAnswers: Number,
    expansion: String, // customCard for custom player card
    owner: {type: Number, ref: 'players'} // optional, available if custom card
});

exports.CardModel = mongoose.model('cards',CardSchema);

var GameSchema = mongoose.Schema({
    _id: Number,
    starter: {type: Number, ref: 'players'},
    players: [{type:Number, ref: 'players'}], // all players incl starter
    rounds: [{ // can be filled upon instantiation
	roundNum: Number,
	roundQuestion: {type: Number, ref: 'cards'}
    }],
    answers: [{ // will get added to when players play
	roundNum: Number,
	player: {type: Number, ref:'players'},
	cards: [{type:Number, ref:'cards'}] // one or more cards depending on the question
    }],
    finished: [{type: Number, ref:'players'}] // client pushes it's id into this to ease the process 
                                              // of parsing for finished players
});

exports.GameModel = mongoose.model('games',GameSchema);

var HandSchema = mongoose.Schema({
   _id: Number,
   player: {type: Number, ref: 'players'},
   game: {type: Number, ref: 'games'},
   cards: [{type:Number, ref: 'cards'}]
});

exports.HandModel = mongoose.model('hands',HandSchema);
