package cw2.miniwebproject.gevs.service;

import cw2.miniwebproject.gevs.dto.BaseApiResponseDTO;
import cw2.miniwebproject.gevs.model.UVC_CODE;
import cw2.miniwebproject.gevs.model.Voter;
import cw2.miniwebproject.gevs.repository.UVC_CodeRepository;
import cw2.miniwebproject.gevs.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class VoterService {

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncode;

    @Autowired
    private ConstituencyService constituencyService;

    @Autowired
    private UVC_CodeRepository uvc_codeRepository;
    public Voter RegisterVoter1(Voter voter) {
      return   this.voterRepository.save(voter);

    }


    public String RegisterVoter(Voter voter,String constituencyName) {
        Voter DuplicateVoterByEmail=voterRepository.findByEmail(voter.getEmail());
        UVC_CODE uvc_code=this.uvc_codeRepository.findByUVC(voter.getUvc());
        UVC_CODE uvc_code1=this.uvc_codeRepository.findByUVCAndUsed(voter.getUvc(),true);
        voter.setPassword(passwordEncode.encode(voter.getPassword()));
        voter.setConstituency(this.constituencyService.findByConstituencyName(constituencyName));
        voter.setRole("ROLE_VOTER");
        if(DuplicateVoterByEmail!=null)
        {
            return "Email Already Exists!";
           // return new ResponseEntity<>(new BaseApiResponseDTO("Email Already Exists! Please choose different email"), HttpStatus.CONFLICT);
        }
        if(uvc_code==null)
        {
            return "Invalid UVC!";
           // return new ResponseEntity<>(new BaseApiResponseDTO("Invalid UVC! UVC not found!"), HttpStatus.NOT_FOUND);

        }
        if(isLessThan18Years(voter.getDob())){
            return "DOB(atleast 18 years) not eligible to vote!";

        }
        if(uvc_code1!=null)
        {
          //  return new ResponseEntity<>(new BaseApiResponseDTO("UVC already Used !"), HttpStatus.NOT_FOUND);
            return "UVC already Used !";
        }
            //make uvc_code is used =true in database
            UVC_CODE uvc_code2=this.uvc_codeRepository.findByUVC(voter.getUvc());
            uvc_code2.setUsed(true);

            Voter registeredVoter=  this.voterRepository.save(voter);
            System.out.println(registeredVoter);
            this.uvc_codeRepository.save(uvc_code2);
            if(registeredVoter!=null) {
                return "Successfully Registered!";
            } else {
                return "Unknown Server Error!";
            }
            // return new ResponseEntity<>(new BaseApiResponseDTO("Successfully Registered",  registeredVoter, null), HttpStatus.OK);
    }
    public static boolean isLessThan18Years(Date dob) {
        // Convert the Date object to LocalDate
        LocalDate birthDate = dob.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

        // Calculate the date 18 years ago
        LocalDate eighteenYearsAgo = LocalDate.now().minusYears(18);

        // Check if the birth date is less than 18 years ago
        return birthDate.isAfter(eighteenYearsAgo);
    }
    public Voter findByEmail(String email) {
        return voterRepository.findByEmail(email);
    }



    public void updateHasVoted() {
        this.voterRepository.resetHasVoted();
    }
}
