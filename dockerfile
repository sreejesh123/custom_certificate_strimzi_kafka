#this dockerfile build the KAFKA community image with the custom Library which support Organization specific Signed Certigicate

  FROM artifactory.sdlc.ctl.gcp.db.com/dkr-quay/strimzi/kafka:0.36.1-kafka-3.5.1 
    
  COPY ./customClass/CustomCMPrincipalBuilder.class /opt/kafka/libs/CustomCMPrincipalBuilder.class 
  
  ENV LD_LIBRARY_PATH=/opt/kafka/libs:$LD_LIBRARY_PATH 
  ENV CLASSPATH=/opt/kafka/libs:$CLASSPATH
