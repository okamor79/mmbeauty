package com.mmbeauty.service.service;

import com.mmbeauty.service.model.Course;
import com.mmbeauty.service.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course addNewCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course editCourse(Course course) {
        return null;
    }
}
