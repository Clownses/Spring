package guru.springframework.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Bill;
import guru.springframework.repositories.BillRepository;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Component
public class BillSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private BillRepository billRepository;

    private Logger log = LogManager.getLogger(BillSeeder.class);

    private List<String> сurency = Arrays.asList("USD", "RUB", "EUR", "MDL", "UAH");

    @Autowired
    private void setBillRepository(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    private UUID randomString() {
        return UUID.randomUUID();
    }

    private String randomCurrency() {
        Random rand = new Random();
        String el = сurency.get(rand.nextInt(сurency.size()));
        return el;
    }

    private static int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("date")
    private static LocalDateTime createRandomDate() {
        int year = createRandomIntBetween(1970, 2021);
        int month = createRandomIntBetween(1, 12);
        int day = createRandomIntBetween(1, 28);

        int hour = createRandomIntBetween(1, 23);
        int minute = createRandomIntBetween(0, 60);
        int second = createRandomIntBetween(0, 60);
        return LocalDateTime.of(year, month, day, hour, minute, second);
    }

    private static double getRandomNumber() {
        double x = (Math.random() * ((1000000.00 - 150.00) + 1)) + 150.00;
        return (double) Math.round(x * 100) / 100;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (billRepository.count() == 0) {
            for (int i = 0; i < 15; i++) {
                Bill bill = new Bill(randomString().toString(), getRandomNumber(), randomCurrency(),
                        createRandomDate().toString());
                billRepository.save(bill);
                log.info("[Saved record: " + bill.getId() + "]\r\n");
            }
        }
    }
}
