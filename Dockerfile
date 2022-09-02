# -------------------------- Dockerfile (jboss) -------------------------- #
FROM registry.infraero.gov.br/siga-base:10
MAINTAINER diogorocha@infraero.gov.br

ENV TZ=America/Sao_Paulo
ENV LANG pt_BR.UTF-8
ENV LANGUAGE pt_BR.UTF-8
ENV LC_ALL pt_BR.UTF-8

USER root
#--- SET TIMEZONE
RUN sh -c "ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone"

USER jboss
ADD modulos/sigadoc ${JBOSS_HOME}/modules/sigadoc
ADD modulos/com ${JBOSS_HOME}/modules/com

#--- APLICACÕES WEB (siga) ---
COPY --chown=jboss:nogroup target/siga.war target/sigaex.war ${JBOSS_HOME}/standalone/deployments/
RUN rm -f /home/jboss/jboss-eap-7.2/standalone/deployments/siga-le.war /home/jboss/jboss-eap-7.2/standalone/deployments/sigawf.war
COPY target/siga-ext.jar ${JBOSS_HOME}/modules/sigadoc/ext/main/siga-ext.jar