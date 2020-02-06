FROM openjdk:11-jdk-slim
ARG jar
RUN groupadd -g 997 censusfsdrreport && \
    useradd -r -u 997 -g censusfsdrreport censusfsdrreport
USER censusfsdrreport
COPY $jar /opt/censusfsdrreport.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "java",  "-jar", "/opt/censusfsdrreport.jar" ]
