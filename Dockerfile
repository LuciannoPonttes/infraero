# -------------------------- Dockerfile (jboss) -------------------------- #
FROM registry.infraero.gov.br/siga-base:10
MAINTAINER diogorocha@infraero.gov.br

ENV TZ=America/Sao_Paulo

USER root
#--- SET TIMEZONE
RUN sh -c "ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone"

USER jboss
ADD modulos/sigadoc ${JBOSS_HOME}/modules/sigadoc

#--- APLICACÕES WEB (siga) ---
COPY --chown=jboss:nogroup target/siga.war target/sigaex.war ${JBOSS_HOME}/standalone/deployments/
COPY target/siga-ext.jar ${JBOSS_HOME}/modules/sigadoc/ext/main/siga-ext.jar