package cw2.miniwebproject.gevs.model;

import javax.persistence.*;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "voters")
public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voter_id")
    private Integer voterId;

    @ManyToOne
    @JoinColumn(name = "constituency_id",nullable = false)
    private Constituency constituency;

    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @Column(name = "full_name",nullable = false)
    private String fullName;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "DOB",nullable = false)
    private Date dob;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "UVC",nullable = false,unique = true)
    private String uvc;

    private String role;

    private Boolean isVoted=false;
}
