# Intro
The NIBL API will be used to view XDCC bots and search for pack listings

This is written in Spring Boot and uses a MySQL database.

Documentation on RESTful calls is handled in Swagger

#### Swagger URL
http://{Server IP}/swagger-ui.html

# Setup

## Required Software
Docker

## Database
Uses the same database from ircBot

## Run App
```
sudo docker build -f Dockerfile -t niblapi . \
&& \
sudo docker run -d \
--net='my-bridge' \
--name=niblapi \
-v /opt/niblapi/logs/:'/logs':'rw' \
niblapi
```