#!/usr/bin/env bash

NAME=keycloak-issue
CPU_COUNT="2"
MEMORY_MB="2048"

docker-machine create \
  --driver virtualbox \
  --virtualbox-hostonly-cidr "192.168.56.1/24" \
  --virtualbox-cpu-count ${CPU_COUNT} \
  --virtualbox-memory ${MEMORY_MB} \
  --virtualbox-ui-type "headless" ${NAME} || echo "Machine created."
