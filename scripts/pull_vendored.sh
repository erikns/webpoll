#!/usr/bin/env bash

set -e

echo "Downloading vendored code..."

[ -d vendor ] || mkdir vendor
curl https://jdbc.postgresql.org/download/postgresql-42.1.1.jre7.jar \
    > vendor/postgres-42.1.1.jre7.jar

