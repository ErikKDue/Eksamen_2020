package dat19d.group.six.motorhomerental.Controller;


import dat19d.group.six.motorhomerental.model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class businessController {

    CustomerMapper customers = new CustomerMapper();

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
    public String opret(Model model)
    {
        model.addAttribute("customer", new Customer());
        return "opret";
    }

    @PostMapping("/opret")
    public String opret(HttpServletRequest request)
    {
        String fn = request.getParameter("firstName");
        String ln = request.getParameter("lastName");
        String adress = request.getParameter("adress");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        Customer c = new Customer(fn, ln, adress, phone, email);
        customers.opret(c);

        return "redirect:/";
    }

}
