package oovies.model;

public class Person{
    protected int userId;
    protected String userName;
    protected String password;
    protected String email;
    protected Role role;

    public enum Role{
        ADMIN, USER
    }

    public Person(int userId, String userName, String password, String email, Role role) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Person(int userId, String password, String email, Role role) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Person(String userName, String password, String email, Role role) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

	@Override
	public String toString() {
		return "Person [userId=" + userId + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", role=" + role + "]";
	}
}