package fatsby.manager;

public class Staff extends User {
    private String invCode;

    public Staff() {
    }

    public Staff(String username, String password, String gender, String invCode) {
        super(username, password, gender);
        this.invCode = invCode;
    }


    public String getInvCode() {
        return invCode;
    }

    public void setInvCode(String invCode) {
        this.invCode = invCode;
    }
}
