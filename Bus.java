public class Bus {
    int capacity=50;
    private N start;
    private int seatsTaken;
    public String type;
    public Bus(String type){
        seatsTaken=0;
        this.type=type;
    }
    //addRider takes a Rider and adds them to the linked list of Riders on the bus. Also increments statistics.
    public void addRider(Rider person){
        if (start==null) {
            start = new N(person, null);
            seatsTaken++;
            Statistics.totalRiderWait+=(Simulation.agenda.getCurrentTime()-person.getTimeArrived());
        }
        else {
            start = new N(person, start);
            seatsTaken++;
            Statistics.totalRiderWait+=(Simulation.agenda.getCurrentTime()-person.getTimeArrived());

        }
    }
    /* where addRider simply adds one rider at a time, removeRiderAtStop removes all the riders on the bus who are getting
    off at the stop. It returns an int to be used for time calculations in BusSim. The reason it puts these Riders in a queue is because
    I thought I might need them for some statistics, but ended up getting all the statistics needed from other means.
     */
    public int  removeRiderAtStop(int stop){
        Q2 leaving = new Q2();
        N temp= start;
        N trailer = start;
        if (start==null){
            return 0;
        }
        if (((Rider)start.getData()).getDestination()==stop){
            leaving.add(start.getData());
            start=start.getNext();
            temp=start;
            trailer=start;
        }
        while (temp!= null) {
            if (((Rider) (temp.getData())).getDestination() == stop) {
                // leaving.add(temp.getData());
                // trailer.setNext(temp.getNext());
                // temp = temp.getNext();
                if (temp != null) {
                    Statistics.ridersGotOff++;
                    Statistics.totalTravelTime += (Simulation.agenda.getCurrentTime() - ((Rider) (temp.getData())).getTimeArrived());
                    leaving.add(temp.getData());
                    trailer.setNext(temp.getNext());
                    temp = temp.getNext();

                }
            } else {
                trailer = temp;
                temp = temp.getNext();
            }
        }

        seatsTaken-=leaving.length();
        return leaving.length();

    }
    public String getType(){
        return this.type;
    }
    public void println(){
        N ptr=start;
        while (ptr!=null){
            System.out.println(((Rider)ptr.getData()).getDestination());
            ptr=ptr.getNext();
        }
    }
    public boolean isntFull(){
        return seatsTaken<50;
    }
    public int getSeatsTaken(){
        return seatsTaken;
    }

}