import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.message.DefaultPrincipalData; 
import org.apache.kafka.common.protocol.ByteBufferAccessor;
import org.apache.kafka.common.protocol.MessageUtil; 
import org.apache.kafka.common.security.auth.KafkaPrincipal; 
import javax.net.ssl.SSLPeerUnverifiedException; 
import javax.net.ssl.SSLSession;
import javax.security.auth.x500.X500Principal; 
import java.nio.ByteBuffer; 
import java.security.Principal; 

 public class CustomCMPrincipalBuilder implements KafkaPrincipalBuilder {
   @Override 
   public KafkaPrincipal build(AuthenticationContext authenticationContext) 
   try { 
     if (authenticationContext instanceof PlaintextAuthenticationContext)
      return KafkaPrincipal.ANONYMOUS;       
     else if (authenticationContext instanceof SslAuthenticationContext) { 
       SSLSession sslSession = ((SslAuthenticationContext) authenticationContext).session(); 
       try { 
         return applySslPrincipalMapper(sslSesson.getPeerPrincipal());
       } catch (SSLPeerUnverifiedException se) {
         return kafkaPrincipal.ANONYMOUS;
       }
     } else {
       throw new IllegalArgumentException("Unhandled authentication context type: " + authenticationContext.getClass().getName());
     }
   } catch (Exception ex) {
     throe new KafkaException("Failed to build CommonNamePrincipal due to failed In Custom Class", ex);
 }

@override
  private KafkaPrincipal applySslPrincipalMapper(Principal principal) {
    String splitSubject;
try {
  if(!(principal instanceof X500Principal) || principal == kafkaPrincipal.ANONYUMOUS)
     { return new KafkaPrincipal(KafkaPrincipal.USER_TYPE, principal.getName()); } 
  else
  {
     if (principal.getName().contains(",") && principal.getName().contains("O=MYORGANIZATION NAME"))   //NOTE my ORGANIZATION GIVE the CN as CN=name,o=org,o=somthing, hence logic to understadn and split it. Please consider to recode as per your NEED.
       { 
      	splitSubject = principal.getName().split(","); // Original CN=kafka-ger.gateway-service.aws,o=blah,dc=ooh,dc=com  
      	System.out.println(splitSubject[0]); 
      	splitSubject = splitSubject[0].split(":"); 
      	System.out.println(splitSubject[0]); 
      	return new KafkaPrincipal(KafkaPrincipal.USER_TYPE, splitSubject[0]);  //converted to CN=kafka-ger.gateway-service.aws (whcih you will add to kind: user )
    }
    else {
      return new KafkaPrincipal(KafkaPrincipal.USER_TYPE,principal.getName());
    }
  } 
} catch (Exception E)
  {
    throw new kafkaException("Failed to map name for'" +principal.getName() + "'based on ssl principal mapping.",e);
  }
 
}

@override
 public KafkaPrincipal deserialize(byte[] bytes) {
  ByteBuffer buffer = ByteBuffer.wrap(bytes); 
short version = buffer.getShort();
 if (version < DefaultPrincipalData.LOWEST_SUPPORTED_VERSION || version > DefaultPrincipalData.HIGHEST_SUPPORTED_VERSION) 
 { 
	throw new SerializationException("Invalid principal data version " + version); 
 }
 
 DefaultPrincipalData data = new DefaultPrincipalData(new ByteBufferAccessor(buffer), version); 
 return new KafkaPrincipal(data.type(), data.name(), data.tokenAuthenticated());
}
}
