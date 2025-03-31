package vn.huyngo.phoneshop.service.Spectification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import vn.huyngo.phoneshop.domain.SANPHAM;
import vn.huyngo.phoneshop.domain.SANPHAM_;

public class ProductSpecs {

    public static Specification<SANPHAM> nameLike(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(SANPHAM_.TEN), "%" + name + "%");
    }

    // case 1
    public static Specification<SANPHAM> minPrice(double price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.ge(root.get(SANPHAM_.GIA), price);
    }

    // case 2
    public static Specification<SANPHAM> maxPrice(double price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.le(root.get(SANPHAM_.GIA), price);
    }

    // case3
    public static Specification<SANPHAM> matchFactory(String factory) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(SANPHAM_.NOI_SAN_XUAT), factory);
    }

    // case4
    public static Specification<SANPHAM> matchListFactory(List<String> factory) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get(SANPHAM_.NOI_SAN_XUAT)).value(factory);
    }

    // case5
    public static Specification<SANPHAM> matchPrice(double min, double max) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.gt(root.get(SANPHAM_.GIA), min),
                criteriaBuilder.le(root.get(SANPHAM_.GIA), max));
    }

    // case6
    public static Specification<SANPHAM> matchMultiplePrice(double min, double max) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(
                root.get(SANPHAM_.GIA), min, max);
    }

    // case7
    public static Specification<SANPHAM> matchRom(String rom) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(SANPHAM_.BO_NHO), rom);
    }

    // case8
    public static Specification<SANPHAM> matchListRom(List<String> rom) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get(SANPHAM_.BO_NHO)).value(rom);
    }

    // case9
    public static Specification<SANPHAM> matchScreen(String screen) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(SANPHAM_.MAN_HINH), screen);
    }

    // case10
    // public static Specification<SANPHAM> matchListScreen(List<String> screen) {
    // return (root, query, criteriaBuilder) ->
    // criteriaBuilder.in(root.get(SANPHAM_.MAN_HINH))
    // .value("%" + screen + "%");
    // }

    // case9 - filter theo list màn hình tương đối
    public static Specification<SANPHAM> matchListScreen(List<String> screenKeywords) {
        return (root, query, criteriaBuilder) -> {
            if (screenKeywords == null || screenKeywords.isEmpty()) {
                return criteriaBuilder.conjunction(); // không lọc gì cả
            }

            List<Predicate> predicates = new ArrayList<>();

            for (String keyword : screenKeywords) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get(SANPHAM_.MAN_HINH)),
                                "%" + keyword.toLowerCase() + "%"));
            }

            // Kết hợp các điều kiện bằng OR: chỉ cần khớp 1 từ khóa là được
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }

}
