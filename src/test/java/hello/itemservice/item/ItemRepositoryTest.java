package hello.itemservice.item;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Item saveItem = new Item("itemA", 10000,10);

        //when
        itemRepository.save(saveItem);

        //then
        Item savedItem = itemRepository.findById(saveItem.getId());
        Assertions.assertThat(saveItem).isEqualTo(savedItem);
    }

    @Test
    void findAll(){
        //given
        Item saveItem = new Item("itemA", 10000,10);
        Item saveItem2 = new Item("itemA", 10000,10);

        //when
        itemRepository.save(saveItem);
        itemRepository.save(saveItem2);

        //then
        List<Item> allItemList = itemRepository.findAll();
        Assertions.assertThat(allItemList.size()).isEqualTo(2);
    }

    @Test
    void updateItem() {
        Item saveItem = new Item("itemA", 10000,10);
        itemRepository.save(saveItem);
        Long id = saveItem.getId();

        //when
        Item updateParam = new Item("itemB", 5000, 5);
        itemRepository.update(id, updateParam);

        //then
        Item updateItem = itemRepository.findById(saveItem.getId());
        Assertions.assertThat(updateParam.getItemName()).isEqualTo(updateItem.getItemName());
        Assertions.assertThat(updateParam.getPrice()).isEqualTo(updateItem.getPrice());
        Assertions.assertThat(updateParam.getQuantity()).isEqualTo(updateItem.getQuantity());
    }
}
