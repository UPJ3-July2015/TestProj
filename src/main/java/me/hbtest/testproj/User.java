package me.hbtest.testproj;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Transient;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "scusers")
@SecondaryTables({
	  @SecondaryTable(name="SCUSER_AVATAR")
})
@NamedQueries({ @NamedQuery(name = "User.getUserByName", query = "SELECT u FROM User u where u.name = :usern"),
		@NamedQuery(name = "User.getAllUsersOrderByName", query = "SELECT u FROM User u ORDER BY u.name"),
		@NamedQuery(name = "User.getCountByUserName", query = "SELECT COUNT(u) FROM User u where u.name = :usern") })
public class User {

	@Id
	@GeneratedValue(generator = "user_seq")
	@SequenceGenerator(name = "user_seq", sequenceName = "SC_SEQ", allocationSize = 1)
	private Long Id;

	@Column(name = "name", length = 40)
	private String name;

	@Column(name = "passwordhash", length = 64)
	private String passwordhash;

	@Transient
	private String password;
	
	@Basic(fetch=FetchType.EAGER)
	@Column(name = "photo", table="SCUSER_AVATAR")
	private byte[] avatar;
	

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswordhash() {
		return passwordhash;
	}

	public void setPasswordhash(String passwordhash) {
		this.passwordhash = passwordhash;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		this.passwordhash = "X_"+password;
	}

}
