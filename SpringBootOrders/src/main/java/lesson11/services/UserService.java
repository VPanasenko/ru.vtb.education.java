package lesson11.services;

import lesson11.model.DTO.UserDTO;
import lesson11.model.User;
import lesson11.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDTO> getAllUsers(){
        return userRepository.findAllDTO();
    }

    public Optional<UserDTO> getUserById(long id){
        return userRepository.findDTOById(id);
    }
}
