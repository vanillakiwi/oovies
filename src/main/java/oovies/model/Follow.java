package oovies.model;

public class Follow {
	protected int followId;
	protected Person user;
	protected Actor actor;
	
	public Follow(int followId, Person user, Actor actor) {
		this.followId = followId;
		this.user = user;
		this.actor = actor;
	}

	public Follow(Person user, Actor actor) {
		this.user = user;
		this.actor = actor;
	}

	public Follow(int followId) {
		this.followId = followId;
	}

	
	
	/** Getters and Setters **/
	
	public int getFollowId() {
		return followId;
	}

	public void setFollowId(int followId) {
		this.followId = followId;
	}

	public Person getUser() {
		return user;
	}

	public void setUser(Person user) {
		this.user = user;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}
	
}