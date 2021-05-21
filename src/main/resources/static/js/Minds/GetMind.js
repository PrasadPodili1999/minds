$(document).ready(function(){
	
	$(Document).on("click","#get",function(){
		
		$.ajax({
			
			type:"GET",
			url:"mind/get",
			success:function(result){
				$.each(result, function(index,mind){
				
					var mindRow = '<tr>' +
										'<td>' + mind.id + '</td>' +
										'<td>' + mind.name.toUpperCase() + '</td>' +
										'<td>' + mind.salary + '</td>' +
										'<td>' + mind.track.name.toUpperCase() + '</td>' +
								        '<td class="text-center">' +
								        	'<input type="hidden" value=' + mind.id + '>' +
								        	'<a class="deletelink" style="cursor:pointer">' +
						          				'<span >Delete</span>' +
						        			'</a>' +
								        '</td>' +
									  '</tr>';
					
					$('#mindTable tbody').append(mindRow);
				});
				$( "#mindTable tbody tr:odd" ).addClass("info");
				$( "#mindTable tbody tr:even" ).addClass("success");
			},
			error:function(e){
				alert("Error!!!");
				consol.log("Error!!"+e);
			}
			
		});
	});
	
	//to delete a particular mind
	
	$(document).on("click","a.deletelink",function() {
		
		var mindId = $(this).parent().find('input').val();
		var workingObject = $(this);
		
		$.ajax({
			type : "DELETE",
			url : "mind/delete/" + mindId,
			success: function(resultMsg){
				$("#resultMsgDiv").html("<p style='background-color:#67597E; color:white; padding:20px 20px 20px 20px'>" +
											"Mind with Id=" + mindId + " is deleted successfully!"  +
										"</p>");
				
				workingObject.closest("tr").remove();
				
				// re-css for table
				$( "#mindTable tbody tr:odd" ).addClass("info");
				$( "#mindTable tbody tr:even" ).addClass("success");
			},
			error : function(e) {
				alert("ERROR: ", e);
				console.log("ERROR: ", e);
			}
		});
	});
});