package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository //Component 스캔의 대상이 됨
public class ItemRepository {

    //스프링 안에서 쓰면 싱글톤이라 static까지 설정 안해도 되긴 하지만, 따로 new해서 쓰거나 static안하면 객채 생성한만큼 따로 store가 생성되서?
    private static final Map<Long, Item> store = new HashMap<>(); //실무에서는 여러 thread가 동시에 접근할때는 HashMap쓰면 안됨, ConcurrentHashMap사용해야함
    private static long sequence = 0L; //동시에 여러명이 접근 할 때는 값이 꼬일수도 있어서 Long을 사용하면 안되고 automicLong같이 다른걸 사용해야 함

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(),item);
        return item;
    }

    public Item findById(Long id){
        return store.get(id);
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam){
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
        store.put(itemId, findItem);
        //store.put(itemId,updateItem);
    }


    public void clearStore() {
        store.clear();
    }
}
