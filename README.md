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

TODO

## Consumer Übersicht
Übersicht über alle Consumer, mit denen ein Contract vereinbart wurde

| Provider-ID | API-Version | Client-ID | Tag der Spec-Version | Consumer | Kontakt |
| --- | --- | --- | --- | --- | --- |
| buchladen-api | v1 | buchverkauf | buchladen-api-v1 | vertrieb | sales@example.com |
| buchladen-api | v1 | buchempfehlung | buchladen-api-v1 | empfehlung | recommendation@example.com |


## Links
| was | Link |
| --- | --- |
| Swagger-UI | http://localhost:8080/swagger-ui.html |
| OpenAPI Spec (Anzeige) | http://localhost:8080/api-docs/ |
| OpenAPI Spec als Download (`yml`) | http://localhost:8080/api-docs.yaml |
