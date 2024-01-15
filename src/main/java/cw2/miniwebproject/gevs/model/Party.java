package cw2.miniwebproject.gevs.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_id")
    private int partyId;

    @Column(name = "party")
    private String party;

    @JsonIgnore
    @OneToMany(mappedBy = "party")
    public List<Candidate> candidates;
}
