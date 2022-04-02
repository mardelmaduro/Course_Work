
main:- settings(X).

settings(X):-retractall(known(_,_,_)),lightingQuantity(X),photoType(X),photoSettings(X).

/*Code for getting input from user to figure out what kind of photo they want and what the current conditions are*/
timeOfDay(TIME):- menuask(timeOfDay,TIME,[dawn,morning,noon,afternoon,dusk,evening,night]).
subject(PPT):-menuask(subject,PPT,[person,place,thing]).
fov(WNN):-menuask(fov,WNN,[wide,normal,narrow]).
movement(M):-menuask(movement,M,[alot,some,none]).
movement(none):-subject(thing).
conditions(C):-menuask(conditions,C,[clear,overcast,underShade]).

/*Code for figuring out the type of the desired photo*/
photoType(landscape):-subject(place),fov(wide).
photoType(portrait):-subject(person),movement(none),fov(normal).
photoType(portrait):-subject(person),movement(none),fov(narrow).
photoType(portrait):-subject(person),movement(some),fov(normal).
photoType(macro):-subject(thing),fov(narrow).
photoType(sport):-subject(person),movement(alot).
photoType(sport):-subject(person),movement(some),fov(narrow).
photoType(stillLife):-subject(thing),fov(normal).
photoType(stillLife):-subject(thing),fov(wide).
photoType(stillLife):-subject(place),fov(narrow).

/*Code for figuring out the reommended lens class for taking the desired photo*/
lensClass(wide):-fov(wide).
lensClass(telephoto):-fov(narrow),subject(person).
lensClass(telephoto):-fov(narrow),subject(place).
lensClass(normal):-fov(normal).
lensClass(macro):-fov(narow),subject(thing).


/*Code for figuring out the chances of a certain lighting quality based on time of day and conditions*/
lightingQuality(hard,0.85) :- timeOfDay(noon).
lightingQuality(hard,0.6) :- timeOfDay(morning).
lightingQuality(hard,0.6) :- timeOfDay(afternoon).
lightingQuality(soft,0.9) :- conditions(overcast).
lightingQuality(hard,0.8) :- conditions(clear).	
lightingQuality(soft,0.6) :- conditions(underShade).	
lightingQuality(soft,0.9) :- conditions(night).	

/*Code for figuring out the type of the desired photo*/
lightingQuantity(medium):-timeOfDay(dawn).
lightingQuantity(bright):-timeOfDay(morning).
lightingQuantity(veryBright):-timeOfDay(noon).
lightingQuantity(bright):-timeOfDay(afternoon).
lightingQuantity(medium):-timeOfDay(dusk).
lightingQuantity(dim):-timeOfDay(evening).
lightingQuantity(verydim):-timeOfDay(night).

/*Code for figuring out the ISO setting based on the lighting quantity*/
iso(100):-lightingQuantity(veryBright).
iso(200):-lightingQuantity(bright).
iso(400):-lightingQuantity(medium).
iso(800):-lightingQuantity(dim).
iso(1600):-lightingQuantity(verydim).

/*Code for figuring out the Aperture setting based on the lighting quantity*/
apertureF(2):-lightingQuantity(verydim) ; lightingQuantity(dim).
apertureF(4):-lightingQuantity(medium).
apertureF(8):-lightingQuantity(bright).
apertureF(16):-lightingQuantity(veryBright).

/*Code for figuring out the Shutter Speed setting based on the lighting quantity and photo type*/
shutterSpeed(1/250):-lightingQuantity(veryBright) ; photoType(sport).
shutterSpeed(1/125):-lightingQuantity(bright).
shutterSpeed(1/60):-lightingQuantity(medium) , photoType(portrait).
shutterSpeed(1/30):-lightingQuantity(veryBright) ; photoType(sport).
shutterSpeed(1/15):-lightingQuantity(veryBright) ; photoType(sport).
shutterSpeed(1):-lightingQuantity(veryBright) ; photoType(sport).
shutterSpeed(5):-lightingQuantity(veryBright) ; photoType(sport).
shutterSpeed(10):-lightingQuantity(veryDim) ; photoType(landscape).

/*Code for printing out the reommended settings for the desired photo*/
photoSettings(X):-write('Recommended Settings are:'),nl,write('ISO: '),write(iso(X)),nl,write('Aperture: F'),write(apertureF(X)),nl,write('Shutter Speed: '),write(shutterSpeed(X)).



/*Code for implementing menuask*/
menuask(Attr,Val,_):-known(yes,Attr,Val),!.
menuask(Attr,_,_):-known(yes,Attr,_),!,fail.

menuask(Attr,Askval,Menu):-nl,write('What is the current '),write(Attr),write('?'),nl,displayMenu(Menu),write('Enter the choice number> '),read(Choice),nl,pickMenu(Choice,Ansval,Menu)
,assert(known(yes,Attr,Ansval)),Askval=Ansval.
 

displayMenu(Menu):-dispMenu(1,Menu), !.
dispMenu(_,[]).
dispMenu(X,[Item|Rest]):-write(X),write(' : '),write(Item),nl,Next is X+1, dispMenu(Next,Rest).
pickMenu(X,Val,Menu):-integer(X),picMenu(1,X,Val,Menu), !.
pickMenu(Val,Val,_).
picMenu(_,_,none,[]).
picMenu(X,X,Item,[Item|_]).
picMenu(Ctr,X,Val,[_,Rest]):-NCtr is Ctr+1, picMenu(NCtr,X,Val,Rest).



/** Test Output
Welcome to SWI-Prolog (Multi-threaded, 64 bits, Version 7.2.3)
Copyright (c) 1990-2015 University of Amsterdam, VU Amsterdam
SWI-Prolog comes with ABSOLUTELY NO WARRANTY. This is free software,
and you are welcome to redistribute it under certain conditions.
Please visit http://www.swi-prolog.org for details.

For help, use ?- help(Topic). or ?- apropos(Word).

1 ?- consult('/Users/mardelmaduro/Documents/Notes/COMP_3190/Assignments/COMP3190_A3').
Warning: /Users/mardelmaduro/Documents/Notes/COMP_3190/Assignments/COMP3190_A3.pl:2:
	Singleton variables: [X]
true.

2 ?- main.

What is the current timeOfDay?
1 : dawn
2 : morning
3 : noon
4 : afternoon
5 : dusk
6 : evening
7 : night
Enter the choice number> 1.

false.

3 ?- settings(X).

What is the current timeOfDay?
1 : dawn
2 : morning
3 : noon
4 : afternoon
5 : dusk
6 : evening
7 : night
Enter the choice number> 2.

false.

4 ?- timeOfDay(X).
X = 2.

5 ?- subject(X).

What is the current subject?
1 : person
2 : place
3 : thing
Enter the choice number> 1.

X = person.

6 ?- movement(X).

What is the current movement?
1 : alot
2 : some
3 : none
Enter the choice number> 3.

X = 3 .

7 ?- fov(X).

What is the current fov?
1 : wide
2 : normal
3 : narrow
Enter the choice number> 2.

X = 2.

8 ?- photoType(X).
false.

9 ?- lightingQuantity(X).
false.

10 ?- lensClass(X).
false.

11 ?- photoSettings(X).
Recommended Settings are:
ISO: iso(_G741)
Aperture: FapertureF(_G741)
Shutter Speed: shutterSpeed(_G741)
true.

12 ?- 

*/