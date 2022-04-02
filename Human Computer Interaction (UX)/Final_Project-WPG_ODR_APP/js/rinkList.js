var rinkList = [
	[ 			//Kildonan - St.Paul
		['0','Archwood','565 Guilbault St','open','OPEN'], 
		['1','Bronx Park','131 Chelsea Place','closed','CLOSED'] 
	],
	[ 			//Winnipeg North 
		['2','Garden City C.C.','725 Kingsbury Ave.','open','OPEN'] ,
		['3','Maples C.C.','434 Adsum Dr.','closed','CLOSED']
	],
	[			//Winnipeg Center
		['4','Fort Garry C.C.','880 Oakenwald St.','open','OPEN'], 
		['5','River Osborne C.C.','101 Pembina Hwy.','open','OPEN'] 
	],
	[  			//Winnipeg South
		['6','St. Norbert C.C.','3450 Pembina Hwy','closed','CLOSED'], 
		['7','Bison C.C.','356 Bison Ave.','open','OPEN'] 
	],
	[			//Elmwood - Transona
		['8','Sir John Franklin C.C.','1 Sir John Franklin Rd.','open','OPEN'] ,
		['9','South Transcona C.C.','124 Borden Ave.','open','OPEN']
	],
	[			//St. Boniface
		['10','Morgan Satellite Site','305 Morgan Cres','open','OPEN']  ,
		['11','Waverley Heights C.C.','1885 Chacellor Dr.','open','OPEN']  
	],
	[			//Charleswood - St. James
		['12','Bord-Aire','471 Hampton St.','open','OPEN'] ,
		['13','Assiniboine West','861 Buchanan Boulevard','closed','CLOSED'] 
	],
	[			//Winnipeg South-Center
		['14','Varsity View C.C.','315 Laxdal Rd.','open','OPEN'] ,
		['15','Lord Roberts C.C.','725 Kylemore Ave.','closed','CLOSED']
	]
];

