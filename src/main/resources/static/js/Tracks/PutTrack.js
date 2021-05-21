$(document).ready(function() {
	
	
	$("#update").click(function(){
	
	$("#updateTrack").slideToggle("slow");
	});
	
	
	
	$("#customizedForm").submit(function(){
		event.preventDefault();
		ajaxPut();
	});
	
	
	/*
	 * AJAX PUT updated-form
	 */
    function ajaxPut(){
    	// PREPARE FORM DATA
    	var formData = {
    			id: $("#updateId").val(),
    			name : $("#updateName").val(),
    			basedOn : $("#updateBasedOn").val()
    	}
    	
    	
    	console.log("formData before PUT: " + formData);
    	
    	// DO PUT
    	$.ajax({
			type : "PUT",
			contentType : "application/json",
			url :  "track/update" ,
			data : JSON.stringify(formData),
			dataType : 'json',
			
			// SUCCESS response
			success : function(track) {
				// Create successful message
				$("#putTrackDiv").html("<p style='background-color:#67597E; color:white; padding:20px 20px 20px 20px'>" + 
											"Put Successfully! <br>" +
											"--> {id: " + track.id +
												"name: " + track.name +
												", basedOn: " + track.basedOn +"}</p>");
				
				console.log(result);
			},
			
			// ERROR response 
			error : function(e) {
				alert("Error!")
				console.log("ERROR: "+ e);
			}
		});
		resetData();
    }
	function resetData(){
		$("#id").val("");
		$("#name").val("");
		$("#basedOn").val("");
	}
})