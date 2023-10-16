FROM tomcat:latest
LABEL maintainer="jovian@finderbar.com"
VOLUME /tmp
ADD /target/*.war /usr/local/tomcat/webapps/
ADD /webportal /usr/local/tomcat/webapps/ROOT
EXPOSE 8080
CMD ["catalina.sh", "run"]
