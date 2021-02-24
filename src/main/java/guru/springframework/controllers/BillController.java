package guru.springframework.controllers;

import guru.springframework.domain.Bill;
import guru.springframework.repositories.BillRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillController {

    @Autowired
    private BillRepository billRepository;

    @RequestMapping(value = "/bills", method = RequestMethod.GET)
    public List<Bill> list() {
        List<Bill> bills = billRepository.findAll();
        return bills;
    }

    @RequestMapping(value = "/bills/{id}", method = RequestMethod.GET)
    public Bill getBill(@PathVariable Long id) {
        return billRepository.findById(id).get();
    }

    @RequestMapping(value = "new_bill", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Bill saveBill(@RequestBody Bill bill) {
        return billRepository.save(bill);
    }

}
