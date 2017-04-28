# Pull base image here instead if you setup publishing your base image
FROM centos:centos7

# Add resources
ADD core/src/main/resources /deploy/resources/

# Add and pack source code
ADD . /tmp/source/app/
WORKDIR /tmp/source/app
RUN sbt core/pack

# Create app contents area
RUN mkdir -p /deploy/contents
RUN cp -R core/target/pack/ /deploy/contents

WORKDIR /deploy

# Start the app
CMD ["/deploy/contents/pack/bin/application"]
