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

path.exists(filename, function(exists) {

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

server.clientTracking = false;

server.on('connection', function (client) {


		




	client.on('message', function (message) { 

	  var me = message.split(" ");
          
	  
	  
		if(me[0] == "POSN"){
		
		  updatePos(me[1], me[2], me[3], me[4]);
		  broadcastPOSN(me[1], me[2], me[3], me[4]);

		  
		}
		
		
		
		
		if(me[0] == "HELO"){
		  
		    if(me[1] !== undefined){
		  
		      Object.keys(passwords).forEach(function(key) {
			 
			 if(passwords[key] == me[1]){
			   
			  client.pos_x = connections[key].pos_x;
			  client.pos_y = connections[key].pos_y;
			  client.pos_z = connections[key].pos_z;
                         client.id = key;
			 
			 connections[key] = client;
			 fs.unlink(passwords[key] + ".jnlp");
			 delete passwords[key];  
			
			}
			 
		      });
		      
		     }else{
		     
		      client.id = connID;
		      client.pos_x = 0;
		      client.pos_y = -0.5;
		      client.pos_z = -2;
		       
		    }
		    
		   client.send("HELO " + client.id);

		   client.send("POSN " + client.id + " " + client.pos_x + " " + client.pos_y + " " + client.pos_z);
		   connections[client.id] = client;
		   
		   
			if (Object.keys(connections).length > 1){
			
				
				notifyAllPOSN(client.id);
		

			}
		
			
			
		
			broadcastPOSN(client.id, client.pos_x, client.pos_y, client.pos_z);
			connID++;
		
		}
		
		if(me[0] == "JAVA"){
		  
		  var pass = crypto.randomBytes(32).toString('hex');
		  passwords[me[1]]  = pass;
		  var fileContent = "<?xml version=\"1.0\" encoding=\"utf-8\"?><jnlp spec=\"1.6+\" codebase=\"http://127.0.0.1:3000/\"><information><title>Scenario</title><vendor>Scenario</vendor><offline-allowed/></information><security><all-permissions/></security><resources><j2se version=\"1.6+\" href=\"http://java.sun.com/products/autodl/j2se\"/><jar href=\"scenario.jar\" main=\"true\" /><jar href=\"java-libraries/java_websocket.jar\"/> <extension name=\"jogl-all-awt\" href=\"http://jogamp.org/deployment/jogamp-current/jogl-all-awt.jnlp\" /></resources><application-desc main-class=\"TestAlb01\"><argument>";
		  fileContent = fileContent + pass;
		  fileContent = fileContent + "</argument></application-desc></jnlp>";
		  
		  
		  fs.writeFile(pass + ".jnlp", fileContent, function(err) {
		    if(err) {
				
			      console.log(err);
			  
		      
		    } 
		  }); 
		  
		  client.send("JAVA " + pass + ".jnlp");
		 
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

function updatePos(id, pos_x, pos_y, pos_z){

		
			
			
					connections[id].pos_x = pos_x;
					connections[id].pos_y = pos_y;
					connections[id].pos_z = pos_z;


};





function broadcastPOSN(idPOSN, pos_x, pos_y, pos_z){

  
  Object.keys(connections).forEach(function(key) {
    if(key != idPOSN){
      
        var connection = connections[key];
	connection.send("POSN " + idPOSN + " " + pos_x + " " + pos_y + " " + pos_z);
      
      
    }
    });
  
  
}
  
  




function broadcastQUIT(idQUIT){

  
   Object.keys(connections).forEach(function(key) {
   
        var connection = connections[key];
     
	connection.send("QUIT " + idQUIT);
      
      
    
    });

		
}

function notifyAllPOSN(id){
  
  
  Object.keys(connections).forEach(function(key) {
  
    if (key != id){
      
        var conn = connections[key];
        connections[id].send("POSN " + conn.id + " " + conn.pos_x + " " + conn.pos_y + " " + conn.pos_z);
      
    }
    
    });

  
  
}