package model.loginsignup;

/**
 *
 * @author Ana
 */
public class NewUser extends User {
    
    private String firstName;
    private String lastName;
    private String phoneNum;
    private int age;
    private int id;
    private static int count = 0; //variable to create id


    public NewUser(String email, String password) {
        super(email, password);

        this.id = count;
        count++; //increment the id
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    
    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getPhoneNum() {
        return phoneNum;
    }


    @Override
    public int getAge() {
        return age;
    }
      
    public int getID(){
        return id;
    }

}
