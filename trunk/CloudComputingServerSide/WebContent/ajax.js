if(typeof(EventSource)!=="undefined"){
  var source=new EventSource("127.0.0.1");
  source.onmessage=function(event){
    document.getElementById("result").innerHTML+=event.data + "<br>";
    };
  }else{
  document.getElementById("result").innerHTML="Sorry, your browser does not support server-sent events...";
  }

function loadXMLDoc(){
	var xmlhttp;
	if (window.XMLHttpRequest){
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}else{
		// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
		xmlhttp.onreadystatechange=function(){
			if (xmlhttp.readyState==4 && xmlhttp.status==200){
                var xmlFile;
                xmlFile=xmlhttp.responseXML;
                var x=xmlFile.getElementsByTagName("user");
                var tableContent;
                tableContent="<tr><th>Player</th><th>Ip</th><th>Platform</th><th>Game</th></tr>"
                for (i=0;i<x.length;i++)
                {
                  tableContent+="<tr><td>"+x[i].getElementsByTagName("nick")[0].childNodes[0].nodeValue+"</td><td>";
                  tableContent+=x[i].getElementsByTagName("ip")[0].childNodes[0].nodeValue+"</td><td>";
                  tableContent+=x[i].getElementsByTagName("platform")[0].childNodes[0].nodeValue+"</td><td>";
                  tableContent+=x[i].getElementsByTagName("game")[0].childNodes[0].nodeValue+"</td></tr>";

                  }
                document.getElementById("tab").innerHTML=tableContent;
//				document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
			}
		}
		xmlhttp.open("POST","./HomeHtml",true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlhttp.send("action=refresh");
		window.setTimeout("loadXMLDoc()", 2000); 

}
window.setTimeout("loadXMLDoc()", 0); 
window.setTimeout("loadProfile()", 0); 




function logout(){
	var xmlhttp;
	if (window.XMLHttpRequest){
		// codice per IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}else{
		// codice per IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.open("POST","./Login",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send("platform=html&action=logout");
	window.location.assign("./html/login/login.html");

}
function loadProfile(){
    var request;
	if (window.XMLHttpRequest){
		// codice per IE7+, Firefox, Chrome, Opera, Safari
		request=new XMLHttpRequest();
	}else{
		// codice per IE6, IE5
		request=new ActiveXObject("Microsoft.XMLHTTP");
	}
	request.open("POST","./HomeHtml",true);
	request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	request.send("action=getProfile");
    request.onreadystatechange=function(){
			if (request.readyState==4 && request.status==200){
                var xmlFile;
                xmlFile=request.responseXML;
                var x=xmlFile.getElementsByTagName("user");
                document.getElementById("utente").innerHTML=x[0].getElementsByTagName("nick")[0].childNodes[0].nodeValue;
                document.getElementsByTagName("input")[0].setAttribute("value", x[0].getElementsByTagName("game")[0].childNodes[0].nodeValue );
                   
                
		}
    }
}

