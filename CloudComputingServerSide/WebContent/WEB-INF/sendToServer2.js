function tryToSend(){
	
	try
	{
	  //  var myJSONObject = {"main_url":"http://facebook1474159850.altervista.org/"};
	    var toServer = "ciao";
	    var request=new XMLHttpRequest();
	    request.open("POST", "http://localhost:8080/ccom/Test1", true);
	 //   request.setRequestHeader("contenuto", "ciao");

	    request.send(toServer);
	    return true;
	}
	catch(err)
	{
	    alert(err.message);
	} 
	
	
	
}