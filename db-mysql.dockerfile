FROM mysql:5.6
LABEL maintainer="Matheus Silva Ferreira"
ENV MYSQL_ALLOW_EMPTY_PASSWORD="yes"
ENV MYSQL_ROOT_PASSWORD=""
COPY ./db/ /docker-entrypoint-initdb.d/
EXPOSE 3306