package guru.springframework.brewery.services;

import guru.springframework.brewery.repository.BeerRepository;
import guru.springframework.brewery.web.mappers.BeerMapper;
import guru.springframework.brewery.web.model.BeerDTO;
import guru.springframework.brewery.web.model.BeerStyleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;


import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.BDDMockito.given;


public class BeerServiceImplTest {

    BeerDTO validBeer;

    BeerService beerService;

    @Mock
    BeerRepository beerRepository;

    BeerMapper beerMapper = BeerMapper.INSTANCE;


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        beerService = new BeerServiceImpl(beerMapper, beerRepository);
        validBeer = BeerDTO.builder().id(UUID.randomUUID())
                .version(1)
                .beerName("Beer1")
                .beerStyleEnum(BeerStyleEnum.PALE_ALE)
                .price(new BigDecimal("12.99"))
                .quantityOnHand(4)
                .upc(123456789012L)
                .createdDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .build();

    }

    @Test
    public void listBeers() throws Exception {

        given(beerService.findBeerById(any())).willReturn(validBeer);

        assertNotNull(validBeer);

        //then
        assertEquals(validBeer.getId(),beerRepository.findAllById(() -> isNotNull()));


    }

    @Test
    public void findBeerById() {
    }
}