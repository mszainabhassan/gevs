package cw2.miniwebproject.gevs.controller;

import cw2.miniwebproject.gevs.dto.BaseApiResponseDTO;
import cw2.miniwebproject.gevs.model.Candidate;
import cw2.miniwebproject.gevs.model.Election;
import cw2.miniwebproject.gevs.model.Voter;
import cw2.miniwebproject.gevs.repository.ElectionRepository;
import cw2.miniwebproject.gevs.service.CandidateService;
import cw2.miniwebproject.gevs.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/voter")
public class VoterController {
    @Autowired
    private CandidateService candidateService;

    @Autowired
    private ElectionRepository electionRepository;

    @GetMapping("/")
    public String home(Model model,@ModelAttribute("voter") Voter voter) {
        List<Candidate> candidates=this.candidateService.getAllCandidatesByConstituencyName(voter.getConstituency().getConstituencyName());
        Optional<Election> election=this.electionRepository.findById(1);
        System.out.println(election);
        model.addAttribute("candidates",candidates);
        if(election.isEmpty())
        {  model.addAttribute("electionStarted",false);}
        else
        { model.addAttribute("electionStarted",election.get().getIsStarted());}
        return "voter/home";
    }

    @PutMapping("/vote/{candidateId}")
    public String setVote(@PathVariable Integer candidateId,@ModelAttribute("voter") Voter voter){
        this.candidateService.setVoteToCandidateByVoter(candidateId,voter);

        return "voter/home";
    }
    @Autowired
    private VoterService voterService;

    @ModelAttribute
    private void voterDetails(Model m, Principal p) {
        String email = p.getName();
        Voter voter = voterService.findByEmail(email);
        m.addAttribute("voter", voter);

    }


}
