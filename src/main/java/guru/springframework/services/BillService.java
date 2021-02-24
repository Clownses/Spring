package guru.springframework.services;

import guru.springframework.domain.Bill;

public interface BillService {
    Iterable<Bill> listAllBills();

    Bill getBillById(Integer id);

    Bill saveBill(Bill bill);
}
