# Beispiel-Szenario und Aufgaben ("Let's REST") 

## Rahmenbedingungen / Anforderungen

- Technologiestack: 
  - JDK 11+
  - Maven
  - H2 (Server-Modus, anstelle Oracle DB), 
  - Spring Boot und weitere Spring-Projekte (Spring Data JPA u.a.)
  - JUnit5, Mockito, weitere Testing-Tools nach Wahl
  - Api-Dokumetation mit OpenAPI (Swagger) 
- Nutzung einer vorhandenen Datenbank
- Implementierung von Entitäten
  - Bean-Validierung
- Implementierung von Repositories
  - ggf. Queries? 
- Implementierung von Services
- Implementierung von REST-Controllern
  - API-Design und URL-Aufbau
  - Fehlerbehandlung
  - optional: HATEOAS/HAL
  - optional: Absicherung der Zugriffe
  - optional: OpenAPI-Dokumetation
- Implementierung von REST-Zugriffen 
  - optional: Absicherung der Zugriffe (s.o.)
- Testing: Unit- und Integrationstests
  - Tools: JUnit5, Mockito, ...
- Package-Layout nach DDD-Prinzipien


## Zielsetzungen

- ** Szenario: ** Benötigt wird eine innerbetriebliche kleine Lösung für die 
  Überwachung der Datenqualität in verschiedenen Datenbeständen. Dazu wurde 
  ein Scoring-Konzept erarbeitet, mit dem unterschiedliche Aspekte der 
  Datenqualität in jeweils einzelnen "Scores" abgebildet werden können, z.B.:
  "Score XY beschreibt, wie weit z.B. die Kundendaten frei von Dubletten sind".
  Es werden einzelne Messungen erfasst, die v.a. die Gesamtanzahl der Datensätze,
  die Anzahl der Befunde und einen Zeitstempel zur Messung enthalten. Der Wert 
  des Scores wird in Prozent angegeben und sagt aus, in wieweit der Datenbestand
  frei von Befunden ist, z.B.: Score-Wert 100 % entspricht: "Keine Befunde, 
  optimale Qualität!".
  
- ** Vorgaben: ** Entwickelt werden sollen zwei Spring-Boot-Anwendungen: 

  - ** Anwendung 1 ** verwaltet die Scores und Messwerte. Sie greift auf die 
    Datenbank zu und ermöglich per REST-API folgende Aktionen: 	
    - Lesen aller definierten Scores.
    - Erstellen/Lesen/Ändern/Löschen eines einzelnen Scores,
	  wobei beim Löschen alle zum Score erfassten Messwerte ebenfalls gelöscht werden.
	- Lesen aller Messwerte zu einem Score.
	- Speichern eines neuen Messwertes zu einem Score.
	
  - ** Anwendung 2 ** erfasst Messwerte und sendet diese an Anwendung 1 zur Speicherung.
    Hierzu wird die oben beschriebene API benützt.
	(Da wir in unserer Situation keinen reale Datenbestand haben, muss Anwendung 2 
	die Messwerte "in plausiblem Rahmen" zufällig generieren. Ob dies als "one shot" 
	mit jedem Anwendungsstart oder zeitgesteuert mehrfach/kontinuierlich geschieht,
	ist in dieser Aufgabenstellung unerheblich).
	
  - ** Datenbank: ** Im "realen Leben" wäre z.B. eine Oracle DB im Einsatz. 
    In dieser Situation wird die Datenbank durch eine kleine Anwendung bereit gestellt, 
	die eine H2-Instanz im Server-Modus startet und die Einrichtung der Tabellen und 
	Beispieldaten übernimmt. An dieser Anwendung sollten keine Änderungen erforderlich 
	sein. Die Anwendung sollte als erster Schritt gebaut und gestartet werden.
	Verbindungparameter zur Datenbank sind: 
	- URL: jdbc:h2:tcp://localhost/qualitybase
	- User: sa
	- Passwort: "" (leer)
	
  - ** Umsetzung: ** 
    - Die Projekte sollen als Maven-Projekte angelegt werden.  	  
	- Beide Anwendungen sollen als Spring Boot-Anwendungen umgesetzt werden. 
	  Für die Datenbank-Zugriffe sollte Spring Data JPA verwendet werden. Weitere
	  Spring-Subprojekte können im eigenen Ermessen hinzugezogen werden.
    - Die Implementierung der beiden Anwendungen sollte mit gemeinsamem Parent-Projekt 
	  erfolgen. Dies kann auch im Parent-Projekt der Datenbank-Anwendung erfolgen, 
	  allerdings sollten Spring-Abhängigkeiten nicht dort hinein gezogen werden, 
	  wegen eventuellen Problemen mit der H2-Version.	
	- Gewünscht ist eine gute Test-Abdeckung. Im Rahmen der Bearbeitungszeit müssen
	  Entscheidungen zum sinnvollen Einsatz und Umfang von Unit- und Integrationstests
	  getroffen werden.
	- Gewünscht ist ein Package-Layout im Stil des Domain Driven Designs (DDD).
	- "Nice to have" ist die Absicherung der REST-Services mit einem im Zeitrahmen 
	  umsetzbaren Ansatz nach Wahl. 
	- "Nice to have" ist ebenfalls der Einsatz von OpenAPI. 
	
- ** Wichtig für Bearbeitung: **
  - 
  - Die Aufgabenstellung ist zeitlich und inhaltlich offen, d.h., sie muss nicht 
    innerhalb der verfügbaren Zeit "vollständig abgearbeitet sein". 
  - Die Aufgabenstellung will als Beispiel-Szenario verstanden werden, in dessen 
    Rahmen eigene Entscheidungen getroffen werden, ggf. alternative Lösungsansätze
    aufgezeigt und das eigene Vorgehen erklärt und begründet werden soll.

	