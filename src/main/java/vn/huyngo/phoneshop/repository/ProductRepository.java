package vn.huyngo.phoneshop.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.huyngo.phoneshop.domain.SANPHAM;

@Repository
public interface ProductRepository extends JpaRepository<SANPHAM, Long> {
    SANPHAM save(SANPHAM save);

    Optional<SANPHAM> findByMaSanPham(Long maSanPham);

    void deleteById(long id);

    Page<SANPHAM> findAll(Pageable Pageable);
}
