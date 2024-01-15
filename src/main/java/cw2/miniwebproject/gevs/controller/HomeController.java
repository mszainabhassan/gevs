package cw2.miniwebproject.gevs.controller;

import cw2.miniwebproject.gevs.model.Candidate;
import cw2.miniwebproject.gevs.model.Constituency;
import cw2.miniwebproject.gevs.model.Election;
import cw2.miniwebproject.gevs.model.Voter;
import cw2.miniwebproject.gevs.service.CandidateService;
import cw2.miniwebproject.gevs.service.ConstituencyService;
import cw2.miniwebproject.gevs.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ConstituencyService constituencyService;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private VoterService voterService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/signin")
    public String Login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        List<Constituency> constituencies=this.constituencyService.findAll();
        System.out.println(constituencies.size());
        model.addAttribute("constituencies", constituencies);
        return "register";
    }

    @PostMapping("/createVoter")
    public String createVoter(@ModelAttribute Voter voter,@ModelAttribute("constituencyName") String constituencyName
            , HttpSession session
    ){



        System.out.println(voter);
        System.out.println(constituencyName);
        String msg=this.voterService.RegisterVoter(voter,constituencyName);
        System.out.println(msg);
       // this.voterService.RegisterVoter1(voter);
        session.setAttribute("msg",msg);
        return "redirect:/register";
    }




}
