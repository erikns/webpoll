#!/usr/bin/env bash

echo "Deploying image..."

if [ "$TRAVIS_PULL_REQUEST" = "false" ]; then
    PR=""
else
    PR="-PR$TRAVIS_PULL_REQUEST"
fi

scp -o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null \
    -q -P $DEPLOY_PORT \
    webpoll-$TRAVIS_BUILD_NUMBER.tar.gz \
    travis@$DEPLOY_HOST:~/webpoll-$TRAVIS_BRANCH$PR-$TRAVIS_BUILD_NUMBER.tar.gz
if [ $? -ne 0 ]; then
    echo "Deploy failed!"
    exit 1
fi

echo "OK"
exit 0

