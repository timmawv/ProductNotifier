package avlyakulov.timur.mockservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/response")
public class StatusCheckController {

    @GetMapping("/200")
    @ResponseStatus(HttpStatus.OK)
    String response200String() {
        return "200";
    }

    @GetMapping("/500")
    ResponseEntity<String> response500String() {
        return ResponseEntity.internalServerError().build();
    }
}