$(document).ready(function(){
	
	$(Document).on("click","#get",function(){
		
		$.ajax({
			
			type:"GET",
			url:"lead/get",
			success:function(result){
				$.each(result, function(index,lead){
				
					//var mindData=lead.minds.serializeArray();
					var mindData="";
					$.each(lead.minds, function(index,mind){
					
						mindData =mindData+mind.name.toUpperCase()+" ";
					});
					var leadRow = '<tr>' +
										'<td>' + lead.id + '</td>' +
										'<td>' + lead.name.toUpperCase() + '</td>' +
										'<td>' + lead.salary + '</td>' +
										'<td>' + mindData + '</td>' +
										'<td>' + lead.track.name.toUpperCase() + '</td>' +
								        '<td class="text-center">' +
								        	'<input type="hidden" value=' + lead.id + '>' +
								        	'<a class="deletelink" style="cursor:pointer">' +
						          				'<span >Delete</span>' +
						        			'</a>' +
								        '</td>' +
									  '</tr>';
					
					$('#leadTable tbody').append(leadRow);
				});
				$( "#leadTable tbody tr:odd" ).addClass("info");
				$( "#leadTable tbody tr:even" ).addClass("success");
			},
			error:function(e){
				alert("Error!!!");
				consol.log("Error!!"+e);
			}
			
		});
	});
	
	//to delete a particular lead
	
	$(document).on("click","a.deletelink",function() {
		
		var leadId = $(this).parent().find('input').val();
		var workingObject = $(this);
		
		$.ajax({
			type : "DELETE",
			url : "lead/delete/" + leadId,
			success: function(resultMsg){
				$("#resultMsgDiv").html("<p style='background-color:#67597E; color:white; padding:20px 20px 20px 20px'>" +
											"Lead with Id=" + leadId + " is deleted successfully!"  +
										"</p>");
				
				workingObject.closest("tr").remove();
				
				// re-css for table
				$( "#leadTable tbody tr:odd" ).addClass("info");
				$( "#leadTable tbody tr:even" ).addClass("success");
			},
			error : function(e) {
				alert("ERROR: ", e);
				console.log("ERROR: ", e);
			}
		});
	});
});