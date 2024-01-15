package cw2.miniwebproject.gevs.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "constituency")
public class Constituency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "constituency_id")
    private int constituencyId;

    @Column(name = "constituency_name")
    private String constituencyName;

    @JsonIgnore
    @OneToMany(mappedBy = "constituency")
    public List<Voter> voters;

    @JsonIgnore
    @OneToMany(mappedBy = "constituency")
    public List<Candidate> candidates;
}
