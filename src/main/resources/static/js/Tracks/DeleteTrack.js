$(Document).ready(function(){
	
	$("#delete").on("click",function(){
		
		$.ajax({
			
			type:"DELETE",
			url:"track/delete",
			success:function(){
				$("#deletemsg").html("<p style='background-color:#67597E; color:white; padding:20px 20px 20px 20px'>"+
				"deleted succesfully!!!   </p>");
			},
			error:function(e){
				alert("Error: ",e);
				console.log("Error: ",e);
			}
		});
	});
});