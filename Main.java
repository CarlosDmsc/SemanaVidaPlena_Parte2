package app;

import eventos.*;
import restaurante.*;
import analytics.*;
import java.time.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        EventManager em = new EventManager();
        RestaurantManager rm = new RestaurantManager();

        Participant p1 = new Participant("P001", "Ana");
        Participant p2 = new Participant("P002", "Bruno");
        Participant p3 = new Participant("P003", "Carlos");
        Participant p4 = new Participant("P004", "Diana");
        Participant p5 = new Participant("P005", "Eduardo");

        em.registerParticipant(p1);
        em.registerParticipant(p2);
        em.registerParticipant(p3);
        em.registerParticipant(p4);
        em.registerParticipant(p5);

        Event e1 = new Lecture("E001", "Palestra: Sono e Saúde", LocalDateTime.of(2025,10,10,9,0));
        Event e2 = new Workshop("E002", "Oficina: Alimentação Funcional", LocalDateTime.of(2025,10,11,14,0));
        Event e3 = new Show("E003", "Show: Movimento e Bem-estar", LocalDateTime.of(2025,10,11,20,0));
        Event e4 = new Workshop("E004", "Oficina: Cozinha Saudável", LocalDateTime.of(2025,10,12,11,0));

        em.addEvent(e1); em.addEvent(e2); em.addEvent(e3); em.addEvent(e4);

        em.markAttendance(e1.getId(), p1.getId());
        em.markAttendance(e1.getId(), p2.getId());
        em.markAttendance(e2.getId(), p1.getId());
        em.markAttendance(e2.getId(), p3.getId());
        em.markAttendance(e2.getId(), p4.getId());
        em.markAttendance(e3.getId(), p2.getId());
        em.markAttendance(e3.getId(), p5.getId());
        em.markAttendance(e4.getId(), p1.getId());
        em.markAttendance(e4.getId(), p3.getId());

        Restaurant rA = new Restaurant("R001", "Bom Sabor");
        Restaurant rB = new Restaurant("R002", "Vida Fit");
        Restaurant rC = new Restaurant("R003", "Comer Bem");

        rm.addRestaurant(rA);
        rm.addRestaurant(rB);
        rm.addRestaurant(rC);

        MenuItem m1 = new MenuItem("M001", "Combo Funcional A", MenuCategory.COMBO, 25.0);
        MenuItem m2 = new MenuItem("M002", "Salada Proteica", MenuCategory.PRATO, 18.0);
        MenuItem m3 = new MenuItem("M003", "Smoothie Verde", MenuCategory.BEBIDA, 8.0);
        MenuItem m4 = new MenuItem("M004", "Combo Funcional B", MenuCategory.COMBO, 30.0);

        rA.addMenuItem(m1); rA.addMenuItem(m3);
        rB.addMenuItem(m2); rB.addMenuItem(m4);
        rC.addMenuItem(m2); rC.addMenuItem(m3);

        Voucher v1 = em.issueVoucherForEvent(e1.getId(), "V-E1-001");
        Voucher v2 = em.issueVoucherForEvent(e2.getId(), "V-E2-001");
        Voucher v3 = em.issueVoucherForEvent(e2.getId(), "V-E2-002");
        Voucher v4 = em.issueVoucherForEvent(e3.getId(), "V-E3-001");
        Voucher v5 = em.issueVoucherForEvent(e4.getId(), "V-E4-001");

        rm.redeemVoucher(v1.getId(), p2.getId(), rA.getId(), e1.getDate().plusHours(5), 20.0);
        rm.redeemVoucher(v2.getId(), p1.getId(), rB.getId(), e2.getDate().plusHours(3), 30.0);
        rm.redeemVoucher(v3.getId(), p4.getId(), rC.getId(), e2.getDate().plusDays(1), 15.0);
        rm.redeemVoucher(v4.getId(), p5.getId(), rB.getId(), e3.getDate().plusHours(2), 30.0);

        rm.recordOrder(new Order("O001", p1.getId(), rA.getId(), e1.getDate().plusHours(6), Arrays.asList(m1, m3)));
        rm.recordOrder(new Order("O002", p2.getId(), rA.getId(), e1.getDate().plusHours(5), Arrays.asList(m3)));
        rm.recordOrder(new Order("O003", p1.getId(), rB.getId(), e2.getDate().plusHours(3), Arrays.asList(m4)));
        rm.recordOrder(new Order("O004", p3.getId(), rC.getId(), e2.getDate().plusHours(30), Arrays.asList(m2)));
        rm.recordOrder(new Order("O005", p5.getId(), rB.getId(), e3.getDate().plusHours(2), Arrays.asList(m4, m3)));
        rm.recordOrder(new Order("O006", p3.getId(), rB.getId(), e4.getDate().plusHours(1), Arrays.asList(m2, m4)));
        rm.recordOrder(new Order("O007", p1.getId(), rC.getId(), e4.getDate().plusHours(2), Arrays.asList(m2)));

        Analytics.runReports(em, rm);
    }
}
