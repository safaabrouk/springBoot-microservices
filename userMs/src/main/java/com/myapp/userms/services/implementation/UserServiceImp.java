package com.myapp.userms.services.implementation;

import com.myapp.userms.entities.User;
import com.myapp.userms.repositories.UserRepository;
import com.myapp.userms.services.UserService;
import com.myapp.userms.tools.dto.UserMapper;
import com.myapp.userms.tools.dto.UserRequestDto;
import com.myapp.userms.tools.dto.UserResponseDto;
import com.myapp.userms.tools.extrenal.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final String addressUrl = "http://localhost:8081/api/address";
    private final RestTemplate restTemplate = new RestTemplate();

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<UserResponseDto> getAllUsers() {

        return userRepository.findAll().stream()
                .map(user -> {
                    Address address = restTemplate.getForObject(addressUrl +"/"+ user.getIdAddress(), Address.class);
                    return UserMapper.fromUserAddressToResponseDto(user, address);
                })
                .collect(Collectors.toList());
        /*
        Method 1
        List<User> users = userRepository.findAll();
        List<UserResponseDto> response = new ArrayList<>();
        for(User u : users){
            Address address = restTemplate.getForObject(addressUrl + u.getIdAddress(), Address.class);
            response.add(UserMapper.fromUserAddressToResponseDto(u,address));
        }
        return response;
        */
        // userRepository.findAll().stream().map(UserMapper::fromUserToUserResponseDto).collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            // Get address of user :
            Address address = restTemplate
                    .getForObject(addressUrl+"/"+user.get().getIdAddress(),Address.class);
            return UserMapper.fromUserAddressToResponseDto(user.get(),address);
        }
        return null;
    }

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        // Add address
        Address addressEntity = new Address(0,"street test","Casablanca","Maroc");
        ResponseEntity<Address> addressResponse = restTemplate.postForEntity(addressUrl, addressEntity, Address.class);
        if(addressResponse.getStatusCode().is2xxSuccessful()){
            log.info("Address response : {}",addressResponse);

            // Add user
            userRequestDto.setAddress(addressResponse.getBody());
            User createdUser = userRepository.save(UserMapper.fromUserRequestDtoToUser(userRequestDto));
            log.info("created user {}",createdUser);
            return UserMapper.fromUserAddressToResponseDto(createdUser,addressResponse.getBody());

        }

        log.warn("Address creation failed");
        return null;
    }

    /*@Override
    public UserResponseDto getUserByEmail(String email) {
        return null;
    }*/

    @Override
    public UserResponseDto updateUser(int id, UserRequestDto userRequestDto) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User updatedUser = user.get();
            log.info("UserRequestDto {}" , userRequestDto);

            // Update address
            log.info("Updating address ... ");
            restTemplate.put(addressUrl+"/"+updatedUser.getIdAddress(), userRequestDto.getAddress(), Address.class );
            log.info("Finish Updating address ... ");
            
            // Update user
            log.info("Updating user ... ");
            updatedUser.setFirstName(userRequestDto.getFirstName());
            updatedUser.setLastName(userRequestDto.getLastName());
            updatedUser.setEmail(userRequestDto.getEmail());
            updatedUser.setPassword(userRequestDto.getPassword());
            updatedUser.setBirthDate(userRequestDto.getBirthDate());
            userRequestDto.getAddress().setId(updatedUser.getIdAddress());

            return UserMapper.fromUserAddressToResponseDto( userRepository.save(updatedUser),userRequestDto.getAddress());
        }
        return null;
    }

    @Override
    public boolean deleteUser(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            // Delete address of user :
            restTemplate.delete(addressUrl+"/"+user.get().getIdAddress());
            return true;
        }
        return false;
    }
}
