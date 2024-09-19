package CODE;

import Models.AirlineManager;
import Models.BaseUser;
import Models.Passanger;
import Models.UserType;

import javax.net.ssl.SSLEngineResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PipedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Database {
    private static Database instance  = null;
    private BaseUser activeUser = null;
    private Hashtable<String , Passanger> PassangerTable = new Hashtable<>();
    private Hashtable<String , AirlineManager> AirlineManagerTable = new Hashtable<>();
    private static final Lock lock = new ReentrantLock(true);
    public Database() throws  Exception{
        if(Database.instance == null){
            Database.instance = this;
        }
        else{
            return;
        }
        this.CreateFiles();
        this.SeedData();
        // read and store data in dynamic hashtable
        this.readPassengerCSV();
        this.readAirlineManagerCSV();
    }
    public static Database getDatabase() throws Exception{
        lock.lock();
        try{
            if(Database.instance == null){
                return  new Database();
            }
            else{
                return  Database.instance;
            }
        }finally {
            lock.unlock();
        }
    }
    public Passanger GetPassengerByEmail(String email){
        Enumeration<String> keys = this.PassangerTable.keys();
        // linear search
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            Passanger passangerFromDb = this.PassangerTable.get(key);
            if(passangerFromDb.GetEmail().equals(email)){
                return  passangerFromDb;
            }
        }
        return null;
    }
    public Passanger GetPassengerByUUID(String uuid){
        return this.PassangerTable.get(uuid);
    }
    private void writePassangerCSV() throws Exception{
        Enumeration<String> iterator = this.PassangerTable.keys();
        FileWriter myWriter = null;
        try{
            myWriter = new FileWriter(SD.PassengerCSVFileName);
            while (iterator.hasMoreElements()){
                String key = iterator.nextElement();
                myWriter.write(this.PassangerTable.get(key).toString());
                myWriter.write(System.lineSeparator()); // move cursor to next line
            }
        }catch (Exception e){
            throw  new AppException("error occured while writing file" ,true);
        }
        finally {
            try {
                if (myWriter != null) {
                    myWriter.flush();
                    myWriter.close();
                }
            } catch (IOException e) {
                throw  new AppException("error occured while writing file" ,true);
            }
        }
    }
    private void writeAirlineManager() throws Exception{
        Enumeration<String> iterator = this.AirlineManagerTable.keys();
        FileWriter myWriter = null;
        try{
            myWriter = new FileWriter(SD.AirlineManagerFileName);
            while (iterator.hasMoreElements()){
                String key = iterator.nextElement();
                myWriter.write(this.AirlineManagerTable.get(key).toString());
                myWriter.write(System.lineSeparator()); // move cursor to next line
            }
        }catch (Exception e){
            throw  new AppException("error occured while writing file" ,true);
        }
        finally {
            try {
                if (myWriter != null) {
                    myWriter.flush();
                    myWriter.close();
                }
            } catch (IOException e) {
                throw  new AppException("error occured while writing file" ,true);
            }
        }
    }
    private void readPassengerCSV() throws Exception{
        Path CSVfilePath = Paths.get(SD.PassengerCSVFileName);
        try{
            Stream<String> rows = Files.lines(CSVfilePath);
            rows.forEach(row ->{
                try{
                    String[] cols = row.split(";");
                    Passanger passengerFromDb = new Passanger();
                    passengerFromDb.SetUUID(cols[0]);
                    passengerFromDb.SetUserName(cols[1]);
                    passengerFromDb.SetUserType(UserType.valueOf(cols[2]));
                    passengerFromDb.SetEmail(cols[3]);
                    passengerFromDb.SetPassword(cols[4]);
                    // save in dynamic database
                    this.PassangerTable.put(passengerFromDb.GetUUID() , passengerFromDb);
                }catch (Exception e){
                    throw new RuntimeException("error while parse passenger data", e);
                }
            } );
        }catch (Exception e){
            throw  new AppException("error occured while reading passenger csv file" , true);
        }

    }
    private void readAirlineManagerCSV() throws Exception{
        Path CSVfilePath = Paths.get(SD.AirlineManagerFileName);
        try{
            Stream<String> rows = Files.lines(CSVfilePath);
            rows.forEach(row ->{
                try{
                    String[] cols = row.split(";");
                    AirlineManager airlineManager = new AirlineManager();
                    airlineManager.SetUUID(cols[0]);
                    airlineManager.SetUserName(cols[1]);
                    airlineManager.SetUserType(UserType.valueOf(cols[2]));
                    airlineManager.SetEmail(cols[3]);
                    airlineManager.SetPassword(cols[4]);
                    airlineManager.SetIdCardNo(cols[5]);
                    airlineManager.SetAge(Integer.parseInt(cols[6]));
                    airlineManager.SetSalary(Double.parseDouble(cols[7]));
                    // save in dynamic database
                    this.AirlineManagerTable.put(airlineManager.GetUUID() , airlineManager);
                }catch (Exception e){
                    throw new RuntimeException("error while parse passenger data", e);
                }
            } );
        }catch (Exception e){
            throw  new AppException("error occured while reading passenger csv file" , true);
        }

    }
    public Passanger CreatePassanger(String userName , String email ,String password) throws Exception{
        Passanger isExist = GetPassengerByEmail(email);
        if(isExist != null){
            throw new AppException("user with this email already exist" , false);
        }

        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        Passanger passanger = new Passanger();
        passanger.SetEmail(email);
        passanger.SetPassword(password);
        passanger.SetUserType(UserType.Passanger);
        passanger.SetUserName(userName);
        passanger.SetUUID(uuidAsString);
        this.PassangerTable.put(uuidAsString , passanger);
        // save to file and then refresh databae
        writePassangerCSV();
        //
        return  passanger;
    }
    public void SetActiveUser(BaseUser user){
        this.activeUser = user;
    }
    public BaseUser GetActiveUser(){
        return this.activeUser;
    }
    public void CreateFiles() throws AppException{
        ArrayList<String> files = new ArrayList<>();
        files.add(SD.PassengerCSVFileName);
        files.add(SD.PlaneCSVFileName);
        files.add(SD.AirlineManagerFileName);
        try{
            for(String fName : files){
                Path PassengerCSVfilePath = Paths.get(fName);
                if (!Files.exists(PassengerCSVfilePath)) {
                    Files.createFile(PassengerCSVfilePath);
                    System.out.println("File created: " + PassengerCSVfilePath.toAbsolutePath());
                } else {
                    System.out.println("File already exists: " + PassengerCSVfilePath.toAbsolutePath());
                }
            }
        }catch (Exception e){
            throw  new AppException("db not initilized" , true);
        }
    }
    public BaseUser LogIn(String email , String  password) throws  Exception{
        Passanger passanger = this.GetPassengerByEmail(email);
        if(passanger.GetPassword().equals(password)) return  passanger;
        throw new AppException("user credentials are wrong" , false);
    }
    public Passanger updatePassenger(String newEmail , String newPassword) throws  Exception{
        if(activeUser.GetUserType() != UserType.Passanger) throw new AppException("invalid user type" , false);
        this.activeUser.SetEmail(newEmail);
        this.activeUser.SetPassword(newPassword);
        // save changes in file
        writePassangerCSV();
        return  (Passanger)activeUser;

    }
    public void SeedData() throws  Exception{
        AirlineManager manager = new AirlineManager();
        manager.SetSalary(1500);
        manager.SetEmail("manager1@gmail.com");
        manager.SetPassword("1aAbc!!d2");
        manager.SetAge(34);
        manager.SetUUID(UUID.randomUUID().toString());
        manager.SetUserName("manager1");
        manager.SetUserType(UserType.AirlineManager);
        manager.SetIdCardNo("440284232");

        AirlineManager manager2 = new AirlineManager();
        manager2.SetSalary(1800);
        manager2.SetEmail("manager2@gmail.com");
        manager2.SetPassword("1aAerc!!d2");
        manager2.SetAge(34);
        manager2.SetUUID(UUID.randomUUID().toString());
        manager2.SetUserName("manager2");
        manager2.SetUserType(UserType.AirlineManager);
        manager2.SetIdCardNo("327495739");

        this.AirlineManagerTable.put(manager.GetUUID() , manager);
        this.AirlineManagerTable.put(manager2.GetUUID() , manager2);

        writeAirlineManager();

    }
}
