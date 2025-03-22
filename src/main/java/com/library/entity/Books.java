package com.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="myBooks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Books {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="book_id")
	private int bid;
	@Column(name="book_name")
	private String bname;
	@Column(name="book_author")
	private String bauthor;
	@Column(name="book_publication_year")
	private String bpulicationyear;
	@Column(name="book_type")
	private String btype;
}
