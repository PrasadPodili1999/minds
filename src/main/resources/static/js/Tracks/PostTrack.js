$(Document).ready(function(){
	
	//to display add form
	$("#add").click(function(){
		
		$("#addTrack").slideToggle("slow");
	});
	
	//to submit form data
	$("#trackForm").submit(function(event){
		
		event.preventDefault();
		ajaxPost();
	});
	
	function ajaxPost(){
		
		// PREPARE FORM DATA
		var formData={
			name:$("#name").val(),
			basedOn:$("#basedOn").val()
		}
		
		console.log("formData before post: " + formData);
		
		// DO POST
		$.ajax({
			
			type : "POST",
			contentType : "application/json",
			url : "track/addTrack",
			data : JSON.stringify(formData),
			dataType : 'json',
			success:function(result){
				
				$("#postTrackDiv").html("<p style='background-color:#7FA7B0; color:white; padding:20px 20px 20px 20px'>"+
				"post successfully!<br>"+
				"-->{name:"+result.name+
				",basedOn: "+result.basedOn+"}</p>");
				
				console.log(result);
			},
			error:function(e){
				alert("Error!!");
				console.log("Error :"+e);
			}
		});
		// Reset FormData after Posting
		resetData();	
		
	}
	 function resetData(){
    	$("#name").val("");
    	$("#basedOn").val("")
    }
});