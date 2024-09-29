package ru.sspo.timesheet_server.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController // @Controller + @ResponseBody
public class HelloController {

    // GET http://localhost:8080/hello
//    @RequestMapping("/hello")
//    public String helloPage() {
//        return "Hello, World!";
//    }

//    // GET http://localhost:8080/hello?username=Igor
//    @RequestMapping("/hello")
//    public String helloUser(@RequestParam(name = "username", required = false) String anyName) {
//        if (anyName == null) {
//            anyName = "World!";
//        }
//        return "<h1>Hello, " + anyName + "!</h1>";
//    }

    // GET http://localhost:8080/hello/igor
    // GET http://localhost:8080/hello/alex
    @RequestMapping("/hello/{username}")
    public String helloUser(@PathVariable(name = "username") String anyName) {
        return "<h1>Hello, " + anyName + "!</h1>";
    }

    // GET http://localhost:8080/home
    @RequestMapping("/home")
    public String homePage() {
        return "<h1>Home page</h1>";
//        return "Home page";
    }
}
