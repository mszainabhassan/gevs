package cw2.miniwebproject.gevs.repository;

import cw2.miniwebproject.gevs.model.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ElectionRepository extends JpaRepository<Election,Integer> {
    Optional<Election> findById(Integer id);
}
