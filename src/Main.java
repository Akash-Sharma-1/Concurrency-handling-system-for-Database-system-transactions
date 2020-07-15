import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {


    static volatile AtomicInteger lockondb=new AtomicInteger(-1);

     static boolean checkforlock()
    {
        return lockondb.compareAndSet(-1,1);
    }

     static void lock(int id)
    {
        lockondb.set(1);
    }

     static void unlock()
    {
        lockondb.set(-1);
    }

    public static void main(String[] args)
    {
        ArrayList<flight> flights=new ArrayList<flight>();
        ArrayList<passenger> passengers=new ArrayList<passenger>();

        flight f1=new flight(6,1);
        flight f2=new flight(4,2);
        flight f3=new flight(5,3);

        passenger p1=new passenger(1);
        passenger p2=new passenger(2);
        passenger p3=new passenger(3);

        flights.add(f1);
        flights.add(f2);
        flights.add(f3);
        passengers.add(p1);
        passengers.add(p2);
        passengers.add(p3);


        database d=new database(flights,passengers);
        manager m1=new manager(1,d);
        Thread t1 = new Thread(m1);
//        t1.start();
        manager m2=new manager(2,d);
        Thread t2 = new Thread(m2);

        manager m3=new manager(3,d);
        Thread t3 = new Thread(m3);


//        t2.start();
        ExecutorService pool = Executors.newFixedThreadPool(5);
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);

        pool.shutdown();


    }
}
