package com.greatlearning.sms.controller;

import com.greatlearning.sms.entity.Student;
import com.greatlearning.sms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping("/list")
    public String listAllStudents(Model model) {

        List<Student> students = studentService.list();

        model.addAttribute("students", students);
        return "list-students";
    }


    @RequestMapping("/add")
    public String addStudent(Model theModel) {

        // create model attribute to bind form data
        Student student = new Student();

        theModel.addAttribute("student", student);

        return "student-form";
    }

    @PostMapping("/save")
    public String saveStudent(
            @ModelAttribute("student") Student student) {

        // save the student
//        Student.setCreatedOn(LocalDate.now());
        studentService.save(student);

        // use a redirect to prevent duplicate submissions
        return "redirect:/student/list";
    }

    @RequestMapping("/update")
    public String updateStudent(
            @RequestParam("studentId") Long studentId,
            Model theModel) {

        // get the Student from the service
        Student student = studentService.findById(studentId);

        // set Employee as a model attribute to pre-populate the form
        theModel.addAttribute("student", student);

        // send over to our form
        return "student-form";
    }


    @RequestMapping("/delete")
    public String delete(
            @RequestParam("studentId") Long studentId) {

        // delete the student
        studentService.deleteById(studentId);

        // redirect to students-Listing page
        return "redirect:/student/list";
    }


    @RequestMapping(value = "/403")
    public ModelAndView handleAccessDeniedError(Principal user) {

        ModelAndView model = new ModelAndView();

        if (user != null) {
            model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
        } else {
            model.addObject("msg", "You do not have permission to access this page!");
        }

        model.setViewName("authorization-error-403");
        return model;
    }


}
