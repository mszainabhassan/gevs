package cw2.miniwebproject.gevs.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "uvc_code")
public class UVC_CODE {
    @Id
    @Column(name = "UVC",nullable = false)
    private String UVC;

    @Column(name = "used",nullable = false)
    private Boolean used;
}
