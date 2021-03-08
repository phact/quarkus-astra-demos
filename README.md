# quarkus-astra-demo

Astra is DataStax's serverless cassandra offering on the cloud. To use this project clone the repo, create a serverless database at https://astra.datastax.com, download your secure connect bundle from the connect screen, and create a token for your app to use in the settings screen.

Create and populate your application.properties file:

    cp  ./src/main/resources/application.properties.template ./src/main/resources/application.properties

Replace the following lines to point at your scb and with your token:

    quarkus.cassandra.cloud.secure-connect-bundle=<</PATH_TO_SCB.ZIP>>
    quarkus.cassandra.auth.password=AstraCS:<<REPLACE_ME:REPLACE_ME>>

If you preffer to stub out the code yourself, pull the `puzzle` branch instead and give it a try. This `main` branch has the required endpoints stubbed out.

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `quarkus-run.jar` file in the `/target/quarkus-app` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.
 
  > Make sure you have a container runtime (docker/podman/etc) installed

You can then execute your native executable with: `./target/quarkus-astra-demo-0.01-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.

## Running on Red Hat Developer Sandbox
Run this application on the free [Red Hat Developer Sandbox](https://developers.redhat.com/developer-sandbox). All you need to do is create an account and then [click here](https://workspaces.openshift.com/f?url=https://github.com/phact/quarkus-astra-demos/tree/todo) to load the workspace!
