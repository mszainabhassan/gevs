package cw2.miniwebproject.gevs.service;

import cw2.miniwebproject.gevs.dto.ElectionResultDto;
import cw2.miniwebproject.gevs.dto.SeatsDto;
import cw2.miniwebproject.gevs.model.*;
import cw2.miniwebproject.gevs.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ConstituencyRepository constituencyRepository;

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private ElectionRepository electionRepository;

    public List<Candidate> getAllCandidatesByConstituencyName(String constituencyName){
        return this.candidateRepository.findByConstituencyConstituencyName(constituencyName);

    }

    public void setVoteToCandidateByVoter(Integer candidateId, Voter voter){

        Candidate candidate=this.candidateRepository.getById(candidateId);
        candidate.setVoteCount(candidate.getVoteCount()+1);
        this.candidateRepository.save(candidate);
        voter.setIsVoted(true);
        this.voterRepository.save(voter);
    }

    public void UpdateVoteCount(){
    this.candidateRepository.resetVoteCounts();
    }


public ElectionResultDto getElectionResult() {
    List<SeatsDto> seatsList = new ArrayList<>();
    List<Constituency> constituencies = this.constituencyRepository.findAll();
    Optional<Election> election=this.electionRepository.findById(1);
    for (Constituency constituency : constituencies) {
        Candidate candidate = this.candidateRepository.findTopByConstituencyConstituencyIdOrderByVoteCountDesc(constituency.getConstituencyId());

        if (candidate != null) {
            seatsList.add(new SeatsDto(candidate.getParty().getParty(), 1));
        }
    }

    // Create a set of all parties
    Set<String> allParties = this.partyRepository.findAll().stream()
            .map(Party::getParty)
            .collect(Collectors.toSet());

    // Create a map with 0 seats for all parties
    Map<String, Integer> partySeatCountMap = allParties.stream()
            .collect(Collectors.toMap(Function.identity(), party -> 0));

    // Update the seat counts based on the winning candidates
    seatsList.forEach(seatsDto -> partySeatCountMap.merge(seatsDto.getParty(), seatsDto.getSeat(), Integer::sum));

    List<SeatsDto> finalSeatsList=new ArrayList<>();
    // Create the final SeatsDto list
    finalSeatsList = partySeatCountMap.entrySet().stream()
            .map(entry -> new SeatsDto(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());

    // Find the party with the maximum seats
    Optional<Map.Entry<String, Integer>> winnerEntry = partySeatCountMap.entrySet().stream()
            .max(Map.Entry.comparingByValue());

    // Check if any party has won an overall majority
    boolean hasOverallMajority = winnerEntry.map(entry -> entry.getValue() > constituencies.size() / 2).orElse(false);

    String winnerParty = hasOverallMajority ? winnerEntry.map(Map.Entry::getKey).orElse("No Winner") : "Hung Parliament";
    if (election.get().getIsStarted() && !election.get().getIsEnded()) {
        // If the election is ongoing, set status as "Pending"
        return new ElectionResultDto("Pending", "Pending", finalSeatsList);
    }
    // Create the ElectionResultDto
    ElectionResultDto electionResultDto = new ElectionResultDto("Completed", winnerParty, finalSeatsList);

    return electionResultDto;
}


    public List<Candidate> findAll() {
        return this.candidateRepository.findAll();
    }

}
