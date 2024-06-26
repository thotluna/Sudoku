package views.console;

import java.util.ResourceBundle;

public class MessageRepository {

    private static MessageRepository instance;
    private final ResourceBundle listMessage;

    private MessageRepository() {
        listMessage = ResourceBundle.getBundle("messages");
    }

    public static MessageRepository getInstance(){
        if(instance == null ){
            instance = new MessageRepository();
        }

        return instance;
    }

    public String get(String message){
        return listMessage.getString(message);
    }


}
