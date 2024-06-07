# custom_certificate_strimzi_kafka


Example showing how to configure custom certificate with strimzi kafka for user AUTH/Z


STRMZI is a very powerful OSS implemention of KAFKA. Which gives an operator lead KAFKA implementaton for K8s.  Since its oprator lead Strimzi uses its own SELFSINGED certificate for internal opearation.  

As a corporate organiztion there might be chance that you will have a CERT provider and you want the KAFKA users (CONSUMER and PUBLISHERS) to use company provided certificate provider signed certs. 

By default APACHE KAFKA gives certificate regex, but that is blocekd in Strimzu. However Strimzi gives a powerful option to override the pricipal builder class which KAFKA uses for CN validation. The example provided here helps you build a custom principal builder and some add on file to package the kafka image and how to use that in your implementation. 

a. Make sure the provided source code is built and class created
b. Class created is passed on to dockerfile  .Make sure you run the docker file provided to add the customClass to the KAFKA image 

c. Make sure the KAFKA manifest is updated  to add 
principal.builder.class: CustomCNPrincipalBuilder

