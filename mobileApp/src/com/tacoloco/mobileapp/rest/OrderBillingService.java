package com.tacoloco.mobileapp.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.tacoloco.mobileapp.Order;
import com.tacoloco.mobileapp.Taco;
import com.tacoloco.mobileapp.constants.TacoLocoConstants;

/**
 * This class contains logic for getting billing order details
 * 
 * @author pchakur 
 */
@Path("/order")
public class OrderBillingService {

	/**
	 * @param order
	 *            order as received from the consumer - this should include a list
	 *            of ordered tacos .
	 * @return Response response which contains Order details, related amounts,
	 *         discount applied, error messages, if any and status code.
	 */
	@POST
	@Path("/postOrder")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getOrderBillingJson(Order order) {

		int totalNumberOftacos = 0;
		double discountApplied = 0.00;
		double totalAmount = 0.00;

		double finalAmount;

		// calculate order bill
		for (Taco taco : order.getTacos()) {
			if (taco.getQuantity() > 0) {
				totalNumberOftacos += taco.getQuantity();
				double tacoAmount = getTacosAmount(taco);

				if (tacoAmount > 0.00) {
					totalAmount += tacoAmount;
				} else {
					order.setStatusCode(406);
					order.setStatusMessage("Invalid taco name");
					return Response.status(Response.Status.NOT_ACCEPTABLE).entity(order).build();
				}
			} else {
				order.setStatusCode(406);
				order.setStatusMessage("Invalid quantity");
				return Response.status(Response.Status.NOT_ACCEPTABLE).entity(order).build();
			}
		}

		// apply discount if 4 or more tacos are ordered.
		if (totalNumberOftacos > 3) {
			discountApplied = getApplicableDiscount(totalAmount);
		}

		finalAmount = totalAmount - discountApplied;

		order.setDiscountApplied(discountApplied);
		order.setOrderBillingAmount(totalAmount);
		order.setFinalAmount(finalAmount);

		return Response.status(Response.Status.OK).entity(order).build();

	}

	/**
	 * Returns discount based on total amount. This discount is set to 20% as per
	 * requirement. If needed, this can be changed in the constants file from where
	 * it is read.
	 * 
	 * @param totalAmount
	 *            This is total amount of the order - calculated based on type of
	 *            taco and corresponding quantity
	 *            
	 * @return The discount applicable based on total amount received.
	 */
	private double getApplicableDiscount(double totalAmount) {

		return (totalAmount * TacoLocoConstants.DISCOUNT_PERCENTAGE) / 100;

	}


	/**
	 * calculates the amount based on taco type and number of tacos of each type.
	 * 
	 * @param taco
	 * 
	 * @return The amount per line item in the order
	 */
	private double getTacosAmount(Taco taco) {

		double tacoPrice = getTacoPrice(taco);
		double totalAmount = -1.00;

		if (tacoPrice > 0.00) {
			totalAmount = taco.getQuantity() * tacoPrice;
		}

		return totalAmount;
	}

	/**
	 * Returns price per taco based on taco name.
	 * 
	 * @param taco
	 * 
	 * @return amount per taco
	 */
	private double getTacoPrice(Taco taco) {

		double tacoPrice;

		switch (taco.getTacoName()) {
		case TacoLocoConstants.VEGGIE_TACO:
			tacoPrice = TacoLocoConstants.VEGGIE_TACO_PRICE;
			break;
		case TacoLocoConstants.CHICKEN_OR_BEEF_TACO:
			tacoPrice = TacoLocoConstants.CHICKEN_OR_BEEF_TACO_PRICE;
			break;
		case TacoLocoConstants.CHORIZO_TACO:
			tacoPrice = TacoLocoConstants.CHORIZO_TACO_PRICE;
			break;
		default:
			tacoPrice = 0.00;
			break;
		}

		return tacoPrice;
	}

}