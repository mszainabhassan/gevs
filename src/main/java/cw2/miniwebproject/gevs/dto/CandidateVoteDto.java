package cw2.miniwebproject.gevs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateVoteDto {
    private String name;
    private String party;
    private Integer vote;
}
