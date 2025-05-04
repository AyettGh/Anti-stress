package org.example.ecommerce.Service.ServiceImpl;

import org.example.ecommerce.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> getAll();
    public User addUser(User user);
    public void  deleteAllUser();
    public void deleteUserById(Long id);
    public Boolean exitById(Long id);
    public Optional<User> getUserById(Long id);
}
