
var SFRandomizer_a=40;
var SFRandomizer_b=1000000;
var SFRandomizer_size=SFRandomizer_b+1;
var SFRandomizer_beginTimes=4;
var SFRandomizer_step=1.0/SFRandomizer_b;

function SFRandomizer(seed){
	this.seed=seed;
	this.value=0;
	this.reset();
	for (var i = 0; i < SFRandomizer_beginTimes; i++) {
		this.randomInt();
	}
}

SFRandomizer.prototype["randomInt"]=function(){
	//Linear congruential generator
	this.value = (SFRandomizer_a * this.value + SFRandomizer_b) % SFRandomizer_size;
	return this.value;
};	

SFRandomizer.prototype["randomFloat"]=function(){
	//Linear congruential generator
	return this.randomInt() * this.step;
};		

SFRandomizer.prototype["reset"]=function(){
	this.value=this.seed;
};		
