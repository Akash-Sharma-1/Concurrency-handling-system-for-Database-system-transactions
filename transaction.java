public class transaction {

    database data;

    public void reserve(int f, int i)
    {
        flight fl=null;
        //fl is the flight in which p passenger reserves
        for(flight x:data.db)
        {
            if(x.id==f)
            {
                fl=x;
            }
        }

        passenger p=null;
        for(passenger x:data.plist)
        {
            if(x.pid==i)
            {
                p=x;
            }
        }

        if(fl.reserve_seat(p)==true)
        {
            p.addtoflight(fl);
        }

        System.out.println("Passenger"+i+" reserved at Flight"+f);

    }

    public void cancel(int f, int i)
    {
        flight fl=null;
        //fl is the flight in which p passenger reserves
        for(flight x:data.db)
        {
            if(x.id==f)
            {
                fl=x;
            }
        }

        passenger p=null;
        for(passenger x:data.plist)
        {
            if(x.pid==i)
            {
                p=x;
            }
        }

        if(fl.cancel_seat(p)==true)
        {
            p.deletefromflight(fl);

        }

        System.out.println("Passenger"+i+" canceled at Flight"+f);
    }

    public void transfer(int f1,int f2 ,int i)
    {
        reserve(f1,i);
        cancel(f2,i);
    }

    public void totalreservations()
    {
        int sum=0;

        for(flight x:data.db)
        {
            sum+=x.booked;
        }
        System.out.println(sum);
    }

    public void myflights(int id)
    {
        passenger p=null;
        for(passenger x:data.plist)
        {
            if(x.pid==id)
            {
                p=x;
            }
        }
        System.out.println(p.mylist);
    }
}
