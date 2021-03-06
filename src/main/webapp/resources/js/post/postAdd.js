$(document).ready(function () {
	$('.content').keyup(function () {
		
		let tmp = $(this).val().length;
		
		if (tmp != 0 || tmp != '') {
			 $('.textCount').text(tmp + '/300자');
		}  
		if (tmp > 299) {
			tmp.substring(0, 299);
		};
		console.log(tmp);
	});
	
	
	$('.hashtag').keyup(function () {
		let element = $(this);
		let count = element.val().split('#').length-1;
		let hashtag = element.val().split('#');
		let result = '';
		let hashCheck =element.val().substr(0,1);

		if(hashCheck !=='#'){
			alert('해쉬태그는 #을 붙여주세요! ');
			element.val('#');
		}
		
		if(count>=11){
			for(let i=0; i<11; i++){
				if(i==0){
					result=hashtag[0];
				}else{
					
				result +='#'+hashtag[i];
				}
			}
			alert('해쉬태그는 10개까지만 등록가능합니다\n'+result);
			element.val(result); 
		}else{
			for(let i=0; i<hashtag.length; i++){
				element.val(element.val().replaceAll(/##/gi, "#")); 
		
				if(hashtag[i]!='' && hashtag[i].replaceAll(' ','')==''){
					element.val(result+'#'); 	
				}
				
				if(i==0){
					result=hashtag[0];
				
				}else{
					result +='#'+hashtag[i];									
				}
			
			}
		}	
			console.log(result);
		
		$(this).attr('value',result);
	});
	
	$(document).on('click','.reimg',function(){
		
		const dataTransfer = new DataTransfer(); 
		let changeData ="";
		let arr = $('.img')[0].files;
		let index = $(this).attr('index');
			
		let fileArray = Array.from(arr); //변수에 할당된 파일을 배열로 변환(FileList -> Array) 
		fileArray.splice(index, 1); //해당하는 index의 파일을 배열에서 제거 
		fileArray.forEach(file => { dataTransfer.items.add(file); }); //남은 배열을 dataTransfer로 처리(Array -> FileList) 
		$('.img')[0].files = dataTransfer.files; //제거 처리된 FileList를 돌려줌
			
		changeData = $('.img')[0].files;
		imgView='';
		for(var i=0; i<changeData.length; i++){
			imgView +='<img src="'+URL.createObjectURL(changeData[i])+'" style="width :23%">';
			imgView +='<i class="fa-solid fa-x reimg" index="'+i+'"></i>';
			imgView +='<br/>';
		}
			imgView +='<i class="fa-brands fa-instagram addImgBtn" style="color : red; font-size: 220px;"></i>';
			
			//동일 이미지 등록 가능 (추가)
			$('.addImg').val('');
			
		$('.imgView').html(imgView);			
	});
	
	$('.addImg').change(function(){ //파일 추가
		const dataTransfer = new DataTransfer(); 
		let arr = $('.img')[0].files;
		let arr2 =$('.addImg')[0].files;
		let extension =''; //확장자
		let totalSize = 0;
		
	
		
		for(var i=0; i<arr.length; i++){ //사진 용량 확인 1
			totalSize+=arr[i].size;
		}
		
		for(var i=0; i<arr2.length; i++){ //사진 용량 확인 1
			totalSize+=arr2[i].size;		
			
		}
		
		if(arr.length+arr2.length>10){
			alert('10장 이상 등록할수 없습니다.\n다시 선택해주세요');
			//파일값 초기화			
			
		}else if (totalSize>100000000-1){
			alert('이미지의 총 용량이 10MB를 초과합니다.\n 다른 이미지를 올려주세요');
			
		}else{
			let fileArray = Array.from(arr); //변수에 할당된 파일을 배열로 변환(FileList -> Array) 
			
			for(var i=0; i<arr2.length; i++){				
				extension = arr2[i].name.substring(arr2[i].name.lastIndexOf('.')+1).toLowerCase(); //확장자명 추출

				if(extension =='jpg' || extension =='jpeg' || extension =='png' || extension ==''){	//확장자 확인		
					fileArray.push(arr2[i]);				
				
				}else{
					alert(arr2[i].name+'은 지원하지 않은 확장자 파일입니다.');
				}
			}
			
			fileArray.forEach(file => { dataTransfer.items.add(file); }); //남은 배열을 dataTransfer로 처리(Array -> FileList) 
			$('.img')[0].files = dataTransfer.files; 
			arr = $('.img')[0].files;
			
		}
		

		let imgView='';
		
		for(var i=0; i<arr.length; i++){
			
			imgView +='<img src="'+URL.createObjectURL(arr[i])+'" style="width :23%">'
			imgView +='<i class="fa-solid fa-x reimg" index="'+i+'"></i>';
			imgView +='<br/>'
		}
		if(arr.length<10){
			imgView +='<i class="fa-brands fa-instagram addImgBtn" style="color : red; font-size: 220px;"></i>'			
		}
		$('.imgView').html(imgView);
		$('.addImg').val('');	
	});
	
	$(document).on('click','.addImgBtn',function(){
		$('input[name=addImg]').trigger('click');
	})
});

function checkfrm() { 
	if($('.img').val()==''){
		alert('1장 이상의 사진등록은 필수입니');
		return false;
	}else{
	$('#addForm').submit();
	}
}
    
var mapContainer = document.getElementById('map'), // 지도를 표시할 div
mapOption = {
    center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
    level: 5 // 지도의 확대 레벨
};

//지도를 미리 생성
var map = new daum.maps.Map(mapContainer, mapOption);

//주소-좌표 변환 객체를 생성
var geocoder = new daum.maps.services.Geocoder();
	
//마커를 미리 생성
var marker = new daum.maps.Marker({
    position: new daum.maps.LatLng(37.537187, 127.005476),
    map: map
});


function sample5_execDaumPostcode() {
	new daum.Postcode({
	    oncomplete: function(data) {
	        var addr = data.address; // 최종 주소 변수
	        $('.location').val(data.sido+" "+data.sigungu);
	        // 주소 정보를 해당 필드에 넣는다.
	        document.getElementById("sample5_address").value = addr;
	        // 주소로 상세 정보를 검색
	        geocoder.addressSearch(data.address, function(results, status) {
	            // 정상적으로 검색이 완료됐으면
	            if (status === daum.maps.services.Status.OK) {
	                var result = results[0]; //첫번째 결과의 값을 활용
	                // 해당 주소에 대한 좌표를 받아서
	                var coords = new daum.maps.LatLng(result.y, result.x);
	                // 지도를 보여준다.
	                mapContainer.style.display = "block";
	                map.relayout();
	                // 지도 중심을 변경한다.
	                map.setCenter(coords);
	                // 마커를 결과값으로 받은 위치로 옮긴다.
	                marker.setPosition(coords)
	            }
	        });
	    }
	}).open({
		left : 500,
		top : 300	
	});
}
