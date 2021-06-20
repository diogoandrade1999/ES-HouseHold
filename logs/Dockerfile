FROM bitnami/logstash:latest

COPY . ./logs
ENV LOGSTASH_CONF_FILENAME=./logs/logstash.conf
EXPOSE 51050
