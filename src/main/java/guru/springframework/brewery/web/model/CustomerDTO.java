package guru.springframework.brewery.web.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class CustomerDTO extends BaseItem {

    @Builder
    public CustomerDTO(UUID id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate, String name, List<CreditCardDTO> creditCards) {
        super(id, version, createdDate, lastModifiedDate);
        this.name = name;
        this.creditCards = creditCards;
    }

    private String name;
    private List<CreditCardDTO> creditCards;
}
