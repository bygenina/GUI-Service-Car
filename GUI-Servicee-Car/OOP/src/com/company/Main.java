package com.company;

import com.company.exception.NeverRentException;
import com.company.exception.TooManyThingsException;
import com.company.service.*;
import com.company.transport.TransportAmfibia;
import com.company.transport.TransportCityCar;
import com.company.transport.TransportMotorbike;
import com.company.transport.TransportOffRoadCar;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.TooManyListenersException;

enum Type {PERSON, SERVICE, CONSUMERWAREHOUSE, PARKINGSPOT, SERVICEWAREHOUSE, CARSERVICESPOT, INDEPENDETCARSERVICESPOT, ITEMS}

public class Main {

    public int id () {
        return (int)(Math.random()*100);
    }

    public String idKey (Type type){
        switch (type) {
            case PERSON:
                return "P" + id();
            case SERVICE:
                return "S" + id();
            case CONSUMERWAREHOUSE:
                return "CW" + id();
            case PARKINGSPOT:
                return "PS" + id();
            case SERVICEWAREHOUSE:
                return "SW" + id();
            case CARSERVICESPOT:
                return "CSS" + id();
            case INDEPENDETCARSERVICESPOT:
                return "ICSS" + id();
            case ITEMS:
                return "I" +id();
            default:
                return null;
        }
    }

    Service serviceMachine = new Service(idKey(Type.SERVICE), "Autoserwis A&M", "Warszawska 40A", 12, 50, 50, LocalTime.of(8, 30), LocalTime.of(22, 0));

    ConsumerWarehouse consumerWinter = new ConsumerWarehouse(idKey(Type.CONSUMERWAREHOUSE), 3, 10, 5, 70);
    ConsumerWarehouse consumerSummer = new ConsumerWarehouse(idKey(Type.CONSUMERWAREHOUSE), 220, 100);

    CarServiceSpot carServiceSpotDay = new CarServiceSpot(idKey(Type.CARSERVICESPOT), 350, 150);
    CarServiceSpot carServiceSpotNight = new CarServiceSpot(idKey(Type.CARSERVICESPOT), 3, 10, 10, 200);
    CarServiceSpot carServiceSpotOrange = new CarServiceSpot(idKey(Type.CARSERVICESPOT), 4, 15, 15, 300);

    IndependentCarServiceSpot independentCarServiceSpotHands = new IndependentCarServiceSpot(idKey(Type.INDEPENDETCARSERVICESPOT), 200, 100);
    IndependentCarServiceSpot independentCarServiceSpotHead = new IndependentCarServiceSpot(idKey(Type.INDEPENDETCARSERVICESPOT), 3, 5, 5, 75);
    IndependentCarServiceSpot independentCarServiceSpotBlack = new IndependentCarServiceSpot(idKey(Type.INDEPENDETCARSERVICESPOT), 3, 10, 10, 175);

    ParkingSpot parkingSpotTriangle = new ParkingSpot(idKey(Type.PARKINGSPOT), 300, 50);
    ParkingSpot parkingSpotCircle = new ParkingSpot(idKey(Type.PARKINGSPOT), 2.5, 10, 10, 35);

    Person personArinaStreaking = new Person(idKey(Type.PERSON), "Arina", "Streaking", "99220378634", "Opaczewska 20, 02-372 Warszawa", "1999-03-22", LocalDate.of(2022, 7, 22));
    Person personMarinaSmith = new Person(idKey(Type.PERSON), "Marina", "Smith", "00051154331", "Krakowskie Przedmieście 68, 00-322 Warszawa", "2000-11-05",null);
    Person personAdamNowak = new Person(idKey(Type.PERSON), "Adam", "Nowak", "78010173434", "Senatorska 2, 00-075 Warszawa", "1978-01-01", LocalDate.of(2018, 10, 3));
    Person personRobertLewandowski = new Person(idKey(Type.PERSON), "Robert", "Lewanadowski", "88210839510", "aleja Solidarności 115, 00-140 Warszawa", "1988-08-21", LocalDate.of(2021, 12, 2));
    Person personMaciejKowalczyk = new Person(idKey(Type.PERSON), "Maciej", "Kowalski", "01311244651", "Grzybowska 32, 00-863 Warszawa", "2001-12-31",null);

    TransportAmfibia amfibia = new TransportAmfibia("VW typ 166 'Schwimmwagen'", 1.08, 1.48, 3.825, 25);
    TransportCityCar cityCar = new TransportCityCar("Audi R8 V12 TDi 2008", 0.5, 0.9, 1.1, 10);
    TransportMotorbike motorbike = new TransportMotorbike("Suzuki V-Strom Grand Touring 2006", 1.395, 0.91, 2.295, 996);
    TransportOffRoadCar transportOffRoadCar = new TransportOffRoadCar("JEEP WRANGLER III", 1.865, 1.873, 4.223, 267);

    Items winterTire = new Items(idKey(Type.ITEMS), "Winter tire", 0.5, 0.5,0.4);
    Items summerTire = new Items(idKey(Type.ITEMS), "Summer tire", 0.4, 0.4,0.3);
    Items chair = new Items(idKey(Type.ITEMS), "Chair", 1, 0.4,0.5);

