import java.util.Scanner;
public class Simulation{
    public static PQ agenda=new PQ();
    public static Stop[] stops= new Stop[30];
    public double interval;
    public static void main(String[] args){
        Scanner s = new Scanner (System.in);
        System.out.println("How many standard buses?");
        int stdBus= s.nextInt();
        System.out.println("How many express buses?");
        int expBus= s.nextInt();
        System.out.println("How long of a simulation?");
        double length=s.nextDouble();
        System.out.println("How often does a passenger arrive at any stop?");
        double interval= s.nextDouble();

        Stop rampB = new Stop(0, "Ramp B");
        Stop fourth = new Stop(1," 4th and Nicollet");
        Stop anderson= new Stop(2,"Anderson Hall");
        Stop jones= new Stop(3, "Jones Hall");
        Stop kasota = new Stop(4, "Kasota Circle");
        Stop eustis= new Stop(5, "Como and Eustis");
        Stop cleveland=new Stop(6, "Como and Cleveland");
        Stop snelling= new Stop(7, "Como and Snelling");
        Stop hamline = new Stop(8, "Como and Hamline");
        Stop dale= new Stop(9, "Maryland and Dale");
        Stop rice= new Stop(10, "Maryland and Rice");
        Stop lexington = new Stop(11, "Front and Lexington");
        Stop frontanddale= new Stop(12, "Front and Dale");
        Stop capitol= new Stop(13, "Capitol");
        Stop cedar= new Stop(14, "Cedar");
        Stop depot= new Stop(15,"Union Depot");
        Stop cedarb= new Stop(16, "Cedar");
        Stop capitolb= new Stop(17, "Capitol");
        Stop frontanddaleb= new Stop(18, "Front and Dale");
        Stop lexingtonb = new Stop(19, "Front and Lexington");
        Stop riceb= new Stop(20, "Maryland and Rice");
        Stop daleb= new Stop(21, "Maryland and Dale");
        Stop hamlineb = new Stop(22, "Como and Hamline");
        Stop snellingb= new Stop(23, "Como and Snelling");
        Stop clevelandb=new Stop(24, "Como and Cleveland");
        Stop eustisb= new Stop(25, "Como and Eustis");
        Stop kasotab = new Stop(26, "Kasota Circle");
        Stop jonesb= new Stop(27, "Jones Hall");
        Stop andersonb= new Stop(28,"Anderson Hall");
        Stop fourthb = new Stop(29," 4th and Nicollet");
        RiderEvent begin=new RiderEvent(interval);
        agenda.add(begin,0.0); //riders will begin being created
        int expCounter=0;
        int stdCounter=0;
        int startingStop=0;
        /* These following two while loops respond to input from the user asking how many of each type of bus to create,
         * the buses are added at different points on the route, because it is not realistic for something like rush hour to hit and all the buses are just
         * leaving the station.*/
        while(expCounter<expBus){
            Bus express=new Bus("express");
            BusEvent leaving= new BusEvent(express, startingStop);
            agenda.add(leaving, 0);
            expCounter++;
            if (startingStop!=24){startingStop+=8;}
            else{startingStop=0;}

        }
        startingStop=0;
        while(stdCounter<stdBus){
            Bus standard= new Bus("standard");
            BusEvent leaving= new BusEvent(standard, startingStop);
            agenda.add(leaving,0);
            stdCounter++;
            if (startingStop!=25){startingStop+=5;}
            else{startingStop=0;}
        }


        while(agenda.getCurrentTime() <= length){
            agenda.remove().run();
        }
        System.out.println("Total riders boarded: " + Statistics.totalRidersBoarded);
        System.out.println("Longest line was: " +Statistics.longestLine);
        System.out.println("Longest line occurred at stop "+Statistics.longestLineStop);
        System.out.println("Average rider wait: " + (Statistics.totalRiderWait/Statistics.totalRidersBoarded));
        System.out.println("Average rider travel time: " + Statistics.totalTravelTime/Statistics.ridersGotOff);
        System.out.println("Simulation finished");

    }
}