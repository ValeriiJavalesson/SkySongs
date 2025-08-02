function goHome() {
	window.location.href = "allsongs";
}

function toggleMenuList() {
	$('#navbarSupportedContent').toggle();
}

function toggleQR() {
	const opacityValue = $('#qrcode').css('opacity');
	console.log(opacityValue);
	switch (opacityValue) {
		case '0':
			$('#qrcode').css({ 'opacity': '1' , 'width': '80%'});
			$('#navbarSupportedContent').hide();
			break;
		case '1':
			$('#qrcode').css({ 'opacity': '0' , 'width': '0'});
			break;
	}

}