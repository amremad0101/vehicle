public class Customer extends User {
        public Customer(String username, String password, String phone) {
        super(username, password, phone);
    }

       public String getphone (){
        return this.phone;
    }
    public String getpass (){
        return this.password;
    }
    public String getname (){
        return this.username;
    }
    
           public static Customer addcustomer(String name, String pass, String id) {
      return new Customer (name,pass,id); 
}

}
