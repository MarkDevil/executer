FROM tomcat
MAINTAINER mark

ENV CATALINA_HOME /usr/local/tomcat
ENV PATH $CATALINA_HOME/bin:$PATH
COPY ./executer-web.war $CATALINA_HOME/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]