
person(jayda).
person(jaidyn).
person(moises).
person(hillary).
person(sophie).
person(leonard).
person(branden).
person(madisyn).
person(frankie).
person(lincon).
person(sloane).
person(logan).
person(maria).
person(emilie).
person(allison).
person(kayden).
person(melina).
person(kolton).
person(bradley).
person(jane).
person(julie).

/*Gender*/
male(jaidyn).
male(moises).
male(leonard).
male(branden).
male(frankie).
male(lincon).
male(logan).
male(kayden).
male(kolton).
male(bradley).

female(hillary).
female(sophie).
female(jayda).
female(madisyn).
female(sloane).
female(maria).
female(emilie).
female(allison).
female(melina).
female(jane).
female(julie).


/*parenthood*/
parent(lincon, logan).
parent(lincon, sophie).
parent(maria, logan).
parent(maria, sophie).
parent(sophie, allison).
parent(kolton, allison).
parent(logan, moises).
parent(logan, jayda).
parent(logan, emilie).
parent(melina, moises).
parent(melina, jayda).
parent(melina, emilie).
parent(emilie, branden).
parent(bradley, branden).
parent(jayda, jaidyn).
parent(kayden, jaidyn).
parent(moises, madisyn).
parent(hillary, madisyn).
parent(allison, leonard).
parent(allison, sloane).
parent(frankie, leonard).
parent(frankie, sloane).
parent(frankie,julie).
parent(jane,julie).


/*marriage*/
married(lincon,maria).
married(maria,lincon).

married(logan, melina).
married(melina, logan).

married(kolton, sophie).
married(sophie, kolton).

married(bradley, emilie).
married(emile, bradley).

married(kayden, jayda).
married(jayda, kayden).

married(moises, hillary).
married(hillary, moises).

married(frankie, allison).
married(allison, frankie).

husband(X,Y):-male(X),married(X,Y).
wife(X,Y):-husband(Y,X).

/*Parenthood continued*/
father(X,Y):- male(X), parent(X,Y).
mother(X,Y):- female(X), parent(X,Y).
stepParent(X,Z):- parent(Y,Z), married(Y,X), (not(parent(X,Z))).
stepMother(X,Y):- female(X), stepParent(X,Y).
stepFather(X,Y):- male(X), stepParent(X,Y).


/*Children*/
child(X,Y):-parent(Y,X).
son(X,Y):-male(X) ,child(X,Y).
daughter(X,Y):-female(X), child(X,Y).
stepChild(X,Y):- stepParent(Y,X).
stepDaughter(X,Y):- female(X), stepChild(X,Y).
stepSon(X,Y):- male(X), stepChild(X,Y).

/*Siblings*/
sibling(X,Y):- parent(Z,X),parent(Z,Y),X\=Y.
sister(X,Y):-female(X), sibling(X,Y).
brother(X,Y):-male(X), sibling(X,Y).

fullSibling(X,Y):-mother(Z,X),mother(Z,Y),father(W,X),father(W,Y), X\=Y.
fullSister(X,Y):-female(X),fullSibling(X,Y).
fullbrother(X,Y):-male(X),fullSibling(X,Y).

halfSibling(X,Y):-sibling(X,Y), not(fullSibling(X,Y)) , X\=Y.
halfSister(X,Y):- female(X), halfSibling(X,Y).
halfBrother(X,Y):- male(X), halfSibling(X,Y).

/*In-Laws*/
parentInLaw(X,Z):-parent(X,Y) , (married(Y,Z) ; married(Z,Y)).
motherInLaw(X,Y):-female(X) ,parentInLaw(X,Y).
fatherInLaw(X,Y):-male(X) ,parentInLaw(X,Y).

childInLaw(X,Y):-parentInLaw(Y,X).
daughterInLaw(X,Y):- female(X),childInLaw(X,Y).
sonInLaw(X,Y):-male(X),childInLaw(X,Y).

siblingInLaw(X,Z):- (sibling(Z,Y);sibling(Y,Z)),(married(X,Y);married(Y,X)).
sisterInLaw(X,Y):- female(X), siblingInLaw(X,Y).
brotherInLaw(X,Y):- male(X), siblingInLaw(X,Y).


