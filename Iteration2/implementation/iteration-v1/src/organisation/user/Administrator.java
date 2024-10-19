package organisation.user;

public class Administrator implements User {
    private final String name = "admin";
    private final String phone = "1234567890";
    private final String password = "admin";


    public int login(String name, String password) {
        if (this.name.equals(name) && this.password.equals(password)) {
            return 1;
        } else {
            return 0;
        }
    }


}
