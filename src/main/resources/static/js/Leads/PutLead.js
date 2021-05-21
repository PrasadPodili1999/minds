$(document).ready(function() {
	
	
	$("#update").click(function(){
	
	$("#updateLead").slideToggle("slow");
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
    			id:$("#leadId").val(),
    			name:$("#leadName").val(),
				salary:$("#leadSalary").val(),
				track:{
					id:$("#leadTrackId").val(),
					
				}
    	}
    	console.log("formData before PUT: " + formData);
    	
    	// DO PUT
    	$.ajax({
			type : "PUT",
			contentType : "application/json",
			url :  "lead/update" ,
			data : JSON.stringify(formData),
			dataType : 'json',
			
			// SUCCESS response
			success : function(lead) {
				// Create successful message
				$("#putLeadDiv").html("<p style='background-color:#67597E; color:white; padding:20px 20px 20px 20px'>" + 
											"Put Successfully! <br>" +
											"--> {id: " + lead.id +
												", name: " + lead.name +
												", salary: " + lead.salary +"<br>"+
												", trackId: " + lead.track.id +
												", trackName: " + lead.track.name +
												"}</p>");
				
				console.log(lead);
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
		$("#leadId").val("");
		$("#leadName").val("");
		$("#leadSalary").val("");
		$("#leadTrackId").val("");
	}
})