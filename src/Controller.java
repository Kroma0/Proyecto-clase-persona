import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Locale.Category;

import javax.sound.sampled.SourceDataLine;

public class Controller implements Serializable{

    private DAO<Product> prod = new DAO();
    private DAO<Supplier> prov = new DAO();
    private DAO<Client> clie = new DAO();
    private PresenceRegisterDAO pres = new PresenceRegisterDAO();

    Locale localitzacioFormat = Locale.getDefault(Category.FORMAT);
    Locale localitzacioDisplay = Locale.getDefault(Category.DISPLAY);

    ResourceBundle texts = ResourceBundle.getBundle("Texts", localitzacioDisplay);
    NumberFormat numberFormatter = NumberFormat.getNumberInstance(localitzacioFormat);
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(localitzacioFormat);
    DateTimeFormatter pattern = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(localitzacioFormat);

    public void run() throws IOException, Exception  {
        //prod.open("products.dat");
        Scanner keyboard = new Scanner(System.in);
        int idperson;
        String dni;
        String name;
        String surnames;
        int idproduct;
        int price;
        int percentatgeDiscount;
        int stock;
        int option;
        int option2;
        int option3;
        String locality;
        String province;
        String zipCode;
        String direction;

            do {
                System.out.println(texts.getString("0000"));//"Que vols fer?"
                System.out.println(texts.getString("0001"));//"0.Sortir"
                System.out.println(texts.getString("0002"));//"1.Productes"
                System.out.println(texts.getString("0003"));//"2.Clients"
                System.out.println(texts.getString("0004"));//"3.Proveïdors"
                System.out.println(texts.getString("0005"));//"4.Control de presencia"

                option = keyboard.nextInt();
                keyboard.nextLine();

                switch (option) {
                    case 0:
                        prod.save();
                        break;
                    case 1:
                        do {
                            System.out.println("Que vols fer?");
                            System.out.println("0.Sortir");
                            System.out.println("1.Afegir producte/pack");
                            System.out.println("2.Buscar producte/pack");
                            System.out.println("3.Modificar producte");
                            System.out.println("4.Eliminar producte/pack");
                            System.out.println("5.Mostrar tots els productes/packs");
                            System.out.println("6.Treure stock d'un producte");
                            System.out.println("7.Afegir stock d'un producte");
                            System.out.println("8.Guardar tots els productes");
                            System.out.println("9.Obrir archiu amb productes");
                            System.out.println("10.Fer una comanda");
                            System.out.println("11.Productes ordenats");
                            System.out.println("12.Productes descatalogats ");

                            option2 = keyboard.nextInt();
                            keyboard.nextLine();

                            switch (option2) {
                                case 1:
                                    System.out.println("1.Afegir un producte");
                                    System.out.println("2.Afegir un pack");
                                    option3 = keyboard.nextInt();

                                    if (option3 == 1) {
                                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                        System.out.println("ID del producte:");
                                        idproduct = keyboard.nextInt();
                                        keyboard.nextLine();

                                        System.out.println("Nom del producte:");
                                        name = keyboard.nextLine();

                                        System.out.println("Preu del producte:");
                                        price = keyboard.nextInt();

                                        System.out.println("Stock del producte:");
                                        stock = keyboard.nextInt();

                                        System.out.println("Fecha de inicio del catalogo (Data (dd/MM/yyyy):");
                                        String startDate = keyboard.next();
                                        LocalDate sd = LocalDate.parse(startDate, dtf);

                                        System.out.println("Fecha de fin del catalogo (Data (dd/MM/yyyy):");
                                        String endingDate = keyboard.next();
                                        LocalDate ed = LocalDate.parse(endingDate, dtf);


                                        Product p = new Product(idproduct, name, price, stock, sd, ed);

                                        prod.add(p);
                                        System.out.println("Producte afegit");
                                    }

                                    if (option3 == 2) {
                                        System.out.println("Percentatge de descompte del pack:");
                                        percentatgeDiscount = keyboard.nextInt();
                                        keyboard.nextLine();

                                        System.out.println("ID del pack:");
                                        idproduct = keyboard.nextInt();
                                        keyboard.nextLine();

                                        System.out.println("Nom del pack:");
                                        name = keyboard.nextLine();

                                        System.out.println("Preu del pack:");
                                        price = keyboard.nextInt();

                                        TreeSet<Product> productList = new TreeSet<>();
                                        int option4;
                                        do{
                                            System.out.println("0.Sortir");
                                            System.out.println("1.Afegir producte al pack");
                                            option4 = keyboard.nextInt();
                                            keyboard.nextLine();
                                            if(option4 == 1){
                                                int id;
                                                System.out.println("Escriu el id del producte que vols afegir al pack:");
                                                id = keyboard.nextInt();
                                                Product pr = (Product) prod.get(id);
                                                if (productList.contains(pr)) {
                                                    System.out.println("Aquest producte ja existeix per la qual cosa no s'afegirà");
                                                }
                                                productList.add(pr);
                                            }
                                        }while(option4 != 0);

                                        if (prod.containsProducts(productList)) {
                                            System.out.println("Ja exiteix un pack amb aquests productes");
                                        }
                                        Pack p = new Pack(productList, percentatgeDiscount, idproduct, name, price);
                                        prod.add(p);
                                        System.out.println("Pack afegit");
                                    }
                                    break;
                                case 2:
                                    System.out.println("1.Buscar un producte");
                                    System.out.println("2.Buscar un pack");
                                    option3 = keyboard.nextInt();
                                    if (option3 == 1) {
                                        System.out.println("Id del producte:");
                                        idproduct = keyboard.nextInt();
                                        if (prod.search(idproduct) != null) {
                                            Product productes = (Product) prod.search(idproduct);
                                            System.out.println(productes.toString());
                                        } else {
                                            System.out.println("El producte no existeix");
                                        }
                                    }

                                    if (option3 == 2) {
                                        System.out.println("Id del pack:");
                                        idproduct = keyboard.nextInt();
                                        if (prod.search(idproduct) != null) {
                                            Pack pck = (Pack) prod.search(idproduct);
                                            System.out.println(pck.toString());
                                        } else {
                                            System.out.println("El pack no existeix");
                                        }
                                    }

                                    break;
                                case 3:
                                    System.out.println("ID del producte que vols modificar:");
                                    idproduct = keyboard.nextInt();
                                    keyboard.nextLine();
                                    if (prod.search(idproduct) != null) {
                                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                                        System.out.println("Nom del producte:");
                                        name = keyboard.nextLine();

                                        System.out.println("Preu del producte:");
                                        price = keyboard.nextInt();

                                        System.out.println("Stock del producte:");
                                        stock = keyboard.nextInt();

                                        System.out.println("Fecha de inicio del catalogo (Data (dd/MM/yyyy):");
                                        String startDate = keyboard.next();
                                        LocalDate sd = LocalDate.parse(startDate, dtf);

                                        System.out.println("Fecha de fin del catalogo (Data (dd/MM/yyyy):");
                                        String endingDate = keyboard.next();
                                        LocalDate ed = LocalDate.parse(endingDate, dtf);

                                        Product p = new Product(idproduct, name, price, stock, sd, ed);
                                        prod.modify(p);

                                        Product products = (Product) prod.search(idproduct);
                                        System.out.println(products.toString());
                                    } else {
                                        System.out.println("El producte no existeix");
                                    }
                                    break;
                                case 4:
                                    System.out.println("ID del producte/pack que vols eliminar:");
                                    idproduct = keyboard.nextInt();
                                    keyboard.nextLine();
                                    prod.delete(idproduct);

                                    break;
                                case 5:
                                    Persistable p = prod;
                                    print(p);
                                    break;
                                case 6:
                                    System.out.println("ID del producte:");
                                    idproduct = keyboard.nextInt();
                                    keyboard.nextLine();
                                    System.out.println("Quantitat que vols treure:");
                                    int amountT = keyboard.nextInt();
                                    keyboard.nextLine();
                                    Product productsT = (Product) prod.search(idproduct);
                                    productsT.takeStock(amountT);
                                    break;
                                case 7:
                                    System.out.println("1.Afegir manualment");
                                    System.out.println("2.Afegir amb un arxiu");
                                    option3 = keyboard.nextInt();
                                    keyboard.nextLine();
                                    if (option3 == 1){
                                        System.out.println("ID del producte:");
                                        idproduct = keyboard.nextInt();
                                        keyboard.nextLine();
                                        System.out.println("Quantitat que vols afegir:");
                                        int amountP = keyboard.nextInt();
                                        keyboard.nextLine();
                                        Product productsP = (Product) prod.search(idproduct);
                                        productsP.putStock(amountP);
                                    } else {
                                        System.out.println("Nom de fitxer:");
                                        File f = new File(keyboard.nextLine() + ".txt");
                                        try(DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(f)))){
                                            while(dis.available() > 0){
                                                int id = dis.readInt();
                                                int quantitat = dis.readInt();
                                                
                                                if(prod.search(id) != null){
                                                    Product productsP = (Product) prod.search(id);
                                                    productsP.putStock(quantitat);
                                                }
                                            }
                                        }
                                        
                                        
                                    }
                                    
                                    break;
                                case 8:
                                    prod.save();
                                    System.out.println("Productes guardats");
                                    break;
                                case 9:
                                    System.out.println("Nom del archiu:");
                                    String file = keyboard.nextLine();
                                    //prod.open(file);
                                    break;
                                case 10:
                                    do{
                                        System.out.println("Que vols fer?");
                                        System.out.println("0.Sortir");
                                        System.out.println("1.Fer una comanda");
                                        option3 = keyboard.nextInt();
                                        keyboard.nextLine();
                                        
                                        if(option3 == 1){
                                            int id;
                                            int amount;
                                            
                                            System.out.println("Nom del fitxer:");
                                            File f = new File(keyboard.nextLine() + ".txt");
                                            if(!f.exists()){
                                                DataOutputStream dos = new DataOutputStream(new FileOutputStream(f));
                                                System.out.println("ID del producte:");
                                                id = keyboard.nextInt();
                                                System.out.println("Quantitat desitjada:");
                                                amount = keyboard.nextInt();
                                                dos.writeInt(id);
                                                dos.writeInt(amount);
                                                dos.close();
                                            } else {
                                                System.out.println("Aquest arxiu ja existeix");
                                            }
                                        }
                                    }while(option3 != 0);
                                    break;
                                case 11:
                                    List<Product> allProd = new ArrayList<Product>(prod.getMap().values());
                                    System.out.println("Per que vols ordenar la llista de productes?");
                                    System.out.println("1.Nom");
                                    System.out.println("2.Preu");
                                    System.out.println("3.Stock");
                                    
                                    option3 = keyboard.nextInt();
                                    
                                    switch (option3) {
                                        case 1:
                                            Collections.sort(allProd, (pr1, pr2) -> pr1.getName().compareTo(pr2.getName()));
                                            System.out.println(allProd.toString());
                                            break;
                                        case 2:
                                            Collections.sort(allProd, (pr1, pr2) -> pr1.getPrice().compareTo(pr2.getPrice()));
                                            System.out.println(allProd.toString());
                                            break;
                                        case 3:
                                            Collections.sort(allProd, (pr1, pr2) -> pr1.getStock().compareTo(pr2.getStock()));
                                            System.out.println(allProd.toString());
                                            break;
                                    
                                        default:
                                            System.out.println("Esta opcion no existe");
                                            break;
                                    }
                                    System.out.println(allProd.toString());
                                break;

                                case 12:
                                    //List<Product> allProd = new ArrayList<Product>(prod.getMap().values());
                                    List<Product> allProd2 = new ArrayList<Product>(prod.getMap().values());
                                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                    System.out.println("Introduce fecha a comparar:");
                                    String fecha = keyboard.next();
                                    
                                    if (fecha == null) {
                                        LocalDate fecha2 = java.time.LocalDate.now();
                                        showDiscontinued(fecha2);

                                    } else {
                                        LocalDate fech = LocalDate.parse(fecha, dtf);
                                        showDiscontinued(fech);

                                    }
                                    
                                break;

                                default:
                                    System.out.println("Opción incorrecta");
                                    break;
                            }
                        } while (option2 != 0);
                        break;

                    case 2:
                        do {
                            System.out.println("Que vols fer?");
                            System.out.println("0.Sortir");
                            System.out.println("1.Afegir client");
                            System.out.println("2.Buscar client");
                            System.out.println("3.Modificar client");
                            System.out.println("4.Esborrar client");
                            System.out.println("5.Mostrar tots els clients");
                            option2 = keyboard.nextInt();
                            keyboard.nextLine();
                            switch (option2) {
                                case 1:
                                    System.out.println("ID del client:");
                                    idperson = keyboard.nextInt();
                                    keyboard.nextLine();

                                    System.out.println("DNI del client:");
                                    dni = keyboard.nextLine();

                                    System.out.println("Nom del client:");
                                    name = keyboard.nextLine();

                                    System.out.println("Cognom del client:");
                                    surnames = keyboard.nextLine();

                                    System.out.println("Localitat del client");
                                    locality = keyboard.nextLine();

                                    System.out.println("Provincia del client");
                                    province = keyboard.nextLine();
                                    System.out.println("Codi postal del client");
                                    zipCode = keyboard.nextLine();

                                    System.out.println("Direcció del client");
                                    direction = keyboard.nextLine();

                                    LinkedHashSet<String> phoneList = new LinkedHashSet<>();
                                        int option4;
                                        do{
                                            System.out.println("0.Sortir");
                                            System.out.println("1.Afegir numero de telefon");
                                            option4 = keyboard.nextInt();
                                            keyboard.nextLine();
                                            if(option4 == 1){
                                                System.out.println("Escriu telefon que vols afegir");
                                                String pr = keyboard.nextLine();
                                                if (phoneList.contains(pr)) {
                                                    System.out.println("Aquest telèfon ja existeix per la qual cosa no s'afegirà");
                                                }
                                                phoneList.add(pr);
                                            }
                                        }while(option4 != 0);
                                    Address a = new Address(locality, province, zipCode, direction);
                                    Client cl = new Client(idperson, dni, name, surnames, a, phoneList);

                                    clie.add(cl);
                                    System.out.println("Client afegit");
                                    break;
                                case 2:
                                    System.out.println("Id del client:");
                                    idperson = keyboard.nextInt();
                                    if (clie.search(idperson) != null) {
                                        Client client = (Client) clie.search(idperson);
                                        System.out.println(client.toString());
                                    } else {
                                        System.out.println("El client no existeix");
                                    }
                                    break;
                                case 3:
                                    System.out.println("ID del client que vols modificar:");
                                    idperson = keyboard.nextInt();
                                    keyboard.nextLine();
                                    if (clie.search(idperson) != null) {
                                        System.out.println("DNI del client:");
                                        dni = keyboard.nextLine();

                                        System.out.println("Nom del client:");
                                        name = keyboard.nextLine();

                                        System.out.println("Cognom del client:");
                                        surnames = keyboard.nextLine();

                                        System.out.println("Localitat del client");
                                        locality = keyboard.nextLine();

                                        System.out.println("Provincia del client");
                                        province = keyboard.nextLine();
                                        System.out.println("Codi postal del client");
                                        zipCode = keyboard.nextLine();

                                        System.out.println("Direcció del client");
                                        direction = keyboard.nextLine();

                                        Address ad = new Address(locality, province, zipCode, direction);

                                        Client c = new Client(idperson, dni, name, surnames, ad);
                                        clie.modify(c);

                                    } else {
                                        System.out.println("El client no existeix");
                                    }
                                    break;
                                case 4:
                                    System.out.println("ID del client que vols eliminar:");
                                    idperson = keyboard.nextInt();
                                    clie.delete(idperson);
                                    break;
                                case 5:
                                    Persistable p = clie;
                                    print(p);
                                    break;
                            }
                        } while (option2 != 0);
                        break;
                    case 3:
                        do {
                            System.out.println("Que vols fer?");
                            System.out.println("0.Sortir");
                            System.out.println("1.Afegir proveidor");
                            System.out.println("2.Buscar proveidor");
                            System.out.println("3.Modificar proveidor");
                            System.out.println("4.Esborrar proveidor");
                            System.out.println("5.Mostrar tots els proveidors");
                            option2 = keyboard.nextInt();
                            keyboard.nextLine();
                            switch (option2) {
                                case 1:
                                    System.out.println("ID del proveidor:");
                                    idperson = keyboard.nextInt();
                                    keyboard.nextLine();

                                    System.out.println("DNI del proveidor:");
                                    dni = keyboard.nextLine();

                                    System.out.println("Nom del proveidor:");
                                    name = keyboard.nextLine();

                                    System.out.println("Cognom del proveidor:");
                                    surnames = keyboard.nextLine();

                                    System.out.println("Localitat del client");
                                    locality = keyboard.nextLine();

                                    System.out.println("Provincia del client");
                                    province = keyboard.nextLine();
                                    System.out.println("Codi postal del client");
                                    zipCode = keyboard.nextLine();

                                    System.out.println("Direcció del client");
                                    direction = keyboard.nextLine();

                                    LinkedHashSet<String> phoneList = new LinkedHashSet<>();
                                        int option4;
                                        do{
                                            System.out.println("0.Sortir");
                                            System.out.println("1.Afegir numero de telefon");
                                            option4 = keyboard.nextInt();
                                            keyboard.nextLine();
                                            if(option4 == 1){
                                                System.out.println("Escriu telefon que vols afegir");
                                                String pr = keyboard.nextLine();
                                                if (phoneList.contains(pr)) {
                                                    System.out.println("Aquest telèfon ja existeix per la qual cosa no s'afegirà");
                                                }
                                                phoneList.add(pr);
                                            }
                                        }while(option4 != 0);

                                    Address a = new Address(locality, province, zipCode, direction);

                                    Supplier s = new Supplier(idperson, dni, name, surnames, a, phoneList);

                                    prov.add(s);
                                    System.out.println("Proveidor afegit");
                                    break;
                                case 2:
                                    System.out.println("Id del proveidor:");
                                    idperson = keyboard.nextInt();
                                    if (prov.search(idperson) != null) {
                                        Supplier proveidor = (Supplier) prov.search(idperson);
                                        System.out.println(proveidor.toString());
                                    } else {
                                        System.out.println("El proveidor no existeix");
                                    }
                                    break;
                                case 3:
                                    System.out.println("ID del proveidor que vols modificar:");
                                    idperson = keyboard.nextInt();
                                    if (clie.search(idperson) != null) {
                                        System.out.println("DNI del proveidor:");
                                        dni = keyboard.nextLine();

                                        System.out.println("Nom del proveidor:");
                                        name = keyboard.nextLine();

                                        System.out.println("Cognom del proveidor:");
                                        surnames = keyboard.nextLine();

                                        System.out.println("Localitat del proveidor");
                                        locality = keyboard.nextLine();

                                        System.out.println("Provincia del proveidor");
                                        province = keyboard.nextLine();
                                        System.out.println("Codi postal del proveidor");
                                        zipCode = keyboard.nextLine();

                                        System.out.println("Direcció del proveidor");
                                        direction = keyboard.nextLine();

                                        Address ad = new Address(locality, province, zipCode, direction);
                                        Supplier su = new Supplier(idperson, dni, name, surnames, ad);
                                        prov.modify(su);

                                        Supplier proveidor = (Supplier) prov.search(idperson);
                                        System.out.println(proveidor.toString());
                                    } else {
                                        System.out.println("El proveidor no existeix");
                                    }
                                    break;
                                case 4:
                                    System.out.println("ID del proveidor que vols eliminar:");
                                    idperson = keyboard.nextInt();
                                    prov.delete(idperson);
                                    break;
                                case 5:
                                    print(prov);
                                    break;
                            }

                        } while (option2 != 0);
                        break;
                    case 4:
                        do {
                            System.out.println("Que vols fer?");
                            System.out.println("0.Sortir");
                            System.out.println("1.Fitxar entrada");
                            System.out.println("2.Fitxar sortida");
                            System.out.println("3.Consultar fitxada");
                            option2 = keyboard.nextInt();
                            keyboard.nextLine();
                            switch (option2) {
                                case 1:
                                    System.out.println("ID del treballador a fitxar la entrada:");
                                    int idPresence1 = keyboard.nextInt();
                                    Presence p = new Presence(idPresence1, LocalDate.now(), LocalTime.now());
                                    //con este sout vemos como varia la fecha dependiendo del idioma del sistema
                                    System.out.println(LocalDate.now().format(pattern));
                                    pres.add(p);
                                    break;
                                case 2:
                                    System.out.println("ID del treballador a fitxar la sortida:");
                                    int idPresence2 = keyboard.nextInt();
                                    pres.clockOut(idPresence2);
                                    break;
                                case 3:
                                    System.out.println("ID del treballador a fitxar la sortida:");
                                    int idPresence3 = keyboard.nextInt();
                                    System.out.println(pres.getWorker(idPresence3));
                                    break;
                            }
                        } while (option2 != 0);
                        break;
                }
            } while (option != 0);

    }

    public static void print(Persistable obj) {
        System.out.println(obj.getMap().toString());
    }
    
    public void showDiscontinued(LocalDate data){
        HashMap<Integer, Product> prods = prod.getMap();
        for (Product pr : prods.values()) {
            if (pr.getEndingCatalogue().compareTo(data) < 0) {
                System.out.println("Productos descatalogados:");
                System.out.println("Hace "+ChronoUnit.DAYS.between(pr.getEndingCatalogue(), data)+" dias que se descatalogo: "+ pr.toString());
            }
            
        }
    }
}
