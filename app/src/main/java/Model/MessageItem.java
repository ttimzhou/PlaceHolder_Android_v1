package Model;

public class MessageItem {
    private String time;
    private String message;
    private String user;

    public MessageItem(String time, String message, String user) {
        this.time = time;
        this.message = message;
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
