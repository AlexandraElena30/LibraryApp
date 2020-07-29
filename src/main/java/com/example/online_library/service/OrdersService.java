package com.example.online_library.service;

import com.example.online_library.domain.entity.OrdersEntity;
import com.example.online_library.domain.model.OrdersDTO;
import com.example.online_library.domain.model.ProductsDTO;
import com.example.online_library.exception.OrdersNotFoundException;
import com.example.online_library.mapper.OrdersEntityToOrdersMapper;
import com.example.online_library.mapper.OrdersToOrdersEntityMapper;
import com.example.online_library.repository.OrdersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Validated
public class OrdersService {

    private final OrdersRepository repository;

    private final OrdersEntityToOrdersMapper ordersEntityToOrdersMapper;

    private final OrdersToOrdersEntityMapper ordersToOrdersEntityMapper;

    public OrdersDTO create(@Valid OrdersDTO product) {
        OrdersEntity ordersEntity = ordersToOrdersEntityMapper.convert(product);
        OrdersEntity savedEntity = repository.save(ordersEntity);
        return ordersEntityToOrdersMapper.convert(savedEntity);
    }

    public OrdersDTO addNewOrder(ArrayList<ProductsDTO> productList, BigDecimal price) {

        OrdersDTO newOrd = new OrdersDTO();
        newOrd.setUserID(CurrentUserService.currentUser.getUserID());
        newOrd.setOrderDate(LocalDate.now());
        String orderProducts = "";
        int maxDays = 0;

        for (ProductsDTO prd : productList) {

            orderProducts += prd.getProductID() + "#" + prd.getQuantity() + ";";
            if (prd.getShippingDays() > maxDays) {
                maxDays = prd.getShippingDays();
            }
        }
        newOrd.setOrderProducts(orderProducts);
        newOrd.setStatus("Processed");
        newOrd.setPrice(price);
        LocalDate ld = LocalDate.now();
        DayOfWeek d = ld.getDayOfWeek();
        if (d == DayOfWeek.SATURDAY || d == DayOfWeek.SUNDAY) {
            maxDays = maxDays + 2;
        }
        newOrd.setShippingDate(ld.plusDays(maxDays));

        OrdersEntity ordersEntity = ordersToOrdersEntityMapper.convert(newOrd);
        OrdersEntity savedEntity = repository.save(ordersEntity);
        return ordersEntityToOrdersMapper.convert(savedEntity);
    }

    public OrdersDTO findById(long orderId) {
        return repository.findById(orderId)
                .map(ordersEntityToOrdersMapper::convert)
                .orElseThrow(() -> new OrdersNotFoundException("No product found!"));
    }

    public List<OrdersDTO> getAllForUser(Long id) {
        return repository.getAllForUser(id)
                .stream()
                .map(ordersEntityToOrdersMapper::convert)
                .collect(Collectors.toList());
    }

    public void updateTransactional(OrdersDTO orders) {
        OrdersEntity existingEntity = repository.findById(orders.getIdCode())
                .orElseThrow(() -> new OrdersNotFoundException("The Order with id code provided cannot be found!"));
        updateFields(existingEntity, orders);
    }

    private void updateFields(OrdersEntity existingEntity, OrdersDTO products) {
        existingEntity.setStatus(products.getStatus());
    }
}

