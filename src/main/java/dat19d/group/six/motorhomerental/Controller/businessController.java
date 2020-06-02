package dat19d.group.six.motorhomerental.Controller;


import dat19d.group.six.motorhomerental.model.Customer;
import datahandling.consumers.ConsumerGenerator;
import datahandling.consumers.StoreableConsumer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

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
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        Customer c = new Customer(fn, ln, address, phone, email);

        StoreableConsumer createCustomer = ConsumerGenerator.getConsumer(c, ConsumerGenerator.CREATE);
        createCustomer.execute();

        return "redirect:/";
    }

}
