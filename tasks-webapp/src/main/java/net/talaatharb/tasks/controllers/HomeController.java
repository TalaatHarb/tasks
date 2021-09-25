package net.talaatharb.tasks.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping(path = { "/index"})
	public String home() {
		return "forward:/index.html";
	}

}
