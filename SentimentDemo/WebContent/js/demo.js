/**
 * 
 */
$(document).ready(function() {
	$( "#sentimentBtn" ).click(function() {
		  var text = $("#textInputArea").val();
		  console.log("Getting sentiment "+text);
		  var url="./services/rest/textSentimentPost";
		  $.ajax({
			  dataType: "json",
			  type: 'post',
			  url: url,
			  data : text,
			  success: handleSentimentResponse
			  });
		});

	
	function handleSentimentResponse(data){
		//console.log(data);
		var innerHtml="<table><tr><th>Attribute</th><th>Value</th></tr>"
		for(var property in data){
		    if (data.hasOwnProperty(property)) {
		    	 innerHtml=innerHtml+"\r\n"+"<tr><td>"+property+"</td><td>"+data[property]+"</td></tr>";
		    }
		}
		innerHtml=innerHtml+"\r\n"+"</table>";
		$("#textResponse").html(innerHtml);
		//addResponseToPlot(data);
	}
});