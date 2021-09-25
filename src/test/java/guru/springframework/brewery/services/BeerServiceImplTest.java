package guru.springframework.brewery.services;

import guru.springframework.brewery.bootstrap.DefaultBreweryLoader;
import guru.springframework.brewery.domain.Beer;
import guru.springframework.brewery.repository.*;
import guru.springframework.brewery.web.mappers.BeerMapper;
import guru.springframework.brewery.web.model.BeerDTO;
import guru.springframework.brewery.web.model.BeerStyleEnum;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@DataJpaTest
public class BeerServiceImplTest {

    BeerService beerService;

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BeerOrderRepository beerOrderRepository;

    @Autowired
    BeerOrderInventoryRepository beerOrderInventoryRepository;

    @Autowired
    BreweryRepository breweryRepository;

    private final UUID uuid = UUID.randomUUID();

    @BeforeEach
    public void setUp() throws Exception {
        System.out.println("Loading Customer Data");
        System.out.println(beerRepository.findAll().size());

        //setup data for testing
        DefaultBreweryLoader bootstrap = new DefaultBreweryLoader(breweryRepository,beerRepository,beerOrderInventoryRepository,beerOrderRepository,customerRepository);
        bootstrap.run(); //load data
        MockitoAnnotations.initMocks(this);
        beerService = new BeerServiceImpl(BeerMapper.INSTANCE, beerRepository);
    }


    @Test
    public void listBeersAndShowDifferenceBetweenBootstrapDataAndTestObjectData() throws Exception {
        String beerName = "Brahma";
        UUID id  = getCustomerIdValue();

        String beerName1 = "Antartica";
        UUID id1 = getCustomerIdValue1();

        String beerName2 = "Bohemia";
        UUID id2 = getCustomerIdValue2();

        Beer originalBeer = beerRepository.getOne(id);
        assertNotNull(originalBeer);

        Beer originalBeer1 = beerRepository.getOne(id1);
        assertNotNull(originalBeer1);

        Beer originalBeer2 = beerRepository.getOne(id2);
        assertNotNull(originalBeer2);

        //save original beer
        String originalBeerName = originalBeer.getBeerName();
        String originalBeerName1 = originalBeer1.getBeerName();
        String originalBeerName2 = originalBeer2.getBeerName();

        Beer updatedBeer = beerRepository.findById(id).get();
        Beer updatedBeer1 = beerRepository.findById(id1).get();
        Beer updatedBeer2 = beerRepository.findById(id2).get();

        List<String> beerListBootstrap = Arrays.asList(beerName,beerName1, beerName2);
        List<String> beerList = Arrays.asList(originalBeerName,originalBeerName1, originalBeerName2);

        assertNotNull(updatedBeer);
        assertNotNull(updatedBeer1);
        assertNotNull(updatedBeer2);

        assertNotNull(beerList);

        assertNotEquals(beerListBootstrap,beerList);

        assertThat(beerName, CoreMatchers.not(equalTo(originalBeer.getBeerName())));
        assertThat(beerName1, CoreMatchers.not(equalTo(originalBeer1.getBeerName())));
        assertThat(beerName2, CoreMatchers.not(equalTo(originalBeer2.getBeerName())));
    }

    @Test
    public void findAllBeers() throws Exception {

        // given
        Beer mockEntity = new Beer();
        mockEntity.setBeerName("Brahma");
        mockEntity.setId(uuid);

        Beer mockEntity1 = new Beer();
        mockEntity.setBeerName("Brahma1");
        mockEntity.setId(uuid);

        Beer mockEntity2 = new Beer();
        mockEntity2.setBeerName("Brahma2");
        mockEntity2.setId(uuid);


        List<Beer> beers = Arrays.asList(mockEntity,mockEntity1,mockEntity2);

        beerRepository.findAll().containsAll(beers);

        //then
        assertEquals(3,beers.size());

    }


    private UUID getCustomerIdValue() {
        List<Beer> beers = beerRepository.findAll();

        System.out.println("Beers Found: " + beers.size());

        //return first id
        return beers.get(0).getId();
    }

    private UUID getCustomerIdValue1() {
        List<Beer> beers = beerRepository.findAll();

        System.out.println("Beers Found: " + beers.size());

        //return first id
        return beers.get(1).getId();
    }

    private UUID getCustomerIdValue2() {
        List<Beer> beers = beerRepository.findAll();

        System.out.println("Beers Found: " + beers.size());

        //return first id
        return beers.get(2).getId();
    }
}