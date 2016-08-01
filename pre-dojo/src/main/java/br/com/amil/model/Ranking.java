package br.com.amil.model;

import java.util.Date;

/**
 * @author LEANDRO
 *
 */
public class Ranking {
	private String match;
	private Date date;
	private Gun gun;
	private Player killer;
	private Player dead;
	
	// GETTERS AND SETTERS
	public String getMatch() {
		return match;
	}
	public void setMatch(String match) {
		this.match = match;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Gun getGun() {
		return gun;
	}
	public void setGun(Gun gun) {
		this.gun = gun;
	}
	public Player getKiller() {
		return killer;
	}
	public void setKiller(Player killer) {
		this.killer = killer;
	}
	public Player getDead() {
		return dead;
	}
	public void setDead(Player dead) {
		this.dead = dead;
	}
	

}