/*Grandparents*/
grandparent(X,Z):-parent(X,Y) ,parent(Y,Z).
grandfather(X,Z):-father(X,Y) ,parent(Y,Z).
grandfather(X,Y):-male(X) ,grandparent(X,Y).
grandmother(X,Z):-mother(X,Y) ,parent(Y,Z).
grandmother(X,Y):-female(X) ,grandparent(X,Y).

/*GrandChildren*/
grandChild(X,Y):-grandparent(Y,X).
grandDaughter(X,Y):- female(X), grandChild(X,Y).
grandSon(X,Y):- male(X), grandChild(X,Y).

/*Great-Grandparents*/
greatGrandparent(X,Y):-grandparent(Z,Y), parent(X,Z).
greatGrandfather(X,Y):- male(X), greatGrandparent(X,Y).
greatGrandmother(X,Y):- female(X), greatGrandparent(X,Y).

/*Great-GrandChildren*/
greatGrandchild(X,Y):-greatGrandparent(Y,X).
greatGrandson(X,Y):- male(X), greatGrandchild(X,Y).
greatGranddaughter(X,Y):- female(X), greatGrandchild(X,Y).


/*Cousins*/
cousin(X,Y):-grandparent(Z,X) ,grandparent(Z,Y) ,X\=Y.

/*Aunt & Uncle*/
aunt(X,Z):-parent(Y,Z) ,sister(X,Y).
aunt(X,Z):- daughterInLaw(X,Y), grandparent(Y,Z) , (not(parent(X,Z))).
uncle(X,Z):-parent(Y,Z) ,brother(X,Y).
uncle(X,Z):- sonInLaw(X,Y), grandparent(Y,Z), (not(parent(X,Z))).


/*Niblings*/
nibling(X,Y):-(aunt(Y,X) ; uncle(Y,X)).
niece(X,Y):-female(X) ,nibling(X,Y).
nephew(X,Y):-male(X) ,nibling(X,Y).


/*Ancestors*/
ancestor(X,Y):-parent(X,Y).
ancestor(X,Y):-parent(X,Z) ,ancestor(Z,Y).

/*Descendant*/
descendant(X,Y):-child(X,Y).
descendant(X,Y):-child(X,Z) ,descendant(Z,Y) .

/*Relatives (by blood)*/
related(X,Y):-ancestor(X,Y).
/*related(X,Y):-descendant(X,Y).*/
related(X,Z):-descendant(Z,Y) , ancestor(Y,X), X\=Z.


/*Print Family*/
printfamily(X):- person(X),child(Y,X),write(Y),nl, fail.
printfamily(X):- person(X), child(Y,X),descendant(Z,Y), write(Z), nl,fail.


