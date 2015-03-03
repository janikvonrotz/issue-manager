package ch.hslu.issueman;

import java.util.List;

public class PersonController implements DAO<Person, Integer>{
	
	private static PersonDAO personDao;
	
	public PersonController() {
		personDao = new PersonDAO();
	}
	
	public PersonDAO personDao() {
		return personDao;
	}
	
	@Override
	public void persist(Person person) {
		personDao.openCurrentSessionwithTransaction();
		personDao.persist(person);
		personDao.closeCurrentSessionwithTransaction();
	}

	@Override
	public Person getById(Integer id) {
		personDao.openCurrentSession();
		Person person = personDao.getById(id);
		personDao.closeCurrentSession();
		return person;
	}

	@Override
	public List<Person> getAll() {
		personDao.openCurrentSession();
		List<Person> people = personDao.getAll();
		personDao.closeCurrentSession();
		return people;
	}

	@Override
	public void update(Person person) {
		personDao.openCurrentSessionwithTransaction();
		personDao.update(person);
		personDao.closeCurrentSessionwithTransaction();
	}

	public void deleteById(Integer id) {
		personDao.openCurrentSessionwithTransaction();
		personDao.delete(personDao.getById(id));
		personDao.closeCurrentSessionwithTransaction();
	}

	@Override
	public void delete(Person person) {
		personDao.openCurrentSessionwithTransaction();
		personDao.delete(person);
		personDao.closeCurrentSessionwithTransaction();
	}
	
	public void deleteAll() {
		personDao.openCurrentSessionwithTransaction();
		personDao.deleteAll();
		personDao.closeCurrentSessionwithTransaction();
	}
	
}
