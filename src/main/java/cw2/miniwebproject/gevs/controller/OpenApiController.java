package cw2.miniwebproject.gevs.controller;

import cw2.miniwebproject.gevs.dto.BaseApiResponseDTO;
import cw2.miniwebproject.gevs.dto.CandidateVoteDto;
import cw2.miniwebproject.gevs.dto.ConstituencyResultDto;
import cw2.miniwebproject.gevs.dto.ElectionResultDto;
import cw2.miniwebproject.gevs.model.Candidate;
import cw2.miniwebproject.gevs.model.Election;
import cw2.miniwebproject.gevs.repository.ElectionRepository;
import cw2.miniwebproject.gevs.service.CandidateService;
import cw2.miniwebproject.gevs.service.ConstituencyService;
import cw2.miniwebproject.gevs.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gevs")
public class OpenApiController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private ConstituencyService constituencyService;

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private VoterService voterService;

    @GetMapping("/constituency/{constituencyName}")
    public ResponseEntity<?> getVoteByDistrict(@PathVariable String constituencyName){
        if(this.constituencyService.findByConstituencyName(constituencyName)==null){
           return new ResponseEntity<>(new BaseApiResponseDTO("Constituency Not Found!"),null, HttpStatus.NOT_FOUND);
        }
       try{ List<Candidate> candidates= this.candidateService.getAllCandidatesByConstituencyName(constituencyName);
        List<CandidateVoteDto> result=candidates
                .stream().map(candidate -> new CandidateVoteDto(
                        candidate.getCandidate(),
                        candidate.getParty().getParty(),
                        candidate.getVoteCount()

                )).collect(Collectors.toList());

       ConstituencyResultDto constituencyResultDto= ConstituencyResultDto.builder()
                .constituency(this.constituencyService.findByConstituencyName(constituencyName).getConstituencyName())
                .result(result).build();
           return ResponseEntity.ok(constituencyResultDto);
       }
    catch (Exception e){
        return new ResponseEntity<>(new BaseApiResponseDTO("An Error Occurred while getting data! "),null, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    }


    @GetMapping("/result")
    public ElectionResultDto getElectionResult(){
        System.out.println(this.candidateService.getElectionResult());
        return this.candidateService.getElectionResult();
    }

    @PutMapping("/resetVoterCount")
    public Election resetVoteCount(){
        this.candidateService.UpdateVoteCount();
        this.voterService.updateHasVoted();
        Election election=this.electionRepository.getById(1);
        election.setResult("Pending");
        election.setIsStarted(true);
        election.setIsEnded(false);
        return this.electionRepository.save(election);
    }

    @GetMapping("/updateElection")
    public Election updateElectionData(){
        Optional<Election> election=this.electionRepository.findById(1);
        election.get().setIsStarted(false);
        election.get().setIsEnded(true);
        System.out.println("IN Update Election..:"+ this.electionRepository.save(election.get()));
        return this.electionRepository.save(election.get());
    }

    @GetMapping("/updateElectionResult/{result}")
    public Election updateElectionResultData(@PathVariable String result){

        Optional<Election> election=this.electionRepository.findById(1);
        election.get().setResult(this.candidateService.getElectionResult().getWinner());
        System.out.println("IN Update Election RESULT.."+election.get());
        return this.electionRepository.save(election.get());
    }

    @GetMapping("/getElectionStatus")
    public Election getElectionStatus(){
        Optional<Election> election=this.electionRepository.findById(1);
        if(election.get()!=null) {
            {
                if (election.get().getIsEnded()) {
                    election.get().setResult("No Election");
                    election.get().setIsStarted(false);
                    election.get().setIsEnded(false);
                    return this.electionRepository.save(election.get());
                }
                return election.get();
            }

        }
        else
            return null;
    }

    @GetMapping("/real-time-results")
    public ResponseEntity<List<Candidate>> getRealTimeResults() {
        List<Candidate> candidates = this.candidateService.findAll();
        return new ResponseEntity<>(candidates, HttpStatus.OK);
    }

}
