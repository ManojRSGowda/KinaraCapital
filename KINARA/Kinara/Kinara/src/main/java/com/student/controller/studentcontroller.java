package com.student.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class studentcontroller {
	


	@RestController
	public class StudentController {
	    private static final String FILE_PATH = "path_to_student_data.csv";

	    @GetMapping("/students")
	    public List<Student> loadStudentDetails(
	            @RequestParam(defaultValue = "1") int pageNumber,
	            @RequestParam(defaultValue = "10") int pageSize
	    ) throws IOException {
	        List<Student> students = new ArrayList<>();

	        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
	            String line;
	            int count = 0;
	            int startIndex = (pageNumber - 1) * pageSize;
	            int endIndex = startIndex + pageSize;

	            while ((line = br.readLine()) != null) {
	                if (count >= startIndex && count < endIndex) {
	                    
	                    String[] values = line.split(",");
	                    int id = Integer.parseInt(values[0]);
	                    String name = values[1];
	                    int totalMarks = Integer.parseInt(values[2]);

	                    students.add(new Student(id, name, totalMarks));
	                }

	                count++;
	            }
	        }

	        return students;
	    }
	}


}
