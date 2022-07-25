#!/bin/bash

# Script used to build images and create containers

# Creating packages
current_dir=$(pwd)
cd $current_dir/account-service && mvn clean package -DskipTests
cd $current_dir/order-service && mvn clean package -DskipTests
cd $current_dir/api-gateway && mvn clean package -DskipTests
cd $current_dir/eureka-server && mvn clean package -DskipTests

# Building images
cd $current_dir/account-service && docker build -t account-service:current .
cd $current_dir/order-service && docker build -t order-service:current .
cd $current_dir/api-gateway && docker build -t api-gateway:current .
cd $current_dir/eureka-server && docker build -t eureka-server:current .

# Creating containers
cd $current_dir
docker-compose up -d

