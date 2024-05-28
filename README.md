# TodoAppFinja - How to
## Installationen
Benötig werden:
- Java => https://www.java.com/de/download/
- Docker Desktop => https://www.docker.com/products/docker-desktop/
- NodeJS => https://nodejs.org/en
- Angular CLI => In der CMD folgenden Befehl ausführen: npm install -g @angular/cli@17 (ACHTUNG: NodeJS muss installiert sein, damit npm verwendet werden kann)
- Intellij COMMUNITY => https://www.jetbrains.com/de-de/idea/download/?section=windows
- VSCode => https://code.visualstudio.com/download
- git => https://git-scm.com/downloads
- Eventuell ein DBMS, um Einsicht in die Datenbank zu bekommen un diese zu verwalten => (DBeaver, HeidiSQL etc.)

## Docker
### Was ist Docker?
Mit Docker werden Anwendungen in Containern virtualisiert. Sie bilden sozusagen eine Virtuelle Maschine (nicht ganz richtig). Das heißt, auf eurem System läuft im Hintergrund ein anderes System mit bestimmten Resourcen.

### Was sind Container?
Ein Docker-Container ist eine Laufzeitumgebung mit allen notwendigen Komponenten wie Code, Abhängigkeiten und Bibliotheken, die benötigt werden, um den Anwendungscode auszuführen, ohne Abhängigkeiten von Host-Maschinen (euer PC) zu verwenden.

### Was ist ein Image?
Docker-Images sind schreibgeschützte Vorlagen, die Anweisungen zum Erstellen eines Containers enthalten. Ein Docker-Image ist ein Snapshot oder eine Vorlage der Bibliotheken und Abhängigkeiten, die in einem Container für die Ausführung einer Anwendung erforderlich sind (in eurem Fall: eine MySQL Datenbank).

### docker-compose.yaml
In einer docker-compose.yaml wird definiert mit welchen Attributen der Container gestartet werden soll (Name, Port, MySQL Username und Password, etc.). Um dieses Script dann letztendlich als Container laufen zu lassen, müsst ihr die CMD aufrufen. In der CMD navigiert ihr dann zu dem Ort, wo die docker-compose.yaml liegt (meistens im Backend im Root-Verzeichnis). Die docker-compose.yaml wird manuell erstellt. In dem Verzeichnis angekommen startet ihr die .yaml mit dem Befehl "docker compose up". Nun läuft eure Datenbank. 

## Aufsetzen des Projektes
1. Rechtsklick im Ordner, in dem das Projekt gespeichert werden soll
2. "git bash here" auswählen => eine CommandLine sollte sich öffnen
3. Github öffnen
4. Grünen "Code" button ancklicken
5. Falls ein public Key  hinterlegt ist, kann "ssh" ausgewählt werden. Sonst "https".
6. Link kopieren
7. In die GHit CommanLine wechseln und "git clone <link>" ausführen
8. Jetzt sollte das Projekt, bzw die Projektdatein erfolgreich in dem Ordner angezeigt werden
9. Docker Anwendung starten
10. Als erstes muss immer die Datenbank gestartet werden!!! => CMD aufrufen => Verzeichnis /todo-backend suchen bzw. aufrufen => "docker compose up" ausführen (Einmalig! Danach ist der Container in Docker Desktop zu finden und per Click zu starten)
11. todo-backend in Intellij öffnen
12. Backend starten
13. frontend in Visual Studio Code öffnen
14. CommandLine in Visual Studio Code öffnen
15. Im "frontend" Verzeichnis folgenden Befehl eingeben: "npm i"
16. Im selben Verzeichnis folgenden Befehl eingeben: "ng s -o" (Kurzform für: angular start --open)
17. Jetzt sollte alles funktionieren
