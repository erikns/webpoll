#!/usr/bin/env bash

set -e

echo "Injecting build number into resource path..."
echo ${TRAVIS_BUILD_NUMBER} > src/main/web/WEB-INF/buildNumber

echo "Injecting revision number into resource path..."
echo ${TRAVIS_COMMIT} > src/main/web/WEB-INF/revision

