# CI/CD Pipeline Übung - Calculator API

Willkommen zur praktischen CI/CD Übung! In dieser Übung werdet ihr eine CI/CD Pipeline für eine Spring Boot Anwendung erstellen und dabei typische Herausforderungen lösen.

## Überblick

Diese Übung enthält eine einfache **Calculator REST API** mit Spring Boot. Die Anwendung hat absichtlich einige Bugs, die durch fehlschlagende Tests aufgedeckt werden. Eure Aufgabe ist es, eine vollständige CI/CD Pipeline zu erstellen und die Bugs zu beheben.

**Technologie-Stack:**
- Java 17
- Spring Boot 3.2.0
- Maven
- Docker
- GitHub Actions
- Swagger/OpenAPI

## Lernziele

Nach Abschluss dieser Übung könnt ihr:
- Eine CI/CD Pipeline mit GitHub Actions erstellen
- Tests automatisiert ausführen
- Fehlerhafte Tests identifizieren und beheben
- Artefakte in GitHub Packages/Container Registry veröffentlichen
- Docker Images bauen und in einer Registry speichern

---

## Teil 1: Java Einrichtung und CI Pipeline (mit fehlschlagenden Tests)

### Aufgabe 1.1: Repository Setup

```
Legt euch in eurem Github Account ein leeres Repo CI_CD_EXERCISE an.
```

```bash
# Repository klonen
git clone https://github.com/abatplus-vl-htw-wise-2526/CI_CD_EXERCISE
cd CI_CD_EXERCISE

# Eigenes Repository auf GitHub erstellen und pushen
git remote set-url origin <your-new-repo-url>
git push -u origin main
```

### Aufgabe 1.2: Java und Maven überprüfen

```bash
# Java Version prüfen (benötigt: min. Java 17)
java -version

# Maven Version prüfen
mvn -version
```

### Aufgabe 1.3: Dependencies installieren

```bash
# Maven Dependencies herunterladen
mvn dependency:resolve
```

### Aufgabe 1.4: Code kompilieren

```bash
# Projekt kompilieren (ohne Tests)
mvn compile
```

### Aufgabe 1.5: Tests ausführen

```bash
# Tests ausführen
mvn test

# Achtung: Tests werden fehlschlagen!
# Das ist beabsichtigt
```

### Aufgabe 1.6: GitHub Actions Workflow erstellen

Erstellt eine Datei `.github/workflows/ci.yml`:

**Die Pipeline soll:**
1. Bei jedem Push auf `main` ausgeführt werden
2. Die Länge eines vorher angelegten Secrets in der console ausgeben
3. Java 17 einrichten
4. Dependencies installieren
5. Code kompilieren
6. Tests ausführen

### Aufgabe 1.7: Workflow committen und ausführen

```bash
git add .github/workflows/ci.yml
git commit -m "Add CI pipeline"
git push
```

- Geht zu GitHub → Actions Tab
- Beobachtet die Pipeline-Ausführung
- ** Die Pipeline wird fehlschlagen, weil die Tests nicht bestehen!**
- Das ist normal und zeigt, dass eure Pipeline funktioniert

---

## 🔧 Teil 2: Tests beheben und Build-Artefakte erstellen

### Aufgabe 2.1: Fehler analysieren

Schaut euch die Test-Logs in GitHub Actions an:
- Welche Tests schlagen fehl?
- Was ist die Ursache?

Schaut in folgende Dateien:
- `src/test/java/com/example/calculator/service/CalculatorServiceTest.java`
- `src/main/java/com/example/calculator/service/CalculatorService.java`

In `CalculatorService.java` gibt es **3 Bugs**:

1.  **Addition-Bug**: Die Methode `add()` macht etwas Falsches
2.  **Subtraction-Bug**: Die Methode `subtract()` macht etwas Falsches
3.  **Division-Bug**: Die Methode `divide()` behandelt Division durch Null nicht

### Aufgabe 2.2: Bugs lokal beheben

Korrigiert alle drei Bugs in `CalculatorService.java`.

### Aufgabe 2.3: Tests lokal ausführen

```bash
# Tests ausführen
mvn test

#  Alle Tests sollten jetzt grün sein!
```

### Aufgabe 2.4: Fixes committen

```bash
git add src/main/java/com/example/calculator/service/CalculatorService.java
git commit -m "Fix calculator bugs: addition, subtraction, and division by zero"
git push
```

- Überprüft in GitHub Actions, dass die Pipeline jetzt **erfolgreich** durchläuft 

### Aufgabe 2.5: Pipeline um Build-Artefakt erweitern

Erweitert eure `.github/workflows/ci.yml`:

**Die Pipeline soll jetzt zusätzlich:**
1. JAR-Artefakt erstellen (`mvn package`)
2. Das JAR-File als GitHub Actions Artifact hochladen

### Aufgabe 2.6: Erweiterte Pipeline committen

```bash
git add .github/workflows/ci.yml
git commit -m "Add artifact upload to pipeline"
git push
```

### Aufgabe 2.7: Artefakt herunterladen und testen

