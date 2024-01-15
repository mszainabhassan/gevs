package cw2.miniwebproject.gevs.repository;

import cw2.miniwebproject.gevs.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface VoterRepository extends JpaRepository<Voter,Integer> {

    Voter findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE Voter v SET v.isVoted = false ")
    void resetHasVoted();
}
