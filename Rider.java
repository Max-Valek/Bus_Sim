public class Rider {
    private int startStation;
    private double timeArrived;
    private int destination;
    private int[] eastbound = {1,1,2,3,4,5,6,7,8,9,10,11,12,13,14,14,15,15};
    private int[] westbound= {16,16,17,18,19,20,21,22,23,24,25,26,27,28, 29,29,0,0};
    public Rider(int start, double timeArrived){
        startStation=start;
        this.timeArrived=timeArrived;
        int whereGoing=-1;
        /*when a rider is created from the RiderEvent, it has where it is to be delivered, and the time it arrived. Since
        Eastbound riders only go East and Westbound West, there are two arrays to choose a destination from, eastbound and westbound.
        However, riders cannot go backwards on their route. Since the arrays are so small, a random index generator will quickly pick a reasonable random
        stop for each rider.
         */
        while(whereGoing<=start && whereGoing!=0){
            if (start>-1 && start <15){
                whereGoing=eastbound[(int)(Math.random() * (18))];
            }
            else{
                whereGoing=westbound[(int)(Math.random() * (18))];
            }
        }
        this.destination=whereGoing;
        //System.out.println("try");

    }
    public int getStartStation(){
        return startStation;
    }
    public double getTimeArrived(){
        return timeArrived;
    }
    public void setTimeArrived(double time){
        timeArrived=time;
    }
    public int getDestination(){
        return destination;
    }
}