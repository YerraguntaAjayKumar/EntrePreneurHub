package models;
import java.io.Serializable;
public abstract class User implements Serializable
{
          protected int userId;
          protected String name;
          protected String email;
          private String pwd;
          public User(int userId,String name,String email,String pwd)
          {
                  this.userId=userId;
                  this.name=name;
                  this.email=email;
                  this.pwd=pwd;
          }
          public boolean login(String ePwd) {
            if(this.pwd.equals(ePwd))
            {
            System.out.println("Hi "+name+"!");
            return true;
            }
            else
              {
                System.out.println("---Invalis Pwd or Userid---");
                return false;
              }

            
          }
          public abstract void displayRole();
    public abstract String getRole();
            public String getName() { return name; }
            public String toString() {
              return "/"+getRole()+">>"+name+"-"+email;
            }
}