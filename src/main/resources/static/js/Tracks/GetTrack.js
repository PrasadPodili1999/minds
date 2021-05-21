$(Document).ready(function(){
	
	$(Document).on("click","#get",function(){
		
		
		$.ajax({
			
			type:"GET",
			url : "track/get",
			success: function(result){
				$.each(result, function(index,track){
					
					var trackRow = '<tr>' +
										'<td>' + track.id + '</td>' +
										'<td>' + track.name.toUpperCase() + '</td>' +
										'<td>' + track.basedOn.toUpperCase() + '</td>' +
								        '<td class="text-center">' +
								        	'<input type="hidden" value=' + track.id + '>' +
								        	'<a class="deletelink" style="cursor:pointer">' +
						          				'<span >Delete</span>' +
						        			'</a>' +
								        '</td>' +
									  '</tr>';
					
					$('#trackTable tbody').append(trackRow);
		        });
				
				$( "#trackTable tbody tr:odd" ).addClass("info");
				$( "#trackTable tbody tr:even" ).addClass("success");
			},
			error : function(e) {
				alert("ERROR: ", e);
				console.log("ERROR: ", e);
			}
		});	
		
	});
	
	// Do DELETE a Track via JQUERY AJAX
	$(document).on("click","a.deletelink",function() {
		
		var trackId = $(this).parent().find('input').val();
		var workingObject = $(this);
		
		$.ajax({
			type : "DELETE",
			url : "track/delete/" + trackId,
			success: function(resultMsg){
				$("#resultMsgDiv").html("<p style='background-color:#67597E; color:white; padding:20px 20px 20px 20px'>" +
											"Track with Id=" + trackId + " is deleted successfully!"  +
										"</p>");
				
				workingObject.closest("tr").remove();
				
				// re-css for table
				$( "#trackTable tbody tr:odd" ).addClass("info");
				$( "#trackTable tbody tr:even" ).addClass("success");
			},
			error : function(e) {
				alert("ERROR: ", e);
				console.log("ERROR: ", e);
			}
		});
	});
});