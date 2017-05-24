FROM tomee

# Remove standard webapps from container
RUN echo "Cleaning up..." && rm -r /usr/local/tomee/webapps/*

# Add the warfile
ADD target/webpoll-0.1-SNAPSHOT.war /usr/local/tomee/webapps/ROOT.war
# Add specific driver dependency
ADD vendor/postgres-42.1.1.jre7.jar /usr/local/tomee/lib/postgres-42.1.1.jre7.jar

RUN echo "Installing application..." \
    && cd webapps \
    && mkdir ROOT \
    && cd ROOT \
    && unzip ../ROOT.war \
    && cd .. \
    && rm ROOT.war

# Add custom startup script
ADD container/run_webpoll.sh /usr/local/tomee/run_webpoll.sh

CMD ["./run_webpoll.sh"]

