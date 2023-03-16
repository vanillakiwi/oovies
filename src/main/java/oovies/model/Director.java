package oovies.model;

public class Director{
    protected int directorId;
    protected String name;
    protected Gender gender;

    public enum Gender{
        M, F
    }

    public Director(int directorId, String name, Gender gender) {
        this.directorId = directorId;
        this.name = name;
        this.gender = gender;
    }

    public Director(String name) {
        this.name = name;
    }

    public Director(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    @Override
	public String toString() {
		return "Director [directorId=" + directorId + ", name=" + name + ", gender=" + gender + "]";
	}
}