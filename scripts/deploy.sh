#!/usr/bin/env bash

echo "Deploying image..."

scp -o StrictHostKeyChecking=no -o UserKnownHotsFile=/dev/null \
    -q -P $DEPLOY_PORT \
    webpoll-$TRAVIS_BUILD_NUMBER.tar.gz \
    travis@$DEPLOY_HOST:~/webpoll-$TRAVIS_BRANCH-$TRAVIS_BUILD_NUMBER.tar.gz
if [ $? -ne 0 ]; then
    echo "Deploy failed!"
    exit 1
fi

echo "OK"
exit 0

