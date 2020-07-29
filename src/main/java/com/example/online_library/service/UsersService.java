package com.example.online_library.service;

import com.example.online_library.domain.entity.UsersEntity;
import com.example.online_library.domain.model.UsersDTO;
import com.example.online_library.exception.UsersNotFoundException;
import com.example.online_library.mapper.UsersEntityToUsersMapper;
import com.example.online_library.mapper.UsersToUsersEntityMapper;
import com.example.online_library.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Validated
public class UsersService {

    private final UsersRepository repository;

    private final UsersEntityToUsersMapper usersEntityToUsersMapper;

    private final UsersToUsersEntityMapper usersToUsersEntityMapper;

    public UsersDTO createRegister(@Valid UsersDTO user) {
        user.setBalance(new BigDecimal(0));
        user.setBasket("");
        user.setUserType(1l);
        user.setBonusPoints(0l);
        UsersEntity usersEntity = usersToUsersEntityMapper.convert(user);
        UsersEntity savedEntity = repository.save(usersEntity);
        return usersEntityToUsersMapper.convert(savedEntity);
    }

    public UsersDTO findById(long userId) {
        return repository.findById(userId)
                .map(usersEntityToUsersMapper::convert)
                .orElseThrow(() -> new UsersNotFoundException("The user with specified id not found"));
    }

    public UsersDTO findByEmail(String email) {
        return repository.findByEmail(email)
                .map(usersEntityToUsersMapper::convert)
                .orElseThrow(() -> new UsersNotFoundException("The user with specified id not found"));
    }

    public List<UsersDTO> getAll() {
        return repository.getAll()
                .stream()
                .map(usersEntityToUsersMapper::convert)
                .collect(Collectors.toList());
    }

    public void updateCurrentUser(UsersDTO user) {
        UsersEntity existingEntity = repository.findById(user.getUserID())
                .orElseThrow(() -> new UsersNotFoundException("User not found in DB - Method: updateCurrentUser"));

        updateAfterOrder(existingEntity, user);
        repository.save(existingEntity);
    }

    private void updateAfterOrder(UsersEntity existingEntity, UsersDTO user) {
        existingEntity.setBalance(user.getBalance());
        existingEntity.setBasket(user.getBasket());
    }

    public void addInBasket(Long productId, int quantity) {
        String basket = CurrentUserService.currentUser.getBasket();
        String newBasket = "";
        boolean present = false;
        String[] productstWithQuantity = basket.split(";");
        if (!basket.equals("")) {
            for (String prod : productstWithQuantity) {
                int delim = prod.indexOf("#");
                if (Long.parseLong(prod.substring(0, delim)) == productId) {
                    present = true;
                    Long newQuantity = Long.parseLong(prod.substring(delim + 1)) + quantity;
                    newBasket += prod.substring(0, delim + 1) + newQuantity + ";";
                } else {
                    newBasket += prod + ";";
                }
            }
        }
        if (!present) {
            CurrentUserService.currentUser.setBasket(newBasket + productId + "#" + quantity + ";");
        } else {
            CurrentUserService.currentUser.setBasket(newBasket);
        }
        saveBasket(CurrentUserService.currentUser);
    }

    public void updateBasket(Long productId) {
        String newBasket = "";
        String[] productstWithQuantity = CurrentUserService.currentUser.getBasket().split(";");
        for (String prod : productstWithQuantity) {
            int delim = prod.indexOf("#");
            if (Long.parseLong(prod.substring(0, delim)) == productId) {

                newBasket += "";
            } else {
                newBasket += prod + ";";
            }
        }
        CurrentUserService.currentUser.setBasket(newBasket);
        saveBasket(CurrentUserService.currentUser);
    }

    public void saveBasket(UsersDTO user) {
        UsersEntity existingEntity = repository.findById(user.getUserID())
                .orElseThrow(() -> new UsersNotFoundException("User not found in DB - Method: saveBasket"));
        existingEntity.setBasket(user.getBasket());
        repository.save(existingEntity);
    }

    public void addBalance(double amount) {
        BigDecimal oldBalance = CurrentUserService.currentUser.getBalance();
        CurrentUserService.currentUser.setBalance(oldBalance.add(BigDecimal.valueOf(amount)));
        UsersEntity existingEntity = repository.findById(CurrentUserService.currentUser.getUserID())
                .orElseThrow(() -> new UsersNotFoundException("User not found in DB - Method: saveBasket"));
        existingEntity.setBalance(CurrentUserService.currentUser.getBalance());
        repository.save(existingEntity);
    }
}

