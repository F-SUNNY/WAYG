$(document).ready(function () {
	$('.content').keyup(function () {
		
		let tmp = $(this).val().length;

		
		if (tmp != 0 || tmp != '') {
			 $('.textCount').text(tmp + '/300자');
		}  
		if (tmp >= 299) {
			tmp.substring(0, 299);
		};
		console.log(tmp);
	});
	
	
	$('.hashtag').keyup(function () {
		let tmp = $(this).val();
		let count = tmp.split('#').length-1;
		let hashtag = tmp.split('#');
		let result = '';
		if(count>=11){
			for(let i=0; i<11; i++){
				if(i==0){
					result=hashtag[0];
				}else{
					
				result +='#'+hashtag[i];
				}
			}
		}else{
			for(let i=0; i<hashtag.length; i++){
				if(i==0){
					result=hashtag[0];
				}else{
				result +='#'+hashtag[i];									
				}
			}
		}	
		
		$(this).attr('value',result);	
	});



	$('.img').change(function(){
		let arr = $('.img')[0].files;
		for(var i=0; i<arr.length; i++){
			if(arr[i].size>5242880){
				alert(arr[i].name+'의 용량이 5MB를 초과합니다.다시 업로드해주세요.');
				$('.img').reset();
			}
			
		}
	});

	
	
	
	
});

function checkfrm() { 
	if($('.location').val()==""){
		alert('장소는 필수입력입니다 ㅜㅜ');
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
