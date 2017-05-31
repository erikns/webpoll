#!/usr/bin/env bash

echo "Verifying dependencies..."
command -v docker >/dev/null 2>&1 || { echo >&2 "Docker required!"; exit 1; }
command -v psql >/dev/null 2>&1 || { \ 
    echo >&2 "postgresql cli required!"; exit 1; }

if [ $1 = "--clean" ]; then
    echo "Cleaning up..."
    docker stop webpoll-db &>/dev/null
    docker rm webpoll-db
    exit 0
fi

echo "Dockerizing..."
scripts/dockerize.sh

DB_RUNNING=$(docker inspect -f {{.State.Running}} webpoll-db)
if [ $? -ne 0 ] || [ "$DB_RUNNING" = "false" ]; then
    if [ "$DB_RUNNING" = "false" ]; then
        echo "Starting existing container..."
        docker start webpoll-db
    else
        echo "Starting database server..."
        docker run --name webpoll-db -e POSTGRES_PASSWORD=postgres \
            -d -p 5434:5432 postgres
        echo "Waiting for database server..."
        sleep 5
    fi
fi

echo "Preparing database..."
PGPASSWORD=postgres psql -h localhost -p 5434 -q -U postgres postgres \
    -f sql/webpoll_schema.sql
PGPASSWORD=postgres psql -h localhost -p 5434 -q -U postgres postgres \
    -f sql/webpoll_seed.sql

if [ -t 1 ]; then
    INTERACTIVE_FLAG=-it
else
    INTERACTIVE_FLAG=-t
fi

echo "Running webpoll..."
docker run --rm ${INTERACTIVE_FLAG} \
    --link webpoll-db:db \
    -e WEBPOLL_JDBC_URL=jdbc:postgresql://db/postgres \
    -e WEBPOLL_USERNAME=postgres \
    -e WEBPOLL_PASSWORD=postgres \
    -e WEBPOLL_DEBUG=1 \
    -p 8080:8080 \
    -p 15005:8000 \
    webpoll:DEV

