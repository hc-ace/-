package model;

import java.sql.Timestamp;

public class LR {
	private String Sno;	
	private String Dno;	
	private String Bno;
	private Timestamp Leave;	
	private Timestamp Lreturn;
	public String getSno() {
		return Sno;
	}
	public void setSno(String sno) {
		Sno = sno;
	}
	public String getDno() {
		return Dno;
	}
	public void setDno(String dno) {
		Dno = dno;
	}
	public String getBno() {
		return Bno;
	}
	public void setBno(String bno) {
		Bno = bno;
	}
	public Timestamp getLeave() {
		return Leave;
	}
	public void setLeave(Timestamp leave) {
		Leave = leave;
	}
	public Timestamp getLreturn() {
		return Lreturn;
	}
	public void setLreturn(Timestamp lreturn) {
		Lreturn = lreturn;
	}	
}
