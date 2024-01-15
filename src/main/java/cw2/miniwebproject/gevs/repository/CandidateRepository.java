package cw2.miniwebproject.gevs.repository;

import cw2.miniwebproject.gevs.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Integer> {
List<Candidate> findByConstituencyConstituencyName(String name);
Candidate findTopByConstituencyConstituencyIdOrderByVoteCountDesc(Integer constituencyId);
    @Modifying
    @Transactional
    @Query("UPDATE Candidate c SET c.voteCount = 0")
    void resetVoteCounts();
}
