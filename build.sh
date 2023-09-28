#!/bin/bash
docker build -f Dockerfile -t devniblapi .
docker stop devniblapi
docker rm devniblapi
docker run -d --net='my-bridge' -v '/opt/dev.niblapi/logs':'/logs':'rw' --name=devniblapi devniblapi
