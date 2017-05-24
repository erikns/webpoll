#!/usr/bin/env bash

# This script is run inside the container when it is started. This is where the
# environment specific configuration is injected.

set -e

echo "ENVIRONMENT CONFIG:"
echo ${!WEBPOLL_*}

# TODO: inject specific configuration here

echo "Running Webpoll..."
catalina.sh run

