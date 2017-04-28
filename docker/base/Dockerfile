# Pull base image.
FROM centos:centos7

# Install
RUN yum -y update; yum clean all
RUN yum -y install epel-release; yum clean all

# Install sbt
RUN curl https://bintray.com/sbt/rpm/rpm | tee /etc/yum.repos.d/bintray-sbt-rpm.repo
RUN yum -y install sbt; yum clean all

# Install java
RUN yum -y install java-1.8.0-openjdk-devel; yum clean all
ENV JAVA_HOME=/usr/lib/jvm/jre
RUN yum -y update; yum clean all

# Add project to cache
ADD . /tmp/project-cache
WORKDIR /tmp/project-cache

# Load up
RUN sbt compile
RUN sbt pack

# Clean up
WORKDIR /
RUN rm -rf /tmp/project-cache/project
