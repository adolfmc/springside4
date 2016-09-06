package org.springside.examples.quickstart.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "job_info")
public class JobInfo extends IdEntity {
	private String titile;
	private String salary;
	private String company;
	private String type;
	private String url;
	private Long times;
	private String jobxz;
	private String jobtime;
	private Date createDate;
	
	public String getJobtime() {
		return jobtime;
	}

	public void setJobtime(String jobtime) {
		this.jobtime = jobtime;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getJobxz() {
		return jobxz;
	}

	public void setJobxz(String jobxz) {
		this.jobxz = jobxz;
	}

	public String getTitile() {
		return titile;
	}

	public void setTitile(String titile) {
		this.titile = titile;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getTimes() {
		return times;
	}

	public void setTimes(Long times) {
		this.times = times;
	}

}
