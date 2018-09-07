package model;

import java.sql.Timestamp;

public class Student {
	private String Sno;
	private String Sname;
	private String Ssex;
	private Timestamp Sbirthday;
	private int Sgrade;
	private String Sdept;
	private String Dno;
	private String Bno;
	private String Spw;
	public String getSno() {
		return Sno;
	}
	public void setSno(String sno) {
		Sno = sno;
	}
	public String getSname() {
		return Sname;
	}
	public void setSname(String sname) {
		Sname = sname;
	}
	public String getSsex() {
		return Ssex;
	}
	public void setSsex(String ssex) {
		Ssex = ssex;
	}
	public Timestamp getSbirthday() {
		return Sbirthday;
	}
	public void setSbirthday(Timestamp sbirthday) {
		Sbirthday = sbirthday;
	}
	public int getSgrade() {
		return Sgrade;
	}
	public void setSgrade(int sgrade) {
		Sgrade = sgrade;
	}
	public String getSdept() {
		return Sdept;
	}
	public void setSdept(String sdept) {
		Sdept = sdept;
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
	public String getSpw() {
		return Spw;
	}
	public void setSpw(String spw) {
		Spw = spw;
	}
}
