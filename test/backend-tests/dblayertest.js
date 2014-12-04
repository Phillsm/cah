var should = require('should');
var dblayer = require('../../server/model/dblayer.js')
var db;

describe('dblayer',function(){

    before(function(done){
	db = require('../../server/model/db.js')
	done();
    })
    it('should find a player by name',function(){
	dblayer.findPlayerByName('psm',function(err,data){
	    console.log(err);
	    console.log(data);
	    assert.equal('thisshouldnotbehappeningshouldit',data.name)
	});
    });

});
