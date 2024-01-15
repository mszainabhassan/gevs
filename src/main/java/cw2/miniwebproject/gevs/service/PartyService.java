package cw2.miniwebproject.gevs.service;

import cw2.miniwebproject.gevs.model.Party;
import cw2.miniwebproject.gevs.repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyService {
    @Autowired
    private PartyRepository partyRepository;

    public List<Party> findAll(){
        return this.partyRepository.findAll();

    }
}
