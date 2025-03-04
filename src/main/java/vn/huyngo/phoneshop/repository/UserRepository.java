package vn.huyngo.phoneshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.huyngo.phoneshop.domain.NGUOIDUNG;

@Repository
public interface UserRepository extends JpaRepository<NGUOIDUNG, Long> {
    NGUOIDUNG save(NGUOIDUNG hoidanit);

    List<NGUOIDUNG> findByEmail(String email);

    NGUOIDUNG findById(long id);

}
