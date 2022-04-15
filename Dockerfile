FROM openjdk:17-ea-3-jdk
EXPOSE 8080
ADD /build/libs/forum-0.0.1-SNAPSHOT.jar forum.jar

ENTRYPOINT ["java","-jar", "forum.jar"]