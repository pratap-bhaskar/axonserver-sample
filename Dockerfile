FROM docker.prod.walmart.com/blockchain/tomcat:9.0.9
ADD ./target/demoapp.war /usr/local/tomcat/webapps/
EXPOSE 8080