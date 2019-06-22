Whats needed (easiest):
- JavaDerby (Netbeans default DBMS)
- Payara

Instruction of deployment:
- JDBC configuration - /Web Pages/Web-INF/glassfish-resources
- persistance.xml is set to Create so please after creating DB run the project to create tables
- SQL initial data to insert is in Other Sources/SQL
- Realm configuration is in RealmConf.PNG

What works (for now):
- all use cases,
- communication with Derby
- hashing passwords
- NamedQueries
- login system
- ranking/notification system
- CRUD
- validation of forms
- loggers (partialy)

What does not work:
-Everything else :)
-Exceptions
-Junit Test
-List of what work :)
-loggers (partialy)
-transactions

