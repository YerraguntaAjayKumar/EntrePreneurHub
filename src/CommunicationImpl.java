package communication;
import models.User;
public class CommunicationImpl implements Communication {
    private final User u;
    public CommunicationImpl(User u) {
        this.u=u;
    }
    public void startCall() {
        System.out.println(u.toString()+" started a call----");
    }

    public void sendMessage(String m) {
        System.out.println(u.toString()+"-"+m);
    }
}