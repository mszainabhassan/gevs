package cw2.miniwebproject.gevs.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Election implements Serializable {
    @Id
    private Integer id;
    private Boolean isStarted=false;
    private Boolean isEnded=false;
    private String result="No ELection";
}
