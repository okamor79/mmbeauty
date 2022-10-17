package com.mmbeauty.service.service;

import com.mmbeauty.service.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> getAllCourse();

    Optional<Course> getCourseById(Long id);

    Course addNewCourse(Course course);

    Course editCourse(Course course);

}
