package lesson10.utils.models;

import lesson10.model.Item;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ItemsList {
    private List<Item> items;

    public ItemsList(List<Item> items){
        items = items;
    }
}
