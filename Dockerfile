FROM openjdk:11-jdk-slim-bullseye

ADD ./build/install/hello_ktor /opt/app/hello_ktor

WORKDIR /opt/app/hello_ktor/bin

CMD [ "./hello_ktor" ]
