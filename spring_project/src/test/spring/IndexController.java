package test.spring;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import test.been.RegistrationBeen;
import test.service.RegistrationService;

@Controller
public class IndexController {
	public String saveDirectory="G:/baabtra/reg_users_pic/";
	@RequestMapping(value = "/", method = RequestMethod.GET)
		
		public String init(Model model) {
			model.addAttribute("msg", "Please Enter Your Login Details");
			return "registration_form";
		}
	@RequestMapping(value="/registration", method = RequestMethod.POST )
	public String init(Model model, @ModelAttribute("registrationBean") RegistrationBeen registrationBean,@RequestParam CommonsMultipartFile file,HttpSession session )
	{
		//System.out.println("fgf");
		
		String filename=file.getOriginalFilename();  
	    System.out.println(saveDirectory+" "+filename);
	    
	    try{  
	        byte barr[]=file.getBytes();  
	        BufferedOutputStream bout=new BufferedOutputStream(  
	        new FileOutputStream(saveDirectory+"/"+filename));  
	        bout.write(barr);  
	        bout.flush();  
	        bout.close();  
	          
	    }
	    catch(Exception e){
	    	
	    	System.out.println(e);
	    }
	    int rt=-1;
		//String saveDirectory =null;
		RegistrationService reg=new RegistrationService();
		
		registrationBean.setFilename(filename);
		rt=reg.register(registrationBean);
	      
		if(rt==1){
			model.addAttribute("name", registrationBean.getFname());
			model.addAttribute("image", registrationBean.getFilename());
			return "home";
		}else
			model.addAttribute("msg","Registration failed");
			return "registration_form";
		
	}
	
}
