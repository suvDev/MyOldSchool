package com.myoldschool.manager.api;

import com.myoldschool.manager.BussLayer;
import com.myoldschool.manager.BussLayerHibernate;
import com.myoldschool.manager.hibernate.StudentHibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.HashMap;


@RestController
public class ApiController {

    @Autowired
    BussLayer bs;

    @Autowired
    BussLayerHibernate bsh;

    @GetMapping("/getRecords")
    public ArrayList<Student> getAllRecords() {
//        return bs.showData();
        return bsh.showData();
    }

    // a call to student procedure for table student_count
    @PostMapping("/callStudentProcedure")
    public ArrayList<Student> callStudentProcedure(@RequestBody StudentCount student) {

        return bsh.callStudentProcedure(student.getCount(), student.getMarks(), student.getNames(), student.getSid());
    }

    @GetMapping("/tblStudentFunction")
    public ArrayList<String> callTblStudentFunction() {
//        return bs.showData();
        return bsh.callTableStudentFunction();
    }

    @GetMapping("/addUser")
    public String addUser(@RequestParam(value = "id", defaultValue = "0") int id,
                          @RequestParam(value = "name", defaultValue = "name") String name,
                          @RequestParam(value = "rollNo", defaultValue = "0") int rollNo,
                          @RequestParam(value = "marks", defaultValue = "0.0") Double marks) {
//        return bs.insertData(id, name, rollNo, marks);
        return bsh.insertData(id, name, rollNo, marks);
    }

    @PostMapping(path = "/postUser", consumes = "application/json", produces = "application/json")
    public HashMap<String, String> postUser(@RequestBody Student student) {
//        return bs.insertData(student.getId(), student.getName(), student.getRollno(), Double.valueOf(student.getMarks()));
        HashMap<String, String> map = new HashMap<>();
        map.put("status",bsh.insertData(student.getId(), student.getName(), student.getRollno(), Double.valueOf(student.getMarks())));
        return map;
    }

    @PostMapping(path = "/updateUser", consumes = "application/json", produces = "application/json")
    public HashMap<String, String> updateUser(@RequestBody Student student) {
//        return bs.updateData(student.getId(), student.getName());
        HashMap<String, String> map = new HashMap<>();
        map.put("status",bsh.updateData(student.getId(), student.getName(), student.getRollno(), student.getMarks()));
        return map;
    }

    @PostMapping(path = "/deleteUser", consumes = "application/json", produces = "application/json")
    public HashMap<String, String> deleteUser(@RequestBody Student student) {

//        return bs.deleteRecord(student.getId());
        HashMap<String, String> map = new HashMap<>();
        map.put("status",bsh.deleteRecord(student.getId()));
        return map;
    }

    @RequestMapping(path = "/getUser", consumes = "application/json", produces = "application/json")
    public StudentHibernate getUser(@RequestBody Student student,@RequestParam(name = "isCacheable") boolean isCacheable) {

//        return bs.deleteRecord(student.getId());
        System.out.println("IsCacheable value: "+isCacheable);
       return bsh.getRecord(student.getId(), isCacheable);
    }
}
