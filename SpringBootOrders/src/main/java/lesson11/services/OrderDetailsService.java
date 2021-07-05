package lesson11.services;

import lesson11.model.DTO.OrderDetailsDTO;
import lesson11.repositories.OrderDetailsRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class OrderDetailsService {
    private final OrderDetailsRepository orderDetailsRepository;

    public List<OrderDetailsDTO> getAllOrderDetails(){
        return orderDetailsRepository.findAllDTO();
    }
}
