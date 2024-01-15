package cw2.miniwebproject.gevs.model;

import javax.persistence.*;
import lombok.Data;


@Entity
@Data
public class Candidate {
    @Id
    @Column(name = "candidate_id")
    private int candidateId;

    @ManyToOne
    @JoinColumn(name = "constituency_id",nullable = false)
    private Constituency constituency;

    @ManyToOne
    @JoinColumn(name = "party_id",nullable = false)
    private Party party;

    @Column(nullable = false)
    private String candidate;

    @Column(name = "vote_count")
    private Integer voteCount;
}
