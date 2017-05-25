#!/usr/bin/env bash

set -e

echo "Deploying image..."
scp -P $DEPLOY_PORT \
    webpoll-$TRAVIS_BUILD_NUMBER.tar.gz \
    travis@$DEPLOY_HOST:~/webpoll-$TRAVIS_BRANCH-$TRAVIS_BUILD_NUMBER.tar.gz
echo "OK"

