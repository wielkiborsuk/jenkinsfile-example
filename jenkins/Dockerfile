FROM jenkins/jenkins:lts
# if we want to install via apt
USER root
RUN apt-get update && apt-get install -y maven
# drop back to the regular jenkins user - good practice
USER jenkins

COPY --chown=jenkins:jenkins master.key /var/jenkins_home/secrets/master.key
COPY --chown=jenkins:jenkins secret.key /var/jenkins_home/secret.key
COPY --chown=jenkins:jenkins credentials.xml /var/jenkins_home/credentials.xml
COPY --chown=jenkins:jenkins config.xml /var/jenkins_home/jobs/pipeline-test/config.xml

