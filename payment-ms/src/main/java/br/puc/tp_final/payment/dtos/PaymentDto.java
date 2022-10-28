package br.puc.tp_final.payment.dtos;

import java.util.Random;

import br.puc.tp_final.payment.enums.EnumPaymentMode;
import br.puc.tp_final.payment.enums.EnumPaymentStatus;
import br.puc.tp_final.payment.enums.EnumPaymentType;
import br.puc.tp_final.payment.models.Payment;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentDto {

	@NotNull
	private Double price;
	
	@NotBlank
	@Enumerated(EnumType.STRING)
	private EnumPaymentType type;
	
	@NotBlank
	@Enumerated(EnumType.STRING)
	private EnumPaymentMode mode;
	
	public PaymentDto(Double price, EnumPaymentType type, EnumPaymentMode mode) {
		this.price = price;
		this.type = type;
		this.mode = mode;
	}
	
	public PaymentDto() {
	}
	
	public Payment dtoToObject() {
		return new Payment(price, type, mode, randomStatus());
	}
	
	public EnumPaymentStatus randomStatus() {
		Random v = new Random();
		if (v.nextInt(100) <= 80) {
			return EnumPaymentStatus.SUCCESS;
		} else {
			return EnumPaymentStatus.FAIL;
		}
	}
}
