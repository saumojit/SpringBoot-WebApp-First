package com.example.SpringBoot_WebApp_First.contoller;

import com.example.SpringBoot_WebApp_First.model.Alien;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// # Unlike typical old servlet projects , don't need to create the servlet as in code with SpringBoot
// @Controller => It creates a servlet and runs on embed-tomcat

// # URL to Servlet Mapping
// @RequestMapping("/") and there are other mapping such as GetMapping , PostMapping and so on.

// # Rendering Observations  ==>
// # JSP Page
// without using jasper , when browser hit with the URL , it downloads the jsp page instead of showing
// reason : springBoot doesn't convert the JSP Page to servlet by default
// # Jasper ( JSP Parser )
// So we need tomcat jasper to parse the JSP Page to Servlet
// lets add tomcat jasper dependency with the same tomcat version
// after adding jasper to pom.xml ==> code automatically rendered the JSP page

// application.properties
// https://docs.spring.io/spring-boot/appendix/application-properties/index.html
// example : we can mention prefix , suffix in the properties for jsp file
// you would find it under Web Properties from the above link and same used in application.properties

@Controller
public class HomeController {

    // we can use this course in JSP
    @ModelAttribute("course")
    public String getCourse(){
        return "JAVA";
    }

    @RequestMapping("/")
    public String home(){
        System.out.println("Home Method Called ...");
        return "calculator.jsp"; // going with explicit declaring the full file name
        // return "calculator"; //  fetches the filename from prefix/suffix as needed
    }


    // from JSP , using fieldNames - field1 , field2
    // 1
    // handling parameters and session in old servlet way
    // url changed to /add_old from /add , defining the controller in spring-boot way
    @RequestMapping("/add_old")
    public String add(HttpServletRequest req , HttpSession session){
        System.out.println("Add Method Called ...");
        int num1 = Integer.parseInt(req.getParameter("field1"));
        int num2 = Integer.parseInt(req.getParameter("field2"));
        int result = num1 + num2 ;
        System.out.println(result);
        session.setAttribute("result" , result);
        return "calc_result.jsp";
    }

    // 2
    // URL => http://localhost:8080/add?field1=2&field2=1000
    // field1/field2 params in the url itself used as args in function , then no need of @RequestParam("param_key")
    // instead of using session , using Model object to hold and pass the data to JSP
    @RequestMapping("/add_model")
    public String add(@RequestParam("field1") int num1 , @RequestParam("field2")  int num2 , Model model){
        System.out.println("Add Method Called with Spring-Boot Concept  ...");
        int result = num1 + num2 ;
        System.out.println(result);
        model.addAttribute("result" , result);
        return "calc_result.jsp";
    }


    // 3 - With ModelAndView
    @RequestMapping("/add")
    public ModelAndView add(@RequestParam("field1") int num1 , @RequestParam("field2")  int num2 , ModelAndView mv){
        System.out.println("Add Method Called with Spring-Boot Concept ModelAndView ...");
        int result = num1 + num2 ;
        mv.addObject("result" , result);
        mv.setViewName("calc_result.jsp");
        return mv;
    }

    // 4 - with Alien Object
    @RequestMapping("/addalien")
    public ModelAndView addAlien(@RequestParam("field1") int aid , @RequestParam("field2") String aname , ModelAndView mv){
        System.out.println("AddAlien Method Called with Spring-Boot Concept ModelAndView ...");
        Alien al = new Alien();
        al.setAid(aid);
        al.setAname(aname);
        mv.addObject("result" , al);
        mv.setViewName("calc_result.jsp");
        return mv;
    }


    // active ...
    // 5 - with Alien Object using @ModelAttribute ( can be mentioned optionally )
    // In JSP ,field names must match to all the variable name in class <Alien>
    // example ,for var aid => field name must be aid else it will return null
    // field names have been altered to aid and anmame in JSP

    // ++
    // Declaration of multiple args for variables in a class -- Unnecessary and Takes too much effort
    // Change the field name to match Class Vars and then in controller pass the class itself
    @RequestMapping("/addalien2")
    public String addAlien2(@ModelAttribute("result") Alien alien){
        System.out.println("AddAlien Method Called with Spring-Boot Concept ModelAndView ...");
        return "calc_result.jsp" ;

        // mv.addObject("result" , alien);
        // mv.setViewName("calc_result.jsp");
        // return mv;
    }
}
