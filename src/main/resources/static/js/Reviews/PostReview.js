$(document).ready(function(){
	
	$("#add").click(function(){
		
		$("#addReview").slideToggle("slow");
	});
	
	$("#addReviewForm").submit(function(event){
		
		event.preventDefault();
		ajaxPost();
	});
	
	function ajaxPost(){
		
		var formData={
			
			capabilityNo:$("#capabilityNo").val(),
			status:$("#status").val(),
			takenFor:{
				id:$("#mindId").val()
			},
			takenBy:{
				id:$("#leadId").val()
			}
		};
		console.log("formdata before post : "+formData);
		
		$.ajax({
			
			type:"POST",
			contentType:"application/json",
			url:"review/addReview",
			data:JSON.stringify(formData),
			datatype:'json',
			success:function(review){
				$("#postReviewDiv").html("<p style='background-color:#7FA7B0; color:white; padding:10px 10px 10px 10px'>"+
				"post successfully!<br>"+
				
				"-->{id"+review.id+
				",capabilityNo:"+review.capabilityNo+
				",status: "+review.status+
				",<br>mind:{<br> id:"+review.takenFor.id+
				",name: "+review.takenFor.name+"}<br>"+
				",lead:{<br> id :"+review.takenBy.id +"<br>"+
				",name: "+review.takenBy.name+"}}</p>");
				console.log(review);
			},
			error:function(e){
				alert("Error!!!");
				console.log("Error"+e);
			}
			
		});
		resetData();
	}
	function resetData()
	{
		$("#capabilityNo").val("");
		$("#status").val("");
		$("#mindId").val("");
		$("#leadId").val("");
	}
});