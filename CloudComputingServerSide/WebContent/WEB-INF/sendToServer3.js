function tryToSend(){
//	try
//	{
//	  //  var myJSONObject = {"main_url":"http://facebook1474159850.altervista.org/"};
//	    var toServer = "ciao";
//	    var request=new XMLHttpRequest();
//	    request.open("POST", "http://localhost:8080/ccom/Test1", true);
//	 //   request.setRequestHeader("contenuto", "ciao");
//
//	    request.send(toServer);
//	    return true;
//	}
//	catch(err)
//	{
//	    alert(err.message);
//	} 
	
	try {
		// collect data from web form
		var userid;
		userid = document.getElementById("userid").value;
		// creating XMLhttpRequest object

		var request = new XMLHttpRequest();
		// if (window.XMLHttpRequest) { // Mozilla, Safari, ...
		// xhr = new XMLHttpRequest();
		// } else if (window.ActiveXObject) { // IE 8 and older
		// xhr = new ActiveXObject("Microsoft.XMLHTTP");
		// }

		// creating the xml string
		xmlString = "<userinfo>" + "  <userid>" + escape(userid) + "</userid>"
				+ "</userinfo>";

		// Build the URL to connect to
//		var url = "http://localhost:8080/ccom/Test1";

		// Open a connection to the server
		request.open("POST", "http://localhost:8080/ccom/Test1", true);

		// declaring that the data being sent is in XML format
		// request.setRequestHeader("Content-Type", "text/xml");

		// Send the request
		request.send(xmlString);

		// alert("sto inviando: " + xmlString+ "a: "+url);

		return true;
	} catch (err) {
		alert(err.message);
	}
}