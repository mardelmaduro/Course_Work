$(document).ready(function () {
	$('#commentButton').click(function () {$('#commentWindow').show()});
	$('#rateButton').click(function () {$('#ratingWindow').show()});
	var rinkNameHolder = $('#rinkName');
	var rinkAdressHolder = $('#rinkAdress');
	var rinkIceQuality = $('#iceQuality');
	var rinkRating = $('#rinkRating');
	var rinkAmenities = $('#rinkAmenities');
	var rinkCommentSection = $('#commentBox');
	var rinkId = window.location.hash.substring(1);	

	rinkNameHolder.html(rinkDetails[rinkId][2]);
	rinkAdressHolder.html(rinkDetails[rinkId][3]);

	for(i=0; i<rinkDetails[rinkId][4]; i++)
	{
		rinkIceQuality.append('<img id="ice-logo" src="img/HCI_Icons/iceRating.png" alt="Ice Quality" title="Ice Quality">');
	}

	for(i=0; i<rinkDetails[rinkId][5]; i++)
	{
		rinkRating.append('<img id="star-rating" src="img/HCI_Icons/starRating.png" alt="Positive Rating" title="Rating">');
	}

	for(i=0; i<rinkDetails[rinkId][6].length; i++)
	{
		rinkAmenities.append('<img src="' + rinkDetails[rinkId][6][i][0] + '" alt="' + rinkDetails[rinkId][6][i][1] + '" title="' + rinkDetails[rinkId][6][i][2] + '">');
	}

	for(i=0; i<rinkComments[rinkId].length; i++)
	{
		rinkCommentSection.append('<label id="user-name">' + rinkComments[rinkId][i][0] + '</label><br><q id="user-message">' + rinkComments[rinkId][i][1] + '</q><br><br>');
	}

	if(rinkDetails[rinkId][1] == 'CLOSED')
	{
		$('#rinkStatusField').html('<div class="item-status" id="closed"> <h3>CLOSED</h3></div>');
	}
	else
	{
		$('#rinkStatusField').html('<div class="item-status" id="open"> <h3>OPEN</h3></div>');
	}

	$('#makeCommentButton').click( function () {
		$('#commentBox').append('<label id="user-name">' + $('#commenterName').val() + '</label><br><q id="user-message">' + $('#commenterMessage').val() + '</q>');
		$('#commentWindow').hide();
		$('#commenterName').val('');
		$('#commenterMessage').val('');
	});


	var oneStar = $('#one-star');
	var twoStar = $('#two-star');
	var threeStar = $('#three-star');
	var fourStar = $('#four-star');
	var fiveStar = $('#five-star');

	var rate1 = $('#1-star-text');
	var rate2 = $('#2-star-text');
	var rate3 = $('#3-star-text');
	var rate4 = $('#4-star-text');
	var rate5 = $('#5-star-text');

	var oneStarSelected = false;
	var twoStarSelected = false;
	var threeStarSelected = false;
	var fourStarSelected = false;
	var fiveStarSelected = false;
	var rinkStarRating = 0;

	oneStar.hover( 
		function() {
			oneStar.css('opacity','1');
			rate1.css('opacity','1');
		},
		function() {
			if (!oneStarSelected) {
				oneStar.css('opacity','0.4');
				rate1.css('opacity','0.4');
			}
		}
	);
	twoStar.hover( 
		function() {
			oneStar.css('opacity','1');
			twoStar.css('opacity','1');
			rate2.css('opacity','1');
		},
		function() {
			if (!twoStarSelected) {
				oneStar.css('opacity','0.4');
				twoStar.css('opacity','0.4');
				rate2.css('opacity','0.4');
			}
		}
	);
	threeStar.hover( 
		function() {
			oneStar.css('opacity','1');
			twoStar.css('opacity','1');
			threeStar.css('opacity','1');
			rate3.css('opacity','1');
		},
		function() {
			if (!threeStarSelected) {
				oneStar.css('opacity','0.4');
				twoStar.css('opacity','0.4');
				threeStar.css('opacity','0.4');
				rate3.css('opacity','0.4');
			}
		}
	);
	fourStar.hover( 
		function() {
			oneStar.css('opacity','1');
			twoStar.css('opacity','1');
			threeStar.css('opacity','1');
			fourStar.css('opacity','1');
			rate4.css('opacity','1');
		},
		function() {
			if (!fourStarSelected) {
				oneStar.css('opacity','0.4');
				twoStar.css('opacity','0.4');
				threeStar.css('opacity','0.4');
				fourStar.css('opacity','0.4');
				rate4.css('opacity','0.4');
			}
		}
	);
	fiveStar.hover( 
		function() {
			oneStar.css('opacity','1');
			twoStar.css('opacity','1');
			threeStar.css('opacity','1');
			fourStar.css('opacity','1');
			fiveStar.css('opacity','1');
			rate5.css('opacity','1');
		},
		function() {
			if (!fiveStarSelected) {
				oneStar.css('opacity','0.4');
				twoStar.css('opacity','0.4');
				threeStar.css('opacity','0.4');
				fourStar.css('opacity','0.4');
				fiveStar.css('opacity','0.4');
				rate5.css('opacity','0.4');
			}
		}
	);
	oneStar.click( function() {
		rinkStarRating = 1;
		oneStarSelected = true;
		twoStarSelected = false;
		threeStarSelected = false;
		fourStarSelected = false;
		fiveStarSelected = false;
		oneStar.css('opacity','1');
		rate1.css('opacity','1');

		twoStar.css('opacity','0.4');
		threeStar.css('opacity','0.4');
		fourStar.css('opacity','0.4');
		fiveStar.css('opacity','0.4');
		rate2.css('opacity','0.4');
		rate3.css('opacity','0.4');
		rate4.css('opacity','0.4');
		rate5.css('opacity','0.4');
	});
	twoStar.click( function() {
		rinkStarRating = 2;
		oneStarSelected = false;
		twoStarSelected = true;
		threeStarSelected = false;
		fourStarSelected = false;
		fiveStarSelected = false;
		twoStar.css('opacity','1');
		oneStar.css('opacity','1');
		rate2.css('opacity','1');

		threeStar.css('opacity','0.4');
		fourStar.css('opacity','0.4');
		fiveStar.css('opacity','0.4');
		rate1.css('opacity','0.4');
		rate3.css('opacity','0.4');
		rate4.css('opacity','0.4');
		rate5.css('opacity','0.4');
	});
	threeStar.click( function() {
		rinkStarRating = 3;
		oneStarSelected = false;
		twoStarSelected = false;
		threeStarSelected = true;
		fourStarSelected = false;
		fiveStarSelected = false;
		twoStar.css('opacity','1');
		oneStar.css('opacity','1');
		threeStar.css('opacity','1');
		rate3.css('opacity','1');
		
		fourStar.css('opacity','0.4');
		fiveStar.css('opacity','0.4');
		rate1.css('opacity','0.4');
		rate2.css('opacity','0.4');
		rate4.css('opacity','0.4');
		rate5.css('opacity','0.4');
	});
	fourStar.click( function() {
		rinkStarRating = 4;
		oneStarSelected = false;
		twoStarSelected = false;
		threeStarSelected = false;
		fourStarSelected = true;
		fiveStarSelected = false;
		twoStar.css('opacity','1');
		oneStar.css('opacity','1');
		threeStar.css('opacity','1');
		fourStar.css('opacity','1');
		rate4.css('opacity','1');

		fiveStar.css('opacity','0.4');
		rate1.css('opacity','0.4');
		rate2.css('opacity','0.4');
		rate3.css('opacity','0.4');
		rate5.css('opacity','0.4');
	});
	fiveStar.click( function() {
		rinkStarRating = 5;
		oneStarSelected = false;
		twoStarSelected = false;
		threeStarSelected = false;
		fourStarSelected = false;
		fiveStarSelected = true;
		oneStar.css('opacity','1');
		twoStar.css('opacity','1');
		threeStar.css('opacity','1');
		fourStar.css('opacity','1');
		fiveStar.css('opacity','1');
		rate1.css('opacity','0.4');
		rate2.css('opacity','0.4');
		rate3.css('opacity','0.4');
		rate4.css('opacity','0.4');
		rate5.css('opacity','1');
	});



	var oneIce = $('#one-ice');
	var twoIce = $('#two-ice');
	var threeIce = $('#three-ice');
	var fourIce = $('#four-ice');
	var fiveIce = $('#five-ice');

	var iceRate1 = $('#1-ice-text');
	var iceRate2 = $('#2-ice-text');
	var iceRate3 = $('#3-ice-text');
	var iceRate4 = $('#4-ice-text');
	var iceRate5 = $('#5-ice-text');

	var oneIceSelected = false;
	var twoIceSelected = false;
	var threeIceSelected = false;
	var fourIceSelected = false;
	var fiveIceSelected = false;
	var iceRating = 0;

	oneIce.hover( 
		function() {
			oneIce.css('opacity','1');
			iceRate1.css('opacity','1');
		},
		function() {
			if (!oneIceSelected) {
				oneIce.css('opacity','0.4');
				iceRate1.css('opacity','0.4');
			}
		}
	);
	twoIce.hover( 
		function() {
			oneIce.css('opacity','1');
			twoIce.css('opacity','1');
			iceRate2.css('opacity','1');
		},
		function() {
			if (!twoIceSelected) {
				oneIce.css('opacity','0.4');
				twoIce.css('opacity','0.4');
				iceRate2.css('opacity','0.4');
			}
		}
	);
	threeIce.hover( 
		function() {
			oneIce.css('opacity','1');
			twoIce.css('opacity','1');
			threeIce.css('opacity','1');
			iceRate3.css('opacity','1');
		},
		function() {
			if (!threeIceSelected) {
				oneIce.css('opacity','0.4');
				twoIce.css('opacity','0.4');
				threeIce.css('opacity','0.4');
				iceRate3.css('opacity','0.4');
			}
		}
	);
	fourIce.hover( 
		function() {
			oneIce.css('opacity','1');
			twoIce.css('opacity','1');
			threeIce.css('opacity','1');
			fourIce.css('opacity','1');
			iceRate4.css('opacity','1');
		},
		function() {
			if (!fourIceSelected) {
				oneIce.css('opacity','0.4');
				twoIce.css('opacity','0.4');
				threeIce.css('opacity','0.4');
				fourIce.css('opacity','0.4');
				iceRate4.css('opacity','0.4');
			}
		}
	);
	fiveIce.hover( 
		function() {
			oneIce.css('opacity','1');
			twoIce.css('opacity','1');
			threeIce.css('opacity','1');
			fourIce.css('opacity','1');
			fiveIce.css('opacity','1');
			iceRate5.css('opacity','1');
		},
		function() {
			if (!fiveIceSelected) {
				oneIce.css('opacity','0.4');
				twoIce.css('opacity','0.4');
				threeIce.css('opacity','0.4');
				fourIce.css('opacity','0.4');
				fiveIce.css('opacity','0.4');
				iceRate5.css('opacity','0.4');
			}
		}
	);
	oneIce.click( function() {
		iceRating = 1;
		oneIceSelected = true;
		twoIceSelected = false;
		threeIceSelected = false;
		fourIceSelected = false;
		fiveIceSelected = false;
		oneIce.css('opacity','1');
		iceRate1.css('opacity','1');

		twoIce.css('opacity','0.4');
		threeIce.css('opacity','0.4');
		fourIce.css('opacity','0.4');
		fiveIce.css('opacity','0.4');
		iceRate2.css('opacity','0.4');
		iceRate3.css('opacity','0.4');
		iceRate4.css('opacity','0.4');
		iceRate5.css('opacity','0.4');
	});
	twoIce.click( function() {
		iceRating = 2;
		oneIceSelected = false;
		twoIceSelected = true;
		threeIceSelected = false;
		fourIceSelected = false;
		fiveIceSelected = false;
		twoIce.css('opacity','1');
		oneIce.css('opacity','1');
		iceRate2.css('opacity','1');

		threeIce.css('opacity','0.4');
		fourIce.css('opacity','0.4');
		fiveIce.css('opacity','0.4');
		iceRate1.css('opacity','0.4');
		iceRate3.css('opacity','0.4');
		iceRate4.css('opacity','0.4');
		iceRate5.css('opacity','0.4');
	});
	threeIce.click( function() {
		iceRating = 3;
		oneIceSelected = false;
		twoIceSelected = false;
		threeIceSelected = true;
		fourIceSelected = false;
		fiveIceSelected = false;
		twoIce.css('opacity','1');
		oneIce.css('opacity','1');
		threeIce.css('opacity','1');
		iceRate3.css('opacity','1');
		
		fourIce.css('opacity','0.4');
		fiveIce.css('opacity','0.4');
		iceRate1.css('opacity','0.4');
		iceRate2.css('opacity','0.4');
		iceRate4.css('opacity','0.4');
		iceRate5.css('opacity','0.4');
	});
	fourIce.click( function() {
		iceRating = 4;
		oneIceSelected = false;
		twoIceSelected = false;
		threeIceSelected = false;
		fourIceSelected = true;
		fiveIceSelected = false;
		twoIce.css('opacity','1');
		oneIce.css('opacity','1');
		threeIce.css('opacity','1');
		fourIce.css('opacity','1');
		iceRate4.css('opacity','1');

		fiveIce.css('opacity','0.4');
		iceRate1.css('opacity','0.4');
		iceRate2.css('opacity','0.4');
		iceRate3.css('opacity','0.4');
		iceRate5.css('opacity','0.4');
	});
	fiveIce.click( function() {
		iceRating = 5;
		oneIceSelected = false;
		twoIceSelected = false;
		threeIceSelected = false;
		fourIceSelected = false;
		fiveIceSelected = true;
		oneIce.css('opacity','1');
		twoIce.css('opacity','1');
		threeIce.css('opacity','1');
		fourIce.css('opacity','1');
		fiveIce.css('opacity','1');
		iceRate1.css('opacity','0.4');
		iceRate2.css('opacity','0.4');
		iceRate3.css('opacity','0.4');
		iceRate4.css('opacity','0.4');
		iceRate5.css('opacity','1');
	});


	$('#submitRating').click( function() {
		rinkDetails[rinkId][4] = rinkStarRating
		rinkDetails[rinkId][3] = iceRating;
		rinkIceQuality.html('<h2>Ice Quality:</h2>');
		rinkRating.html(''); 	

		for(i=0; i<rinkDetails[rinkId][3]; i++)
		{
			rinkIceQuality.append('<img id="ice-logo" src="img/HCI_Icons/iceRating.png" alt="Ice Quality" title="Ice Quality">');
		}

		for(i=0; i<rinkDetails[rinkId][4]; i++)
		{
			rinkRating.append('<img id="star-rating" src="img/HCI_Icons/starRating.png" alt="Positive Rating" title="Rating">');
		}

		$('#ratingWindow').hide();
	});
});

	