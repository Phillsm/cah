var dblayer = require('../../server/model/dblayer.js');
var assert = require('assert');
var should = require('should');
var db;

describe('dblayer',function(){

    before(function(done){
	db = require('../../server/model/db.js');
	done();
    });


    // Find player
    it('should find a player by name',function(done){
        dblayer.findPlayerByName('testUser',function(err,data){
            assert.equal(data.name, 'testUser');
            done();
        });
    });


    // Create player
    it('should create a new player by name', function(done) {
        var username = 'testUser';
        dblayer.createNewPlayer(username, function(err,data) {
        assert.equal(data.name, username);
        done();
        })
    });


    // Get answer
    it('should return a random answer', function(done) {
        dblayer.getRandomAnswer(function(err,data) {
            assert.equal(data, !null);
            done();
        });
    });


    // Get question
    it('should return a random question', function(done) {
        dblayer.getRandomQuestion(function(err,data) {
            assert.equal(data.text, !null);
            done();
        });
    });


    // Get hand (no save hand)
    it()
    // Get game -(no save game)
    it()

});
