package ch.issueman.client;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.List;

import junit.framework.TestResult;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.issueman.common.Model;
import ch.issueman.common.Login;
import ch.issueman.common.Sachbearbeiter;

public class ControllerTestModel<T, Id extends Serializable> implements junit.framework.Test {

	private Controller<T, Integer> controller;
	private Class<T> clazz;
	private T model;
	
	@SuppressWarnings("unchecked")
	public ControllerTestModel(Controller<T, Id> controller, Class<T> clazz, T model){
		this.controller = (Controller<T, Integer>) controller;
		this.clazz = clazz;
		this.model = model;
	}
	
	@Before
	public void setUp() throws Exception {
		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
		Context.login();
	}

	@After
	public void tearDown() throws Exception {
		Context.logout();
	}
	
	@Test
	public void testPersist() {
		try {
			controller.persist(model);
		} catch (Exception e) {
			fail("Persist for " + clazz.getSimpleName() + " failed");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAll() {
		try {
			@SuppressWarnings("unused")
			List<T> listModel = controller.getAll();
		} catch (Exception e) {
			fail("Get list of " + clazz.getSimpleName() + " failed");
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testGetById() {
		try {
			List<T> listModel = controller.getAll();
			@SuppressWarnings("unused")
			T model = controller.getById(( (Model) listModel.get(listModel.size()-1)).getId());
		} catch (Exception e) {
			fail("Get by id for " + clazz.getSimpleName() + " failed");
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {
		try {
			List<T> listModel = controller.getAll();
			T model = listModel.get(listModel.size()-1);
			controller.update(model);
		} catch (Exception e) {
			fail("Update for " + clazz.getSimpleName() + " failed");
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		try {
			List<T> listModel = controller.getAll();
			controller.delete(listModel.get(listModel.size()-1));
		} catch (Exception e) {
			fail("Deletion of " + clazz.getSimpleName() + " failed");
			e.printStackTrace();
		}
	}

	@Override
	public int countTestCases() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void run(TestResult result) {
		// TODO Auto-generated method stub
		
	}
}

