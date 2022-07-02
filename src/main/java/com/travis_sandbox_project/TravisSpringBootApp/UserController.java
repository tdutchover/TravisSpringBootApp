package com.travis_sandbox_project.TravisSpringBootApp;

import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {
    private Map<Integer, User> users = new HashMap<>();

    public UserController() {
        addUser("Billy", "OR",1);
        addUser("julie", "CA",2);
        addUser("drew", "NY",3);
        addUser("jack", "PA",4);
    }

    private void addUser(String name, String location, Integer id){
        User user = new User(name, location, id);
        users.put(id,user);
    }

    @GetMapping("/all")
    public Iterator allUsers() {
        users.values().stream().forEach(System.out::println);
        return users.values().iterator();
    }

    @GetMapping("/{id}")
    public Object user(@PathVariable Integer id) {
        if (users.containsKey(id)) {
            System.out.println(users.get(id).toString());
            return users.get(id);
        } else {
            return "User not found for id=%d".formatted(id);
        }
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Integer userId) {
        if (users.containsKey(userId)) {
            users.remove(userId);
            System.out.println("******* List of users after deletion ********");
            users.values().forEach(System.out::println);
            return "User id=%d has been deleted".formatted(userId);
        } else {
            return "User not found for id=%d".formatted(userId);
        }
    }

    // Example command:
    // curl -X POST -d '{"name": "Travis", "location": "Kirkland", "id":6}' -H "Content-Type:application/json" localhost:8080/user/addUser
    // curl -X POST -d '{"name": "Steve", "location": "Redmond", "id":7}' -H "Content-Type:application/json" localhost:8080/user/addUser
    @PostMapping("/addUser")
    @ResponseBody
    public User createUser(@RequestBody User user) {
        users.put(user.getId(), user);
        System.out.println("******* List of users after adding a user ********");
        users.values().forEach(System.out::println);
        System.out.println("Added user %s".formatted(user.toString()));
        return user;
    }
}
