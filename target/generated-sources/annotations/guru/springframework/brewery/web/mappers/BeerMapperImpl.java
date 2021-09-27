package guru.springframework.brewery.web.mappers;

import guru.springframework.brewery.domain.Beer;
import guru.springframework.brewery.domain.Beer.BeerBuilder;
import guru.springframework.brewery.web.model.BeerDTO;
import guru.springframework.brewery.web.model.BeerDTO.BeerDTOBuilder;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-25T08:59:55-0300",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class BeerMapperImpl implements BeerMapper {

    @Autowired
    private DateMapper dateMapper;

    @Override
    public BeerDTO beerToBeerDto(Beer beer) {
        if ( beer == null ) {
            return null;
        }

        BeerDTOBuilder beerDTO = BeerDTO.builder();

        beerDTO.id( beer.getId() );
        if ( beer.getVersion() != null ) {
            beerDTO.version( beer.getVersion().intValue() );
        }
        beerDTO.createdDate( dateMapper.asOffsetDateTime( beer.getCreatedDate() ) );
        beerDTO.lastModifiedDate( dateMapper.asOffsetDateTime( beer.getLastModifiedDate() ) );
        beerDTO.beerName( beer.getBeerName() );
        beerDTO.upc( beer.getUpc() );
        beerDTO.price( beer.getPrice() );

        return beerDTO.build();
    }

    @Override
    public Beer beerDtoToBeer(BeerDTO beerDto) {
        if ( beerDto == null ) {
            return null;
        }

        BeerBuilder beer = Beer.builder();

        beer.id( beerDto.getId() );
        if ( beerDto.getVersion() != null ) {
            beer.version( beerDto.getVersion().longValue() );
        }
        beer.createdDate( dateMapper.asTimestamp( beerDto.getCreatedDate() ) );
        beer.lastModifiedDate( dateMapper.asTimestamp( beerDto.getLastModifiedDate() ) );
        beer.beerName( beerDto.getBeerName() );
        beer.upc( beerDto.getUpc() );
        beer.price( beerDto.getPrice() );

        return beer.build();
    }
}
