package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Model;

public class ModelDAO {
	EntityManagerFactory factory;
	EntityManager manager;

	public ModelDAO() {
		this.factory = Persistence.createEntityManagerFactory("default");
		this.manager = factory.createEntityManager();
	}

	public List<Model> executeFindList(String sql, Model obj) {
		manager.getTransaction().begin();
		List<Model> list = manager.createQuery("from " + obj.getClass().getSimpleName() + " where 1=1 " + sql, //
				Model.class).getResultList();
		manager.close();
		factory.close();
		return list;
	}

	public Model executeFindById(@SuppressWarnings("rawtypes") Class c, Long id) {
		manager.getTransaction().begin();
		@SuppressWarnings("unchecked")
		Model item = (Model) manager.find(c, id);
		manager.close();
		factory.close();
		return item;
	}

	public boolean executeInsert(Model obj) {
		manager.getTransaction().begin();
		manager.persist(obj);
		manager.getTransaction().commit();
		manager.close();
		factory.close();
		return true;
	}

	public boolean executeDelete(Model obj) {
		manager.getTransaction().begin();
		manager.remove(manager.contains(obj) ? obj : manager.merge(obj));
		manager.getTransaction().commit();
		manager.close();
		factory.close();
		return true;
	}

	public boolean executeUpdate(Model obj) {
		manager.getTransaction().begin();
		manager.merge(obj);
		manager.getTransaction().commit();
		manager.close();
		factory.close();
		return true;
	}

	@SuppressWarnings("rawtypes")
	public static <T> List cast(List<T> list) {
		return list;
	}
}
