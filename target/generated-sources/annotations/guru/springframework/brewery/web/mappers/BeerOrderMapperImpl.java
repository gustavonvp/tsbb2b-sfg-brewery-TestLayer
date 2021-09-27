package guru.springframework.brewery.web.mappers;

import guru.springframework.brewery.domain.BeerOrder;
import guru.springframework.brewery.domain.BeerOrder.BeerOrderBuilder;
import guru.springframework.brewery.domain.BeerOrderLine;
import guru.springframework.brewery.web.model.BeerOrderDTO;
import guru.springframework.brewery.web.model.BeerOrderDTO.BeerOrderDTOBuilder;
import guru.springframework.brewery.web.model.BeerOrderLineDto;
import guru.springframework.brewery.web.model.BeerOrderLineDto.BeerOrderLineDtoBuilder;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-25T08:59:55-0300",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class BeerOrderMapperImpl implements BeerOrderMapper {

    @Autowired
    private DateMapper dateMapper;

    @Override
    public BeerOrderDTO beerOrderToDto(BeerOrder beerOrder) {
        if ( beerOrder == null ) {
            return null;
        }

        BeerOrderDTOBuilder beerOrderDTO = BeerOrderDTO.builder();

        beerOrderDTO.id( beerOrder.getId() );
        if ( beerOrder.getVersion() != null ) {
            beerOrderDTO.version( beerOrder.getVersion().intValue() );
        }
        beerOrderDTO.createdDate( dateMapper.asOffsetDateTime( beerOrder.getCreatedDate() ) );
        beerOrderDTO.lastModifiedDate( dateMapper.asOffsetDateTime( beerOrder.getLastModifiedDate() ) );
        beerOrderDTO.customerRef( beerOrder.getCustomerRef() );
        beerOrderDTO.orderStatus( beerOrder.getOrderStatus() );
        beerOrderDTO.orderStatusCallbackUrl( beerOrder.getOrderStatusCallbackUrl() );

        return beerOrderDTO.build();
    }

    @Override
    public BeerOrder dtoToBeerOrder(BeerOrderDTO dto) {
        if ( dto == null ) {
            return null;
        }

        BeerOrderBuilder beerOrder = BeerOrder.builder();

        beerOrder.id( dto.getId() );
        if ( dto.getVersion() != null ) {
            beerOrder.version( dto.getVersion().longValue() );
        }
        beerOrder.createdDate( dateMapper.asTimestamp( dto.getCreatedDate() ) );
        beerOrder.lastModifiedDate( dateMapper.asTimestamp( dto.getLastModifiedDate() ) );
        beerOrder.customerRef( dto.getCustomerRef() );
        beerOrder.orderStatusCallbackUrl( dto.getOrderStatusCallbackUrl() );

        return beerOrder.build();
    }

    @Override
    public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line) {
        if ( line == null ) {
            return null;
        }

        BeerOrderLineDtoBuilder beerOrderLineDto = BeerOrderLineDto.builder();

        beerOrderLineDto.id( line.getId() );
        if ( line.getVersion() != null ) {
            beerOrderLineDto.version( line.getVersion().intValue() );
        }
        beerOrderLineDto.createdDate( dateMapper.asOffsetDateTime( line.getCreatedDate() ) );
        beerOrderLineDto.lastModifiedDate( dateMapper.asOffsetDateTime( line.getLastModifiedDate() ) );
        beerOrderLineDto.orderQuantity( line.getOrderQuantity() );

        return beerOrderLineDto.build();
    }
}
