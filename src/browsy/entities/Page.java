package browsy.entities;

public class Page {
	private int id;
	private String name;
	private String link;
	/**
	 * @param id
	 * @param name
	 * @param link
	 */
	public Page(int id, String name, String link) {
		super();
		this.id = id;
		this.name = name;
		this.link = link;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}
	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}


}
