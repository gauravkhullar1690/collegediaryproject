/**
 * 
 */
$(document).ready(function() {
	$("#webcam").scriptcam({
		showMicrophoneErrors : false,
		onError : onError,
		cornerRadius : 20,
		cornerColor : 'e3e5e2',
		onWebcamReady : onWebcamReady,
		uploadImage : 'images/upload.gif',
		onPictureAsBase64 : base64_tofield_and_image,
		fileReady : savefile
	});
});
function savefile() {
	$.scriptcam.fileReady("upload.jpeg");
};
function base64_tofield() {
	$('#txtPic').val($.scriptcam.getFrameAsBase64());
	$('#image').attr("src",
			"data:image/png;base64," + $.scriptcam.getFrameAsBase64());
	$('#webcamUpload').submit();
};
function base64_toimage() {
	$('#image').attr("src","data:image/png;base64," + $.scriptcam.getFrameAsBase64());
};
function base64_tofield_and_image(b64) {
	$('#txtPic').val(b64);
	$('#image').attr("src", "data:image/png;base64," + b64);
};
function changeCamera() {
	$.scriptcam.changeCamera($('#cameraNames').val());
}
function onError(errorId, errorMsg) {
	$("#btnSnapshot").attr("disabled", true);
	$("#btn2").attr("disabled", true);
	alert(errorMsg);
}
function onWebcamReady(cameraNames, camera, microphoneNames, microphone, volume) {
	$.each(cameraNames, function(index, text) {
		$('#cameraNames').append($('<option></option>').val(index).html(text))
	});
	$('#cameraNames').val(camera);
}