# hello_ktor

This is a demo app to use Kolin, Gradle, Exposed.

## How to run

```bash
./gradlew installDist
docker build -t hello_ktor .
docker run --rm -p 8080:8080 hello_ktor
```
