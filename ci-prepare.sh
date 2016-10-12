#!/usr/bin/env bash

# Add build number coming from command line to proper buildNumber file resource in source tree

re='^[0-9]+$'
if ! [[ $1 =~ $re ]] ; then
   echo "error: Not a number" >&2; exit 1
fi

echo "$1" > src/main/web/WEB-INF/buildNumber
exit 0

