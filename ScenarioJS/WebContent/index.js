var ws = require("websocket.io");
var http = require('http');
var sys = require('sys');
var path = require('path');
var fs = require('fs');
var url = require('url');
var crypto = require('crypto');
var mime = require('mime');




var srv = http.createServer(function(request, response) {
  
  
  
  var uri = url.parse(request.url).pathname
  
  , filename = path.join(process.cwd(), uri);
  
  fs.exists(filename, function(exists) {
    
    if(!exists) {
      
      response.writeHead(404, {"Content-Type": "text/plain"});
      
      response.write("404 Not Found\n");
  
  response.end();
  
  return;
  
  }
  
  
  
  if (fs.statSync(filename).isDirectory()) filename += '/index.html';
  
  
  
  fs.readFile(filename, "binary", function(err, file) {
    
    if(err) {
      
      response.writeHead(500, {"Content-Type": "text/plain"});
      
      response.write(err + "\n");
      
      response.end();
      
      return;
      
  }
  
  
  var type = mime.lookup(filename);
  response.writeHead(200, {
    "Content-Type": type
    
  });
  
  response.write(file, "binary");
  
  response.end();
  
});
  
  });
  
  
  
  
  })
  
  
  srv.listen(3000);
  
  
  
  var server = ws.attach(srv);
  var id = 0;
  var connections = {};
  var passwords = {};
  var connID = 0;
  var spc = " ";
  server.clientTracking = false;
  
  server.on('connection', function (client) {
    
    
    
    
    
    
    client.on('message', function (message) { 
      
      var me = message.split(" ");
      
      for(var i = 0; i < me.length; i++){
	
	var stringa = stringa + " " + me[i] ;
	
	
      }
      
      
      if(me[0] == "CLST"){
	var orient = new Array(me[4],me[5],me[6],me[7],me[8],me[9],me[10],me[11],me[12]);
	updatePos(client.id, me[1], me[2], me[3], orient);
	broadcastState(client.id, me[1], me[2], me[3], orient);
	
	
      }
      
      
      
      
      if(me[0] == "HELO"){
	
	
	
	if(me[1] !== undefined){
	  
	  Object.keys(passwords).forEach(function(key) {
	    
	    if(passwords[key] == me[1]){
	      
	      client.pos_x = connections[key].pos_x;
	      client.pos_y = connections[key].pos_y;
	      client.pos_z = connections[key].pos_z;
	      client.dir_x = connections[key].dir_x;
	      client.dir_y = connections[key].dir_y;
	      client.dir_z = connections[key].dir_z;
	      
	      client.id = key;
	      
	      connections[key] = client;
	      fs.unlink(passwords[key] + ".jnlp");
	      delete passwords[key];  
	      
	    }
	    
	  });
	  
	}else{
	  
	  client.id = connID;
	  client.pos_x = 0;
	  client.pos_y = 0;
	  client.pos_z = 0;
	  client.dir = new Array(1, 0, 0, 0, 1, 0, 0, 0, 1);
	  
	  
	}
	client.send("HELO " + client.id + " " + client.pos_x + " " + client.pos_y + " " + client.pos_z + " " + client.dir.join(" "));
	connections[client.id] = client;
	
	
	if (Object.keys(connections).length > 1){
	  
	  
	  sendActualClients(client);
	  
	  
	}
	
	
	
	
	broadcastState(client.id, client.pos_x, client.pos_y, client.pos_z, client.dir);
	connID++;
	
      }
      
      if(me[0] == "JAVA"){
	
	var pass = crypto.randomBytes(32).toString('hex');
	var url = crypto.randomBytes(32).toString('hex');
	passwords[me[1]]  = pass;
	var fileContent = "<?xml version=\"1.0\" encoding=\"utf-8\"?><jnlp spec=\"1.6+\" codebase=\"http://127.0.0.1:3000/\"><information><title>Scenario</title><vendor>Scenario</vendor><offline-allowed/></information><security><all-permissions/></security><resources><j2se version=\"1.6+\" href=\"http://java.sun.com/products/autodl/j2se\"/><jar href=\"scenario.jar\" main=\"true\" /><jar href=\"java-libraries/java_websocket.jar\"/> <extension name=\"jogl-all-awt\" href=\"http://jogamp.org/deployment/jogamp-current/jogl-all-awt.jnlp\" /></resources><application-desc main-class=\"StartClient\"><argument>";
	fileContent = fileContent + pass;
	fileContent = fileContent + "</argument></application-desc></jnlp>";
	
	
	fs.writeFile(url + ".jnlp", fileContent, function(err) {
	  if(err) {
	    
	    console.log(err);
	    
	    
	  } 
	}); 
	
	client.send("JAVA " + url + ".jnlp");
	
      }
      
      if(me[0] == "QUIT"){
	
	
	broadcastQUIT(me[1]);
	
      }
      
      
      
      
    });
	
	
	
	
	client.on('close', function () { 
	  
	  var id = client.id;
	  
	  if(!passwords[id]){
	    removeClient(id);
	    
	    broadcastQUIT(id);
	  }
	  
	});
	
	
	
	
	
  });
	
	
	
	function removeClient(id){
	  
	  delete connections[id];
	  
	  
	  
	  
	}
	
	function updatePos(id, pos_x, pos_y, pos_z, dir){
	  
	  
	  
	  
	  connections[id].pos_x = pos_x;
	  connections[id].pos_y = pos_y;
	  connections[id].pos_z = pos_z;
	  connections[id].dir = dir;
	  
	  
	};
	
	
	
	
	
	function broadcastState(idPOSN, pos_x, pos_y, pos_z, dir){
	  
	  
	  Object.keys(connections).forEach(function(key) {
	    if(key != idPOSN){
	      
	      var connection = connections[key];
	      connection.send("IDST " + idPOSN + " " + pos_x + " " + pos_y + " " + pos_z + " " + dir.join(" "));
	  
	    }
	  });
	
	
	}
	
	
	
	
	
	
	function broadcastQUIT(idQUIT){
	  
	  
	  Object.keys(connections).forEach(function(key) {
	    
	    var connection = connections[key];
	    
	    connection.send("QUIT " + idQUIT);
	  
	  
	  
	  });
	
	
	}
	
	function sendActualClients(client){
	
	var messaggio = "LSST" + " " + (Object.keys(connections).length-1) + " ";
	
	Object.keys(connections).forEach(function(key) {
	  
	  
	  if (client.id != key){
	    
	    var conn = connections[key];
	    
	    //client.send(conn.id + " " + conn.pos_x + " " + conn.pos_y + " " + conn.pos_z + " " + conn.dir_x + " " + conn.dir_y + " " + conn.dir_z);
	    messaggio = messaggio + " " + conn.id + " " + conn.pos_x + " " + conn.pos_y + " " + conn.pos_z + " " + conn.dir.join(" ");
	    
	    
	  }
	  
	  
	});
	    
	    client.send(messaggio);
	    
	}