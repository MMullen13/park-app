package model.loginsignup;

/**
 *
 * @author Ana
 */
public class NewUser implements UserIF{
    
    private String email;
    private String password;
    private String phoneNum;
    private AgeCategory age;
    private int id;
    private static int count = 0; //variable to create id

    public NewUser() {
        this.id = count;
        count++; //increment the id
    }

    public NewUser(String email, String password, String phoneNum, AgeCategory age) {
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
        this.age = age;
    }
    

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setAge(AgeCategory age) {
        this.age = age;
    }

    @Override
    public String getEmail() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getPassword() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
}
