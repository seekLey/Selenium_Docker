#!/bin/bash

# Build Docker image
docker build -t mastertest .

# Run Docker container and get the container ID
container_id=$(docker run -d mastertest)

# Wait for the container to finish execution (assuming your tests are quick)
docker wait "$container_id"

# Copy the surefire-reports from the running container to the local folder
docker cp "$container_id:/usr/src/app/target/surefire-reports" "$(pwd)/cucumber-reports"

# Stop and remove the container
docker stop "$container_id"
docker rm "$container_id"
