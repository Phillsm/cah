var mongoose = require('mongoose');

var PlayerSchema = mongoose.Schema({
    _id: String,
    name: String,
    customCards: [{type: Number, ref: 'cards'}],
    games: [{type: String, ref: 'games'}]
});

exports.PlayerModel = mongoose.model('players',PlayerSchema);

var CardSchema = mongoose.Schema({
    _id: Number,
    cardType: String,
    text: String,
    numAnswers: Number,
    expansion: String, // customCard for custom player card
    owner: {type: String, ref: 'players'} // optional, available if custom card
});

exports.CardModel = mongoose.model('cards',CardSchema);

var GameSchema = mongoose.Schema({
    _id: String,
    starter: {type: String, ref: 'players'},
    players: [{type:String, ref: 'players'}], // all players incl starter
    rounds: [{ // can be filled upon instantiation
	roundNum: Number,
	roundQuestion: {type: Number, ref: 'cards'}
    }],
    answers: [{ // will get added to when players play
	roundNum: Number,
	player: {type: String, ref:'players'},
	cards: [{type: String, ref:'cards'}] // one or more cards depending on the question
    }],
    finished: [{type: String, ref:'players'}] // client pushes it's id into this to ease the process 
                                              // of parsing for finished players
});

exports.GameModel = mongoose.model('games',GameSchema);

var HandSchema = mongoose.Schema({
   _id: String,
   player: {type: String, ref: 'players'},
   game: {type: String, ref: 'games'},
   cards: [{type:String, ref: 'cards'}]
});

exports.HandModel = mongoose.model('hands',HandSchema);
