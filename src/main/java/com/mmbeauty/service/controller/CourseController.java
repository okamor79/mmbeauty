package com.mmbeauty.service.controller;

import com.mmbeauty.service.model.Course;
import com.mmbeauty.service.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/list")
    public List<Course> getAllCourse() {
        return courseService.getAllCourse();
    }

    @GetMapping("/{id}")
    public Optional<Course> getCourse(@PathVariable("id") Long id){
        return courseService.getCourseById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Course> addNew(@RequestBody Course course) {
        courseService.addNewCourse(course);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
