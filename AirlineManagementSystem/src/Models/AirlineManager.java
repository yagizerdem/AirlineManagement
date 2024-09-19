package Models;

public class AirlineManager extends BaseUser{
    private String IDCardNo;
    private int Age;
    private double Salary;

    public void SetAge(int age){
        this.Age = age;
    }
    public  int GetAge(){
        return  this.Age;
    }
    public  void SetIdCardNo(String no){
        this.IDCardNo = no;
    }
    public String GetIdCardNo(){
        return  this.IDCardNo;
    }
    public void SetSalary(double salary){
        this.Salary = salary;
    }
    public double GetSalary(){
        return this.Salary;
    }

    @Override
    public String toString() {
        return String.format("%s;%s;%s;%s;%s;%s;%s;%s",this.GetUUID(),this.GetUserName(),
                this.GetUserType(),this.GetEmail(),this.GetPassword() , this.GetIdCardNo() , this.GetAge() , this.GetSalary());
    }
}

