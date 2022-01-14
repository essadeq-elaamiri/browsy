package browsy.entities;

import java.util.List;

public class Tab {
	private int id;
	private List<Page> pages;
	/**
	 * @param id
	 * @param pages
	 */
	public Tab(int id) {

		this.id = id;
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
	 * @return the pages
	 */
	public List<Page> getPages() {
		return pages;
	}
	/**
	 * @param pages the pages to set
	 */
	public void setPages(List<Page> pages) {
		this.pages = pages;
	}



}
