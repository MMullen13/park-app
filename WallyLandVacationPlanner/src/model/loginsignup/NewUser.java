package model.loginsignup;

/**
 *
 * @author Ana
 */
public class NewUser extends User{
    
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNum;

    private AgeCategory age;
    private int id;
    private static int count = 0; //variable to create id


    public NewUser(String email, String password) {
        super(email, password);
        
        this.id = count;
        count++; //increment the id
    }
    
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setAge(AgeCategory age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public AgeCategory getAge() {
        return age;
    }
      
}
