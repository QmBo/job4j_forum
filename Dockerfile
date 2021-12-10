FROM openjdk
WORKDIR forum
ADD target/forum-1.war app.war
ENTRYPOINT java -jar app.war