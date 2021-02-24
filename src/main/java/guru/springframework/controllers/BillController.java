package guru.springframework.controllers;

import guru.springframework.domain.Bill;
import guru.springframework.repositories.BillRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

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

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/bills", method = RequestMethod.GET)
    public List<Bill> list() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getAllBills");
        query.execute();
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/bills/{id}", method = RequestMethod.GET)
    public List<Bill> getBill(@PathVariable Long id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getBill");
        query.registerStoredProcedureParameter("id", Long.class, ParameterMode.IN);
        query.setParameter("id", id).execute();

        return query.getResultList();
    }

    @RequestMapping(value = "new_bill", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Bill saveBill(@RequestBody Bill bill) {
        return billRepository.save(bill);
    }

}
