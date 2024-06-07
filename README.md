# custom_certificate_strimzi_kafka
Example showing how to configure custom certificate with strimzi kafka for user AUTH/Z


a. Make sure the provided source code is built and class created
b. Class created is passed on to dockerfile  .Make sure you run the docker file provided to add the customClass to the KAFKA image 

c. Make sure the KAFKA manifest is updated  to add 
principal.builder.class: CustomCNPrincipalBuilder

