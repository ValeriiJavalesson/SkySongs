function goToSong(id) {
	window.location.href = "song?id=" + id;
}

function editSong(id){
	window.location.href = "addsong?id=" + id;
}

function deleteSong(song_id){
	var id = { id: song_id };
	$.ajax({
		url: 'delete_song',
		type: 'DELETE',
		data: id,
		complete: function(data) {
			alert(data.responseText);
			window.location = "allsongs";
		}
	});
}