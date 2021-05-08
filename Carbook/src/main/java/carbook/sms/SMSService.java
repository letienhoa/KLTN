package carbook.sms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Component
public class SMSService {

	@Value("#{systemEnvironment['TWILIO_ACCOUNT_SID']}")
    private String ACCOUNT_SID="ACb15455b5d86f863c19b26a134021428d";

    @Value("#{systemEnvironment['TWILIO_AUTH_TOKEN']}")
    private String AUTH_ID="6e7a42125d0362419b90d58ad707a00a";

    @Value("#{systemEnvironment['TWILIO_PHONE_NUMBER']}")
    private String FROM_NUMBER;
    
    public void send() {
        Twilio.init(ACCOUNT_SID, AUTH_ID);

        Message.creator(new PhoneNumber("0348551924"), new PhoneNumber("0337006601"),
                "Chào chú Đỉnh- message was sent from VietNam").create();

    }
}
