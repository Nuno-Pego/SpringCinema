package io.altar.CinemaTicketSystem.Repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.altar.CinemaTicketSystem.Models.BaseEntity;

public abstract class EntityRepository <T extends BaseEntity> {
	
	@PersistenceContext
	protected EntityManager entityManager;

	protected abstract Class<T> getEntityClass();

	protected abstract String getAllEntityQueryName();

	protected abstract String deleteAllEntityQueryName();
	
	// Methods CRUD:
	public T save(T entity) {
		return entityManager.merge(entity);
	}

	public T update(T entity) {
		return entityManager.merge(entity);
	}

	public List<T> getAll() {
		return entityManager.createNamedQuery(getAllEntityQueryName(), getEntityClass()).getResultList();
	}

	public int removeAll() {
		return entityManager.createNamedQuery(deleteAllEntityQueryName()).executeUpdate();
	}
	
	// By ID:
	public T getById(long id) {
		return entityManager.find(getEntityClass(), id);
	}

	public void removeByID(long id) {
		if(entityManager.find(getEntityClass(), id)!=null)
			entityManager.remove(entityManager.find(getEntityClass(), id));
	}

}
