package cw2.miniwebproject.gevs.service;

import cw2.miniwebproject.gevs.repository.UVC_CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UVC_CODEService {

    @Autowired
    private UVC_CodeRepository uvc_codeRepository;
}
