# Building webpoll

The build process is primarily driven by Maven. Maven manages most compile- and
runtime dependencies.

## Using Maven

### Configuration
The runtime configuration is specified in several .xml-files in
`src/main/web/WEB-INF`. 

Most options are deployment agnostic, but the
configuration of the database connection is contained in a template file
`resources.xml.templ` in this directory. This file contains placeholders
suitable for substitution with proper values.

There is a convenience script located in the `scripts/`-directory for
automating this process. This script should be run to make sure the proper
configuration is in place before the source code is built and packaged. The
script expects certain variables to be set before it is run. See example below.

Example:
```bash
JDBC_URL=postgres://test/db \
    JDBC_USERNAME=test \
    JDBC_PASSWORD=test \
    ./scripts/dbauth_inject.sh
```

### Building
After the configuration is injected, a .WAR-file can be produced by running: 
`mvn package`.

Note that the project is written for running inside a TomEE container.
Furthermore, it depends on the JDBC-driver for PostgreSQL being present in the
container classpath (i.e. in its `lib/` directory). The required .jar-file can
be downloaded by running `scripts/pull_vendored.sh`.

## Using Docker

Using Docker simplifies the build process, and the image produced is versatile,
as the configuration is provided when the image is run.

### Building
The Docker process uses the same .WAR-file as the plain Maven route. It is
produced the same way: `mvn package`.

To build the image run: `scripts/dockerize.sh`. If you want to generate a
compressed image file, add the `--imagefile`-flag to the above command.

The resulting image accepts environment variables when run with docker.
Example:

```bash
docker run --rm -it \
    -e WEBPOLL_JDBC_URL=jdbc:postgresql://localhost/db \
    -e WEBPOLL_USERNAME=user \
    -e WEBPOLL_PASSWORD=password \
    -p 8080:8080 \
    webpoll:DEV
```

### Running a development build

There is a convenience script in `scripts/`-directory called `serve_dev.sh`. It
sets up a development runtime environment including a postgres database, and
runs the application in the console. It even exposes a JVM debug port `15005`.

