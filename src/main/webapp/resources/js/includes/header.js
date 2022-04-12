$(document).ready(function(){
	let keyword = '';
	let searchVal = '';
	
	
	$('#anFeed').click(function(e){
		e.preventDefault();
		$('#loginModalBtn').trigger('click');
	});
	
	$('.keyword').click(function () {

		$('.keywordView').text($(this).attr('value'));
		keyword = $(this).attr('value');
		
	});
	
	$('.search').click(function () {
		
		searchVal = $('.searchVal').val();


		if(keyword==''){
			alert('검색하실 키워드를 선택해주세요 ! ')
			
		}else if(searchVal==''){
			alert('검색어를 입려해주세요 ! ')

		}else{
			$(this).attr('href','post/search?keyword='+keyword+'&searchVal='+searchVal);
			
	
		}
	});
	
	
});