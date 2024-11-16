package organisation.user;

public class UnderageClient extends Client{
    private Client guardian;
    public UnderageClient(String username, String password,Client guardian) {
        super(username, password);
        this.guardian=guardian;
    }
    public UnderageClient(int id, String username, String password,Client guardian) {
        super(id,username, password);
        this.guardian=guardian;
    }
}