

	window.onload = function(){
		var i;
		var pianoArea = document.getElementById('pianoArea');
	
	
	//指定发出的声音
	function playSound(noteNumber){
		var soundId = 'sound' + noteNumber;
		var keyId = 'key' + noteNumber;
		var key = document.getElementById(keyId);
		var audio = null;
		var audio = new Audio();
			audio.src = 'music/'+noteNumber+'.mp3'  ;
			audio.play();
		
	}

	
	
	//按下键盘时
	document.onkeydown = function(e) {
		var pressEvent = e || window.event;
		var keyCode = '';
		if(pressEvent.keyCode){
			keyCode = pressEvent.keyCode;
		}else if(pressEvent.charCode){
			keyCode = pressEvent.charCode;
		}else if(pressEvent.which){
			keyCode = pressEvent.which;
		}
		console.log(keyCode);           
		playSound(keyCode);
	}
}