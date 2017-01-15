package cz.kp.expensecalc.common.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.GenericTypeResolver;
import org.springframework.util.Assert;

import cz.kp.expensecalc.common.model.AbstractEntity;


/**
 * 
 * @author dmarusca
 *
 * @param <E>
 */
public abstract class AbstractJpaDao<E extends AbstractEntity> implements InitializingBean {

	private EntityManager entityManager;

	private Class<E> entityClass = null;

	@SuppressWarnings("unchecked")
	protected Class<E> resolveEntityClass() {
		return (Class<E>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractJpaDao.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(entityManager, "The entity manager is not set. Be sure "
				+ "to set one, or use org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor to automatically set one.");
		entityClass = resolveEntityClass();
	}


	/**
	 * @return The filter class this dao is configured for
	 */
	protected final Class<E> getEntityClass() {
		return entityClass;
	}

	/**
	 * @param entityManager the entityManager to set
	 */
	@PersistenceContext
	public final void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * @return the entityManager
	 */
	protected final EntityManager getEntityManager() {
		return entityManager;
	}
	
	protected final CriteriaBuilder getCriteriaBuilder() {
		return getEntityManager().getCriteriaBuilder();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public E findByPK(Serializable pk) {
		return getEntityManager().find(getEntityClass(), pk);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<E> findAll(String property, boolean asc) {
		CriteriaBuilder bld = getCriteriaBuilder();
		CriteriaQuery<E> query = bld.createQuery(getEntityClass());
		Root<E> root = query.from(getEntityClass());
		
		Order order;
		if (asc) {
			order = bld.asc(root.get(property));
		} else {
			order = bld.desc(root.get(property));
		}
		query.orderBy(order);
		
		return getEntityManager().createQuery(query).getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	public void save(E entity) {
		Assert.notNull(entity, "Entity to save may not be null!");
	    getEntityManager().merge(entity);
	}
	
	/**
	 * {@inheritDoc}
	 */
	
	public void saveOrUpdate(E entity) {
		Assert.notNull(entity, "Entity to save/update may not be null!");
		if (entity.getId() == null) {
			 save(entity);
		}
		 update(entity);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void update(E entity) {
		Assert.notNull(entity, "Entity to update may not be null!");
		getEntityManager().merge(entity);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void remove(E entity) {
		Assert.notNull(entity, "Entity to remove may not be null!");
		getEntityManager().remove(entity);
	}
	
}
