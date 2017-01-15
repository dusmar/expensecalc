package cz.kp.expensecalc.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

/**
 * 
 * @author dmarusca
 *
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idGenerator")
	@SequenceGenerator(name = "idGenerator", sequenceName = "ID_GENERATOR", allocationSize = 1)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;

	public AbstractEntity() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? super.hashCode() : getId().hashCode());
		result = prime * result + (getClass().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!obj.getClass().isAssignableFrom(getClass()) && !getClass().isAssignableFrom(obj.getClass())) {
			return false;
		}
		final AbstractEntity other = (AbstractEntity) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId())) {
			return false;
		}

		return true;
	}

	/**
	 * This is the name of the entity which will identify it for users. It will
	 * be used in text of notifications to identify changed references to other
	 * objects.
	 * <p>
	 * This base implementation is insufficient. In case of null id, we can not
	 * provide worthwhile value.
	 * 
	 * @return
	 */
	public String getName() {
		if (getId() == null) {
			return "New entity";
		}
		return getId().toString();
	}

}
