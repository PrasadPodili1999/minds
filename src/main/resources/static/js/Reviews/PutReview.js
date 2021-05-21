$(document).ready(function() {
	
	
	$("#update").click(function(){
	
	$("#updateReview").slideToggle("slow");
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
    			id:$("#reviewId").val(),
    			capabilityNo:$("#updatecapabilityNo").val(),
				status:$("#updatestatus").val(),
				takenFor:{
					id:$("#updatemindId").val()
				},
				takenBy:{
					id:$("#updateleadId").val()
				}
    	}
    	console.log("formData before PUT: " + formData);
    	
    	// DO PUT
    	$.ajax({
			type : "PUT",
			contentType : "application/json",
			url :  "review/update" ,
			data : JSON.stringify(formData),
			dataType : 'json',
			
			// SUCCESS response
			success : function(review) {
				// Create successful message
				$("#putReviewDiv").html("<p style='background-color:#67597E; color:white; padding:20px 20px 20px 20px'>" + 
											"Put Successfully! <br>" +
											"--> {id: " + review.id +
												", capabilityNo: " + review.capabilityNo +
												", status: " + review.status +"<br>"+
												"}</p>");
				
				console.log(review);
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
		$("#reviewId").val("");
		$("#updatecapabilityNo").val("");
		$("#updatestatus").val("");
		$("#updatemindId").val("");
		$("#updateleadId").val("");
	}
})