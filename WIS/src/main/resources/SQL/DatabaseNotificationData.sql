--SCRIPT FOR SEVERAL BASIC INITIAL SCENARIOS 

--FIRST SCENARIO NEW: EMPLOYEE CREATED NOTIFICATION, JOHN VOTED UP, 1 RANKING POINTS
INSERT INTO WIS.NOTIFICATION(CATEGORY, CREATED_BY, CREATED_ON, FULLDESCRIPTION, MONEYREQUIRED, PRIORITY, SHORTDESCRIPTION, RANKINGPOINTS, STATUS, VERSION, EMPLOYEE_ID)
    VALUES('Process', 'employee', CURRENT_TIMESTAMP, 'W procesie zarządzania należnościami brakuje elementu audytu wewnętrznego wpisanych braków. Dodanie do łańcucha osoby odpowiedzialnej za kontrole zapobiegłoby dużej ilości skarg. Dodatkowa osoba nie musi być zatrudniona jako osobny etat. Część obowiązków można podzielić', 0, 4, 'Potrzebujemy dodatkowej osoby do kontroli i audytu należności', 1, 'New', 1, 5);
INSERT INTO WIS.VOTE_VOTER(VOTE_ID, VOTER_ID)
    VALUES (1, 5);
INSERT INTO WIS.VOTE_VOTER(VOTE_ID, VOTER_ID)
    VALUES (1, 6);

--SECOND SCENARIO NEW: EMPLOYEE CREATED NOTIFICATION, JOHN AND JOE VOTED UP, 2 RANKING POINTS
INSERT INTO WIS.NOTIFICATION(CATEGORY, CREATED_BY, CREATED_ON, FULLDESCRIPTION, MONEYREQUIRED, PRIORITY, SHORTDESCRIPTION, RANKINGPOINTS, STATUS, VERSION, EMPLOYEE_ID)
    VALUES('Workspace', 'employee', CURRENT_TIMESTAMP, 'Przy biurkach przydałyby się dodatkowe podstawki pod nogi zgodnie z BHP. Zapewniłoby to odpoczynek zmęczonym nogom i nie musielibyśmy podkładać książek pod nogi. Na chwilę obecną trzeba się prosić o coś, co powinno być w standardzie.', 1, 3, 'Brak podstawek pod nogi', 2, 'New', 1, 5);
INSERT INTO WIS.VOTE_VOTER(VOTE_ID, VOTER_ID)
    VALUES (2, 5);
INSERT INTO WIS.VOTE_VOTER(VOTE_ID, VOTER_ID)
    VALUES (2, 6);
INSERT INTO WIS.VOTE_VOTER(VOTE_ID, VOTER_ID)
    VALUES (2, 7);

--THIRD SCENARIO WIP: EMPLOYEE CREATED NOTIFICATION, JOHN, JOE AND JENNY VOTED UP, 3 RANKING POINTS
INSERT INTO WIS.NOTIFICATION(CATEGORY, CREATED_BY, CREATED_ON, FULLDESCRIPTION, MONEYREQUIRED, PRIORITY, SHORTDESCRIPTION, RANKINGPOINTS, STATUS, VERSION, EMPLOYEE_ID)
    VALUES('Other', 'employee', CURRENT_TIMESTAMP, 'W celu zabezpieczenia środowiska i wspólnej przyszłości nalegamy na wymianę jednorazowych kubków do wody z plastikowych na papierowe. Dzięki użyciu biodegradowalnych materiałów, których koszt jest identyczny jak plastikowych, zadbamy wspólnie o środowisko w miejscu pracy i nauczymy innych ludzi dbać wspólnie o naturę.', 0, 2, 'Kubki papierowe zamiast plastikowych.', 3, 'WIP', 1, 5);
INSERT INTO WIS.VOTE_VOTER(VOTE_ID, VOTER_ID)
    VALUES (3, 5);
