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

echo "Running Webpoll..."
catalina.sh run

