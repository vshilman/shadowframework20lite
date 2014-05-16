function tryToSend(txt) {
	var xmlhttp;
	var text;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();

	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	text = convertToXML(txt);
	xmlhttp.open("GET", "localhost:6789/127.0.0.1", true);
	xmlhttp.setRequestHeader("Accept", "text/xml");
	httpRequest.onreadystatechange = OnStateChange;
	xmlhttp.send(text);
};


function convertToXML(txt) {

	var openTag = "<testo>";
	var closeTag = "</testo>";

	txt = openTag + txt + closeTag;
	return txt;
};

function OnStateChange() {
	if (httpRequest.readyState == 0 || httpRequest.readyState == 4) {
		if (IsRequestSuccessful(httpRequest)) { // defined in ajax.js
			alert("Request complete.");
		} else {
			alert("Operation failed.");
		}
	}
};

function IsRequestSuccessful(httpRequest) {
	// IE: sometimes 1223 instead of 204
	var success = (httpRequest.status == 0
			|| (httpRequest.status >= 200 && httpRequest.status < 300)
			|| httpRequest.status == 304 || httpRequest.status == 1223);

	return success;
};
/*
function CreateXMLDocumentObject(rootName) {
	if (!rootName) {
		rootName = "";
	}
	var xmlDoc = CreateMSXMLDocumentObject();
	if (xmlDoc) {
		if (rootName) {
			var rootNode = xmlDoc.createElement(rootName);
			xmlDoc.appendChild(rootNode);
		}
	} else {
		if (document.implementation.createDocument) {
			xmlDoc = document.implementation.createDocument("", rootName, null);
		}
	}

	return xmlDoc;
}
*/