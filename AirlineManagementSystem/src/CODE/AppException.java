package CODE;

public class AppException extends  Exception{
    public String developerMessage = "";
   public boolean isCritical = false;
    public  AppException(){

    }
    public  AppException(String developerMessage, boolean isCritical){
        this.developerMessage = developerMessage;
        this.isCritical = isCritical;
    }
}
