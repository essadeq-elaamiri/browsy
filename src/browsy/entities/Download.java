package browsy.entities;

import java.sql.Date;

public class Download {
	private int id;
	private String name;
	private Date downloadedAt;
	private String locationInSystem;
	private String link;
	private double size;
	private String status;

	/**
	 * @param id
	 * @param name
	 * @param downloadedAt
	 * @param locationInSystem
	 * @param link
	 * @param size
	 * @param status
	 */
	public Download(int id, String name, Date downloadedAt, String locationInSystem, String link, double size,
			String status) {
		this.id = id;
		this.name = name;
		this.downloadedAt = downloadedAt;
		this.locationInSystem = locationInSystem;
		this.link = link;
		this.size = size;
		this.status = status;
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
	 * @return the downloadedAt
	 */
	public Date getDownloadedAt() {
		return downloadedAt;
	}
	/**
	 * @param downloadedAt the downloadedAt to set
	 */
	public void setDownloadedAt(Date downloadedAt) {
		this.downloadedAt = downloadedAt;
	}
	/**
	 * @return the locationInSystem
	 */
	public String getLocationInSystem() {
		return locationInSystem;
	}
	/**
	 * @param locationInSystem the locationInSystem to set
	 */
	public void setLocationInSystem(String locationInSystem) {
		this.locationInSystem = locationInSystem;
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
	/**
	 * @return the size
	 */
	public double getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(double size) {
		this.size = size;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}




}
