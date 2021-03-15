# Der Online-Buchladen


## Übersicht
In unserem Beispiel gibt es den Online-Buchladen (`buchladen`), über den die Autoren ihre Bücher einstellen können.

![gesamt](docs/gesamt.png "Gesamtübersicht")

Daneben gibt es zwei Systeme, die die API des `buchladen` nutzen wollen - nämlich zum einen die Empfehlung `buchempfehlung` und den Buchverkauf `buchverkauf`.
Beide Systeme haben unterschiedliche Sichten und Verständnisse darüber, welche ein Buch haben sollte.

Die Consumer haben zwei wesentliche Erwartung an die API des Buchladens:
1. sicherstellen, dass die Schnittstelle des Providers die jeweils notwendigen Felder liefert 
2. Im Falle von Releases neuer Versionen des Providers darf die Schnittstelle zum Consumer nicht brechen 

Aus diesem Grund entscheiden sich, die Consumer mit dem Provider einen `Contract` einzugehen. 


## Consumer Driven Contract Testing

Wir haben nun verstanden, _warum_ die Consumer ein Interesse haben, mit dem Provider einen Contract auszuhandeln. 
Jetzt wollen wir uns darum kümmern, _was_ dafür getan werden muss.

### Aufgaben
- [Aufgabe 0: Vorbereitung](#aufgabe-0-vorbereitung)
- [Aufgabe 1: Pact-Broker](#aufgabe-1-pact-broker)
- [Aufgabe 2: Consumer - Contract definieren](#aufgabe-2-consumer-contract-definieren-als-consumer)
- [Aufgabe 3: Consumer - Contract auf Broker publizieren](#aufgabe-3-consumer-contract-auf-broker-publizieren)
- [Aufgabe 4: Provider - Contract im Test berücksichtigen](#)
- [Aufgabe 5: Provider - Contracts verifizieren](#)
- [Aufgabe 6: Provider - Deployment in Stage](#)
- [Aufgabe 7: Consumer - Can I deploy](#)

  
#### Aufgabe 0: Vorbereitung

Für die nachfolgenden Übungen werden folgende Dinge benötigt:

1. Ein _Java Development Kit_ (kurz JDK) in Version >=11
2. Eine Entwicklungsumgebung für Java (bspw. IntelliJ, etc.)
3. Da wir _Lombok_ einsetzen, sollte sichergestellt sein, dass das _Annotation Processing_ in der IDE aktiviert ist
4. Eine _Docker_-Maschine (für den Pact-Broker) 
5. Ein GitHub-Account anlegen - falls nicht schon
5. Laden Sie sich dieses Repository (`buchladen`) von GitHub herunter
6. Laden Sie sich das Repository (`buchverkauf`) von GitHub herunter
7. Laden Sie sich das Repository (`buchempfelung`) von GitHub herunter


#### Aufgabe 1: PACT-Broker aufsetzen

_TODO_



## Consumer Übersicht
Übersicht über alle Consumer, mit denen ein Contract vereinbart wurde

| Provider-ID | API-Version | Client-ID | Tag der Spec-Version | Consumer | Kontakt |
| --- | --- | --- | --- | --- | --- |
| buchladen-api | v1 | buchverkauf | buchladen-api-v1 | vertrieb | sales@example.com |
| buchladen-api | v1 | buchempfehlung | buchladen-api-v1 | empfehlung | recommendation@example.com |


## Links
| System | was | Link |
| --- | --- | --- |
| buchladen-app | Swagger-UI | http://localhost:8090/swagger-ui.html |
| buchladen-app | OpenAPI Spec (Anzeige) | http://localhost:8090/api-docs/ |
| buchladen-app | OpenAPI Spec als Download (`yml`) | http://localhost:8080/api-docs.yaml |
