package com.student.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class studentservice {
	
	

	@RestController
	public class StudentController {
	    private static final String FILE_PATH = "path_to_student_data.csv";

	    

	    @GetMapping("/students/filter")
	    public List<Student> filterStudents(
	            @RequestParam(required = false) String name,
	            @RequestParam(required = false) Integer marks
	    ) throws IOException {
	        List<Student> filteredStudents = new ArrayList<>();

	        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
	            String line;

	            while ((line = br.readLine()) != null) {
	                // Assuming CSV structure: id,name,total_marks
	                String[] values = line.split(",");
	                int id = Integer.parseInt(values[0]);
	                String studentName = values[1];
	                int totalMarks = Integer.parseInt(values[2]);

	                if ((name == null || studentName.contains(name)) &&
	                    (marks == null || totalMarks >= marks)) {
	                    filteredStudents.add(new Student(id, studentName, totalMarks));
	                }
	            }
	        }

	        return filteredStudents;
	    }
	}


}
