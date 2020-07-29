package com.example.online_library.service;

import com.example.online_library.domain.entity.ProductsEntity;
import com.example.online_library.domain.model.ProductsDTO;
import com.example.online_library.exception.ProductsNotFoundException;
import com.example.online_library.mapper.ProductsEntityToProductsMapper;
import com.example.online_library.mapper.ProductsToProductsEntityMapper;
import com.example.online_library.repository.ProductsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@Service
@AllArgsConstructor
@Validated
public class ProductsService {

    private final ProductsRepository repository;

    private final ProductsEntityToProductsMapper productsEntityToProductsMapper;

    private final ProductsToProductsEntityMapper productsToProductsEntityMapper;

    public ProductsDTO create(@Valid ProductsDTO product) {
        ProductsEntity productsEntity = productsToProductsEntityMapper.convert(product);
        ProductsEntity savedEntity = repository.save(productsEntity);
        return productsEntityToProductsMapper.convert(savedEntity);
    }

    public ProductsDTO findById(long productId) {
        return repository.findById(productId)
                .map(productsEntityToProductsMapper::convert)
                .orElseThrow(() -> new ProductsNotFoundException("No product found"));
    }

    public List<ProductsDTO> getAll() {
        return repository.getAll()
                .stream()
                .map(productsEntityToProductsMapper::convert)
                .collect(Collectors.toList());
    }

    public List<ProductsDTO> searchBy(String name) {
        return repository.findByNameContains(name)
                .stream()
                .map(productsEntityToProductsMapper::convert)
                .collect(Collectors.toList());
    }

    public List<ProductsDTO> getAllInList() {
        List<Long> productIds = new ArrayList<Long>();
        List<Integer> quantities = new ArrayList<Integer>();

        if (CurrentUserService.currentUser.getBasket().equals("") || CurrentUserService.currentUser.getBasket() == null) {
            return null;
        }
        String[] productstWithQuantity = CurrentUserService.currentUser.getBasket().split(";");

        for (String prod : productstWithQuantity) {
            int delim = prod.indexOf("#");
            productIds.add(Long.parseLong(prod.substring(0, delim)));
            quantities.add(Integer.parseInt(prod.substring(delim + 1)));
        }
        List<ProductsEntity> l = repository.findByproductIDIn(productIds);
        List<ProductsDTO> ret = l.stream()
                .map(productsEntityToProductsMapper::convert)
                .collect(Collectors.toList());
        int idx;
        for (ProductsDTO prd : ret) {
            idx = productIds.indexOf(prd.getProductID());
            prd.setQuantity(quantities.get(idx));
        }
        return ret;
    }

    public void updateDatabase(ProductsDTO products) {

        ProductsEntity existingEntity = repository.findById(products.getProductID())
                .orElseThrow(() -> new ProductsNotFoundException("The products with id provided cannot be found"));

        updateFields(existingEntity, products);
        repository.save(existingEntity);
    }

    private void updateFields(ProductsEntity existingEntity, ProductsDTO products) {
        existingEntity.setQuantity(products.getQuantity());
    }

    public void addRating(long productId, int stars) {
        ProductsDTO prd = repository.findById(productId)
                .map(productsEntityToProductsMapper::convert)
                .orElseThrow(() -> new ProductsNotFoundException("No product found"));

        Double newRating = ((prd.getRating().doubleValue() * prd.getTotalrating()) + stars) / (prd.getTotalrating() + 1);
        prd.setRating(BigDecimal.valueOf(newRating).setScale(2, RoundingMode.HALF_EVEN));
        prd.setTotalrating(prd.getTotalrating() + 1);
        ProductsEntity prdEntity = productsToProductsEntityMapper.convert(prd);
        repository.save(prdEntity);
    }

    public List<ProductsDTO> findByCategory(String category) {
        return repository.findAllByCategory(category).stream()
                .map(productsEntityToProductsMapper::convert)
                .collect(Collectors.toList());
    }

    public BigDecimal getPrice(List<ProductsDTO> prdList, Short promoType, int promoAmount) {
        BigDecimal price = new BigDecimal(0.00);
        for (ProductsDTO prd : prdList) {
            price = price.add(prd.getPrice().multiply(BigDecimal.valueOf(prd.getQuantity())));
        }
        if (promoType != 0) {
            if (promoType == 1) {
                BigDecimal pr = price.multiply(BigDecimal.valueOf(promoAmount / 100.00));
                price = price.subtract(pr);
            }
            if (promoType == 2) {
                price = price.subtract(BigDecimal.valueOf(promoAmount));
            }
        }
        return price;
    }
}
