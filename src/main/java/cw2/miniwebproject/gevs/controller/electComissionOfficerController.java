package cw2.miniwebproject.gevs.controller;

import cw2.miniwebproject.gevs.model.*;
import cw2.miniwebproject.gevs.repository.ElectionRepository;
import cw2.miniwebproject.gevs.service.CandidateService;
import cw2.miniwebproject.gevs.service.ConstituencyService;
import cw2.miniwebproject.gevs.service.PartyService;
import cw2.miniwebproject.gevs.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ec_officer")
public class electComissionOfficerController {
    @Autowired
    private CandidateService candidateService;

    @Autowired
    private ConstituencyService constituencyService;

    @Autowired
    private PartyService partyService;

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private VoterService voterService;

    @ModelAttribute
    private void EComissionDetails(Model m, Principal p) {
        String email = p.getName();
        m.addAttribute("email", email);
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Constituency> constituencies=this.constituencyService.findAll();
        List<Party> parties=this.partyService.findAll();
        List<Candidate> candidates=this.candidateService.findAll();
        model.addAttribute("constituencies",constituencies);
        model.addAttribute("parties",parties);
        model.addAttribute("candidates",candidates);
        model.addAttribute("announcement","Winner is: ");
        return "ec_officer/home";
    }

}
