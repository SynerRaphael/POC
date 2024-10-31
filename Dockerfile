FROM amazoncorretto:22.0.1-al2023-headless

RUN mkdir /app
WORKDIR /app

COPY *.jar /app
RUN mv *.jar app.jar
CMD ["java","-jar","app.jar"]