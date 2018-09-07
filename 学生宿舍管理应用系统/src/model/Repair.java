package model;

import java.sql.Timestamp;

public class Repair {
	private String Dno;	
	private String Gno;	
	private String Bno;
	private Timestamp Rsubmit;	
	private String Rreason;	
	private String Rstate;
	public String getDno() {
		return Dno;
	}
	public void setDno(String dno) {
		Dno = dno;
	}
	public String getGno() {
		return Gno;
	}
	public void setGno(String gno) {
		Gno = gno;
	}
	public String getBno() {
		return Bno;
	}
	public void setBno(String bno) {
		Bno = bno;
	}
	public Timestamp getRsubmit() {
		return Rsubmit;
	}
	public void setRsubmit(Timestamp rsubmit) {
		Rsubmit = rsubmit;
	}
	public String getRreason() {
		return Rreason;
	}
	public void setRreason(String rreason) {
		Rreason = rreason;
	}
	public String getRstate() {
		return Rstate;
	}
	public void setRstate(String rstate) {
		Rstate = rstate;
	}
}
