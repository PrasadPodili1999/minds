$(Document).ready(function() {

	var i=0;
	$("#add").click(function() {

		$("#addLead").slideToggle("slow");
	});

	$("#addNewMind").click(function(event){
	
		event.preventDefault();
		$("#fig").append("<div>MindId:<input type='text' class='form-control' id='id"+i+"'></div>");
		i++;
	})
	
	
	//add new rows dynamically
	/* $("#addRow").click(function(){
		var addRow = '<div id="inputFormRow">'
			+ '<div class="input-group mb-3">'
			+ '<input type="number" name="title[]" class="form-control " id="mindId"'
			+ '	placeholder="Enter MindId" >'
			+ '<div class="input-group-append">'
			+ '<button id="removeRow" type="button" class="btn btn-danger">Remove</button></div></div></div>';

		$('#newRow').append(addRow);
	});

	// remove row
	$(document).on("click", "#removeRow", function() {
		$(this).closest("#inputFormRow").remove();
	});*/

	$("#submit").click(function(){

		event.preventDefault();
		ajaxPost();
	});
	function ajaxPost() {

		var mind=[];
		for(var j=0;j<i;j++)
		{
			var minds={};
			id="id"+j;
			console.log(id);
			minds.id=document.getElementById(id).value;
			mind.push(minds);
		}
		var formData={
		
			name:$("#name").val(),
			salary:$("#salary").val(),
			minds:mind,
			track:{
				id:$("#trackId").val()
				}
		}
		
		console.log(formData);

		$.ajax({

			type: "POST",
			contentType: "application/json",
			url : "lead/addLead",
			data: JSON.stringify(formData),
			dataType: 'json',
			success: function(result) {
				$("#postLeadDiv").html("<p style='background-color:#7FA7B0; color:white; padding:20px 20px 20px 20px'>" +
					"post successfully!<br>" +
					"-->{name:" + result.name +
					",salary: " + result.salary +
					", " + "}</p>");

				console.log(formData);
			},
			error: function(e) {
				alert("Error!!");
				console.log("Error :" + e);
			}
		});
		// Reset FormData after Posting
		resetData();
	}
	function resetData() {
		$("#name").val("");
		$("#salary").val("");
		
		$("#trackId").val("")
	}

});