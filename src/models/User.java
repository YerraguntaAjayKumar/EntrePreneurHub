abstract class User
{
          protected int userId;
          protected String name;
          protected String email;
          User(int userId,String name,String email)
          {
                  this.userId=userId;
                  this.name=name;
                  this.email=email;
          }
          boolean login() {
            System.out.println(name+" Login successfully");
            return true;
          }
          abstract void displayRole();
}