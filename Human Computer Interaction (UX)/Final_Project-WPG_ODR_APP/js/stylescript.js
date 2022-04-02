$(document).ready(function(){
	$('#tab1').click(function() {
		$("#tab-content1").show();
		$("#tab-content2").hide();	
	});
	$('#tab2').click(function() {
		$("#tab-content2").show();
		$("#tab-content1").hide();
	});
	$('.close-box').click(function() {
		$('.show-make-comment-box').hide();
		$('.show-make-rating').hide();
	});
	$('#tab-comments').click(function() {
		$("#feed-section").hide();
		$('#comment-section').show();
	});
	$('#tab-feed').click(function() {
		$('#comment-section').hide();
		$("#feed-section").show();
	});
	
})
