# scala-akka-skeleton
A service setup and definition stub, complete with database, akka http setup, and some stubbed out cake patterns.
The idea is that this repo serves as a base for service development and lets one skip all the boring setup of akka-scala service setup.

Caveat here is that some dependencies, plugins and workflows are specific to moi, or how I choose to develop.

## Development

### Local development packages
Install these packages on your machine to do development

- sbt
- Docker
- Docker compose

### Dependencies
How dependencies are managed / used for development

#### Clean up Dependencies
> docker-compose -f docker-compose.deps.yml rm

> docker-compose -f docker-compose.deps.yml rm db

#### Build Dependencies
> docker-compose -f docker-compose.deps.yml build

> docker-compose -f docker-compose.deps.yml build db

#### Launch Dependencies
> docker-compose -f docker-compose.deps.yml up

> docker-compose -f docker-compose.deps.yml up db

### Running locally
These are instructions for running the dependencies and the app locally

#### With Docker

> docker build -t app:latest .

> docker run -d -p 3000:3000 app

#### With Scala

> docker-compose -f docker-compose.deps.yml rm

> docker-compose -f docker-compose.deps.yml build

> docker-compose -f docker-compose.deps.yml up

> sbt

> ~re-start
