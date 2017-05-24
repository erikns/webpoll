#!/usr/bin/env bash

set -e

BUILD_TAG=${TRAVIS_BUILD_NUMBER:-DEV}

IMAGE_TAG=webpoll:$BUILD_TAG
IMAGE_FILE=webpoll-$BUILD_TAG.tar.gz

echo "Building docker image..."
docker build -t $IMAGE_TAG .
echo "OK"

echo "Generating image artifact..."
docker save $IMAGE_TAG | gzip -v > $IMAGE_FILE
echo "OK"

