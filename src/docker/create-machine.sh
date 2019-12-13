#!/usr/bin/env bash

NAME=keycloak-issue
CPU_COUNT="2"
MEMORY_MB="2048"

docker-machine create \
  --driver virtualbox \
  --virtualbox-cpu-count ${CPU_COUNT} \
  --virtualbox-memory ${MEMORY_MB} \
  --virtualbox-ui-type "headless" ${NAME} || echo "Machine created."
