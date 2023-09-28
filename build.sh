#!/bin/bash
docker build -f Dockerfile -t niblapi .
docker stop niblapi
docker rm niblapi
docker run -d --net='my-bridge' -v '/opt/niblapi/logs':'/logs':'rw' --name=niblapi niblapi
