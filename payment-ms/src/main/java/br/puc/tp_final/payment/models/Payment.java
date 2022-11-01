package br.puc.tp_final.payment.models;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.puc.tp_final.payment.enums.EnumPaymentMode;
import br.puc.tp_final.payment.enums.EnumPaymentStatus;
import br.puc.tp_final.payment.enums.EnumPaymentType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Entity
@Data
@XmlRootElement(name = "payment")
public class Payment {
	
	@Id
	private String payId = UUID.randomUUID().toString();
	
	@Column(nullable = false)
	private Double price;
	
	@Column(nullable = false)
	private EnumPaymentType type;
	
	@Column(nullable = false)
	private EnumPaymentMode mode;
	
	@Column(nullable = false)
	private EnumPaymentStatus status;
	
	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime creationDate;
	
	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime updateDate;
	
	public Payment() {
	}
	
	public Payment(Double price, EnumPaymentType type, EnumPaymentMode mode, EnumPaymentStatus status) {
		this.price = price;
		this.status = status;
		this.type = type;
		this.mode = mode;
	}
	
}
