package vn.huyngo.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.huyngo.phoneshop.domain.SANPHAM;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<SANPHAM, Long> {
    SANPHAM save(SANPHAM save);

}
