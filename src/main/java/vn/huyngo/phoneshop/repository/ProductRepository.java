package vn.huyngo.phoneshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.huyngo.phoneshop.domain.SANPHAM;

@Repository
public interface ProductRepository extends JpaRepository<SANPHAM, Long>, JpaSpecificationExecutor<SANPHAM> {
    SANPHAM save(SANPHAM save);

    Optional<SANPHAM> findByMaSanPham(Long maSanPham);

    void deleteById(long id);

    Page<SANPHAM> findAll(Pageable Pageable);

    Page<SANPHAM> findAll(Specification<SANPHAM> specification, Pageable Pageable);

    List<SANPHAM> findByNoiSanXuat(String noiSanXuat);
}
