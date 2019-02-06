package tiketbioskop.model;

public class Order {
 private int id_order;
 private int id_customer;
 private int id_ticket;
 private int order_quantity;
public int getId_order() {
	return id_order;
}
public void setId_order(int id_order) {
	this.id_order = id_order;
}
public int getId_customer() {
	return id_customer;
}
public void setId_customer(int id_customer) {
	this.id_customer = id_customer;
}
public int getId_ticket() {
	return id_ticket;
}
public void setId_ticket(int id_ticket) {
	this.id_ticket = id_ticket;
}
public int getOrder_quantity() {
	return order_quantity;
}
public void setOrder_quantity(int order_quantity) {
	this.order_quantity = order_quantity;
}

 

}
