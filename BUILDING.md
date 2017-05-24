# Building webpoll

The project is built with Maven.

Before building the Java code and packaging into web archive, make sure the
configuration is in place.

## Dependencies
The software is written to work with PostgreSQL as the database backend. As
such, the database is required to be running for the application to function.

## Configuration
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
JDBC_URL=postgres://test/db JDBC_USERNAME=test JDBC_PASSWORD=test ./scripts/dbauth_inject.sh
```

## Building the Java code
To produce a deployable web archive (.WAR-file), run the following
command:

```bash
mvn package
```

