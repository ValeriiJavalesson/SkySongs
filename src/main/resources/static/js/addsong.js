function saveSong(){
	let id = $('input[name="newSongId"]').val();
	let number = $('input[name="number"]').val();
	let title = $('input[name="title"]').val();
	/*let text = $('textarea[name="text"]').val();*/
	let text = $('#fake_textarea').html(); 
	console.log(text);
	
	
	let song = {
		id: id,
		number: number,
		title: title,
		text: text
	}

	$.ajax({
		type: "POST",
		url: 'savesong',
		data: JSON.stringify(song),
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		complete: function(data) {
			window.location = data.responseText;
		}
	});
}