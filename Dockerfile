FROM openjdk:15-buster

RUN addgroup --system rdf4j ; \
    adduser --system rdf4j --ingroup rdf4j

RUN wget https://download-gcdn.ej-technologies.com/jprofiler/jprofiler_linux_13_0_1.tar.gz -P /tmp/
RUN tar -xzf /tmp/jprofiler_linux_13_0_1.tar.gz -C /usr/local/
RUN rm /tmp/jprofiler_linux_13_0_1.tar.gz

EXPOSE 8849

RUN mkdir /data
RUN chown rdf4j:rdf4j /data

USER rdf4j:rdf4j

ADD target/rdf4j-issue-3806-1.0.0-SNAPSHOT-jar-with-dependencies.jar /rdf4j-issue-3806-1.0.0-SNAPSHOT-jar-with-dependencies.jar
ADD eclipse-rdf4j-4.0.0-SNAPSHOT-sdk/eclipse-rdf4j-4.0.0-SNAPSHOT/lib/ /jars/
ENTRYPOINT ["java", "-cp", "/jars/*:/rdf4j-issue-3806-1.0.0-SNAPSHOT-jar-with-dependencies.jar", "com.dalstonsemantics.rdf4j.NativeStoreAddClear"]