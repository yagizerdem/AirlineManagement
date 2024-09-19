package CODE;

public class SD {
    // frame names
    public static String RegisterFrameName = "registerFrame";
    public static String LogInFrameName = "logInFrame";
    public static String PassengerMenuFrameName = "PassengerMenuFrame";

    public static String PassengerSettingsFrameName = "PassengerSettings";

    public static String WeakPasswordErrorMessage = """
At least 8 characters long.
At least one lowercase letter.
At least one uppercase letter.
At least one digit.
At least one special character.
            """;
    public static String InvalidEmailErrorMessage = "Invalid Email";

    public static String PassengerCSVFileName = "Passenger.csv";
    public static String PlaneCSVFileName = "Plane.csv";
    public static String AirlineManagerFileName = "AirlineManager.csv";
    public static String InvalidUserName = """
            user name lenght must be:
             - greater than 2 chars
             - less than 30 chars
            """;
}
