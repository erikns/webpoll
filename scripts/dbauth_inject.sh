#!/usr/bin/env bash

# Script to replace JDBC URL and credential placeholders in configuration file.
# Expects JDBC_URL, USERNAME, and PASSWORD variables to be set before it is
# run.
#
# Ex: JDBC_URL=postgres://test/db USERNAME=test PASSWORD=test ./dbauth_inject.sh
#

set -e

DIR=$(dirname $0)
ROOT=$DIR/..

SOURCE_FILE=src/main/web/WEB-INF/resources.xml.templ
FULL_SRC_PATH=$ROOT/$SOURCE_FILE

TARGET_FILE=src/main/web/WEB-INF/resources.xml
FULL_TARGET_PATH=$ROOT/$TARGET_FILE

echo "Replacing placeholders in $SOURCE_FILE"

sed -e 's ${jdbcUrl} '"$JDBC_URL"' g;' \
    -e 's/${userName}/'"$USERNAME"'/g;' \
    -e 's/${password}/'"$PASSWORD"'/g;' \
    $FULL_SRC_PATH > $FULL_TARGET_PATH

echo "Result written to $TARGET_FILE"

