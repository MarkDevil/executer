FROM davidcaste/alpine-tomcat:tomcat8
MAINTAINER mamingfeng
ADD executer-web/target/executer-web.war /opt/tomcat/webapps/executer.war
EXPOSE 8080
ENTRYPOINT ["/bin/sh", "/opt/tomcat/bin/catalina.sh","run"]