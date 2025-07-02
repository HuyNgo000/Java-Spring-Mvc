package vn.huyngo.phoneshop.controller.client;

import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.huyngo.phoneshop.service.ProductService;
import vn.huyngo.phoneshop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

class CartRequest {
    private long productId;
    private long quantity;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

}

@RestController
public class CartApi {
    private final ProductService productService;
    private final UserService userService;

    public CartApi(ProductService productService, UserService userService) {
        this.userService = userService;
        this.productService = productService;
    }

    @PostMapping("/api/add-product-to-cart")
    public ResponseEntity<Integer> addProductToCart(@RequestBody CartRequest cartRequest,
            HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");

        int result = this.productService.handleAddProductCart(email, cartRequest.getProductId(), session,
                cartRequest.getQuantity());

        if (result == -1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(-1); // Không đủ hàng
        }

        if (result == -2) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(-2); // Lỗi hệ thống
        }

        return ResponseEntity.ok(result); // Trả về tổng số sản phẩm trong giỏ
    }

}
