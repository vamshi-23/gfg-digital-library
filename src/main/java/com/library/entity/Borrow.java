package com.library.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name="Book_Borrow")
@Getter
@Setter
@ToString
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="borrow_id")
    private int borrowId;

    @ManyToOne
    private User user;
    @ManyToOne
    private Books books;
    @Column(name="issue_date")
    private Date issueDate;
    @Column(name="return_date")
    private Date returnDate;

}
