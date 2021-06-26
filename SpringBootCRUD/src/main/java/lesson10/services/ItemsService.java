package lesson10.services;

import lesson10.model.Item;
import lesson10.utils.models.ItemsList;
import lesson10.repositories.ItemRepository;
import lesson10.utils.models.LongList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemsService {
    private final ItemRepository repository;

    // CREATE
    public Optional<Item> save(Item item) {
        if (priceIsCorrect(item)) {
            return Optional.ofNullable(repository.save(item));
        }
        return Optional.ofNullable(null);
    }

    public Optional<List<Item>> saveAll(ItemsList itemsList) {
        if (priceIsCorrect(itemsList)) {
            return Optional.ofNullable(repository.saveAll(itemsList.getItems()));
        }
        return Optional.ofNullable(null);
    }

    // READ
    public Optional<Item> findItem(long id) {
        return repository.findById(id);
    }

    public List<Item> findAllItems() {
        return repository.findAll();
    }

    // UPDATE
    public Optional<Item> updateItem(Item item) {
        if (priceIsCorrect(item)) {
            return Optional.ofNullable(repository.save(item));
        }
        return Optional.ofNullable(null);
    }

    public Optional<List<Item>> updateItems(ItemsList itemsList) {
        if (priceIsCorrect(itemsList)) {
            return Optional.ofNullable(repository.saveAll(itemsList.getItems()));
        }
        return Optional.ofNullable(null);
    }

    // DELETE
    public void deleteItem(Item item){
        repository.delete(item);
    }
    public void deleteItems(ItemsList itemsList){
        repository.deleteAll(itemsList.getItems());
    }
    public void deleteItemById(Long id){
        repository.deleteById(id);
    }
    public void deleteItemsByIds(LongList ids){
        repository.deleteAllById(ids.getIDs());
    }

    private boolean priceIsCorrect(Item item){
        if (item.getPrice() <= 0) {
            return false;
        }
        return true;
    }

    private boolean priceIsCorrect(ItemsList items){
        for (Item item: items.getItems()) {
            if (item.getPrice() <= 0) {
                return false;
            }
        }
        return true;
    }

    public String getMessageIncorrectPriceItem (Item item){
        return String.format("Set incorrect price (0 or lower) to item with id id '%1$s' and title '%2$s'", item.getId(), item.getTitle());
    }

    public String getMessageIncorrectPriceItems (ItemsList items){
        String errorMessage = "";
        for (Item item: items.getItems()) {
            if (item.getPrice() <= 0) {
                errorMessage = String.format("%1$s Incorrect price (0 or lower) in item with id '%2$s' and title '%3$s'; \r\n", errorMessage, item.getId(), item.getTitle());
            }
        }
        return errorMessage.substring(0, (errorMessage.length() - 5));
    }
}
