package carbook;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/homes")
public class HomeApi {


	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public String test() {
		return("test client thanh cong");
	}
}
