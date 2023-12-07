FROM openjdk:11
EXPOSE 8082
ADD target/EcomerceApp-0.0.1-SNAPSHOT.jar EcomerceApp-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java","-jar","/EcomerceApp-0.0.1-SNAPSHOT.jar" ]