public class BusEvent implements Event{
    Bus bus;
    int stop;
    String type;
    Stop arriving;
    public BusEvent(Bus bus, int stop){
        this.bus=bus;
        this.stop=stop;
        type=bus.getType();
        arriving= Simulation.stops[stop];
    }
    public void run(){
        /* The run method for BusEvent signifies a bus arriving at a stop. People first get off by calling removeRiderAtStop
        on the bus, and people are then boarded calling addRider on the bus and gettingOn on Stop. The number of people getting off and
        on are recorded and used to keep track of the time to schedule the next BusEvent, which is then done.
         */
        int numberGettingOff= bus.removeRiderAtStop(stop);
        double timeGettingOff= 2*numberGettingOff;
        int numberGettingOn=0;
        while(bus.isntFull()) {
            Rider passenger = arriving.gettingOn(bus);
            if (passenger != null) {
                numberGettingOn++;
                bus.addRider(passenger);
                Statistics.totalRidersBoarded++;
            }
            else{
                break;
            }
        }
        double timeGettingOn=3*numberGettingOn;
        double time=15;
        if (timeGettingOn+timeGettingOff>15) {
            time=timeGettingOn+timeGettingOff;
        }
        BusEvent nextEvent;
        /* This is where the type attribute of each bus comes into play. Standard buses go to every stop, so the BusEvent is just
        scheduled for every stop. Express buses skip quite a few. One note- could these be handled in a switch statement? Yes,
        but they're not. Hope that's not a problem, it still works.
         */
        if (type.equals("standard")){
            if (stop==29){
                nextEvent=new BusEvent(bus, 0);
            }
            else {
                nextEvent = new BusEvent(bus, stop + 1);
            }
        }
        else{
            if(stop==29){
                nextEvent= new BusEvent(bus, 0);
            }
            else if(stop==0){
                nextEvent= new BusEvent(bus, 1);
            }
            else if(stop==1){
                nextEvent= new BusEvent(bus, 4);
            }
            else if(stop==14){
                nextEvent= new BusEvent(bus, 15);
            }
            else if(stop==15){
                nextEvent= new BusEvent(bus, 16);
            }
            else if(stop==12){
                nextEvent= new BusEvent(bus, 14);
            }
            else if (stop==28){
                nextEvent= new BusEvent(bus, 29);
            }
            else
            {nextEvent= new BusEvent(bus, stop+4);
            }
        }
        Simulation.agenda.add(nextEvent, time+240);

    }

}
