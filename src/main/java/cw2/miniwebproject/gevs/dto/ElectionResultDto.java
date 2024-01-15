package cw2.miniwebproject.gevs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElectionResultDto {
    private String status;
    private String winner;
    private List<SeatsDto> seats;
}
