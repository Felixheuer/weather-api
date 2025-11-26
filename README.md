# Weather App

Eine Spring Boot REST API-Anwendung, die Wetterdaten für Städte über die OpenWeatherMap API abruft.

## 📋 Inhaltsverzeichnis

- [Überblick](#überblick)
- [Features](#features)
- [Technologie-Stack](#technologie-stack)
- [Voraussetzungen](#voraussetzungen)
- [Installation](#installation)
- [Konfiguration](#konfiguration)
- [Verwendung](#verwendung)
- [API-Endpunkte](#api-endpunkte)
- [Projektstruktur](#projektstruktur)
- [Entwicklung](#entwicklung)

## 🎯 Überblick

Die Weather App ist eine REST API, die es ermöglicht, aktuelle Wetterinformationen für beliebige Städte abzurufen. Die Anwendung nutzt die OpenWeatherMap API, um zunächst die Geokoordinaten einer Stadt zu ermitteln und anschließend die entsprechenden Wetterdaten abzurufen.

## ✨ Features

- **Stadt-basierte Wetterabfrage**: Abfrage von Wetterdaten über den Stadtnamen
- **Geocoding-Integration**: Automatische Umwandlung von Stadtnamen in Koordinaten
- **RESTful API**: Saubere REST-Schnittstelle für einfache Integration
- **Spring Boot**: Moderne Java-Framework-Architektur
- **Lombok**: Reduzierter Boilerplate-Code

## 🛠 Technologie-Stack

- **Java 21**: Programmiersprache
- **Spring Boot 4.0.0**: Framework für die Anwendung
- **Spring Web MVC**: REST API-Funktionalität
- **Lombok**: Code-Generierung zur Reduzierung von Boilerplate
- **Maven**: Build-Management und Dependency-Management
- **OpenWeatherMap API**: Externe Wetterdatenquelle

## 📦 Voraussetzungen

- Java 21 oder höher
- Maven 3.6+ (oder verwenden Sie den mitgelieferten Maven Wrapper)
- Ein OpenWeatherMap API-Key ([kostenlos erhältlich](https://openweathermap.org/api))

## 🚀 Installation

1. **Repository klonen**
   ```bash
   git clone <repository-url>
   cd weather-app
   ```

2. **Dependencies installieren**
   ```bash
   ./mvnw clean install
   ```

3. **Konfiguration einrichten** (siehe [Konfiguration](#konfiguration))

4. **Anwendung starten**
   ```bash
   ./mvnw spring-boot:run
   ```

Die Anwendung läuft standardmäßig auf `http://localhost:8080`

## ⚙️ Konfiguration

### API-Key einrichten

1. **Kopieren Sie die Beispiel-Konfigurationsdatei:**
   ```bash
   cp src/main/resources/application.properties.example src/main/resources/application.properties
   ```

2. **Öffnen Sie `application.properties` und fügen Sie Ihren OpenWeatherMap API-Key ein:**
   ```properties
   spring.application.name=weather-app
   api.key=IHHR_API_KEY_HIER
   geocoding.url=http://api.openweathermap.org/geo/1.0/direct
   weather.url=https://api.openweathermap.org/data/2.5/weather
   ```

3. **Ersetzen Sie `IHHR_API_KEY_HIER` mit Ihrem tatsächlichen API-Key**

   > ⚠️ **WICHTIG**: Die `application.properties` Datei ist in `.gitignore` enthalten und wird nicht in Git hochgeladen. Ihre API-Keys bleiben sicher!

### Konfigurationsoptionen

| Eigenschaft | Beschreibung | Standardwert |
|------------|-------------|--------------|
| `api.key` | OpenWeatherMap API-Key | **Erforderlich** |
| `geocoding.url` | URL für die Geocoding-API | `http://api.openweathermap.org/geo/1.0/direct` |
| `weather.url` | URL für die Wetter-API | `https://api.openweathermap.org/data/2.5/weather` |

## 📖 Verwendung

### Anwendung starten

```bash
./mvnw spring-boot:run
```

### API-Aufruf

Nachdem die Anwendung gestartet wurde, können Sie Wetterdaten für eine Stadt abrufen:

```bash
curl http://localhost:8080/api/v1/weather/Berlin
```

**Beispiel-Response:**
```json
{
  "weather": "Clear",
  "details": "clear sky"
}
```

## 🔌 API-Endpunkte

### GET `/api/v1/weather/{city}`

Ruft Wetterdaten für eine bestimmte Stadt ab.

**Parameter:**
- `city` (Path-Parameter, erforderlich): Name der Stadt (z.B. "Berlin", "London", "New York")

**Response:**
```json
{
  "weather": "Clear",
  "details": "clear sky"
}
```

**Beispiel-Requests:**
```bash
# Wetter für Berlin abrufen
curl http://localhost:8080/api/v1/weather/Berlin

# Wetter für London abrufen
curl http://localhost:8080/api/v1/weather/London

# Wetter für New York abrufen
curl http://localhost:8080/api/v1/weather/New%20York
```

**Fehlerbehandlung:**
- Bei ungültigen Städtenamen oder API-Fehlern wird eine Exception geworfen
- HTTP-Status-Codes werden entsprechend der Fehlerursache zurückgegeben

## 📁 Projektstruktur

```
weather-app/
├── src/
│   ├── main/
│   │   ├── java/com/weather_app/weather_app/
│   │   │   ├── domain/              # Domain-Modelle
│   │   │   │   ├── CityCoordinates.java
│   │   │   │   ├── CityWeather.java
│   │   │   │   └── WeatherRequestDetails.java
│   │   │   ├── entity/              # Entity-Klassen für API-Responses
│   │   │   │   ├── GeocodingCoordinatesEntity.java
│   │   │   │   ├── OpenWeatherResponseEntity.java
│   │   │   │   ├── WeatherEntity.java
│   │   │   │   └── WeatherResponse.java
│   │   │   ├── provider/            # Externe API-Provider
│   │   │   │   ├── GeocodingProvider.java
│   │   │   │   └── WeatherProvider.java
│   │   │   ├── resource/            # REST-Controller
│   │   │   │   └── WeatherResource.java
│   │   │   ├── service/             # Business-Logik
│   │   │   │   └── WeatherService.java
│   │   │   ├── transformer/         # Domain-Entity-Transformationen
│   │   │   │   ├── GeocodingCoordinatesTransformer.java
│   │   │   │   └── OpenWeatherTransformer.java
│   │   │   └── WeatherAppApplication.java
│   │   └── resources/
│   │       ├── application.properties.example
│   │       └── application.properties (nicht in Git)
│   └── test/
│       └── java/
└── pom.xml
```

### Architektur-Überblick

- **Domain Layer**: Enthält die Geschäftslogik-Modelle
- **Entity Layer**: Repräsentiert externe API-Responses und interne Response-Objekte
- **Provider Layer**: Kommuniziert mit externen APIs (OpenWeatherMap)
- **Service Layer**: Orchestriert die Geschäftslogik
- **Resource Layer**: REST-Controller für HTTP-Endpunkte
- **Transformer Layer**: Konvertiert zwischen Domain- und Entity-Objekten

## 🔧 Entwicklung

### Build

```bash
./mvnw clean package
```

### Tests ausführen

```bash
./mvnw test
```

### Development-Modus

Die Anwendung nutzt Spring Boot DevTools für automatisches Neuladen bei Code-Änderungen.

### Code-Struktur

Die Anwendung folgt einer sauberen Architektur mit klarer Trennung der Verantwortlichkeiten:

1. **REST-Controller** (`WeatherResource`) empfängt HTTP-Requests
2. **Service-Layer** (`WeatherService`) orchestriert den Ablauf
3. **Provider-Layer** kommuniziert mit externen APIs
4. **Transformer-Layer** konvertiert zwischen verschiedenen Objekttypen
5. **Domain-Layer** enthält die Geschäftslogik-Modelle

## 🔒 Sicherheit

- ✅ API-Keys werden nicht in Git hochgeladen (`.gitignore` enthält `application.properties`)
- ✅ Verwenden Sie niemals echte API-Keys in Commits
- ✅ Für Produktionsumgebungen sollten Umgebungsvariablen oder sichere Konfigurationsmanagement-Tools verwendet werden


**Hinweis**: Diese Anwendung benötigt einen gültigen OpenWeatherMap API-Key. Sie können einen kostenlosen API-Key auf [openweathermap.org](https://openweathermap.org/api) erhalten.
