package organisation.user;

public class Administrator implements User {
    private final String username = "admin";
    private final String password = "admin";


    public int login(String name, String password) {
        if (this.username.equals(name) && this.password.equals(password)) {
            return 1;
        } else {
            return 0;
        }
    }


}
