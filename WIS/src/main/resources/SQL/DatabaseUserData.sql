INSERT INTO WIS.EMPLOYEE (LOGIN, PASSWORD, STATUS, "TYPE") VALUES ('admin', 'passadmin', 1, 'Admin');
INSERT INTO WIS.PERSONALDATA (EMPLOYEE_ID, EMAIL, EMPLOYEENUMBER, FIRSTNAME, LASTNAME, NOTIFICATIONPOINTS) VALUES (1, 'admin@admin.pl', 'A1001', 'AdminImie', 'AdminNazwisko', 0);

INSERT INTO WIS.EMPLOYEE (LOGIN, PASSWORD, STATUS, "TYPE", LASTTYPE) VALUES ('bartosz', 'passbartosz', 0, 'Disabled', 'Admin');
INSERT INTO WIS.PERSONALDATA (EMPLOYEE_ID, EMAIL, EMPLOYEENUMBER, FIRSTNAME, LASTNAME, NOTIFICATIONPOINTS) VALUES (2, 'bartosz@bartosz.pl', 'A1002', 'Bartosz', 'Bartoszewski', 0);

INSERT INTO WIS.EMPLOYEE (LOGIN, PASSWORD, STATUS, "TYPE") VALUES ('impteam', 'passimpteam', 1, 'ImpTeam');
INSERT INTO WIS.PERSONALDATA (EMPLOYEE_ID, EMAIL, EMPLOYEENUMBER, FIRSTNAME, LASTNAME, NOTIFICATIONPOINTS) VALUES (3, 'impteam@impteam.pl', 'I1001', 'ImpTeamImie', 'ImpTeamNazwisko', 0);

INSERT INTO WIS.EMPLOYEE (LOGIN, PASSWORD, STATUS, "TYPE", LASTTYPE) VALUES ('beata', 'passbeata', 0, 'Disabled', 'ImpTeam');
INSERT INTO WIS.PERSONALDATA (EMPLOYEE_ID, EMAIL, EMPLOYEENUMBER, FIRSTNAME, LASTNAME, NOTIFICATIONPOINTS) VALUES (4, 'beata@beata.pl', 'I1002', 'Beata', 'Beatowa', 0);

INSERT INTO WIS.EMPLOYEE (LOGIN, PASSWORD, STATUS, "TYPE") VALUES ('employee', 'passemployee', 1, 'Employee');
INSERT INTO WIS.PERSONALDATA (EMPLOYEE_ID, EMAIL, EMPLOYEENUMBER, FIRSTNAME, LASTNAME, NOTIFICATIONPOINTS) VALUES (5, 'employee@employee.pl', 'E1001', 'EmployeeImie', 'EmployeeNazwisko', 0);

INSERT INTO WIS.EMPLOYEE (LOGIN, PASSWORD, STATUS, "TYPE") VALUES ('john', 'passjohn', 1, 'Employee');
INSERT INTO WIS.PERSONALDATA (EMPLOYEE_ID, EMAIL, EMPLOYEENUMBER, FIRSTNAME, LASTNAME, NOTIFICATIONPOINTS) VALUES (6, 'john@john.com', 'E1002', 'John', 'Johnowy', 0);