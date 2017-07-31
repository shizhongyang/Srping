package com.lq.entity;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;

import javax.persistence.Column;

import javax.persistence.Entity;

import javax.persistence.FetchType;

import javax.persistence.GeneratedValue;

import javax.persistence.GenerationType;

import javax.persistence.Id;

import javax.persistence.OneToMany;

import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "reader")
public class Reader {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;


	@NotEmpty
	@Column(name = "MENO")
	private String meno;

	@NotEmpty
	@Column(name = "NAME")
	private String name;
	
	
	@Column(name = "imgAvatar")
	private String imgAvatar;
	
	
	public String getImgAvatar() {
		return imgAvatar;
	}

	public void setImgAvatar(String imgAvatar) {
		this.imgAvatar = imgAvatar;
	}

	@OneToMany(mappedBy = "reader", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Book> books = new HashSet<Book>();

	public Reader()
	{
	}

	public Set getBooks() {
		return books;
	}

	public void setBooks(Set books) {

		this.books = books;

	}

	public int getId() {

		return id;

	}

	public void setId(int id) {

		this.id = id;

	}

	public String getMeno() {

		return meno;

	}

	public void setMeno(String meno) {

		this.meno = meno;

	}

	public String getName() {

		return name;

	}

	public void setName(String name) {

		this.name = name;

	}

	@Override
	public String toString() {
		return "Reader [id=" + id + ", meno=" + meno + ", name=" + name + ", books=" + books + "]";
	}
	
	
	
}