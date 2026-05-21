package communication;
public class ChatSession extends Thread {
 private CommunicationImpl comm;
 private String messages[];
 public ChatSession(CommunicationImpl comm,String messeges[])
 {
    this.comm=comm;
     this.messages=messages;
 }
public void run() {
    for(String msg:messages) 
   {
      comm.sendMessage(msg);
         try { Thread.sleep(500); }
         catch(InterruptedException e) {
                e.printStackTrace();
           }
   }
}
}