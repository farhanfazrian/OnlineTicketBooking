package tiketbioskop.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tiketbioskop.service.CustomerService;
import tiketbioskop.service.OrderService;
import tiketbioskop.service.TicketService;

@Controller
public class OrderController {

	@Autowired
	private TicketService ts;
	@Autowired
	private CustomerService cs;
	@Autowired
	private OrderService os;

	@RequestMapping(value = "/movie_search", method = RequestMethod.POST)
	public String movieSearch(@RequestParam("movie_name") String name, Model md) throws ParseException {
		md.addAttribute("cus", cs.findByEmail());
		md.addAttribute("names", ts.getName());
		md.addAttribute("selected", name);
		md.addAttribute("dates", ts.findByName(name));
		return "index";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model md) {
		md.addAttribute("names", ts.getName());
		md.addAttribute("cus", cs.findByEmail());
		return "index";
	}

	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String order(Model md, @RequestParam("time") int idFilm, @RequestParam("orderQuantity") int order_quantity,
			@RequestParam("id_customer") int id_customer, RedirectAttributes redirectAttributes) throws ParseException {
		md.addAttribute("dates", ts.findById(idFilm));
		md.addAttribute("cus", cs.findByEmail());
		md.addAttribute("qty", order_quantity);

		if (order_quantity > ts.findQuantity(idFilm)) {
			redirectAttributes.addFlashAttribute("error", "Purchase failed because it exceeds the remaining ticket quota");
			return "redirect:/";
		}
		else if (ts.dateStatus(idFilm)) {
			if (ts.timeStatus(idFilm)) {
				redirectAttributes.addFlashAttribute("error",
						"Purchase failed because the show time has passed");
				return "redirect:/";
			} else {
				ts.updateQuantity(idFilm, order_quantity, ts.findQuantity(idFilm));
				os.insertOrder(idFilm, id_customer, order_quantity);
				return "receipt";
			}
		} else {
			ts.updateQuantity(idFilm, order_quantity, ts.findQuantity(idFilm));
			os.insertOrder(idFilm, id_customer, order_quantity);
			return "receipt";
		}

	}

}