/** Test OUTPUT 
Welcome to SWI-Prolog (Multi-threaded, 64 bits, Version 7.2.3)
Copyright (c) 1990-2015 University of Amsterdam, VU Amsterdam
SWI-Prolog comes with ABSOLUTELY NO WARRANTY. This is free software,
and you are welcome to redistribute it under certain conditions.
Please visit http://www.swi-prolog.org for details.

For help, use ?- help(Topic). or ?- apropos(Word).

1 ?- consult('/Users/mardelmaduro/Documents/Notes/COMP_3190/Assignments/COMP_3190_A2').
true.

2 ?- person(X).
X = jayda 
X = jaidyn 
X = moises 
X = hillary 
X = sophie 
X = leonard 
X = branden 
X = madisyn 
X = frankie 
X = lincon 
X = sloane 
X = logan 
X = maria 
X = emilie 
X = allison 
X = kayden 
X = melina 
X = kolton 
X = bradley 
X = jane 
X = julie.

3 ?- male(X).
X = jaidyn 
X = moises 
X = leonard 
X = branden 
X = frankie 
X = lincon 
X = logan 
X = kayden 
X = kolton 
X = bradley.

4 ?- female(X).
X = hillary 
X = sophie 
X = jayda 
X = madisyn 
X = sloane 
X = maria 
X = emilie 
X = allison 
X = melina 
X = jane 
X = julie.

5 ?- parent(logan,Y).
Y = moises 
Y = jayda 
Y = emilie.

6 ?- married(sophie,Y).
Y = kolton.

7 ?- husband(maria,X).
false.

8 ?- husband(X,maria).
X = lincon 
false.

9 ?- wife(maria,Y).
Y = lincon 
false.

10 ?- father(lincon,Y).
Y = logan 
Y = sophie.

11 ?- mother(sophie,Y).
Y = allison.

12 ?- mother(X,sophie).
X = maria 
false.

13 ?- stepMother(X,Y).
X = allison,
Y = julie 
false.

14 ?- child(X,allison).
X = leonard 
X = sloane.

15 ?- son(X,melina).
X = moises 
false.

16 ?- daughter(madisyn,Y).
Y = moises 
Y = hillary.

17 ?- stepDaughter(julie,Y).
Y = allison 
false.

18 ?- sibling(X,jayda).
X = moises 
X = emilie 
X = moises 
X = emilie 
false.

19 ?- sister(X,logan).
X = sophie 
X = sophie 
false.

20 ?- brother(moises,Y).
Y = jayda 
Y = emilie 
Y = jayda 
Y = emilie.

21 ?- fullSister(emilie,Y).
Y = moises 
Y = jayda 
false.

22 ?- fullBrother(X,sloane).
Correct to: "fullbrother(X,sloane)"? 
Please answer 'y' or 'n'? yes
X = leonard 
false.

23 ?- halfSister(X,Y).
X = sloane,
Y = julie 
X = julie,
Y = leonard 
X = julie,
Y = sloane 
false.

24 ?- parentInLaw(X,frankie).
X = sophie 
X = sophie 
X = kolton 
X = kolton 
false.

25 ?- motherInLaw(melina,Y).
Y = hillary 
Y = hillary 
Y = kayden 
Y = kayden 
Y = bradley.

26 ?- daughterInLaw(melina,Y).
Y = lincon 
Y = lincon 
Y = maria 
Y = maria 
false.

27 ?- sisterInLaw(hillary,Y).
Y = jayda 
Y = jayda 
Y = emilie 
Y = emilie 
Y = jayda 
Y = jayda 
Y = emilie 
Y = emilie 
Y = jayda 
Y = jayda 
Y = emilie 
Y = emilie 
Y = jayda 
Y = jayda 
Y = emilie 
Y = emilie 
false.

28 ?- grandfather(lincon,Y).
Y = moises 
Y = jayda 
Y = emilie 
Y = allison 
Y = moises 
Y = jayda 
Y = emilie 
Y = allison.

29 ?- grandDaughter(X,maria).
X = jayda 
X = emilie 
X = allison 
false.

30 ?- greatGrandfather(X,sloane).
X = lincon 
false.

31 ?- cousin(madisyn,X).
X = jaidyn 
X = branden 
X = jaidyn 
X = branden 
false.

32 ?- aunt(hillary,X).
X = jaidyn 
X = branden 
X = jaidyn 
X = branden 
X = jaidyn 
X = branden 
X = jaidyn 
X = branden 
false.

33 ?- nephew(branden,Y).
Y = jayda 
Y = jayda 
Y = hillary 
Y = hillary 
Y = hillary 
Y = hillary 
Y = moises 
Y = moises 
Y = kayden 
Y = kayden 
Y = kayden 
Y = kayden 
false.

34 ?- ancestor(X,leonard).
X = allison 
X = frankie 
X = lincon 
X = maria 
X = sophie 
X = kolton 
false.

35 ?- descendant(X,kolton).
X = allison 
X = leonard 
X = sloane 
false.

36 ?- related(jayda,Y).
Y = jaidyn 
Y = logan 
Y = sophie 
Y = logan 
Y = sophie 
Y = moises 
Y = emilie 
Y = moises 
Y = emilie 
Y = allison 
Y = allison 
Y = moises 
Y = moises 
Y = emilie 
Y = emilie 
Y = branden 
Y = branden 
Y = branden 
Y = branden 
Y = jaidyn 
Y = jaidyn 
Y = jaidyn 
Y = jaidyn 
Y = madisyn 
Y = madisyn 
Y = madisyn 
Y = madisyn 
Y = leonard 
Y = leonard 
Y = sloane 
Y = sloane 
false.

37 ?- printfamily(maria).
logan
sophie
moises
jayda
emilie
branden
jaidyn
madisyn
allison
leonard
sloane
false.

38 ?- 
*/
