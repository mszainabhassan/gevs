package cw2.miniwebproject.gevs.repository;

import cw2.miniwebproject.gevs.model.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRepository extends JpaRepository<Party,Integer> {
}
