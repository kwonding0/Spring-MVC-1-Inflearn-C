package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;
    @GetMapping
    public String items(Model model){
        model.addAttribute("items", itemRepository.findAll());

        return "/basic/items";
    }

    /*테스트용 데이터 추가*/
    @PostConstruct //?????
    public void init(){
        itemRepository.save(new Item("itemA", 10000, 1000));
        itemRepository.save(new Item("itemB", 5000, 500));
    }

    @GetMapping("/{itemId}")
    public String items(@PathVariable Long itemId, Model model){
        model.addAttribute("item",itemRepository.findById(itemId));

        return "/basic/item";
    }

    @GetMapping("/add")
    public String add(){
        return "/basic/addForm";
    }

    /*@PostMapping("/add")*/
    public String saveV1(@RequestParam String itemName, @RequestParam Integer price, Integer quantity, Model model){
        Item item = new Item(itemName, price, quantity);
        itemRepository.save(item);
        model.addAttribute("item", item);

        return "/basic/item";
    }

    /*@PostMapping("/add")*/
    public String saveV2(@ModelAttribute("item") Item item){
        //Item item = new Item(itemName, price, quantity); ModelAttribute가 대신 처리해줌
        itemRepository.save(item);
        //model.addAttribute("item", item); @ModelAttribute가 ("이름")으로 model에 담아줌

        return "/basic/item";
    }

    /*@PostMapping("/add")*/
    public String saveV3(@ModelAttribute Item item){
        itemRepository.save(item);
        //model.addAttribute("item", item); ("이름")을 설정 안하면 class명(Item)의 앞글자만 소문자로 바꿔(item) model에 담아줌

        return "/basic/item";
    }

    /*@PostMapping("/add")*/
    public String saveV4(Item item){ //단순 타입이 아닌 객체는 자동으로 @ModelAttribute가 설정되기 때문에 생략가능
        itemRepository.save(item);
        //model.addAttribute("item", item); ("이름")을 설정 안하면 class명(Item)의 앞글자만 소문자로 바꿔(item) model에 담아줌

        return "/basic/item";
    }

    /*@PostMapping("/add")*/
    public String saveV5(Item item){ //단순 타입이 아닌 객체는 자동으로 @ModelAttribute가 설정되기 때문에 생략가능
        itemRepository.save(item);
        //model.addAttribute("item", item); ("이름")을 설정 안하면 class명(Item)의 앞글자만 소문자로 바꿔(item) model에 담아줌

        return "redirect:/basic/items/"+item.getId();
    }

    @PostMapping("/add")
    public String saveV6(Item item, RedirectAttributes redirectAttributes){ //단순 타입이 아닌 객체는 자동으로 @ModelAttribute가 설정되기 때문에 생략가능
        itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", item.getId());
        redirectAttributes.addAttribute("status",true);

        //model.addAttribute("item", item); ("이름")을 설정 안하면 class명(Item)의 앞글자만 소문자로 바꿔(item) model에 담아줌

        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/edit/{itemId}")
    public String edit(@PathVariable Long itemId, Model model){
        model.addAttribute("item", itemRepository.findById(itemId));

        return "/basic/editForm";
    }

    @PostMapping("/edit/{itemId}")
    public String update(@PathVariable Long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId,item);

        return "redirect:/basic/item/{itemId}";
    }
}
