package springmvc.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "status_update")
public class StatusUpdate {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	
	private String comment;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Column(name="time_changed")
	private Date timeChanged;
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public Date getTimeChanged() {
		return timeChanged;
	}


	public void setTimeChanged(Date timeChanged) {
		this.timeChanged = timeChanged;
	}


	public Application getApplication() {
		return application;
	}


	public void setApplication(Application application) {
		this.application = application;
	}


	public Status getCurrentStatus() {
		return currentStatus;
	}


	public void setCurrentStatus(Status currentStatus) {
		this.currentStatus = currentStatus;
	}


	@ManyToOne
	private Application application;
	
	
	@OneToOne
	private Status currentStatus;
	
	
	
}