    // dadajemy serwisy i magazyny do serwisu
    public void addWarehouse () {
        try {
            consumerWinter.addWarehouse(serviceMachine);
            consumerSummer.addWarehouse(serviceMachine);

            carServiceSpotDay.addWarehouse(serviceMachine);
            carServiceSpotNight.addWarehouse(serviceMachine);
            carServiceSpotOrange.addWarehouse(serviceMachine);

            independentCarServiceSpotHands.addWarehouse(serviceMachine);
            independentCarServiceSpotHead.addWarehouse(serviceMachine);
            independentCarServiceSpotBlack.addWarehouse(serviceMachine);

            parkingSpotTriangle.addWarehouse(serviceMachine);
            parkingSpotCircle.addWarehouse(serviceMachine);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Scanner scanner = new Scanner(System.in);

    public void system () throws TooManyThingsException {

        System.out.println(serviceMachine.getName() + " serwis samohodowy, zapraszamy do nas " + serviceMachine.getAddress() + " z pn-sb w godzinach " + serviceMachine.getOpenService() + "-" + serviceMachine.getCloseService());

        System.out.println("Wyjsc? 1 - Tak");
        int out = scanner.nextInt();
        if(out == 1 ) {
            System.exit(-1);
        }
        System.out.print("Prosimy o podaniu daty rezerwacji: rok: ");
        int year = scanner.nextInt();
        System.out.print("miesiac: ");
        int month = scanner.nextInt();
        System.out.print("dzien: ");
        int day = scanner.nextInt();

        System.out.println("Prosimy o wyboru celu: " + "\n" +
                "1. Rezerwacja magazynu" + "\n" +
                "2. Naprawnienie samochodowe" + "\n" +

                "Prosimy o wpisaniu konkretnej literki: ");

        switch (scanner.nextInt()) {
            case 1:
                System.out.println("Prosimy wybrac magazyn/serwis po naprawie: " + "\n" +
                        "1." + consumerWinter.toString() + "\n" +
                        "2." + consumerSummer.toString() + "\n" +
                        "Prosimy o wpisaniu konkretnej literki: ");
                switch (scanner.nextInt()) {
                    case 1:
                        System.out.println("Prosimy o wyboru osoby: " + "\n" +
                                "1. " + personArinaStreaking.getName() + " " + personArinaStreaking.getFirstName() + "\n" +
                                "2. " + personMarinaSmith.getName() + " " + personMarinaSmith.getFirstName() + "\n" +
                                "3. " + personAdamNowak.getName() + " " + personAdamNowak.getFirstName() + "\n" +
                                "4. " + personRobertLewandowski.getName() + " " + personRobertLewandowski.getFirstName() + "\n" +
                                "5. " + personMaciejKowalczyk.getName() + " " + personMaciejKowalczyk.getFirstName() + "\n" +
                                "Prosimy o wpisaniu konkretnej literki: ");
                        switch (scanner.nextInt()) {
                            case 1:
                                consumerWinter.rentWarehouse(personArinaStreaking, LocalDate.of(year, month, day));
                                consumerWinter.grandPermission(personArinaStreaking);
                                System.out.println("Możemy wlożuc przedmiot. 1 - Tak, 2 - Nie");
                                int out1 = scanner.nextInt();
                                if(out1 == 1) {
                                    System.out.println("Jakij to bendzie przedmiot: " + "\n" +
                                            "1. " + winterTire.toString() + "\n" +
                                            "2. " + summerTire.toString() + "\n" +
                                            "3. " + chair.toString() + "\n" +
                                            "Prosze wpisacz numer: ");
                                    if(scanner.nextInt() == 1) {
                                        consumerWinter.addItems(personArinaStreaking, winterTire);
                                    } else if (scanner.nextInt() == 2) {
                                        consumerWinter.addItems(personArinaStreaking, summerTire);
                                    } else if (scanner.nextInt() == 3) {
                                        consumerWinter.addItems(personArinaStreaking, chair);
                                    } else {
                                        System.out.println("Nie mamy takiego przedmiotu");
                                        system();
                                    }
                                } else
                                    system();
                            case 2:
                                consumerWinter.rentWarehouse(personMarinaSmith, LocalDate.of(year, month, day));
                                consumerWinter.grandPermission(personMarinaSmith);
                                System.out.println("Możemy wlożuc przedmiot. 1 - Tak, 2 - Nie");
                                int out2 = scanner.nextInt();
                                if(out2 == 1) {
                                    System.out.println("Jakij to bendzie przedmiot: " + "\n" +
                                            "1. " + winterTire.toString() + "\n" +
                                            "2. " + summerTire.toString() + "\n" +
                                            "3. " + chair.toString() + "\n" +
                                            "Prosze wpisacz numer: ");
                                    if(scanner.nextInt() == 1) {
                                        consumerWinter.addItems(personMarinaSmith, winterTire);
                                    } else if (scanner.nextInt() == 2) {
                                        consumerWinter.addItems(personMarinaSmith, summerTire);
                                    } else if (scanner.nextInt() == 3) {
                                        consumerWinter.addItems(personMarinaSmith, chair);
                                    } else {
                                        System.out.println("Nie mamy takiego przedmiotu");
                                        system();
                                    }
                                } else
                                    system();
                            case 3:
                                consumerWinter.rentWarehouse(personAdamNowak, LocalDate.of(year, month, day));
                                consumerWinter.grandPermission(personAdamNowak);
                                System.out.println("Możemy wlożuc przedmiot. 1 - Tak, 2 - Nie");
                                int out3 = scanner.nextInt();
                                if(out3 == 1) {
                                    System.out.println("Jakij to bendzie przedmiot: " + "\n" +
                                            "1. " + winterTire.toString() + "\n" +
                                            "2. " + summerTire.toString() + "\n" +
                                            "3. " + chair.toString() + "\n" +
                                            "Prosze wpisacz numer: ");
                                    if(scanner.nextInt() == 1) {
                                        consumerWinter.addItems(personAdamNowak, winterTire);
                                    } else if (scanner.nextInt() == 2) {
                                        consumerWinter.addItems(personAdamNowak, summerTire);
                                    } else if (scanner.nextInt() == 3) {
                                        consumerWinter.addItems(personAdamNowak, chair);
                                    } else {
                                        System.out.println("Nie mamy takiego przedmiotu");
                                        system();
                                    }
                                } else
                                    system();
                            case 4:
                                consumerWinter.rentWarehouse(personRobertLewandowski, LocalDate.of(year, month, day));
                                consumerWinter.grandPermission(personRobertLewandowski);
                                System.out.println("Możemy wlożuc przedmiot. 1 - Tak, 2 - Nie");
                                int out4 = scanner.nextInt();
                                if(out4 == 1) {
                                    System.out.println("Jakij to bendzie przedmiot: " + "\n" +
                                            "1. " + winterTire.toString() + "\n" +
                                            "2. " + summerTire.toString() + "\n" +
                                            "3. " + chair.toString() + "\n" +
                                            "Prosze wpisacz numer: ");
                                    if(scanner.nextInt() == 1) {
                                        consumerWinter.addItems(personRobertLewandowski, winterTire);
                                    } else if (scanner.nextInt() == 2) {
                                        consumerWinter.addItems(personRobertLewandowski, summerTire);
                                    } else if (scanner.nextInt() == 3) {
                                        consumerWinter.addItems(personRobertLewandowski, chair);
                                    } else {
                                        System.out.println("Nie mamy takiego przedmiotu");
                                        system();
                                    }
                                } else
                                    system();
                            case 5:
                                consumerWinter.rentWarehouse(personMaciejKowalczyk, LocalDate.of(year, month, day));
                                consumerWinter.grandPermission(personMaciejKowalczyk);
                                System.out.println("Możemy wlożuc przedmiot. 1 - Tak, 2 - Nie");
                                int out5 = scanner.nextInt();
                                if(out5 == 1) {
                                    System.out.println("Jakij to bendzie przedmiot: " + "\n" +
                                            "1. " + winterTire.toString() + "\n" +
                                            "2. " + summerTire.toString() + "\n" +
                                            "3. " + chair.toString() + "\n" +
                                            "Prosze wpisacz numer: ");
                                    if(scanner.nextInt() == 1) {
                                        consumerWinter.addItems(personMaciejKowalczyk, winterTire);
                                    } else if (scanner.nextInt() == 2) {
                                        consumerWinter.addItems(personMaciejKowalczyk, summerTire);
                                    } else if (scanner.nextInt() == 3) {
                                        consumerWinter.addItems(personMaciejKowalczyk, chair);
                                    } else {
                                        System.out.println("Nie mamy takiego przedmiotu");
                                        system();
                                    }
                                } else
                                    system();
                            default:
                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie");
                                system();
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("Prosimy o wyboru osoby: " + "\n" +
                                "1. " + personArinaStreaking.getName() + " " + personArinaStreaking.getFirstName() + "\n" +
                                "2. " + personMarinaSmith.getName() + " " + personMarinaSmith.getFirstName() + "\n" +
                                "3. " + personAdamNowak.getName() + " " + personAdamNowak.getFirstName() + "\n" +
                                "4. " + personRobertLewandowski.getName() + " " + personRobertLewandowski.getFirstName() + "\n" +
                                "5. " + personMaciejKowalczyk.getName() + " " + personMaciejKowalczyk.getFirstName() + "\n" +
                                "Prosimy o wpisaniu konkretnej literki: ");
                        switch (scanner.nextInt()) {
                            case 1:
                                consumerSummer.rentWarehouse(personArinaStreaking, LocalDate.of(year, month, day));
                                consumerSummer.grandPermission(personArinaStreaking);
                                System.out.println("Możemy wlożuc przedmiot. 1 - Tak, 2 - Nie");
                                int out1 = scanner.nextInt();
                                if(out1 == 1) {
                                    System.out.println("Jakij to bendzie przedmiot: " + "\n" +
                                            "1. " + winterTire.toString() + "\n" +
                                            "2. " + summerTire.toString() + "\n" +
                                            "3. " + chair.toString() + "\n" +
                                            "Prosze wpisacz numer: ");
                                    if(scanner.nextInt() == 1) {
                                        consumerSummer.addItems(personArinaStreaking, winterTire);
                                    } else if (scanner.nextInt() == 2) {
                                        consumerSummer.addItems(personArinaStreaking, summerTire);
                                    } else if (scanner.nextInt() == 3) {
                                        consumerSummer.addItems(personArinaStreaking, chair);
                                    } else {
                                        System.out.println("Nie mamy takiego przedmiotu");
                                        system();
                                    }
                                } else
                                system();
                            case 2:
                                consumerSummer.rentWarehouse(personMarinaSmith, LocalDate.of(year, month, day));
                                consumerSummer.grandPermission(personMarinaSmith);
                                System.out.println("Możemy wlożuc przedmiot. 1 - Tak, 2 - Nie");
                                int out2 = scanner.nextInt();
                                if(out2 == 1) {
                                    System.out.println("Jakij to bendzie przedmiot: " + "\n" +
                                            "1. " + winterTire.toString() + "\n" +
                                            "2. " + summerTire.toString() + "\n" +
                                            "3. " + chair.toString() + "\n" +
                                            "Prosze wpisacz numer: ");
                                    if(scanner.nextInt() == 1) {
                                        consumerSummer.addItems(personMarinaSmith, winterTire);
                                    } else if (scanner.nextInt() == 2) {
                                        consumerSummer.addItems(personMarinaSmith, summerTire);
                                    } else if (scanner.nextInt() == 3) {
                                        consumerSummer.addItems(personMarinaSmith, chair);
                                    } else {
                                        System.out.println("Nie mamy takiego przedmiotu");
                                        system();
                                    }
                                } else
                                    system();
                            case 3:
                                consumerSummer.rentWarehouse(personAdamNowak, LocalDate.of(year, month, day));
                                consumerSummer.grandPermission(personAdamNowak);
                                System.out.println("Możemy wlożuc przedmiot. 1 - Tak, 2 - Nie");
                                int out3 = scanner.nextInt();
                                if(out3 == 1) {
                                    System.out.println("Jakij to bendzie przedmiot: " + "\n" +
                                            "1. " + winterTire.toString() + "\n" +
                                            "2. " + summerTire.toString() + "\n" +
                                            "3. " + chair.toString() + "\n" +
                                            "Prosze wpisacz numer: ");
                                    if(scanner.nextInt() == 1) {
                                        consumerSummer.addItems(personAdamNowak, winterTire);
                                    } else if (scanner.nextInt() == 2) {
                                        consumerSummer.addItems(personAdamNowak, summerTire);
                                    } else if (scanner.nextInt() == 3) {
                                        consumerSummer.addItems(personAdamNowak, chair);
                                    } else {
                                        System.out.println("Nie mamy takiego przedmiotu");
                                        system();
                                    }
                                } else
                                    system();
                            case 4:
                                consumerSummer.rentWarehouse(personRobertLewandowski, LocalDate.of(year, month, day));
                                consumerSummer.grandPermission(personRobertLewandowski);
                                System.out.println("Możemy wlożuc przedmiot. 1 - Tak, 2 - Nie");
                                int out4 = scanner.nextInt();
                                if(out4 == 1) {
                                    System.out.println("Jakij to bendzie przedmiot: " + "\n" +
                                            "1. " + winterTire.toString() + "\n" +
                                            "2. " + summerTire.toString() + "\n" +
                                            "3. " + chair.toString() + "\n" +
                                            "Prosze wpisacz numer: ");
                                    if(scanner.nextInt() == 1) {
                                        consumerSummer.addItems(personRobertLewandowski, winterTire);
                                    } else if (scanner.nextInt() == 2) {
                                        consumerSummer.addItems(personRobertLewandowski, summerTire);
                                    } else if (scanner.nextInt() == 3) {
                                        consumerSummer.addItems(personRobertLewandowski, chair);
                                    } else {
                                        System.out.println("Nie mamy takiego przedmiotu");
                                        system();
                                    }
                                } else
                                    system();
                            case 5:
                                consumerSummer.rentWarehouse(personMaciejKowalczyk, LocalDate.of(year, month, day));
                                consumerSummer.grandPermission(personMaciejKowalczyk);
                                System.out.println("Możemy wlożuc przedmiot. 1 - Tak, 2 - Nie");
                                int out5 = scanner.nextInt();
                                if(out5 == 1) {
                                    System.out.println("Jakij to bendzie przedmiot: " + "\n" +
                                            "1. " + winterTire.toString() + "\n" +
                                            "2. " + summerTire.toString() + "\n" +
                                            "3. " + chair.toString() + "\n" +
                                            "Prosze wpisacz numer: ");
                                    if(scanner.nextInt() == 1) {
                                        consumerSummer.addItems(personMaciejKowalczyk, winterTire);
                                    } else if (scanner.nextInt() == 2) {
                                        consumerSummer.addItems(personMaciejKowalczyk, summerTire);
                                    } else if (scanner.nextInt() == 3) {
                                        consumerSummer.addItems(personMaciejKowalczyk, chair);
                                    } else {
                                        System.out.println("Nie mamy takiego przedmiotu");
                                        system();
                                    }
                                } else
                                    system();
                            default:
                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                system();
                        }
                        break;
                }
                break;
            case 2:
                System.out.println("Prosimy wybrac serwis po naprawie:" + "\n" +
                        "1. Specjalizowane miejsce po naprawie" + "\n" +
                        "2. Samodzielne miejsce serwisowe" + "\n" +
                        "Prosimy o wpisaniu konkretnej literki: ");
                switch (scanner.nextInt()) {
                    case 1:
                        System.out.println("Prosimy wybrac Specjalizowane miejsce po naprawie: " + "\n" +
                                "1. " + carServiceSpotDay.toString() + "\n" +
                                "2. " + carServiceSpotNight.toString() + "\n" +
                                "3. " + carServiceSpotOrange.toString() + "\n" +
                                "Prosimy o wpisaniu konkretnej literki: ");
                        switch (scanner.nextInt()) {
                            case 1:
                                System.out.println("Prosimy o wyboru osoby: " + "\n" +
                                        "1. " + personArinaStreaking.getName() + " " + personArinaStreaking.getFirstName() + "\n" +
                                        "2. " + personMarinaSmith.getName() + " " + personMarinaSmith.getFirstName() + "\n" +
                                        "3. " + personAdamNowak.getName() + " " + personAdamNowak.getFirstName() + "\n" +
                                        "4. " + personRobertLewandowski.getName() + " " + personRobertLewandowski.getFirstName() + "\n" +
                                        "5. " + personMaciejKowalczyk.getName() + " " + personMaciejKowalczyk.getFirstName() + "\n" +
                                        "Prosimy o wpisaniu konkretnej literki: ");
                                switch (scanner.nextInt()) {
                                    case 1:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                carServiceSpotDay.rentService(personArinaStreaking, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personArinaStreaking, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personArinaStreaking, amfibia);
                                                    }
                                                } else {
                                                    carServiceSpotDay.removalOfRent(personArinaStreaking);
                                                }
                                                system();
                                            case 2:
                                                carServiceSpotDay.rentService(personArinaStreaking, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personArinaStreaking, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personArinaStreaking, cityCar);
                                                    }
                                                } else {
                                                    carServiceSpotDay.removalOfRent(personArinaStreaking);
                                                }
                                                system();
                                            case 3:
                                                carServiceSpotDay.rentService(personArinaStreaking, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personArinaStreaking, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personArinaStreaking, motorbike);
                                                    }
                                                } else {
                                                    carServiceSpotDay.removalOfRent(personArinaStreaking);
                                                }
                                                system();
                                            case 4:
                                                carServiceSpotDay.rentService(personArinaStreaking, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personArinaStreaking, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personArinaStreaking, transportOffRoadCar);
                                                    }
                                                } else {
                                                    carServiceSpotDay.removalOfRent(personArinaStreaking);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                carServiceSpotDay.rentService(personMarinaSmith, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMarinaSmith, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMarinaSmith, amfibia);
                                                    }
                                                } else {
                                                    carServiceSpotDay.removalOfRent(personMarinaSmith);
                                                }
                                                system();
                                            case 2:
                                                carServiceSpotDay.rentService(personMarinaSmith, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMarinaSmith, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMarinaSmith, cityCar);
                                                    }
                                                } else {
                                                    carServiceSpotDay.removalOfRent(personMarinaSmith);
                                                }
                                                system();
                                            case 3:
                                                carServiceSpotDay.rentService(personMarinaSmith, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMarinaSmith, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMarinaSmith, motorbike);
                                                    }
                                                } else {
                                                    carServiceSpotDay.removalOfRent(personMarinaSmith);
                                                }
                                                system();
                                            case 4:
                                                carServiceSpotDay.rentService(personMarinaSmith, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMarinaSmith, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMarinaSmith, transportOffRoadCar);
                                                    }
                                                } else {
                                                    carServiceSpotDay.removalOfRent(personMarinaSmith);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;
                                    case 3:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                carServiceSpotDay.rentService(personAdamNowak, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personAdamNowak, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personAdamNowak, amfibia);
                                                    }
                                                } else {
                                                    carServiceSpotDay.removalOfRent(personAdamNowak);
                                                }
                                                system();
                                            case 2:
                                                carServiceSpotDay.rentService(personAdamNowak, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personAdamNowak, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personAdamNowak, cityCar);
                                                    }
                                                } else {
                                                    carServiceSpotDay.removalOfRent(personAdamNowak);
                                                }
                                                system();
                                            case 3:
                                                carServiceSpotDay.rentService(personAdamNowak, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personAdamNowak, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personAdamNowak, motorbike);
                                                    }
                                                } else {
                                                    carServiceSpotDay.removalOfRent(personAdamNowak);
                                                }
                                                system();
                                            case 4:
                                                carServiceSpotDay.rentService(personAdamNowak, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personAdamNowak, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personAdamNowak, transportOffRoadCar);
                                                    }
                                                } else {
                                                    carServiceSpotDay.removalOfRent(personAdamNowak);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                                break;
                                        }
                                        break;
                                    case 4:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                carServiceSpotDay.rentService(personRobertLewandowski, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personRobertLewandowski, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personRobertLewandowski, amfibia);
                                                    }
                                                } else {
                                                    carServiceSpotDay.removalOfRent(personRobertLewandowski);
                                                }
                                                system();
                                            case 2:
                                                carServiceSpotDay.rentService(personRobertLewandowski, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personRobertLewandowski, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personRobertLewandowski, cityCar);
                                                    }
                                                } else {
                                                    carServiceSpotDay.removalOfRent(personRobertLewandowski);
                                                }
                                                system();
                                            case 3:
                                                carServiceSpotDay.rentService(personRobertLewandowski, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personRobertLewandowski, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personRobertLewandowski, motorbike);
                                                    }
                                                } else {
                                                    carServiceSpotDay.removalOfRent(personRobertLewandowski);
                                                }
                                                system();
                                            case 4:
                                                carServiceSpotDay.rentService(personRobertLewandowski, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personRobertLewandowski, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personRobertLewandowski, transportOffRoadCar);
                                                    }
                                                } else {
                                                    carServiceSpotDay.removalOfRent(personRobertLewandowski);
                                                }
                                                system();;
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;
                                    case 5:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                carServiceSpotDay.rentService(personMaciejKowalczyk, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMaciejKowalczyk, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMaciejKowalczyk, amfibia);
                                                    }
                                                } else {
                                                    carServiceSpotDay.removalOfRent(personMaciejKowalczyk);
                                                }
                                                system();
                                            case 2:
                                                carServiceSpotDay.rentService(personMaciejKowalczyk, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMaciejKowalczyk, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMaciejKowalczyk, cityCar);
                                                    }
                                                } else {
                                                    carServiceSpotDay.removalOfRent(personMaciejKowalczyk);
                                                }
                                                system();
                                            case 3:
                                                carServiceSpotDay.rentService(personMaciejKowalczyk, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMaciejKowalczyk, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMaciejKowalczyk, motorbike);
                                                    }
                                                } else {
                                                    carServiceSpotDay.removalOfRent(personMaciejKowalczyk);
                                                }
                                                system();
                                            case 4:
                                                carServiceSpotDay.rentService(personMaciejKowalczyk, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMaciejKowalczyk, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMaciejKowalczyk, transportOffRoadCar);
                                                    }
                                                } else {
                                                    carServiceSpotDay.removalOfRent(personMaciejKowalczyk);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;
                                }
                                break;
                            case 2:
                                System.out.println("Prosimy o wyboru osoby: " + "\n" +
                                        "1. " + personArinaStreaking.getName() + " " + personArinaStreaking.getFirstName() + "\n" +
                                        "2. " + personMarinaSmith.getName() + " " + personMarinaSmith.getFirstName() + "\n" +
                                        "3. " + personAdamNowak.getName() + " " + personAdamNowak.getFirstName() + "\n" +
                                        "4. " + personRobertLewandowski.getName() + " " + personRobertLewandowski.getFirstName() + "\n" +
                                        "5. " + personMaciejKowalczyk.getName() + " " + personMaciejKowalczyk.getFirstName() + "\n" +
                                        "Prosimy o wpisaniu konkretnej literki: ");
                                switch (scanner.nextInt()) {
                                    case 1:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                carServiceSpotNight.rentService(personArinaStreaking, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personArinaStreaking, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personArinaStreaking, amfibia);
                                                    }
                                                } else {
                                                    carServiceSpotNight.removalOfRent(personArinaStreaking);
                                                }
                                                system();
                                            case 2:
                                                carServiceSpotNight.rentService(personArinaStreaking, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personArinaStreaking, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personArinaStreaking, cityCar);
                                                    }
                                                } else {
                                                    carServiceSpotNight.removalOfRent(personArinaStreaking);
                                                }
                                                system();
                                            case 3:
                                                carServiceSpotNight.rentService(personArinaStreaking, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personArinaStreaking, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personArinaStreaking, motorbike);
                                                    }
                                                } else {
                                                    carServiceSpotNight.removalOfRent(personArinaStreaking);
                                                }
                                                system();
                                            case 4:
                                                carServiceSpotNight.rentService(personArinaStreaking, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personArinaStreaking, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personArinaStreaking, transportOffRoadCar);
                                                    }
                                                } else {
                                                    carServiceSpotNight.removalOfRent(personArinaStreaking);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                carServiceSpotNight.rentService(personMarinaSmith, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMarinaSmith, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMarinaSmith, amfibia);
                                                    }
                                                } else {
                                                    carServiceSpotNight.removalOfRent(personMarinaSmith);
                                                }
                                                system();
                                            case 2:
                                                carServiceSpotNight.rentService(personMarinaSmith, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMarinaSmith, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMarinaSmith, cityCar);
                                                    }
                                                } else {
                                                    carServiceSpotNight.removalOfRent(personMarinaSmith);
                                                }
                                                system();
                                            case 3:
                                                carServiceSpotNight.rentService(personMarinaSmith, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMarinaSmith, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMarinaSmith, motorbike);
                                                    }
                                                } else {
                                                    carServiceSpotNight.removalOfRent(personMarinaSmith);
                                                }
                                                system();
                                            case 4:
                                                carServiceSpotNight.rentService(personMarinaSmith, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMarinaSmith, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMarinaSmith, transportOffRoadCar);
                                                    }
                                                } else {
                                                    carServiceSpotNight.removalOfRent(personMarinaSmith);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;
                                    case 3:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                carServiceSpotNight.rentService(personAdamNowak, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personAdamNowak, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personAdamNowak, amfibia);
                                                    }
                                                } else {
                                                    carServiceSpotNight.removalOfRent(personAdamNowak);
                                                }
                                                system();
                                            case 2:
                                                carServiceSpotNight.rentService(personAdamNowak, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personAdamNowak, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personAdamNowak, cityCar);
                                                    }
                                                } else {
                                                    carServiceSpotNight.removalOfRent(personAdamNowak);
                                                }
                                                system();
                                            case 3:
                                                carServiceSpotNight.rentService(personAdamNowak, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personAdamNowak, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personAdamNowak, motorbike);
                                                    }
                                                } else {
                                                    carServiceSpotNight.removalOfRent(personAdamNowak);
                                                }
                                                system();
                                            case 4:
                                                carServiceSpotNight.rentService(personAdamNowak, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personAdamNowak, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personAdamNowak, transportOffRoadCar);
                                                    }
                                                } else {
                                                    carServiceSpotNight.removalOfRent(personAdamNowak);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;
                                    case 4:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                carServiceSpotNight.rentService(personRobertLewandowski, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personRobertLewandowski, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personRobertLewandowski, amfibia);
                                                    }
                                                } else {
                                                    carServiceSpotNight.removalOfRent(personRobertLewandowski);
                                                }
                                                system();
                                            case 2:
                                                carServiceSpotNight.rentService(personRobertLewandowski, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personRobertLewandowski, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personRobertLewandowski, cityCar);
                                                    }
                                                } else {
                                                    carServiceSpotNight.removalOfRent(personRobertLewandowski);
                                                }
                                                system();
                                            case 3:
                                                carServiceSpotNight.rentService(personRobertLewandowski, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personRobertLewandowski, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personRobertLewandowski, motorbike);
                                                    }
                                                } else {
                                                    carServiceSpotNight.removalOfRent(personRobertLewandowski);
                                                }
                                                system();
                                            case 4:
                                                carServiceSpotNight.rentService(personRobertLewandowski, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personRobertLewandowski, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personRobertLewandowski, transportOffRoadCar);
                                                    }
                                                } else {
                                                    carServiceSpotNight.removalOfRent(personRobertLewandowski);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;
                                    case 5:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                carServiceSpotNight.rentService(personMaciejKowalczyk, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMaciejKowalczyk, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMaciejKowalczyk, amfibia);
                                                    }
                                                } else {
                                                    carServiceSpotNight.removalOfRent(personMaciejKowalczyk);
                                                }
                                                system();
                                            case 2:
                                                carServiceSpotNight.rentService(personMaciejKowalczyk, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMaciejKowalczyk, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMaciejKowalczyk, cityCar);
                                                    }
                                                } else {
                                                    carServiceSpotNight.removalOfRent(personMaciejKowalczyk);
                                                }
                                                system();
                                            case 3:
                                                carServiceSpotNight.rentService(personMaciejKowalczyk, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMaciejKowalczyk, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMaciejKowalczyk, motorbike);
                                                    }
                                                } else {
                                                    carServiceSpotNight.removalOfRent(personMaciejKowalczyk);
                                                }
                                                system();
                                            case 4:
                                                carServiceSpotNight.rentService(personMaciejKowalczyk, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMaciejKowalczyk, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMaciejKowalczyk, transportOffRoadCar);
                                                    }
                                                } else {
                                                    carServiceSpotNight.removalOfRent(personMaciejKowalczyk);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;
                                }
                                break;
                            case 3:
                                System.out.println("Prosimy o wyboru osoby: " + "\n" +
                                        "1. " + personArinaStreaking.getName() + " " + personArinaStreaking.getFirstName() + "\n" +
                                        "2. " + personMarinaSmith.getName() + " " + personMarinaSmith.getFirstName() + "\n" +
                                        "3. " + personAdamNowak.getName() + " " + personAdamNowak.getFirstName() + "\n" +
                                        "4. " + personRobertLewandowski.getName() + " " + personRobertLewandowski.getFirstName() + "\n" +
                                        "5. " + personMaciejKowalczyk.getName() + " " + personMaciejKowalczyk.getFirstName() + "\n" +
                                        "Prosimy o wpisaniu konkretnej literki: ");
                                switch (scanner.nextInt()) {
                                    case 1:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                carServiceSpotOrange.rentService(personArinaStreaking, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personArinaStreaking, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personArinaStreaking, amfibia);
                                                    }
                                                } else {
                                                    carServiceSpotOrange.removalOfRent(personArinaStreaking);
                                                }
                                                system();
                                            case 2:
                                                carServiceSpotOrange.rentService(personArinaStreaking, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personArinaStreaking, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personArinaStreaking, cityCar);
                                                    }
                                                } else {
                                                    carServiceSpotOrange.removalOfRent(personArinaStreaking);
                                                }
                                                system();
                                            case 3:
                                                carServiceSpotOrange.rentService(personArinaStreaking, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personArinaStreaking, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personArinaStreaking, motorbike);
                                                    }
                                                } else {
                                                    carServiceSpotOrange.removalOfRent(personArinaStreaking);
                                                }
                                                system();
                                            case 4:
                                                carServiceSpotOrange.rentService(personArinaStreaking, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personArinaStreaking, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personArinaStreaking, transportOffRoadCar);
                                                    }
                                                } else {
                                                    carServiceSpotOrange.removalOfRent(personArinaStreaking);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                carServiceSpotOrange.rentService(personMarinaSmith, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMarinaSmith, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMarinaSmith, amfibia);
                                                    }
                                                } else {
                                                    carServiceSpotOrange.removalOfRent(personMarinaSmith);
                                                }
                                                system();
                                            case 2:
                                                carServiceSpotOrange.rentService(personMarinaSmith, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMarinaSmith, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMarinaSmith, cityCar);
                                                    }
                                                } else {
                                                    carServiceSpotOrange.removalOfRent(personMarinaSmith);
                                                }
                                                system();
                                            case 3:
                                                carServiceSpotOrange.rentService(personMarinaSmith, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMarinaSmith, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMarinaSmith, motorbike);
                                                    }
                                                } else {
                                                    carServiceSpotOrange.removalOfRent(personMarinaSmith);
                                                }
                                                system();
                                            case 4:
                                                carServiceSpotOrange.rentService(personMarinaSmith, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMarinaSmith, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMarinaSmith, transportOffRoadCar);
                                                    }
                                                } else {
                                                    carServiceSpotOrange.removalOfRent(personMarinaSmith);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;
                                    case 3:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                carServiceSpotOrange.rentService(personAdamNowak, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personAdamNowak, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personAdamNowak, amfibia);
                                                    }
                                                } else {
                                                    carServiceSpotOrange.removalOfRent(personAdamNowak);
                                                }
                                                system();
                                            case 2:
                                                carServiceSpotOrange.rentService(personAdamNowak, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personAdamNowak, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personAdamNowak, cityCar);
                                                    }
                                                } else {
                                                    carServiceSpotOrange.removalOfRent(personAdamNowak);
                                                }
                                                system();
                                            case 3:
                                                carServiceSpotOrange.rentService(personAdamNowak, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personAdamNowak, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personAdamNowak, motorbike);
                                                    }
                                                } else {
                                                    carServiceSpotOrange.removalOfRent(personAdamNowak);
                                                }
                                                system();
                                            case 4:
                                                carServiceSpotOrange.rentService(personAdamNowak, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personAdamNowak, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personAdamNowak, transportOffRoadCar);
                                                    }
                                                } else {
                                                    carServiceSpotOrange.removalOfRent(personAdamNowak);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;
                                    case 4:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                carServiceSpotOrange.rentService(personRobertLewandowski, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personRobertLewandowski, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personRobertLewandowski, amfibia);
                                                    }
                                                } else {
                                                    carServiceSpotOrange.removalOfRent(personRobertLewandowski);
                                                }
                                                system();
                                            case 2:
                                                carServiceSpotOrange.rentService(personRobertLewandowski, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personRobertLewandowski, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personRobertLewandowski, cityCar);
                                                    }
                                                } else {
                                                    carServiceSpotOrange.removalOfRent(personRobertLewandowski);
                                                }
                                                system();
                                            case 3:
                                                carServiceSpotOrange.rentService(personRobertLewandowski, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personRobertLewandowski, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personRobertLewandowski, motorbike);
                                                    }
                                                } else {
                                                    carServiceSpotOrange.removalOfRent(personRobertLewandowski);
                                                }
                                                system();
                                            case 4:
                                                carServiceSpotOrange.rentService(personRobertLewandowski, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personRobertLewandowski, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personRobertLewandowski, transportOffRoadCar);
                                                    }
                                                } else {
                                                    carServiceSpotOrange.removalOfRent(personRobertLewandowski);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                                break;
                                        }
                                        break;
                                    case 5:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                carServiceSpotOrange.rentService(personMaciejKowalczyk, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMaciejKowalczyk, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMaciejKowalczyk, amfibia);
                                                    }
                                                } else {
                                                    carServiceSpotOrange.removalOfRent(personMaciejKowalczyk);
                                                }
                                                system();
                                            case 2:
                                                carServiceSpotOrange.rentService(personMaciejKowalczyk, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMaciejKowalczyk, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMaciejKowalczyk, cityCar);
                                                    }
                                                } else {
                                                    carServiceSpotOrange.removalOfRent(personMaciejKowalczyk);
                                                }
                                                system();
                                            case 3:
                                                carServiceSpotOrange.rentService(personMaciejKowalczyk, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMaciejKowalczyk, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMaciejKowalczyk, motorbike);
                                                    }
                                                } else {
                                                    carServiceSpotOrange.removalOfRent(personMaciejKowalczyk);
                                                }
                                                system();
                                            case 4:
                                                carServiceSpotOrange.rentService(personMaciejKowalczyk, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMaciejKowalczyk, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMaciejKowalczyk, transportOffRoadCar);
                                                    }
                                                } else {
                                                    carServiceSpotOrange.removalOfRent(personMaciejKowalczyk);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;
                                }
                                break;
                        }
                        break;

                    case 2:
                        System.out.println("Prosimy wybrac Samodzielne miejsce serwisowe: " + "\n" +
                                "1. " + independentCarServiceSpotHands + "\n" +
                                "2. " + independentCarServiceSpotHead + "\n" +
                                "3. " + independentCarServiceSpotBlack + "\n" +
                                "Prosimy o wpisaniu konkretnej literki: ");

                        switch (scanner.nextInt()) {
                            case 1:
                                System.out.println("Prosimy o wyboru osoby: " + "\n" +
                                        "1. " + personArinaStreaking.getName() + " " + personArinaStreaking.getFirstName() + "\n" +
                                        "2. " + personMarinaSmith.getName() + " " + personMarinaSmith.getFirstName() + "\n" +
                                        "3. " + personAdamNowak.getName() + " " + personAdamNowak.getFirstName() + "\n" +
                                        "4. " + personRobertLewandowski.getName() + " " + personRobertLewandowski.getFirstName() + "\n" +
                                        "5. " + personMaciejKowalczyk.getName() + " " + personMaciejKowalczyk.getFirstName() + "\n" +
                                        "Prosimy o wpisaniu konkretnej literki: ");
                                switch (scanner.nextInt()) {
                                    case 1:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                independentCarServiceSpotHands.rentService(personArinaStreaking, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personArinaStreaking, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personArinaStreaking, amfibia);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHands.removalOfRent(personArinaStreaking);
                                                }
                                                system();
                                            case 2:
                                                independentCarServiceSpotHands.rentService(personArinaStreaking, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personArinaStreaking, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personArinaStreaking, cityCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHands.removalOfRent(personArinaStreaking);
                                                }
                                                system();
                                            case 3:
                                                independentCarServiceSpotHands.rentService(personArinaStreaking, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personArinaStreaking, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personArinaStreaking, motorbike);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHands.removalOfRent(personArinaStreaking);
                                                }
                                                system();
                                            case 4:
                                                independentCarServiceSpotHands.rentService(personArinaStreaking, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personArinaStreaking, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personArinaStreaking, transportOffRoadCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHands.removalOfRent(personArinaStreaking);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                independentCarServiceSpotHands.rentService(personMarinaSmith, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMarinaSmith, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMarinaSmith, amfibia);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHands.removalOfRent(personMarinaSmith);
                                                }
                                                system();
                                            case 2:
                                                independentCarServiceSpotHands.rentService(personMarinaSmith, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMarinaSmith, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMarinaSmith, cityCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHands.removalOfRent(personMarinaSmith);
                                                }
                                                system();
                                            case 3:
                                                independentCarServiceSpotHands.rentService(personMarinaSmith, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMarinaSmith, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMarinaSmith, motorbike);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHands.removalOfRent(personMarinaSmith);
                                                }
                                                system();
                                            case 4:
                                                independentCarServiceSpotHands.rentService(personMarinaSmith, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMarinaSmith, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMarinaSmith, transportOffRoadCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHands.removalOfRent(personMarinaSmith);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;
                                    case 3:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                independentCarServiceSpotHands.rentService(personAdamNowak, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personAdamNowak, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personAdamNowak, amfibia);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHands.removalOfRent(personAdamNowak);
                                                }
                                                system();
                                            case 2:
                                                independentCarServiceSpotHands.rentService(personAdamNowak, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personAdamNowak, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personAdamNowak, cityCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHands.removalOfRent(personAdamNowak);
                                                }
                                                system();
                                            case 3:
                                                independentCarServiceSpotHands.rentService(personAdamNowak, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personAdamNowak, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personAdamNowak, motorbike);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHands.removalOfRent(personAdamNowak);
                                                }
                                                system();
                                            case 4:
                                                independentCarServiceSpotHands.rentService(personAdamNowak, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personAdamNowak, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personAdamNowak, transportOffRoadCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHands.removalOfRent(personAdamNowak);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;
                                    case 4:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                independentCarServiceSpotHands.rentService(personRobertLewandowski, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personRobertLewandowski, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personRobertLewandowski, amfibia);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHands.removalOfRent(personRobertLewandowski);
                                                }
                                                system();
                                            case 2:
                                                independentCarServiceSpotHands.rentService(personRobertLewandowski, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personRobertLewandowski, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personRobertLewandowski, cityCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHands.removalOfRent(personRobertLewandowski);
                                                }
                                                system();
                                            case 3:
                                                independentCarServiceSpotHands.rentService(personRobertLewandowski, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personRobertLewandowski, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personRobertLewandowski, motorbike);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHands.removalOfRent(personRobertLewandowski);
                                                }
                                                system();
                                            case 4:
                                                independentCarServiceSpotHands.rentService(personRobertLewandowski, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personRobertLewandowski, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personRobertLewandowski, transportOffRoadCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHands.removalOfRent(personRobertLewandowski);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;
                                    case 5:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                independentCarServiceSpotHands.rentService(personMaciejKowalczyk, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMaciejKowalczyk, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMaciejKowalczyk, amfibia);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHands.removalOfRent(personMaciejKowalczyk);
                                                }
                                                system();
                                            case 2:
                                                independentCarServiceSpotHands.rentService(personMaciejKowalczyk, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMaciejKowalczyk, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMaciejKowalczyk, cityCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHands.removalOfRent(personMaciejKowalczyk);
                                                }
                                                system();
                                            case 3:
                                                independentCarServiceSpotHands.rentService(personMaciejKowalczyk, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMaciejKowalczyk, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMaciejKowalczyk, motorbike);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHands.removalOfRent(personMaciejKowalczyk);
                                                }
                                                system();
                                            case 4:
                                                independentCarServiceSpotHands.rentService(personMaciejKowalczyk, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMaciejKowalczyk, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMaciejKowalczyk, transportOffRoadCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHands.removalOfRent(personMaciejKowalczyk);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;

                                }
                                break;
                            case 2:
                                System.out.println("Prosimy o wyboru osoby: " + "\n" +
                                        "1. " + personArinaStreaking.getName() + " " + personArinaStreaking.getFirstName() + "\n" +
                                        "2. " + personMarinaSmith.getName() + " " + personMarinaSmith.getFirstName() + "\n" +
                                        "3. " + personAdamNowak.getName() + " " + personAdamNowak.getFirstName() + "\n" +
                                        "4. " + personRobertLewandowski.getName() + " " + personRobertLewandowski.getFirstName() + "\n" +
                                        "5. " + personMaciejKowalczyk.getName() + " " + personMaciejKowalczyk.getFirstName() + "\n" +
                                        "Prosimy o wpisaniu konkretnej literki: ");
                                switch (scanner.nextInt()) {
                                    case 1:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                independentCarServiceSpotHead.rentService(personArinaStreaking, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personArinaStreaking, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personArinaStreaking, amfibia);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHead.removalOfRent(personArinaStreaking);
                                                }
                                                system();
                                            case 2:
                                                independentCarServiceSpotHead.rentService(personArinaStreaking, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personArinaStreaking, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personArinaStreaking, cityCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHead.removalOfRent(personArinaStreaking);
                                                }
                                                system();
                                            case 3:
                                                independentCarServiceSpotHead.rentService(personArinaStreaking, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personArinaStreaking, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personArinaStreaking, motorbike);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHead.removalOfRent(personArinaStreaking);
                                                }
                                                system();
                                            case 4:
                                                independentCarServiceSpotHead.rentService(personArinaStreaking, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personArinaStreaking, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personArinaStreaking, transportOffRoadCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHead.removalOfRent(personArinaStreaking);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                independentCarServiceSpotHead.rentService(personMarinaSmith, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMarinaSmith, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMarinaSmith, amfibia);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHead.removalOfRent(personMarinaSmith);
                                                }
                                                system();
                                            case 2:
                                                independentCarServiceSpotHead.rentService(personMarinaSmith, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMarinaSmith, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMarinaSmith, cityCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHead.removalOfRent(personMarinaSmith);
                                                }
                                                system();
                                            case 3:
                                                independentCarServiceSpotHead.rentService(personMarinaSmith, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMarinaSmith, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMarinaSmith, motorbike);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHead.removalOfRent(personMarinaSmith);
                                                }
                                                system();
                                            case 4:
                                                independentCarServiceSpotHead.rentService(personMarinaSmith, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMarinaSmith, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMarinaSmith, transportOffRoadCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHead.removalOfRent(personMarinaSmith);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;
                                    case 3:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                independentCarServiceSpotHead.rentService(personAdamNowak, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personAdamNowak, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personAdamNowak, amfibia);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHead.removalOfRent(personAdamNowak);
                                                }
                                                system();
                                            case 2:
                                                independentCarServiceSpotHead.rentService(personAdamNowak, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personAdamNowak, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personAdamNowak, cityCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHead.removalOfRent(personAdamNowak);
                                                }
                                                system();
                                            case 3:
                                                independentCarServiceSpotHead.rentService(personAdamNowak, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personAdamNowak, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personAdamNowak, motorbike);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHead.removalOfRent(personAdamNowak);
                                                }
                                                system();
                                            case 4:
                                                independentCarServiceSpotHead.rentService(personAdamNowak, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personAdamNowak, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personAdamNowak, transportOffRoadCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHead.removalOfRent(personAdamNowak);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;
                                    case 4:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                independentCarServiceSpotHead.rentService(personRobertLewandowski, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personRobertLewandowski, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personRobertLewandowski, amfibia);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHead.removalOfRent(personRobertLewandowski);
                                                }
                                                system();
                                            case 2:
                                                independentCarServiceSpotHead.rentService(personRobertLewandowski, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personRobertLewandowski, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personRobertLewandowski, cityCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHead.removalOfRent(personRobertLewandowski);
                                                }
                                                system();
                                            case 3:
                                                independentCarServiceSpotHead.rentService(personRobertLewandowski, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personRobertLewandowski, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personRobertLewandowski, motorbike);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHead.removalOfRent(personRobertLewandowski);
                                                }
                                                system();
                                            case 4:
                                                independentCarServiceSpotHead.rentService(personRobertLewandowski, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personRobertLewandowski, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personRobertLewandowski, transportOffRoadCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHead.removalOfRent(personRobertLewandowski);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;
                                    case 5:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                independentCarServiceSpotHead.rentService(personMaciejKowalczyk, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMaciejKowalczyk, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMaciejKowalczyk, amfibia);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHead.removalOfRent(personMaciejKowalczyk);
                                                }
                                                system();
                                            case 2:
                                                independentCarServiceSpotHead.rentService(personMaciejKowalczyk, cityCar, LocalDate.of(year, month, day));
                                                 System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMaciejKowalczyk, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMaciejKowalczyk, cityCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHead.removalOfRent(personMaciejKowalczyk);
                                                }
                                                system();
                                            case 3:
                                                independentCarServiceSpotHead.rentService(personMaciejKowalczyk, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMaciejKowalczyk, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMaciejKowalczyk, motorbike);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHead.removalOfRent(personMaciejKowalczyk);
                                                }
                                                system();
                                            case 4:
                                                independentCarServiceSpotHead.rentService(personMaciejKowalczyk, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMaciejKowalczyk, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMaciejKowalczyk, transportOffRoadCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotHead.removalOfRent(personMaciejKowalczyk);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;
                                }
                                break;
                            case 3:
                                System.out.println("Prosimy o wyboru osoby: " + "\n" +
                                        "1. " + personArinaStreaking.getName() + " " + personArinaStreaking.getFirstName() + "\n" +
                                        "2. " + personMarinaSmith.getName() + " " + personMarinaSmith.getFirstName() + "\n" +
                                        "3. " + personAdamNowak.getName() + " " + personAdamNowak.getFirstName() + "\n" +
                                        "4. " + personRobertLewandowski.getName() + " " + personRobertLewandowski.getFirstName() + "\n" +
                                        "5. " + personMaciejKowalczyk.getName() + " " + personMaciejKowalczyk.getFirstName() + "\n" +
                                        "Prosimy o wpisaniu konkretnej literki: ");
                                switch (scanner.nextInt()) {
                                    case 1:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                independentCarServiceSpotBlack.rentService(personArinaStreaking, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personArinaStreaking, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personArinaStreaking, amfibia);
                                                    }
                                                } else {
                                                    independentCarServiceSpotBlack.removalOfRent(personArinaStreaking);
                                                }
                                                system();
                                            case 2:
                                                independentCarServiceSpotBlack.rentService(personArinaStreaking, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2== 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personArinaStreaking, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personArinaStreaking, cityCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotBlack.removalOfRent(personArinaStreaking);
                                                }
                                                system();
                                            case 3:
                                                independentCarServiceSpotBlack.rentService(personArinaStreaking, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personArinaStreaking, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personArinaStreaking, motorbike);
                                                    }
                                                } else {
                                                    independentCarServiceSpotBlack.removalOfRent(personArinaStreaking);
                                                }
                                                system();
                                            case 4:
                                                independentCarServiceSpotBlack.rentService(personArinaStreaking, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personArinaStreaking, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personArinaStreaking, transportOffRoadCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotBlack.removalOfRent(personArinaStreaking);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                independentCarServiceSpotBlack.rentService(personMarinaSmith, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMarinaSmith, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMarinaSmith, amfibia);
                                                    }
                                                } else {
                                                    independentCarServiceSpotBlack.removalOfRent(personMarinaSmith);
                                                }
                                                system();
                                            case 2:
                                                independentCarServiceSpotBlack.rentService(personMarinaSmith, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMarinaSmith, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMarinaSmith, cityCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotBlack.removalOfRent(personMarinaSmith);
                                                }
                                                system();
                                            case 3:
                                                independentCarServiceSpotBlack.rentService(personMarinaSmith, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMarinaSmith, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMarinaSmith, motorbike);
                                                    }
                                                } else {
                                                    independentCarServiceSpotBlack.removalOfRent(personMarinaSmith);
                                                }
                                                system();
                                            case 4:
                                                independentCarServiceSpotBlack.rentService(personMarinaSmith, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMarinaSmith, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMarinaSmith, transportOffRoadCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotBlack.removalOfRent(personMarinaSmith);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;
                                    case 3:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                independentCarServiceSpotBlack.rentService(personAdamNowak, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personAdamNowak, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personAdamNowak, amfibia);
                                                    }
                                                } else {
                                                    independentCarServiceSpotBlack.removalOfRent(personAdamNowak);
                                                }
                                                system();
                                            case 2:
                                                independentCarServiceSpotBlack.rentService(personAdamNowak, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personAdamNowak, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personAdamNowak, cityCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotBlack.removalOfRent(personAdamNowak);
                                                }
                                                system();
                                            case 3:
                                                independentCarServiceSpotBlack.rentService(personAdamNowak, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personAdamNowak, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personAdamNowak, motorbike);
                                                    }
                                                } else {
                                                    independentCarServiceSpotBlack.removalOfRent(personAdamNowak);
                                                }
                                                system();
                                            case 4:
                                                independentCarServiceSpotBlack.rentService(personAdamNowak, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personAdamNowak, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personAdamNowak, transportOffRoadCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotBlack.removalOfRent(personAdamNowak);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;
                                    case 4:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                independentCarServiceSpotBlack.rentService(personRobertLewandowski, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personRobertLewandowski, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personRobertLewandowski, amfibia);
                                                    }
                                                } else {
                                                    independentCarServiceSpotBlack.removalOfRent(personRobertLewandowski);
                                                }
                                                system();
                                            case 2:
                                                independentCarServiceSpotBlack.rentService(personRobertLewandowski, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personRobertLewandowski, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personRobertLewandowski, cityCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotBlack.removalOfRent(personRobertLewandowski);
                                                }
                                                system();
                                            case 3:
                                                independentCarServiceSpotBlack.rentService(personRobertLewandowski, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personRobertLewandowski, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personRobertLewandowski, motorbike);
                                                    }
                                                } else {
                                                    independentCarServiceSpotBlack.removalOfRent(personRobertLewandowski);
                                                }
                                                system();
                                            case 4:
                                                independentCarServiceSpotBlack.rentService(personRobertLewandowski, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personRobertLewandowski, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personRobertLewandowski, transportOffRoadCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotBlack.removalOfRent(personRobertLewandowski);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;
                                    case 5:
                                        System.out.println("Prosimy o wyboru pojazdu: " + "\n" +
                                                "1. " + amfibia.getName() + "\n" +
                                                "2. " + cityCar.getName() + "\n" +
                                                "3. " + motorbike.getName() + "\n" +
                                                "4. " + transportOffRoadCar.getName() + "\n" +
                                                "Prosimy o wpisaniu konkretnej literki: ");
                                        switch (scanner.nextInt()) {
                                            case 1:
                                                independentCarServiceSpotBlack.rentService(personMaciejKowalczyk, amfibia, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out1 = scanner.nextInt();
                                                if(out1 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMaciejKowalczyk, amfibia);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMaciejKowalczyk, amfibia);
                                                    }
                                                } else {
                                                    independentCarServiceSpotBlack.removalOfRent(personMaciejKowalczyk);
                                                }
                                                system();
                                            case 2:
                                                independentCarServiceSpotBlack.rentService(personMaciejKowalczyk, cityCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out2 = scanner.nextInt();
                                                if(out2 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMaciejKowalczyk, cityCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMaciejKowalczyk, cityCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotBlack.removalOfRent(personMaciejKowalczyk);
                                                }
                                                system();
                                            case 3:
                                                independentCarServiceSpotBlack.rentService(personMaciejKowalczyk, motorbike, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out3 = scanner.nextInt();
                                                if(out3 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMaciejKowalczyk, motorbike);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMaciejKowalczyk, motorbike);
                                                    }
                                                } else {
                                                    independentCarServiceSpotBlack.removalOfRent(personMaciejKowalczyk);
                                                }
                                                system();
                                            case 4:
                                                independentCarServiceSpotBlack.rentService(personMaciejKowalczyk, transportOffRoadCar, LocalDate.of(year, month, day));
                                                System.out.println("Zostawiamy na parkingu? 1 - Tak 2 - Nie");
                                                int out4 = scanner.nextInt();
                                                if(out4 == 1){
                                                    System.out.println("Prosimy wybrac parking :" + "\n" +
                                                            "1. " + parkingSpotTriangle.toString() + "\n" +
                                                            "2. " + parkingSpotCircle.toString() + "\n" +
                                                            "Prosimy wpisac numerek: ");
                                                    if(scanner.nextInt() == 1){
                                                        parkingSpotTriangle.rentParkingSpot(personMaciejKowalczyk, transportOffRoadCar);
                                                    } else {
                                                        parkingSpotCircle.rentParkingSpot(personMaciejKowalczyk, transportOffRoadCar);
                                                    }
                                                } else {
                                                    independentCarServiceSpotBlack.removalOfRent(personMaciejKowalczyk);
                                                }
                                                system();
                                            default:
                                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                                system();
                                        }
                                        break;
                                }
                                break;
                            default:
                                System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                                system();
                        }
                        break;
                    default:
                        System.out.println("Przepraszamy, takiego numera nie mamy w liscie. Prosimy ponownie wpisc dane");
                        system();
                }
                break;
            default:
                System.out.println("Przepraszamy, takiego numera nie mamy w liscie");
                system();

        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.addWarehouse();
        try {
            main.system();
        } catch (TooManyThingsException e) {
            e.printStackTrace();
        }

    }
}
