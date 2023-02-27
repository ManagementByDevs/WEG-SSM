package net.weg.wegssm.controller;

import net.weg.wegssm.model.service.ExcelGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ExcelExportController {

//    private final ExcelGeneratorService;
//
//    @GetMapping("/excel/demandas")
//    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
//        response.setContentType("application/octet-stream");
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=student" + currentDateTime + ".xlsx";
//        response.setHeader(headerKey, headerValue);
//
//        List<Student> listOfStudents = studentService.getTheListStudent();
//        ExcelGenerator generator = new ExcelGenerator(listOfStudents);
//        generator.generateExcelFile(response);
//    }


}
