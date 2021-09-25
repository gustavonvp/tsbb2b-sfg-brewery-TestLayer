package guru.springframework.brewery.services;

import guru.springframework.brewery.web.model.BeerOrderDTO;
import guru.springframework.brewery.web.model.BeerOrderPagedList;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BeerOrderService {
         BeerOrderPagedList listOrders(UUID customerId, Pageable pageable);

        BeerOrderDTO placeOrder(UUID customerId, BeerOrderDTO beerOrderDto);

         BeerOrderDTO getOrderById(UUID customerId, UUID orderId);

        void pickupOrder(UUID customerId, UUID orderId);
}
