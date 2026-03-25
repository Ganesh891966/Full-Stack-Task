package client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
  @GetMapping("/client")
  public String ClientServer() {
	return "client page";
  }
}
