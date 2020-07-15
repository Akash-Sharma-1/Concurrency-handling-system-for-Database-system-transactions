import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

class manager implements Runnable{
    static volatile AtomicInteger transactioncounter=new AtomicInteger(0);//completed trans
    volatile AtomicInteger counter;
    int id; // thread id
    database db;

    manager(int id,database d)
    {
        this.id=id;
        db=d;
        counter = new AtomicInteger(0);
    }


    @Override
    public void run() {
        //Each thread runs 2 or 3 or 5 transactions(operations)

        while(counter.get()<1)
        {
            //giving random choices and random values
            Random r=new Random();
            int option=r.nextInt(5);
            //while it gets the lock acquired on the database - it waits in the loop
            while(true)
            {
                if(Main.checkforlock()==true)
                {
                    System.out.println("Database locked by thread"+id+" transaction "+counter.get());

                    //complete the transaction by performing operations
                    System.out.println("operation "+option);
                    transaction t=new transaction();
                    t.data=db;

                    if(option==0)
                    {
                        int flightno=r.nextInt(3)+1;
                        int passno=r.nextInt(3)+1;
//                        System.out.println(flightno+""+passno);
                        t.reserve(flightno,passno);
                    }
                    if(option==1)
                    {
                        int flightno=r.nextInt(3)+1;
                        int passno=r.nextInt(3)+1;
                        t.cancel(flightno,passno);
                    }
                    if(option==2)
                    {
                        int passno=r.nextInt(3)+1;
                        t.myflights(passno);
                    }
                    if(option==3)
                    {
                        t.totalreservations();
                    }
                    if(option==4)
                    {
                        int flightno1=r.nextInt(3)+1;
                        int flightno2=r.nextInt(3)+1;
                        int passno=r.nextInt(3)+1;
                        while(flightno1==flightno2) flightno2=r.nextInt(3)+1;
                        t.transfer(flightno1,flightno2,passno);
                    }
                    System.out.println("Database unlocked by" +id+"transaction"+counter.get());
                    transactioncounter.getAndIncrement();
                    Main.unlock();
                    break; // leave and unlock the db
                    // add sleep time
                }
                else
                {
                    continue;
                }
            }
            counter.getAndIncrement();


        }
    }
}