**Artefakt von GitHub Actions herunterladen:**
1. Geht zu GitHub → Actions
2. Klickt auf den letzten erfolgreichen Workflow-Run
3. Scrollt nach unten zu "Artifacts"
4. Ladet das `calculator-api-jar` herunter
5. Entpackt die ZIP-Datei

**Artefakt lokal testen:**
```bash
# Anwendung aus dem heruntergeladenen JAR-File starten
java -jar calculator-api.jar

# Testen: http://localhost:8080/swagger-ui.html
# Health Check: http://localhost:8080/api/calculator/health
```

---

##  Teil 3: Docker Image bauen und zu GitHub Container Registry pushen

### Aufgabe 3.1: Docker Image lokal testen

```bash
# Docker Image bauen
docker build -t calculator-api:latest .

# Container starten
docker run -p 8080:8080 calculator-api:latest

# In einem anderen Terminal testen:
curl http://localhost:8080/api/calculator/health

# Swagger UI: http://localhost:8080/swagger-ui.html
```

### Aufgabe 3.2: Pipeline um Docker Build erweitern

Erweitert eure `.github/workflows/ci.yml`:

**Die Pipeline soll jetzt zusätzlich:**
1. Docker Image bauen
2. Bei GitHub Container Registry (ghcr.io) anmelden
3. Image mit Tag `latest` und Git-SHA pushen

### Aufgabe 3.3: Erweiterte Pipeline committen

```bash
git add .github/workflows/ci.yml
git commit -m "Add Docker build and push to GHCR"
git push
```

- Geht zu GitHub → Actions
- Beobachtet die Pipeline-Ausführung
- Überprüft, dass das Docker Image erfolgreich gebaut und gepusht wurde

### Aufgabe 3.4: Package-Sichtbarkeit auf Public setzen

Nach dem ersten erfolgreichen Push:

1. Geht zu eurem GitHub Profil → Packages
2. Klickt auf `calculator-api`
3. Package settings (rechts oben)
4. Scrollt nach unten zu "Danger Zone"
5. "Change visibility" → "Public"
6. Bestätigt die Änderung

### Aufgabe 3.5: Docker Image von GHCR pullen und ausführen

```bash
# Image von GitHub Container Registry pullen
docker pull ghcr.io/<your-username>/calculator-api:latest

# Container aus Registry-Image starten
docker run -p 8080:8080 ghcr.io/<your-username>/calculator-api:latest

# Testen
curl http://localhost:8080/api/calculator/health

# Swagger UI öffnen: http://localhost:8080/swagger-ui.html
```

**Erfolgreich!** Ihr habt jetzt eine vollständige CI/CD Pipeline:
-  Automatisches Kompilieren und Testen
-  Build-Artefakte erstellen und bereitstellen
-  Docker Images bauen und in Container Registry pushen

---

##  API Endpunkte

Nach dem Start der Anwendung sind folgende Endpunkte verfügbar:

| Methode | Endpunkt | Beschreibung |
|---------|----------|--------------|
| GET | `/api/calculator/health` | Health Check |
| POST | `/api/calculator/add` | Addition |
| POST | `/api/calculator/subtract` | Subtraktion |
| POST | `/api/calculator/multiply` | Multiplikation |
| POST | `/api/calculator/divide` | Division |
| POST | `/api/calculator/power` | Potenzierung |

**Request Body Beispiel:**
```json
{
  "a": 10.0,
  "b": 5.0
}
```

**Response Beispiel:**
```json
{
  "result": 15.0,
  "operation": "addition"
}
```

---

##  Checkliste

### Teil 1: Java Einrichtung und CI Pipeline (mit fehlschlagenden Tests)
- [ ] Repository geklont und eigenes Repository erstellt
- [ ] Java 17 und Maven überprüft
- [ ] Dependencies installiert
- [ ] Code kompiliert
- [ ] Tests ausgeführt (fehlgeschlagen - wie erwartet)
- [ ] GitHub Actions Workflow erstellt (`.github/workflows/ci.yml`)
- [ ] Pipeline committet und ausgeführt
- [ ] Pipeline-Fehler beobachtet (Tests schlagen fehl - erwartet!)

### Teil 2: Tests beheben und Build-Artefakte erstellen
- [ ] Test-Fehler in GitHub Actions analysiert
- [ ] Alle 3 Bugs im `CalculatorService` gefunden und behoben
- [ ] Tests lokal erfolgreich ausgeführt (alle grün)
- [ ] Fixes committet und gepusht
- [ ] Pipeline läuft erfolgreich durch
- [ ] Pipeline um Artifact-Upload erweitert
- [ ] Erweiterte Pipeline committet
- [ ] JAR-Artefakt von GitHub Actions heruntergeladen
- [ ] JAR-File lokal getestet

### Teil 3: Docker Image bauen und zu GHCR pushen
- [ ] Docker Image lokal gebaut und getestet
- [ ] Pipeline um Docker Build und GHCR Push erweitert
- [ ] Erweiterte Pipeline committet und ausgeführt
- [ ] Package-Sichtbarkeit auf Public gesetzt
- [ ] Image von GitHub Container Registry gepullt
- [ ] Container aus Registry-Image gestartet und getestet

---

**Viel Erfolg! **
