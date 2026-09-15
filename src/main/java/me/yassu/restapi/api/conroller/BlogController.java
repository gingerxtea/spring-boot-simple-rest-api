package me.yassu.restapi.api.conroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {

	@GetMapping("/blogs")
	public String index() {
		return "index.html";
	}
}
