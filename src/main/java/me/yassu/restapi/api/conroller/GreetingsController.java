package me.yassu.restapi.api.conroller;

import me.yassu.restapi.api.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

    @GetMapping("/greetings")
    ResponseEntity<ResponseDto> greetings() {
        return ResponseEntity.ok(new ResponseDto("Hello World"));
    }
}
