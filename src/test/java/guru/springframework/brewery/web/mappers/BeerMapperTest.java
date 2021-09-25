package guru.springframework.brewery.web.mappers;

import guru.springframework.brewery.domain.Beer;
import guru.springframework.brewery.web.model.BeerDTO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BeerMapperTest {

    BeerMapper beerMapper = BeerMapper.INSTANCE;

    @Test
    void beerToBeerDto() {
        //given
        Beer beer = new Beer();
        beer.setBeerName("Brahma");
        beer.setId(UUID.fromString(String.valueOf(UUID.randomUUID())));

        //when
        BeerDTO beerDTO = beerMapper.beerToBeerDto(beer);

        //then
        assertEquals(Long.valueOf(1L), beerDTO.getId());
        assertEquals("Joe", beerDTO.getBeerName());
    }

    @Test
    void beerDtoToBeer() {
    }
}