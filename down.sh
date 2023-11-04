#!/bin/sh

# Exits immediately if a command exits with a non-zero status
set -e

docker-compose -f "db/cars/docker-compose.yml" down;