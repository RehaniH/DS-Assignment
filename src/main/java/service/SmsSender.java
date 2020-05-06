package service;
// Install the Java helper library from twilio.com/docs/libraries/java
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

//SMS sender class

public class SmsSender {
    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID =
            "AC4a8b0fa10c8ae4f70a6b0c74040b56b3";
    public static final String AUTH_TOKEN =
            "07add7b4a46b9378b1a876567734b078";

    public SmsSender(String to, String from) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber(to), // to
                        new PhoneNumber(from), // from
                        "Alert: One or more of the Overnight Coders® sensors have been triggered")
                .create();
    }
}