/*
AMENITIES
	['img/HCI_Icons/changinRoom.png','Change Room','Rink includes a Change Room']
	['img/HCI_Icons/skateTrail.png','Skating Trail','Rink includes a Skating Trail']
	['img/HCI_Icons/warmingHut.png','Hut','Rink includes a Warming Hut']
	['img/HCI_Icons/washRoom.png','Washroom','Rink includes a Washroom']

AS HTML CODE
	<img id="change-room" src="img/HCI_Icons/changinRoom.png" alt="Change Room" title="Rink includes a Change Room">
	<img id="skate-trail" src="img/HCI_Icons/skateTrail.png" alt="Skating Trail" title="Rink includes a Skating Trail">
	<img id="warming-hut" src="img/HCI_Icons/warmingHut.png" alt="Hut" title="Rink includes a Warming Hut">
	<img id="wash-room" src="img/HCI_Icons/washRoom.png" alt="Washroom" title="Rink includes a Washroom">

Rink details... EXAMPLE (PLZ match the above ID to the blow ID to insure ou are matching the rinks)
[ ID# , NAME , ADRESS , ICEQUALITY/5 , RATING/5 , [AMENITIES [copy the code from above] , [as many as needed] ] ]

*/
var rinkDetails = [
	['0','OPEN','Archwood','565 Guilbault St','4','5',[['img/HCI_Icons/changinRoom.png','Change Room','Rink includes a Change Room'],['img/HCI_Icons/skateTrail.png','Skating Trail','Rink includes a Skating Trail']]], 
	['1','CLOSED','Bronx Park','131 Chelsea Place','5','2',[['img/HCI_Icons/warmingHut.png','Hut','Rink includes a Warming Hut'],['img/HCI_Icons/changinRoom.png','Change Room','Rink includes a Change Room']]],
	['2','OPEN','Garden City','725 Kingsbury Ave.','3','4',[['img/HCI_Icons/warmingHut.png','Hut','Rink includes a Warming Hut'],['img/HCI_Icons/changinRoom.png','Change Room','Rink includes a Change Room']]],
	['3','CLOSED','Maples','434 Adsum Dr.','3','2',[['img/HCI_Icons/changinRoom.png','Change Room','Rink includes a Change Room'],['img/HCI_Icons/skateTrail.png','Skating Trail','Rink includes a Skating Trail']]], 
	['4','OPEN','Fort Garry','880 Oakenwald St.','3','3',[['img/HCI_Icons/warmingHut.png','Hut','Rink includes a Warming Hut'],['img/HCI_Icons/changinRoom.png','Change Room','Rink includes a Change Room']]],
	['5','OPEN','River Osborne','101 Pembina Hwy.','1','2',[['img/HCI_Icons/warmingHut.png','Hut','Rink includes a Warming Hut'],['img/HCI_Icons/changinRoom.png','Change Room','Rink includes a Change Room']]],
	['6','CLOSED','St. Norbert','3450 Pembina Hwy','1','2',[['img/HCI_Icons/washRoom.png','Washroom','Rink includes a Washroom'],['img/HCI_Icons/warmingHut.png','Hut','Rink includes a Warming Hut']]], 
	['7','OPEN','Bison','356 Bison Ave.','4','4',[['img/HCI_Icons/changinRoom.png','Change Room','Rink includes a Change Room'],['img/HCI_Icons/skateTrail.png','Skating Trail','Rink includes a Skating Trail']]], 
	['8','OPEN','Sir John Franklin','1 Sir John Franklin Rd.','2','2',[['img/HCI_Icons/changinRoom.png','Change Room','Rink includes a Change Room'],['img/HCI_Icons/skateTrail.png','Skating Trail','Rink includes a Skating Trail']]], 
	['9','OPEN','South Transcona','124 Borden Ave.','5','2',[['img/HCI_Icons/changinRoom.png','Change Room','Rink includes a Change Room'],['img/HCI_Icons/skateTrail.png','Skating Trail','Rink includes a Skating Trail']]], 
	['10','OPEN','Morgan Satellite','305 Morgan Cres','5','5',[['img/HCI_Icons/changinRoom.png','Change Room','Rink includes a Change Room'],['img/HCI_Icons/skateTrail.png','Skating Trail','Rink includes a Skating Trail']]], 
	['11','OPEN','Waverley Heights','1885 Chacellor Dr.','5','5',[['img/HCI_Icons/changinRoom.png','Change Room','Rink includes a Change Room'],['img/HCI_Icons/skateTrail.png','Skating Trail','Rink includes a Skating Trail']]], 
	['12','OPEN','Bord-Aire','471 Hampton St.','5','1',[['img/HCI_Icons/skateTrail.png','Skating Trail','Rink includes a Skating Trail'],['img/HCI_Icons/washRoom.png','Washroom','Rink includes a Washroom']]],
	['13','CLOSED','Assiniboine West','861 Buchanan Boulevard','3','3',[['img/HCI_Icons/washRoom.png','Washroom','Rink includes a Washroom'],['img/HCI_Icons/warmingHut.png','Hut','Rink includes a Warming Hut']]], 
	['14','OPEN','Varsity View','315 Laxdal Rd.','5','5',[['img/HCI_Icons/washRoom.png','Washroom','Rink includes a Washroom'],['img/HCI_Icons/warmingHut.png','Hut','Rink includes a Warming Hut']]], 
	['15','CLOSED','Lord Roberts','725 Kylemore Ave.','4','3',[['img/HCI_Icons/washRoom.png','Washroom','Rink includes a Washroom'],['img/HCI_Icons/warmingHut.png','Hut','Rink includes a Warming Hut']]], 
	
];


var rinkComments = [
	[['Billy','Great Spot'],['Playnich','Love coming here'],['Garret','Spent time with the family today']],
	[['Jean','Come here every week'],['Garet','Played a little puck today']],
	[['IsNot','Annual BBq here was a success'],['Frank','Went here with Garet'],['John','Love the heated shack']],
	[['MyLover','Oh baby great location'],['Smitty','Free Powerades in the Shack'],['Aport','Thumbs Up']],
	[['ShesJust','Came with the girl friend'],['Name','Such Rink']],
	[['AGirl','I like rink'],['Jion','Much rink'],['NAME','COMMENT']],
	[['Because','comment:sweet spot'],['Doge','Rink Great']],
	[['IAm','I like rink'],['Jude','The place to be'],['Metallica','Rockin on the rink']],
	[['TheOne','<3 rink'],['DaBoys','connel was here'],['Jansen','mardel went with connel']],
	[['ShesJust','Came with the girl friend'],['Name','Such Rink']],
	[['AGirl','I like rink'],['Jion','Much rink'],['NAME','COMMENT']],
	[['Because','comment:sweet spot'],['Doge','Rink Great']],
	[['IAm','I like rink'],['Jude','The place to be'],['Metallica','Rockin on the rink']],
	[['TheOne','<3 rink'],['DaBoys','connel was here'],['Jansen','mardel went with connel']],
	[['Billy','Great Spot'],['Playnich','Love coming here'],['Garret','Spent time with the family today']],
	[['Jean','Come here every week'],['Garet','Played a little puck today']]
];