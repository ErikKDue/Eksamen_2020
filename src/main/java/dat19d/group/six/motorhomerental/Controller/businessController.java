package dat19d.group.six.motorhomerental.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class businessController {

    @GetMapping("/")
    public String index(Model model)
    {
        return "index";
    }

    @GetMapping("/book")
    public String book()
    {
        return "book";
    }

    @GetMapping("/returner")
    public String returner()
    {
        return "returner";
    }

    @GetMapping("/opret")
    public String opret()
    {
        return "opret";
    }

}
