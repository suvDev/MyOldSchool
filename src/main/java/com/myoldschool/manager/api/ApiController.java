package com.myoldschool.manager.api;

import com.myoldschool.manager.BussLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ApiController {

    @Autowired
    BussLayer bs;

    @GetMapping("/getRecords")
    public ArrayList<Student> getAllRecords() {
        return bs.showData();
    }

    @GetMapping("/addUser")
    public String addUser(@RequestParam(value = "id", defaultValue = "0") int id,
                          @RequestParam(value = "name", defaultValue = "name") String name,
                          @RequestParam(value = "rollNo", defaultValue = "0") int rollNo,
                          @RequestParam(value = "marks", defaultValue = "0.0") Double marks) {
        return bs.insertData(id, name, rollNo, marks);
    }

    @PostMapping(path = "/postUser", consumes = "application/json", produces = "application/json")
    public String postUser(@RequestBody Student student) {
        return bs.insertData(student.getId(), student.getName(), student.getRollno(), Double.valueOf(student.getMarks()));
    }

    @PostMapping(path = "/updateUser", consumes = "application/json", produces = "application/json")
    public String updateUser(@RequestBody Student student) {
        return bs.updateData(student.getId(), student.getName());
    }

    @PostMapping(path = "/deleteUser", consumes = "application/json", produces = "application/json")
    public String deleteUser(@RequestBody Student student) {
        return bs.deleteRecord(student.getId());
    }
}
