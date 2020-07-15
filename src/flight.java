import java.util.ArrayList;

public class flight {

    int total_seats;
    int booked;
    int id;
    ArrayList<passenger> list;


    flight(int t,int i)
    {
        total_seats=t;
        booked=0;
        id=i;
        list=new ArrayList<passenger>();
    }

    public boolean reserve_seat(passenger p)
    {
        booked++;
        if(booked>total_seats)
        {
            booked--;
            return false;
        }
        else
        {
            list.add(p);
            return true;
        }

    }

    public String toString()
    {
        return booked+"seats out of "+total_seats+" flight"+id;
    }

    public void printlist()
    {
        for(passenger f:list)
        {
            System.out.print(f.pid+",");
        }
    }
    public boolean cancel_seat(passenger p)
    {
        booked--;
        if(booked<0)
        {
            booked++;
            return false;
        }
        else
        {
            for(passenger i:list)
            {
                if(i.pid==p.pid)
                {
                    list.remove(i);
                }
            }
            return true;
        }
    }



}
