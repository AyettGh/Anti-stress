package org.example.ecommerce.Controller;

import jakarta.persistence.EntityNotFoundException;
import org.example.ecommerce.Entity.Admin;
import org.example.ecommerce.Service.ServiceImpl.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
@RestController
@RequestMapping("/ecommerce")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/alladmin")
    public List<Admin> getALlAdmin()
    {
        return adminService.getAll();
    }

    @GetMapping("/getadmin/{id}")
    public Admin getAdminById(@PathVariable Long id)
    {
        return adminService.getAdminById(id).orElseThrow(
                ()-> new EntityNotFoundException("no admin is found")
        );
    }

    @PostMapping("/addadmin")
    public Admin addAdmin(@RequestBody Admin admin)
    {
        return adminService.addAdmin(admin);
    }

    @DeleteMapping("/deleteadmins")
    public void deleteAdmin()
    {
        adminService.deleteAllAdmin();

    }
    @DeleteMapping("/deleteadmin/{id}")
    public ResponseEntity<?> deleteAdminById(@RequestBody Admin admin, @PathVariable Long id)
    {
        if (adminService.exitById(id))
        {
            adminService.deleteAdminById(id);
            HashMap<Admin,String> map=new HashMap<>();
            map.put(admin," this admin is deleted");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        else {
            HashMap<String,String> map=new HashMap<>();
            map.put("admin"," not found");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }

    }

    @PutMapping("/updateadmin/{id}")
    public ResponseEntity<?> updateAdmin(@RequestBody Admin admin,@PathVariable Long id)
    {
        if(adminService.exitById(id))
        {
            Admin admin1=adminService.getAdminById(id).orElseThrow(
                    ()->new EntityNotFoundException("admin not found")
            );
            admin1.setName(admin.getName());
            admin1.setAdminSpecificField(admin.getAdminSpecificField());
            admin1.setEmail(admin.getEmail());
            admin1.setRole(admin.getRole());
            admin1.setPermissions(admin.getPermissions());
            return ResponseEntity.ok().body(admin1);
        }
        else {
            HashMap<String,String> map=new HashMap<>();
            map.put("admin"," not found");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
    }

}
