package oovies.tools;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import oovies.dal.*;
import oovies.model.*;

/**
 * Lists of methods:
 * Person:
 *    public Person create(Person person)
 *    public Person delete(Person person)
 *    public Person getPersonByUserName(String userName)
 *    public Person getPersonByUserId(int userId)
 *    public Person updatePassword(Person person, String password)
 *    public Person updateEmail(Person person, String email)
 * Director:
 *     public Director create(Director director)
 *     public Director delete(Director director)
 *     public Director getDirectorByDirectorId(int directorId)
 *     public List<Director> getDirectorByDirectorName(String name)
 *
 *
 *
 */

/**
 * main() runner, used for the app demo.
 */
public class Inserter {

	public static void main(String[] args) throws SQLException, ParseException {

		PersonDao personDao = PersonDao.getInstance();
		DirectorDao directorDao = DirectorDao.getInstance();

		// INSERT objects from our model.
		Person person = new Person("user", "password", "email", Person.Role.ADMIN);
		Person person1 = new Person("user1", "password1", "email1", Person.Role.USER);
		personDao.create(person);
		personDao.create(person1);
		Director director = new Director("director", Director.Gender.F);
		Director director1 = new Director("director1", Director.Gender.M);
		Director director2 = new Director("director", Director.Gender.F);
		directorDao.create(director);
		directorDao.create(director1);
		directorDao.create(director2);

		// READ.
		Person person2 = personDao.getPersonByUserName("user");
		System.out.format("Reading person by userName: id:%d name:%s password:%s email:%s role:%s \n",
				person2.getUserId(), person2.getUserName(), person2.getPassword(), person2.getEmail(), person2.getRole().name());
		Person person3 = personDao.getPersonByUserId(person2.getUserId());
		System.out.format("Reading person by userId: id:%d name:%s password:%s email:%s role:%s \n",
				person3.getUserId(), person3.getUserName(), person3.getPassword(), person3.getEmail(), person3.getRole().name());
		List<Director> dList = directorDao.getDirectorByDirectorName("director");
		int directorId = -1;
		for(Director d : dList){
			directorId = d.getDirectorId();
			System.out.format("Looping directors by directorName: id:%d name:%s gender:%s \n",
					d.getDirectorId(), d.getName(), d.getGender().name());

		}
		Director director3 = directorDao.getDirectorByDirectorId(directorId);
		System.out.format("Reading director by directorId: id:%d name:%s gender:%s \n",
				director3.getDirectorId(), director3.getName(), director3.getGender().name());

		// UPDATE.
		System.out.format("Reading person before updating password: id:%d name:%s password:%s email:%s role:%s \n",
				person2.getUserId(), person2.getUserName(), person2.getPassword(), person2.getEmail(), person2.getRole().name());
		Person person31 = personDao.updatePassword(person2, "newPassword");
		System.out.format("Reading person after updating password: id:%d name:%s password:%s email:%s role:%s \n",
				person31.getUserId(), person31.getUserName(), person31.getPassword(), person31.getEmail(), person31.getRole().name());
		Person person4 = personDao.updateEmail(person31, "newEmail");
		System.out.format("Reading person after updating email: id:%d name:%s password:%s email:%s role:%s \n",
				person4.getUserId(), person4.getUserName(), person4.getPassword(), person4.getEmail(), person4.getRole().name());

		// DELETE.

		personDao.delete(person1);
		directorDao.delete(director2);




		
	}
}
