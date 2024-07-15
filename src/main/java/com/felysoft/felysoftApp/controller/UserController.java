package com.felysoft.felysoftApp.controller;

//import com.felysoft.felysoftApp.entity.Role;
import com.felysoft.felysoftApp.entity.User;
import com.felysoft.felysoftApp.service.imp.RoleImp;
import com.felysoft.felysoftApp.service.imp.UserImp;
import com.felysoft.felysoftApp.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/user/", method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.HEAD})
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserImp userImp;

    /*@Autowired
    private RoleImp roleImp;*/

    @GetMapping("all")
    public ResponseEntity<Map<String, Object>> findAll(){
        Map<String,Object> response= new HashMap<>();
        try{
            List<User> userList= this.userImp.findAll();

            response.put("status","success");
            response.put("data", userList);
        }catch (Exception e){
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("list/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            User user = this.userImp.findById(id);

            response.put("status", "success");
            response.put("data", user);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("validate")
    public ResponseEntity<Map<String, Object>> findByEmail(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            User user = this.userImp.validateUser(
                    request.get("email").toString().toLowerCase(),
                    request.get("password").toString()
            );

            if(user == null) {
                throw new RuntimeException("Usuario no encontrado");
            }

            response.put("status", "success");
            response.put("data", user);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> create(
            @RequestParam("numIdentification") Long numIdentification,
            @RequestParam("typeDoc") User.TypeDoc typeDoc,
            @RequestParam("names") String names,
            @RequestParam("lastNames") String lastNames,
            @RequestParam("address") String address,
            @RequestParam("phoneNumber") Long phoneNumber,
            @RequestParam("email") String email,
            @RequestParam("gender") User.Gender gender,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam(name = "image", required = false) MultipartFile image,
            //@RequestParam("fkIdRole") Long fkIdRole
            @RequestParam("role") Role role) {

        Map<String, Object> response = new HashMap<>();
        try {
            // Construir el objeto User usando el patrón Builder
            User.UserBuilder userBuilder = User.builder()
                    .numIdentification(numIdentification)
                    .typeDoc(typeDoc)
                    .names(names.toUpperCase())
                    .lastNames(lastNames.toUpperCase())
                    .address(address.toUpperCase())
                    .phoneNumber(phoneNumber)
                    .email(email.toLowerCase())
                    .gender(gender)
                    .username(username)
                    .password(password)
                    .dateRegister(new Timestamp(System.currentTimeMillis()))
                    .lastModification(new Timestamp(System.currentTimeMillis()));

            // Guardar información de la imagen si se proporciona
            if (image != null) {
                userBuilder.nameImg(image.getOriginalFilename())
                        .typeImg(image.getContentType())
                        .image(image.getBytes());
            }

            // Obtener y asignar el rol
            //Role role = roleImp.findById(fkIdRole);

            User user = userBuilder
                    .role(role)
                    .build();

            // Crear el usuario
            this.userImp.create(user);

            response.put("status", "success");
            response.put("data", "Registro Exitoso");
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id,
          @RequestParam(value = "numIdentification", required = false) Long numIdentification,
          @RequestParam(value = "typeDoc", required = false) User.TypeDoc typeDoc,
          @RequestParam(value = "names", required = false) String names,
          @RequestParam(value = "lastNames", required = false) String lastNames,
          @RequestParam(value = "address", required = false) String address,
          @RequestParam(value = "phoneNumber", required = false) Long phoneNumber,
          @RequestParam(value = "email", required = false) String email,
          @RequestParam(value = "gender", required = false) User.Gender gender,
          @RequestParam(value = "username", required = false) String username,
          @RequestParam(value = "password", required = false) String password,
          @RequestParam(value = "image", required = false) MultipartFile image,
          //@RequestParam(value = "fkIdRole", required = false) Long fkIdRole
          @RequestParam(name = "role", required = false) Role role) {

        Map<String, Object> response = new HashMap<>();
        try {
            // INSTANCIA DEL OBJETO USER
            User user = this.userImp.findById(id);

            if (user == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("data", "Usuario no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            // Actualizar los campos del usuario con los nuevos valores si se proporcionan
            if (numIdentification != null) {
                user.setNumIdentification(numIdentification);
            }

            if (typeDoc != null) {
                user.setTypeDoc(typeDoc);
            }

            if (names != null) {
                user.setNames(names.toUpperCase());
            }

            if (lastNames != null) {
                user.setLastNames(lastNames.toUpperCase());
            }

            if (address != null) {
                user.setAddress(address.toUpperCase());
            }

            if (phoneNumber != null) {
                user.setPhoneNumber(phoneNumber);
            }

            if (email != null) {
                user.setEmail(email.toLowerCase());
            }

            if (gender != null) {
                user.setGender(gender);
            }

            if (username != null) {
                user.setUsername(username);
            }

            if (password != null) {
                user.setPassword(password);
            }

            if (image != null) {
                user.setImage(image.getBytes());
                user.setNameImg(image.getOriginalFilename());
                user.setTypeImg(image.getContentType());
            }

//            if (role != null) {
                //Role role = roleImp.findById(fkIdRole);
                if (role == null) {
                    response.put("status", HttpStatus.NOT_FOUND);
                    response.put("data", "Rol no encontrado");
                    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                }
                user.setRole(role);
//            }

            // Actualizar la fecha de última modificación
            user.setLastModification(new Timestamp(System.currentTimeMillis()));

            this.userImp.update(user);

            response.put("status", "success");
            response.put("data", "Actualización exitosa");
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            User user = this.userImp.findById(id);
            user.setEliminated(true);

            this.userImp.delete(user);

            response.put("status", "success");
            response.put("data", "Eliminado Correctamente");
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
