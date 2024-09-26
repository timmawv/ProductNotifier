package avlyakulov.timur.controller;

import avlyakulov.timur.service.ProductService;
import avlyakulov.timur.service.dto.CreateProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createProduct(@RequestBody CreateProductDto createProductDto) {
        String productId = productService.createProduct(createProductDto);
        return productId;
    }
}