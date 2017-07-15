#!/usr/bin/env bash

# This script is run inside the container when it is started. This is where the
# environment specific configuration is injected.

set -e

echo "ENVIRONMENT CONFIG:"
echo ${!WEBPOLL_*}

SOURCE_CONFIG=webapps/ROOT/WEB-INF/resources.xml.templ
TARGET_CONFIG=webapps/ROOT/WEB-INF/resources.xml

echo "Configuring Webpoll..."
sed -e 's ${jdbcUrl} '"$WEBPOLL_JDBC_URL"' g;' \
    -e 's/${userName}/'"$WEBPOLL_USERNAME"'/g;' \
    -e 's/${password}/'"$WEBPOLL_PASSWORD"'/g;' \
    $SOURCE_CONFIG > $TARGET_CONFIG
echo "Final configuration written to: $TARGET_CONFIG"

if [ "$WEBPOLL_DEBUG" -eq 1 ]; then
    OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000"
    echo "DEBUG mode!"
    echo 'JAVA_OPTS="$JAVA_OPTS '"$OPTS"'"' > bin/setenv.sh
    chmod +x bin/setenv.sh
fi

echo "Running Webpoll..."
exec catalina.sh run

