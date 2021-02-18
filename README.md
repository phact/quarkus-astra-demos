# quarkus-astra-demo

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
