package cw2.miniwebproject.gevs.repository;

import cw2.miniwebproject.gevs.model.Constituency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstituencyRepository extends JpaRepository<Constituency,Integer> {
    List<Constituency> findAll();
    Constituency findByConstituencyName(String constituencyName);
}
