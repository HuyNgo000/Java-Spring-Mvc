package vn.huyngo.phoneshop.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.huyngo.phoneshop.domain.VAITRO;
import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<VAITRO, Long> {
    VAITRO findByTen(String ten);
}
