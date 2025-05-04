package org.example.ecommerce.Controller;

import jakarta.persistence.EntityNotFoundException;
import org.example.ecommerce.Entity.User;
import org.example.ecommerce.Service.ServiceImpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/ecommerce")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/alluser")
    public List<User> getALluser()
    {
        return userService.getAll();
    }

    @GetMapping("/getuser/{id}")
    public User getUserById(@PathVariable Long id)
    {
        return userService.getUserById(id).orElseThrow(
                ()-> new EntityNotFoundException("no user is found")
        );
    }

    @PostMapping("/adduser")
    public User addUser(@RequestBody User user)
    {
        return userService.addUser(user);
    }

    @DeleteMapping("/deleteusers")
    public void deleteUser()
    {
        userService.deleteAllUser();

    }
    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<?> deleteUserById(@RequestBody User user, @PathVariable Long id)
    {
        if (userService.exitById(id))
        {
            userService.deleteUserById(id);
            HashMap<User,String> map=new HashMap<>();
            map.put(user," this user is deleted");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        else {
            HashMap<String,String> map=new HashMap<>();
            map.put("user"," not found");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }

    }

    @PutMapping("/updateuser/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable Long id)
    {
        if(userService.exitById(id))
        {
            User user1=userService.getUserById(id).orElseThrow(
                    ()->new EntityNotFoundException("user not found")
            );
            user1.setName(user.getName());
            user1.setEmail(user.getEmail());
            user1.setRole(user.getRole());
            return ResponseEntity.ok().body(user1);
        }
        else {
            HashMap<String,String> map=new HashMap<>();
            map.put("user"," not found");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
    }

}
