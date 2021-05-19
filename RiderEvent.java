public class RiderEvent implements Event{
    int[] stopSelect = {0, 0, 1, 1, 29, 29, 14, 14, 15, 15, 16, 16,2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28};
    double[] relativeTime={.75, .75, .5, .5, .5, .2, .2, .2, .2, 0, 0, -.2, -.2, -.2, -.2, -.5, -.5, -.5, -.75, -.75};
    double interval;
    public RiderEvent(double interval){
        this.interval=interval;
    }
    public void run(){
        double time=interval+(interval*relativeTime[(int)(Math.random()*20)]);
        int stop=stopSelect[(int)(Math.random() * (36))];
        /*when a RiderEvent is run, it creates a rider at a stop based on randomly picking an index of stopSelect, a weighted
        array towards downtown stops. The next RiderEvent is scheduled based on the interval input by the user, and a random relativeTime
        to add a degree of randomness to when a person "arrives" at a stop.
         */
        RiderEvent nextEvent= new RiderEvent(interval);
        Simulation.agenda.add(nextEvent, time);
        Rider next= new Rider(stop, Simulation.agenda.getCurrentTime());
        Simulation.stops[stop].addRider(next);



    }

}