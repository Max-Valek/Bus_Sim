public class Stop {
    int stopNumber;
    String stopName;
    Q2 express=new Q2();
    Q2 standard= new Q2();
    public Stop(int stopNumber, String stopName){
        this.stopNumber=stopNumber;
        this.stopName=stopName;
        Simulation.stops[stopNumber]=this;
    }
    /*addRider is called through RiderEvent.If the rider is at a stop an express bus comes to AND it is going to a stop the express bus
    goes to, only then are they added to the express queue. Otherwise, the person will be added to the standard queue.
     */
    public void addRider(Rider person) {
        if (person.getStartStation() % 4 == 0 || person.getStartStation() == 1 || person.getStartStation() == 29 || person.getStartStation() == 14 || person.getStartStation() == 15) {
            if (person.getDestination() % 4 == 0 || person.getDestination() == 1 || person.getDestination() == 29 || person.getDestination() == 14 || person.getDestination() == 15) {
                express.add(person);
                person.setTimeArrived(Simulation.agenda.getCurrentTime());
                if (express.length() > Statistics.longestLine) {
                    Statistics.longestLine = express.length();
                    Statistics.longestLineStop = stopNumber;
                }
            }
            else {
                standard.add(person);
                if (standard.length() > Statistics.longestLine) {
                    Statistics.longestLine = standard.length();
                    Statistics.longestLineStop = stopNumber;
                }
            }
        }
        else {
            standard.add(person);
            if (standard.length() > Statistics.longestLine) {
                Statistics.longestLine = standard.length();
                Statistics.longestLineStop = stopNumber;
            }
        }
    }
    /* gettingOn checks a bus arriving at a stop for its type. If it is an express bus, it removes and returns
    riders from the express bus queue. If it is a standard bus, it removes and returns riders from the standard queue.
     */
    public Rider gettingOn(Bus bus){
        if(bus.getType().equals("express")){
            return (Rider)express.remove();
        }
        else
            return (Rider)standard.remove();
    }

}
