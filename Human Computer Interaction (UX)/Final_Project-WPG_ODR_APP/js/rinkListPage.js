$(document).ready(function () {
	var rinkListHolder = $('#mainList');
	var region = window.location.hash.substring(1);

	switch (region)
	{
		case '0':
			$('#areaTitle').html('Kildonan - St.Paul');		
			break;
		case '1':
			$('#areaTitle').html('Winnipeg North');
			break;
		case '2':
			$('#areaTitle').html('Winnipeg Center');
			break;
		case '3':
			$('#areaTitle').html('Winnipeg South');
			break;
		case '4':
			$('#areaTitle').html('Elmwood - Transona');
			break;
		case '5':
			$('#areaTitle').html('St. Boniface');
			break;
		case '6':
			$('#areaTitle').html('Charleswood - St. James');
			break;
		case '7':
			$('#areaTitle').html('Winnipeg South-Center');
		break;
		default:
			alert('Invalid region!');
			location.href='home.html';
	}

	for(i=0; i<rinkList[region].length; i++)
	{
		rinkListHolder.append('<div class="list-item" onclick="location.href=\'rink.html#' + rinkList[region][i][0] + '\'";> <div class="item-info"> <h4>' + rinkList[region][i][1] + '</h4><p>' + rinkList[region][i][2] + '</p></div><div class="item-status" id="' + rinkList[region][i][3] +'"> <h3>' + rinkList[region][i][4] +'</h3></div></div>');
	}
});