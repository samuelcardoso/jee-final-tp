package br.puc.tp_final.payment.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;

import br.puc.tp_final.payment.enums.EnumPaymentMode;
import br.puc.tp_final.payment.enums.EnumPaymentStatus;
import br.puc.tp_final.payment.enums.EnumPaymentType;
import br.puc.tp_final.payment.models.Payment;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.EntityType;

@Stateless
public class PaymentService {

	@PersistenceContext(unitName = "persistence-unit")
	private EntityManager entityManager;

	public void pay(Payment p) {
		
		p.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
		p.setUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
		
		entityManager.persist(p);
	}

	public Payment find(String payId){
		Payment p = entityManager.find(Payment.class,payId);

		return p;
	}
	
	public EnumPaymentType randomType() {

		Random v = new Random();
		int paymentCode = v.nextInt(4);

		EnumPaymentType type = EnumPaymentType.VISA;

		switch (paymentCode) {
		case 1:
			type = EnumPaymentType.VISA;
			break;
		case 2:
			type = EnumPaymentType.MASTERCARD;
			break;
		case 3:
			type = EnumPaymentType.PAYPAL;
			break;
		case 4:
			type = EnumPaymentType.GOOGLEPAY;
			break;
		}

		return type;

	}

	public EnumPaymentMode randomMode() {

		Random v = new Random();
		int paymentCode = v.nextInt(4);

		EnumPaymentMode type = EnumPaymentMode.CASH;

		switch (paymentCode) {
		case 1:
			type = EnumPaymentMode.CASH;
			break;
		case 2:
			type = EnumPaymentMode.PARCELED_OUT;
			break;
		}

		return type;

	}

	public List<Payment> findAll() {
		CriteriaBuilder qb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Payment> cq = qb.createQuery(Payment.class);
		Root<Payment> root = cq.from(Payment.class);
		EntityType<Payment> type = entityManager.getMetamodel().entity(Payment.class);

		TypedQuery<Payment> q = entityManager.createQuery(cq);
		return q.getResultList();
	}

	public void cancelPayment(String payId) {
		Payment payment = entityManager.find(Payment.class, payId);	
		payment.setStatus(EnumPaymentStatus.CANCELED);
		entityManager.merge(payment);
	}
}