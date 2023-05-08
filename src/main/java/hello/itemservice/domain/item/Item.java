package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Data //@RequiredArgsConstructor @ToString @EqualsAndHashCode 생성자들을 다 만들어주기 때문에 위험함. 핵심도메인 모델에 사용하면 위험함. 예측하지 못하게 동작할 가능성 있음
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item(){

    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
