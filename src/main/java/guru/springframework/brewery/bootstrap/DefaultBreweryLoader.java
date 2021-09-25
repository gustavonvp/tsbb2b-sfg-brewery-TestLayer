package guru.springframework.brewery.bootstrap;

import guru.springframework.brewery.domain.*;
import guru.springframework.brewery.repository.*;
import guru.springframework.brewery.web.model.BeerStyleEnum;
import guru.springframework.brewery.web.model.OrderStatusEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
public class DefaultBreweryLoader implements CommandLineRunner {

    private final BreweryRepository breweryRepository;
    private final BeerRepository beerRepository;
    private final BeerOrderInventoryRepository beerOrderInventoryRepository;
    private final BeerOrderRepository beerOrderRepository;
    private final CustomerRepository customerRepository;

    public DefaultBreweryLoader(BreweryRepository breweryRepository, BeerRepository beerRepository, BeerOrderInventoryRepository beerOrderInventoryRepository, BeerOrderRepository beerOrderRepository, CustomerRepository customerRepository) {
        this.breweryRepository = breweryRepository;
        this.beerRepository = beerRepository;
        this.beerOrderInventoryRepository = beerOrderInventoryRepository;
        this.beerOrderRepository = beerOrderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBreweryData();
    }

    private void loadBreweryData() {
        if (breweryRepository.count() == 0){
            breweryRepository.save(Brewery
                    .builder()
                    .breweryName("Cage Brewing")
                    .build());

            Beer mangoBobs = Beer.builder()
                    .beerName("Mango Bobs")
                    .beerStyle(BeerStyleEnum.IPA)
                    .minOnHand(12)
                    .quantityToBrew(200)
                    .upc(337010000001L)
                    .build();

            beerRepository.save(mangoBobs);

            beerOrderInventoryRepository.save(BeerInventory.builder()
                    .beer(mangoBobs)
                    .quantityOnHand(100)
                    .build());

            Beer galaxyCat = Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle(BeerStyleEnum.PALE_ALE)
                    .minOnHand(12)
                    .quantityToBrew(200)
                    .upc(337010000002L)
                    .build();

            beerRepository.save(galaxyCat);

            beerOrderInventoryRepository.save(BeerInventory.builder()
                    .beer(galaxyCat)
                    .quantityOnHand(100)
                    .build());

            Beer pinball = Beer.builder()
                    .beerName("Pinball Porter")
                    .beerStyle(BeerStyleEnum.PORTER)
                    .minOnHand(12)
                    .quantityToBrew(200)
                    .upc(337010000003L)
                    .build();

            beerRepository.save(pinball);

            beerOrderInventoryRepository.save(BeerInventory.builder()
                    .beer(pinball)
                    .quantityOnHand(100)
                    .build());

            Customer testCustomer = customerRepository.save(Customer
                    .builder()
                    .customerName("Test 1").apiKey(UUID.randomUUID())
                    .build());

            Set<BeerOrderLine> orderLines1 = new HashSet<>();
            orderLines1.add(BeerOrderLine.builder().beer(galaxyCat).orderQuantity(15).quantityAllocated(0).build());
            orderLines1.add(BeerOrderLine.builder().beer(pinball).orderQuantity(7).quantityAllocated(0).build());

            BeerOrder testOrder1 = beerOrderRepository.save(BeerOrder.builder()
                    .orderStatusEnum(OrderStatusEnum.NEW)
                    .customer(testCustomer)
                    .customerRef("testOrder1")
                    .orderStatusCallbackUrl("http://example.com/post")
                    .beerOrderLine(orderLines1)
                    .build());

            orderLines1.forEach(line -> line.setBeerOrder(testOrder1));

            beerOrderRepository.save(testOrder1);
        }
    }
}
