<!--
               _                 _ _ 
              | |               | | |
 __      _____| |__  _ __   ___ | | |
 \ \ /\ / / _ \ '_ \| '_ \ / _ \| | |
  \ V  V /  __/ |_) | |_) | (_) | | |
   \_/\_/ \___|_.__/| .__/ \___/|_|_|
                    | |              
                    |_|              
-->

# webpoll
This is a webapplication with database integration that allows users to create
and take polls, the results of which can be presented in different ways.

## Screenshots
![Placeholder screenshot](https://github.com/erikns/webpoll/blob/master/Screenshot.png)
<!--![Screenshot of poll creation](placeholderlink)-->
<!--![Screenshot of poll taking](placeholderlink)-->

## Building
The project is built with Maven.

Before building the Java code and packaging into web archive, make shure the
configuration is in place.

### Configuration
The runtime configuration is specified in several .xml-files in
`src/main/web/WEB-INF`. 

Most options are deployment agnostic, but the
configuration of the database connection is contained in a template file
`resources.xml.templ` in this directory. This file contains placeholders
suitable for substitution with proper values.

There is a convenience script located in the `scripts/`-directory for
automating this process. This script should be run to make shure the proper
configuration is in place before the source code is built and packaged. The
script expects certain variables to be set before it is run. See example below.

Example:
```bash
JDBC_URL=postgres://test/db USERNAME=test PASSWORD=test ./scripts/dbauth_inject.sh
```

### Building the Java code
To produce a deployable web archive
(.WAR-file), run the following command:

```bash
mvn package
```

