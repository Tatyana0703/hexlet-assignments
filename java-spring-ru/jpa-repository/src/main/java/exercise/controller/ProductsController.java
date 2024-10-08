package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Sort;
import java.util.List;
import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @GetMapping(path = "")
    public List<Product> index(@RequestParam(defaultValue = "-1") Integer min,
                               @RequestParam(defaultValue = "-1") Integer max) {
        if (min != -1 && max != -1) {
            return productRepository.findByPriceBetweenOrderByPrice(min, max);
        } else if (min != -1) {
            return productRepository.findByPriceGreaterThanEqualOrderByPrice(min);
        } else if (max != -1) {
            return productRepository.findByPriceLessThanEqualOrderByPrice(max);
        }
        return productRepository.findAll(Sort.by(Sort.Order.asc("price")));
    }
    // END

    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
    }
}
