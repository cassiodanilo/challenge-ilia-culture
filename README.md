<br />
<p align="center">
  <a href="https://github.com/cassiodanilo/challenge-ilia-culture">
    <img src="https://www.nicepng.com/png/full/273-2730429_paper-vector-png-pencil-and-paper-free.png" alt="Logo" width="80" height="80">
  </a>
  <h3 align="center">challenge-ilia-culture</h3>
  <p align="center">
    <br />
    <a href="https://challenge-ilia-culture.herokuapp.com/swagger-ui/">View Demo (Swagger)</a>
  </p>
</p>

## Table of Contents
* [About the Project](#about-the-project)
  * [Built With](#built-with)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Installation And Usage](#installation-and-usage)
  * [Docker](#docker)
* [Troubleshooting](#troubleshooting)

## About The Project

This project was developed for a technical evaluation

### Built With
* [Springboot](https://spring.io/projects/spring-boot)
* [HSQLDB](http://hsqldb.org/)
* [OAuth2](https://oauth.net/2/)
* [Swagger](https://swagger.io/)

## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites

This is an example of how to list things you need to use the software and how to install them.
* Java 8+
* [Docker](https://docs.docker.com/get-docker/)

### Installation And Usage

1. Clone the repo
```sh
git clone https://github.com/cassiodanilo/challenge-ilia-culture.git
cd challenge-ilia-culture
```
2. Build
```sh
./gradle build
```
3. Run
```sh
./gradle bootRun
```
4. Open
```
http://localhost:8080/swagger-ui/
```

### Docker
From your application’s root directory
1. Build docker image
```sh
docker build -t <DOCKER_TAG> -f docker/Dockerfile .
```
2. Run docker
```
docker run --publish 8080:8080 --detach --name <CONTAINER_NAME> <DOCKER_IMAGE>:<DOCKER_TAG>
```
3. Open
```
http://localhost:8080/swagger-ui/
```

## Troubleshooting

Because google api was used for authentication, google account is required to access swagger-ui
