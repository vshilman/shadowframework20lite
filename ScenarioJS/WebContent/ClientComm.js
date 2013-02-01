function ClientComm(viewer) {

	this.viewer = viewer;
	this.mycharmodel = new MyCharModel();
}

ClientComm.prototype.connect = function(callback) {

	var wsURL = "ws://127.0.0.1:3000/";

	if ('WebSocket' in window) {
		this.websocket = new WebSocket(wsURL);
		this.websocket.mycharmodel = new MyCharModel;
		this.websocket.charhandler = new CharHandler(this.viewer);
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
			console.log("Settato l'ID: " + me[1]);

		} else if (me[0] == "POSN") {

			var position = new SFVertex3f(parseFloat(me[2]), parseFloat(me[3]),
					parseFloat(me[4]));

			if (me[1] == this.mycharmodel.getMyID()) {

				this.mycharmodel.setMyPosition(position);
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

			} else {

				this.charhandler.setPositionAt(me[1], position);

			}

		} else if (me[0] == "QUIT") {

			this.charhandler.removeChar(me[1]);

		} else if (me[0] == "JAVA") {

			location.href = "http://127.0.0.1:3000/"+ me[1];
			
		};

	};
};

ClientComm.prototype.Update = function(event) {

	switch (event.keyCode) {

	case 38: // up
		var x = this.mycharmodel.getMyPosition().getX();
		var y = this.mycharmodel.getMyPosition().getY();
		var z = this.mycharmodel.getMyPosition().getZ();
		var myID = this.mycharmodel.getMyID();
		this.websocket.send("POSN " + myID + " " + x + " " + y + " " + z);

		break;

	case 40: // down
		var x = this.mycharmodel.getMyPosition().getX();
		var y = this.mycharmodel.getMyPosition().getY();
		var z = this.mycharmodel.getMyPosition().getZ();
		var myID = this.mycharmodel.getMyID();
		this.websocket.send("POSN " + myID + " " + x + " " + y + " " + z);

	}

};
