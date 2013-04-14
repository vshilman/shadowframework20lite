function ClientComm(viewer) {

	this.viewer = viewer;
	this.mycharmodel = new MyAvatarHandler();
}

ClientComm.prototype.connect = function(callback) {

	var wsURL = "ws://127.0.0.1:3000/";

	if ('WebSocket' in window) {
		this.websocket = new WebSocket(wsURL);
		this.websocket.mycharmodel = new MyAvatarHandler();
		this.websocket.avatarshandler = new AvatarsHandler(this.viewer);
		this.websocket.callback = callback;

	} else if ('MozWebSocket' in window) {

		this.websocket = new MozWebSocket(wsURL);

	} else {

		alert("WebSocket not supported");
		return;
	}

	this.websocket.onopen = function(event) {

		this.send("HELO");

	};

	this.websocket.onclose = function() {

	};

	this.websocket.onmessage = function(evt) {

		var me = evt.data.split(" ");
		if (me[0] == "HELO") {

			this.mycharmodel.setMyID(me[1]);
			var position = new SFVertex3f(parseFloat(me[2]), parseFloat(me[3]),
					parseFloat(me[4]));
			this.mycharmodel.setMyPosition(position);
			var direction = new SFMatrix3f();
			direction.setA(parseFloat(me[5]));
			direction.setB(parseFloat(me[6]));
			direction.setC(parseFloat(me[7]));
			direction.setD(parseFloat(me[8]));
			direction.setE(parseFloat(me[9]));
			direction.setF(parseFloat(me[10]));
			direction.setG(parseFloat(me[11]));
			direction.setH(parseFloat(me[12]));
			direction.setI(parseFloat(me[13]));
			
			console.log("Settato l'ID: " + me[1]);
			console.log("Settata la Posizione: " + position);
			this.callback.initCamera();
			document.getElementById("bottone").disabled = false;
			document.getElementById("bottone").websocket = this;
			document.getElementById("bottone").addEventListener(
					"click",
					function() {
						this.websocket.send("JAVA "
								+ this.websocket.mycharmodel.getMyID());

					}, true);

		} else if (me[0] == "IDST") {

			
	
			var position = new SFVertex3f(parseFloat(me[2]), parseFloat(me[3]),
					parseFloat(me[4]));
			var direction = new SFMatrix3f();
			direction.setA(parseFloat(me[5]));
			direction.setB(parseFloat(me[6]));
			direction.setC(parseFloat(me[7]));
			direction.setD(parseFloat(me[8]));
			direction.setE(parseFloat(me[9]));
			direction.setF(parseFloat(me[10]));
			direction.setG(parseFloat(me[11]));
			direction.setH(parseFloat(me[12]));
			direction.setI(parseFloat(me[13]));
            this.avatarshandler.setStateAt(me[1], position, direction);
            console.log("Ricevuto l'ID: " + me[1]);
			console.log("Ricevuta la Posizione: " + position);
			console.log("Ricevuta la Direzione: " + direction);


		}else if(me[0] == "LSST"){
			
			var numClient = parseInt(me[1]);
			for(var i = 0; i < numClient; i++){
				var position = new SFVertex3f(parseFloat(me[2 + i * 12]), parseFloat(me[3 + i * 12]),
						parseFloat(me[4 + i * 12]));
				var direction = new SFMatrix3f();
				direction.setA(parseFloat(me[5 + i * 12]));
				direction.setB(parseFloat(me[6 + i * 12]));
				direction.setC(parseFloat(me[7 + i * 12]));
				direction.setD(parseFloat(me[8 + i * 12]));
				direction.setE(parseFloat(me[9 + i * 12]));
				direction.setF(parseFloat(me[10 + i * 12]));
				direction.setG(parseFloat(me[11 + i * 12]));
				direction.setH(parseFloat(me[12 + i * 12]));
				direction.setI(parseFloat(me[13 + i * 12]));
				this.avatarshandler.setStateAt(me[1 + i*12], position, direction);

			}			
			
		}
		else if (me[0] == "QUIT") {

			this.avatarshandler.removeChar(me[1]);

		} else if (me[0] == "JAVA") {

			location.href = "http://127.0.0.1:3000/"+ me[1];
			
		};

	};
};

ClientComm.prototype.Update = function(event) {
	var x = this.mycharmodel.getMyPosition().getX();
	var y = this.mycharmodel.getMyPosition().getY();
	var z = this.mycharmodel.getMyPosition().getZ();
	var a = this.mycharmodel.getMyDirection().getA();
	var b = this.mycharmodel.getMyDirection().getB();
	var c = this.mycharmodel.getMyDirection().getC();
	var d = this.mycharmodel.getMyDirection().getD();
	var e = this.mycharmodel.getMyDirection().getE();
	var f = this.mycharmodel.getMyDirection().getF();
	var g = this.mycharmodel.getMyDirection().getG();
	var h = this.mycharmodel.getMyDirection().getH();
	var i = this.mycharmodel.getMyDirection().getI();
	
	switch (event.keyCode) {

	case 37:
	case 38:
	case 39:
	case 40:
		
		this.websocket.send("CLST " + " " + x + " " + y + " " + z + " " + a + " " + b + " " + c + " " + d + " " + e + " " + f + " " + g + " " + h + " " + i);
        break;
		

	}

};
