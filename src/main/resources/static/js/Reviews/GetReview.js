$(document).ready(function(){
	
	$(Document).on("click","#get",function(){
		
		$.ajax({
			
			type:"GET",
			url:"review/get",
			success:function(result){
				$.each(result, function(index,review){
				
					var reviewRow = '<tr>' +
										'<td>' + review.id + '</td>' +
										'<td>' + review.capabilityNo + '</td>' +
										'<td>' + review.status + '</td>' +
										'<td>' + review.takenFor.name.toUpperCase() + '</td>' +
										'<td>' + review.takenBy.name.toUpperCase() + '</td>' +
								        '<td class="text-center">' +
								        	'<input type="hidden" value=' + review.id + '>' +
								        	'<a class="deletelink" style="cursor:pointer">' +
						          				'<span >Delete</span>' +
						        			'</a>' +
								        '</td>' +
									  '</tr>';
					
					$('#reviewTable tbody').append(reviewRow);
				});
				$( "#reviewTable tbody tr:odd" ).addClass("info");
				$( "#reviewTable tbody tr:even" ).addClass("success");
			},
			error:function(e){
				alert("Error!!!");
				consol.log("Error!!"+e);
			}
			
		});
	});
	
	//to delete a particular review
	
	$(document).on("click","a.deletelink",function() {
		
		var reviewId = $(this).parent().find('input').val();
		var workingObject = $(this);
		
		$.ajax({
			type : "DELETE",
			url : "review/delete/" + reviewId,
			success: function(resultMsg){
				$("#resultMsgDiv").html("<p style='background-color:#67597E; color:white; padding:20px 20px 20px 20px'>" +
											"Review with Id=" + reviewId + " is deleted successfully!"  +
										"</p>");
				
				workingObject.closest("tr").remove();
				
				// re-css for table
				$( "#reviewTable tbody tr:odd" ).addClass("info");
				$( "#reviewTable tbody tr:even" ).addClass("success");
			},
			error : function(e) {
				alert("ERROR: ", e);
				console.log("ERROR: ", e);
			}
		});
	});
});