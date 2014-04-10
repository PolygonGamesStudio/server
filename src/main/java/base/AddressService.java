package base;

public interface AddressService{
	public boolean addService(Abonent abonent, String name);
	public Address getAddressByName(String name);
}