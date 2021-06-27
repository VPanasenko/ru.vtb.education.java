package lesson11.utils.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LongList {
    private List<Long> IDs;

    public LongList(List<Long> ids){
        IDs = ids;
    }
}
