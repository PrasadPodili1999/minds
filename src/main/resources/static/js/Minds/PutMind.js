$(document).ready(function() {
	
	
	$("#update").click(function(){
	
	$("#updateMind").slideToggle("slow");
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
    			id:$("#mindId").val(),
    			name:$("#mindName").val(),
				salary:$("#mindSalary").val(),
				track:{
					id:$("#trackId").val()
				}
    	}
    	console.log("formData before PUT: " + formData);
    	
    	// DO PUT
    	$.ajax({
			type : "PUT",
			contentType : "application/json",
			url :  "mind/update" ,
			data : JSON.stringify(formData),
			dataType : 'json',
			
			// SUCCESS response
			success : function(mind) {
				// Create successful message
				$("#putMindDiv").html("<p style='background-color:#67597E; color:white; padding:20px 20px 20px 20px'>" + 
											"Put Successfully! <br>" +
											"--> {id: " + mind.id +
												", name: " + mind.name +
												", salary: " + mind.salary +"<br>"+
												", trackId: " + mind.track.id +
												", trackName: " + mind.track.name +
												", trackBasedOn: " + mind.track.basedOn +
												"}</p>");
				
				console.log(mind);
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
		$("#mindId").val("");
		$("#mindName").val("");
		$("#mindSalary").val("");
		$("#trackId").val("");
		
	}
})