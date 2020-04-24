package dev.collegue.entite;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Antoine Renou
 *
 */
@MappedSuperclass
public class BaseEntite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * Constructor
	 *
	 * @param id
	 */
	public BaseEntite(Integer id) {
		this.id = id;
	}

	public BaseEntite() {
	}

	/**
	 * Setter
	 *
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

}
