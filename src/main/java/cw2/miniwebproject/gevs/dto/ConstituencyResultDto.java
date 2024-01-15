package cw2.miniwebproject.gevs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConstituencyResultDto {
    private String constituency;
    private List<CandidateVoteDto> result;
}
