package com.example.studentmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.studentmanagementsystem.entity.Student;
import com.example.studentmanagementsystem.service.StudentService;

@Controller
public class StudentContoller {
	
	private StudentService studentService ;

	public StudentContoller(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping("/students")
	public String listStudents(Model model) {
		
		model.addAttribute("students", studentService.getAllStudents());
		return "students-list";
	}
	
	@GetMapping("/showFormForAdd")
	public String addStudents(Model model) {
		
		Student student = new Student();
		model.addAttribute("student", student);
		return "add-students";
	}
	
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		return "redirect:/students";
	}
	
	@GetMapping("/showFormForUpdate")
	public String updateStudent(@RequestParam("studentId") Long id,Model model)
	{
		studentService.getStudentById(id);
		model.addAttribute("student", studentService.getStudentById(id));
		return "add-students";
	}
	
	@GetMapping("/showFormForDelete")
	public String deleteStudent(@RequestParam("studentId") Long id) {
		studentService.deleteStudent(id);
		return "redirect:/students";
	}
}
