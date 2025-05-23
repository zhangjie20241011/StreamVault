package com.flower.spirit.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.flower.spirit.common.DataEntity;

@Entity
@Table(name = "biz_collect_data")
public class CollectDataEntity   extends DataEntity<CollectDataEntity> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2752642646580560817L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="biz_collect_data")
	@TableGenerator(name = "biz_collect_data", allocationSize = 1, table = "seq_common", pkColumnName = "seq_id", valueColumnName = "seq_count")
    private Integer id;
	
	private String taskid;
	
	private String platform;
	
	private String taskname;
	
	private String taskstatus;
	
	private String createtime;
	
	private String endtime;
	
	/**
	 * 总任务数
	 */
	private String count;
	
	/**
	 * 已经完成数
	 */
	private String carriedout;
	
	private String originaladdress;
	
	private String monitoring;   //是否监控
	
	private String lastCheckTime;
	
	private String lastid;
	
	private Integer maxcur;
	
	private Integer omaxcur;
	
	private String generatenfo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getTaskstatus() {
		return taskstatus;
	}

	public void setTaskstatus(String taskstatus) {
		this.taskstatus = taskstatus;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getCarriedout() {
		return carriedout;
	}

	public void setCarriedout(String carriedout) {
		this.carriedout = carriedout;
	}

	public String getOriginaladdress() {
		return originaladdress;
	}

	public void setOriginaladdress(String originaladdress) {
		this.originaladdress = originaladdress;
	}

	public String getMonitoring() {
		return monitoring;
	}

	public void setMonitoring(String monitoring) {
		this.monitoring = monitoring;
	}

	public String getLastCheckTime() {
		return lastCheckTime;
	}

	public void setLastCheckTime(String lastCheckTime) {
		this.lastCheckTime = lastCheckTime;
	}

	public String getLastid() {
		return lastid;
	}

	public void setLastid(String lastid) {
		this.lastid = lastid;
	}

	public Integer getMaxcur() {
		return maxcur;
	}

	public void setMaxcur(Integer maxcur) {
		this.maxcur = maxcur;
	}

	public Integer getOmaxcur() {
		return omaxcur;
	}

	public void setOmaxcur(Integer omaxcur) {
		this.omaxcur = omaxcur;
	}

	public String getGeneratenfo() {
		return generatenfo;
	}

	public void setGeneratenfo(String generatenfo) {
		this.generatenfo = generatenfo;
	}

	
	
	
	
	

}
