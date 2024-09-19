package Models;

public class Passanger extends  BaseUser{

    @Override
    public String toString() {
        return String.format("%s;%s;%s;%s;%s",this.GetUUID(),this.GetUserName(),
                this.GetUserType(),this.GetEmail(),this.GetPassword());
    }
}
