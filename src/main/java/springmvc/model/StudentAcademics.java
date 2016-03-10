package springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "academics")
public class StudentAcademics {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

	@Column(name="tofel_score")
	private long tofelScore;
	
	@Column(name="gre_score")
	private long greScore;
	
	private float gpa;
	public void setGpa(float gpa) {
		this.gpa = gpa;
	}
	@Column(name="file_location_transcript")
	private String fileLocationTranscript;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public long getTofelScore() {
		return tofelScore;
	}
	public void setTofelScore(long tofelScore) {
		this.tofelScore = tofelScore;
	}
	public long getGreScore() {
		return greScore;
	}
	public void setGreScore(long greScore) {
		this.greScore = greScore;
	}
	public float getGpa() {
		return gpa;
	}
	public void setGpa(long gpa) {
		this.gpa = gpa;
	}
	public String getFileLocationTranscript() {
		return fileLocationTranscript;
	}
	public void setFileLocationTranscript(String fileLocationTranscript) {
		this.fileLocationTranscript = fileLocationTranscript;
	}
	
}
