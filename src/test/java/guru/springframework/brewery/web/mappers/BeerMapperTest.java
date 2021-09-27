package guru.springframework.brewery.web.mappers;

import guru.springframework.brewery.domain.Beer;
import guru.springframework.brewery.repository.BeerRepository;
import guru.springframework.brewery.services.BeerService;
import guru.springframework.brewery.services.BeerServiceImpl;
import guru.springframework.brewery.web.model.BeerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BeerMapperTest {

    private BeerMapper mapperMock;
    private BeerRepository repositoryMock;
    private BeerService beerService;
    private final UUID uuid = UUID.randomUUID();


    @BeforeEach
    public void setup() {
        repositoryMock = mock(BeerRepository.class);
        mapperMock = mock(BeerMapper.class);
        beerService = new BeerServiceImpl(repositoryMock, mapperMock);
    }

    @Test
    void beerToBeerDto() {

        // given
        Beer mockEntity = new Beer();
        mockEntity.setBeerName("Brahma");
        mockEntity.setId(uuid);


        BeerDTO mockDto = new BeerDTO();
        mockDto.setBeerName("Brahma");
        mockDto.setId(uuid);


        when(repositoryMock.findAll())
                .thenReturn(Collections.singletonList(mockEntity));
        when(mapperMock.beerToBeerDto(mockEntity))
                .thenReturn(mockDto);

        //then
        assertEquals(uuid, mockDto.getId());
        assertEquals("Brahma", mockDto.getBeerName());

        // -------------------- //

        // Public Converter Data Use Converter Simple like that !!!
        //when
        BeerDTO beerDTO = mapperMock.beerToBeerDto(mockEntity);

        //then
        assertEquals(uuid, beerDTO.getId());
        assertEquals("Brahma", beerDTO.getBeerName());

    }

    @Test
    void beerDtoToBeer() {
        // given
        Beer mockEntity = new Beer();
        mockEntity.setBeerName("Brahma");
        mockEntity.setId(uuid);


        BeerDTO mockDto = new BeerDTO();
        mockDto.setBeerName("Brahma");
        mockDto.setId(uuid);


        when(repositoryMock.findAll())
                .thenReturn(Collections.singletonList(mockEntity));
        when(mapperMock.beerDtoToBeer(mockDto))
                .thenReturn(mockEntity);

        //then
        assertEquals(uuid, mockDto.getId());
        assertEquals("Brahma", mockDto.getBeerName());

        // -------------------- //

        // Public Converter Data Use Converter Simple like that !!!
        //when
        Beer beer = mapperMock.beerDtoToBeer(mockDto);

        //then
        assertEquals(uuid, beer.getId());
        assertEquals("Brahma", beer.getBeerName());


    }
}