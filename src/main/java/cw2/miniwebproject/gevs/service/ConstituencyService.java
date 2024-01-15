package cw2.miniwebproject.gevs.service;

import cw2.miniwebproject.gevs.model.Constituency;
import cw2.miniwebproject.gevs.repository.ConstituencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConstituencyService {

    @Autowired
    private ConstituencyRepository constituencyRepository;


    public List<Constituency> findAll() {
        return this.constituencyRepository.findAll();
    }

    public Constituency findByConstituencyName(String constituencyName) {
        return this.constituencyRepository.findByConstituencyName(constituencyName);
    }


}
