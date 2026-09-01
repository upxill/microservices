# Dev-Play K8s Microservice

A minimal Spring Boot REST service built as a hands-on exercise in containerizing and deploying a Java microservice to Kubernetes. It exists as a clean, working reference for the Docker build → image → Deployment pipeline, not as a feature-complete application.

## Tech Stack

- Java (Spring Boot, Gradle build)
- Docker (`openjdk:8-jdk-alpine` runtime image)
- Kubernetes (Deployment manifest, 2 replicas)

## How it works

The app is a single Spring Boot service (`DevPlayK8sApplication`) exposing one REST endpoint:

```
GET /hello  ->  "working on k8s..."
```

It's packaged into a Docker image via a two-line Dockerfile (`Dockerfile` copies the built jar and runs it), and `deployment.yaml` defines a Kubernetes `Deployment` with 2 replicas listening on container port 8080, pulling the image `docker.io/polagani/microservices:dev-play-k8s`.

There's no persistence layer, no additional business endpoints, and no CI pipeline in this repo — it's intentionally scoped to demonstrate the build-and-deploy mechanics.

## Getting Started

**Run locally:**

```bash
./gradlew bootRun
curl http://localhost:8080/hello
```

**Build and run the container:**

```bash
./gradlew build
docker build -t microservices:dev-play-k8s .
docker run -p 8080:8080 microservices:dev-play-k8s
```

**Deploy to Kubernetes:**

```bash
kubectl apply -f deployment.yaml
```

Note: `deployment.yaml` does not include a matching `Service` manifest — add a `ClusterIP`/`NodePort` Service to expose the Deployment inside or outside the cluster.

## Project Structure

```
├── Dockerfile                                    # Builds a runtime image from the packaged jar
├── deployment.yaml                               # Kubernetes Deployment (2 replicas, port 8080)
├── build.gradle / settings.gradle / gradlew      # Gradle build tooling
└── src/
    ├── main/java/com/dev/play/
    │   ├── DevPlayK8sApplication.java            # Spring Boot entry point
    │   └── DevPlayK8sController.java             # Single GET /hello endpoint
    ├── main/resources/application.properties
    └── test/java/com/dev/play/DevPlayK8sApplicationTests.java  # Context-load smoke test
```
