package browsy.entities;

import java.sql.Date;

public class History {
	private int id;
	private Page page;
	private Date createdAt;
	/**
	 * @param id
	 * @param createdAt
	 */
	public History(int id, Date createdAt) {
		super();
		this.id = id;
		this.createdAt = createdAt;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the page
	 */
	public Page getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(Page page) {
		this.page = page;
	}
	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


}
