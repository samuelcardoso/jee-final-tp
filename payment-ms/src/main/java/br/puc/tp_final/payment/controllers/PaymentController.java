package br.puc.tp_final.payment.controllers;

import java.util.List;

import br.puc.tp_final.payment.dtos.PaymentDto;
import br.puc.tp_final.payment.models.Payment;
import br.puc.tp_final.payment.services.PaymentService;
import jakarta.ejb.EJB;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("payment")
@Produces({ "application/json" })
public class PaymentController {

	@EJB
	private PaymentService paymentService;

	@POST
	@Path("pay")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response pay(@Valid PaymentDto obj) {
		Payment payment = obj.dtoToObject();
		try {
			paymentService.pay(payment);
		}catch(Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("PaymentService error").build();
		}
        return Response.status(Response.Status.CREATED).build();
	}

	@GET
	@Path("status")
	@Consumes("application/json")
	public List<Payment> status() {
		return paymentService.findAll();
	}

	@PUT
	@Path("cancel/{payId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cancelPayment(@PathParam("payId") String payId) {
		paymentService.cancelPayment(payId);
		return Response.status(Response.Status.OK).build();
	}

}