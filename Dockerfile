# -------------------------- Dockerfile (jboss) -------------------------- #
FROM registry.infraero.gov.br/siga-base:10
MAINTAINER diogorocha@infraero.gov.br

#--- APLICACÕES WEB (siga) ---
COPY target/siga.war target/sigaex.war ${JBOSS_HOME}/standalone/deployments/
#COPY target/siga-ext.jar /opt/jboss-eap-6.2/modules/sigadoc/ext/main/siga-ext.jar
