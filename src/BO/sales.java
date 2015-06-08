package BO;

public class sales {

	public int id;
	public int customer_id;
	public int movie_id;
	public int sale_date;

	public sales()
	{

	}

	public sales(int id, int cust_id, int mov_id, int saleD)
	{
		this.id = id;
		this.customer_id = cust_id;
		this.movie_id = mov_id;
		this.sale_date= saleD;
	}
}
