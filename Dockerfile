FROM maven:3.5-jdk-8-alpine
WORKDIR /usr/src/Agents_i2a
COPY . /usr/src/Agents_i2a/
RUN mvn package -Dmaven.test.skip=true
EXPOSE 8080
CMD ["java", "-jar", "jar/agents_i2a-0.0.1.jar"]