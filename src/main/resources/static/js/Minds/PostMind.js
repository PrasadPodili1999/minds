$(document).ready(function(){
	
	$("#add").click(function(){
		
		$("#addMind").slideToggle("slow");
	});
	
	$("#addMindForm").submit(function(event){
		
		event.preventDefault();
		ajaxPost();
	});
	
	function ajaxPost(){
		
		var formData={
			
			name:$("#name").val(),
			salary:$("#salary").val(),
			track:{
				id:$("#id").val()
			}
		};
		console.log("formdata before post : "+formData);
		
		$.ajax({
			
			type:"POST",
			contentType:"application/json",
			url:"mind/addMind",
			data:JSON.stringify(formData),
			datatype:'json',
			success:function(mind){
				$("#postMindDiv").html("<p style='background-color:#7FA7B0; color:white; padding:10px 10px 10px 10px'>"+
				"post successfully!<br>"+
				
				"-->{id"+mind.id+
				",name:"+mind.name+
				",salary: "+mind.salary+
				"track:{<br> id:"+mind.track.id+
				",name: "+mind.track.name+"}<br>}</p>");
				console.log(mind);
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
		$("#name").val("");
		$("#salary").val("");
		$("#id").val("");
	}
});