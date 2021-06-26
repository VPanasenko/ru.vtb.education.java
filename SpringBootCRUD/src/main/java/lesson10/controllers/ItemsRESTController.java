package lesson10.controllers;

import lesson10.model.Item;
import lesson10.utils.models.ItemsList;
import lesson10.services.ItemsService;
import lesson10.utils.exceptions.IncorrectPriceException;
import lesson10.utils.exceptions.ResourceNotFoundException;
import lesson10.utils.models.LongList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemsRESTController {
    private final ItemsService ItemsService;

    // CREATE
    @PostMapping("/createOne")
    @ResponseStatus(HttpStatus.CREATED)
    public Item createNewItem(@RequestBody Item item) {
        return ItemsService.save(item).orElseThrow(() -> new IncorrectPriceException(ItemsService.getMessageIncorrectPriceItem(item)));
    }

    @PostMapping("/createMany")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Item> createNewItem(@RequestBody ItemsList items) {
        return ItemsService.saveAll(items).orElseThrow(() -> new IncorrectPriceException(ItemsService.getMessageIncorrectPriceItems(items)));
    }

    // READ
    @GetMapping("/{id}")
    public Item getItem(@PathVariable Long id) {
        return ItemsService.findItem(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Item with id %1$s not found", id)));
    }

    @GetMapping
    public List<Item> getAllItems() {
        return ItemsService.findAllItems();
    }

    // UPDATE
    @PostMapping("/updateOne")
    @ResponseStatus(HttpStatus.OK)
    public Item updateItem(@RequestBody Item item) {
        return ItemsService.updateItem(item).orElseThrow(() -> new IncorrectPriceException(ItemsService.getMessageIncorrectPriceItem(item)));
    }

    @PostMapping("/updateMany")
    @ResponseStatus(HttpStatus.OK)
    public List<Item> updateItems(@RequestBody ItemsList items) {
        return ItemsService.updateItems(items).orElseThrow(() -> new IncorrectPriceException(ItemsService.getMessageIncorrectPriceItems(items)));
    }

    // DELETE
    @DeleteMapping("/deleteOne")
    public void deleteItem(@RequestBody Item item){
        ItemsService.deleteItem(item);
    }

    @DeleteMapping("/deleteMany")
    public void deleteItems(@RequestBody ItemsList items){
        ItemsService.deleteItems(items);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteItemById(@PathVariable Long id){
        ItemsService.deleteItemById(id);
    }

    @DeleteMapping("/deleteManyByIds")
    public void deleteItemsByIds(@RequestBody LongList ids){
        ItemsService.deleteItemsByIds(ids);
    }
}
