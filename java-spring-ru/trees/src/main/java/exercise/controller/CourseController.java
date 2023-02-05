package exercise.controller;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(path = "")
    public Iterable<Course> getCourses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    // BEGIN
    @GetMapping(path = "/{id}/previous")
    public Iterable<Course> getPreviousCourses(@PathVariable long id) {

        String s = courseRepository.findById(id).getPath();
        List<Course> l = new ArrayList<>();
        if (s == null) {
            return l;
        }

//        String[] steps = s.split("\\.");

        l = Arrays.stream(s.split("\\.")) // for (String x : steps)
                .map(x -> courseRepository.findById(Long.parseLong(x)))
                .collect(Collectors.toList());








//        long x;
//
//        for (String value : steps) {
//            x = Long.parseLong(value);
//            l.add(courseRepository.findById(x));
//        }

        return l;
    }


    // END

}
