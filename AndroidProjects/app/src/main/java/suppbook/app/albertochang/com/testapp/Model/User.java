package suppbook.app.albertochang.com.testapp.Model;

public class User {
    private String email;
    private String user_id;

    public User(){

    }

    public User (String email, String user_id){
        this.setEmail(email);
        this.setUser_id(user_id);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
