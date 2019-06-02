package org.rrtf.flcb.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity
public class Fdata {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)//自动生成主键
	private int dataId;
	private String dataUrl;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinTable(name="flcbData", joinColumns=@JoinColumn(name="dataId"),
			inverseJoinColumns=@JoinColumn(name="lessonId"))
	private Flcbkcxxb flcb;
	
	public Flcbkcxxb getFlcb() {
		return flcb;
	}
	public void setFlcb(Flcbkcxxb flcb) {
		this.flcb = flcb;
	}
	public int getDataId() {
		return dataId;
	}
	public void setDataId(int dataId) {
		this.dataId = dataId;
	}
	public String getDataUrl() {
		return dataUrl;
	}
	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}
	@Override
	public String toString() {
		return "Data [dataId=" + dataId + ", dataUrl=" + dataUrl + ", flcb=" + flcb + "]";
	}

	
}
