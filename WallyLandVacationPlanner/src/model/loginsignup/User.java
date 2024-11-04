package model.loginsignup;


/**
 *
 * @author Ana
 */
public abstract class User implements UserIF {

    String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int age;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

//    @Override
    public String getEmail() {
        return email;
    }

//    @Override
    public String getPassword() {
        return password;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public String getPhoneNum(){
        return phoneNumber;
    }
    
    public int getAge(){
        return age;
    }

    public abstract void setFirstName(String firstName);

    public abstract void setLastName(String lastName);
    
    public abstract void setPhoneNum(String phoneNum);
    
    public abstract void setAge(int age);

}
