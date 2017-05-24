#!/usr/bin/env bash

# This script creates a Docker image of the solution. Use the --imagefile flag
# to also generate a .tar.gz image for transfer.

set -e

BUILD_TAG=${TRAVIS_BUILD_NUMBER:-DEV}

IMAGE_TAG=webpoll:$BUILD_TAG
IMAGE_FILE=webpoll-$BUILD_TAG.tar.gz

echo "Building docker image..."
docker build -t $IMAGE_TAG .
echo "OK"

if [ "$1" = "--imagefile" ]; then
    echo "Generating image artifact..."
    docker save $IMAGE_TAG | gzip > $IMAGE_FILE
    echo "OK"
fi

