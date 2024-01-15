package cw2.miniwebproject.gevs.repository;

import cw2.miniwebproject.gevs.model.UVC_CODE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UVC_CodeRepository extends JpaRepository<UVC_CODE,Integer> {
    UVC_CODE findByUVC(String uvc);

    UVC_CODE findByUVCAndUsed(String uvc, boolean used);
}