INSERT INTO WIS.VOTE_VOTER(VOTE_ID, VOTER_ID)
    VALUES (3, 6);
INSERT INTO WIS.VOTE_VOTER(VOTE_ID, VOTER_ID)
    VALUES (3, 7);
INSERT INTO WIS.VOTE_VOTER(VOTE_ID, VOTER_ID)
    VALUES (3, 8);

--FOURTH SCENARIO COMPLETED: EMPLOYEE CREATED NOTIFICATION, JOHN, JOE AND JENNY VOTED UP, 3 RANKING POINTS
INSERT INTO WIS.NOTIFICATION(CATEGORY, CREATED_BY, CREATED_ON, FULLDESCRIPTION, MONEYREQUIRED, PRIORITY, SHORTDESCRIPTION, RANKINGPOINTS, STATUS, VERSION, EMPLOYEE_ID)
    VALUES('Workspace', 'employee', CURRENT_TIMESTAMP, 'Serdecznie prosimy o wymianę krzeseł ze stołowych na obrotowe. Przy obecnych efektywna praca jest niemożliwa.', 0, 2, 'Krzesła obrotowe potrzebne na wczoraj.', 15, 'Completed', 1, 5);
INSERT INTO WIS.VOTE_VOTER(VOTE_ID, VOTER_ID)
    VALUES (4, 5);
INSERT INTO WIS.VOTE_VOTER(VOTE_ID, VOTER_ID)
    VALUES (4, 6);
INSERT INTO WIS.VOTE_VOTER(VOTE_ID, VOTER_ID)
    VALUES (4, 7);
INSERT INTO WIS.VOTE_VOTER(VOTE_ID, VOTER_ID)
    VALUES (4, 8);

--FIFTH SCENARIO COMPLETED: EMPLOYEE CREATED NOTIFICATION, JENNY VOTED UP, 1 RANKING POINTS
INSERT INTO WIS.NOTIFICATION(CATEGORY, CREATED_BY, CREATED_ON, FULLDESCRIPTION, MONEYREQUIRED, PRIORITY, SHORTDESCRIPTION, RANKINGPOINTS, STATUS, VERSION, EMPLOYEE_ID)
    VALUES('Workspace', 'employee', CURRENT_TIMESTAMP, 'Pracownicy działu Należności chcieliby zorganizować symboliczny bieg z fakturami wokół budynku. Okrążeń 10. Dla pierwszej osoby nagroda w postaci zszywacza. Kwota wstępu 25 zł z przeznaczeniem na chore dzieci.', 0, 2, 'Organizacja biegu na rzecz chorych dzieci.', 1, 'New', 1, 5);
INSERT INTO WIS.VOTE_VOTER(VOTE_ID, VOTER_ID)
    VALUES (5, 5);
INSERT INTO WIS.VOTE_VOTER(VOTE_ID, VOTER_ID)
    VALUES (5, 8);

--SIXTH SCENARIO REJECTED: EMPLOYEE CREATED NOTIFICATION, NOBODY VOTED UP, 0 RANKING POINTS
INSERT INTO WIS.NOTIFICATION(CATEGORY, CREATED_BY, CREATED_ON, FULLDESCRIPTION, MONEYREQUIRED, PRIORITY, SHORTDESCRIPTION, RANKINGPOINTS, STATUS, VERSION, EMPLOYEE_ID)
    VALUES('Organization', 'employee', CURRENT_TIMESTAMP, 'Powinna być możliwość wnoszenia i spożywania alkoholu na terenie pracy. Stres bardzo łatwo jest rozwiązać alkoholem, a w obecnej chwili jest to zakazane (niezrozumiale)...', 0, 2, 'Alkohol w lodówce pracowniczej.', 0, 'Rejected', 1, 5);
INSERT INTO WIS.VOTE_VOTER(VOTE_ID, VOTER_ID)
    VALUES (6, 5);
