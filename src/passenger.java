import java.util.ArrayList;

public class passenger {

        ArrayList<flight> mylist;
        int pid;

        passenger(int p)
        {
            this.pid=p;
            mylist=new ArrayList<flight>();
        }

        public void addtoflight(flight f)
        {
            mylist.add(f);
        }

        public void deletefromflight(flight f)
        {
            for(flight i:mylist)
            {
                if(i.id==f.id)
                {
                    mylist.remove(f);
                }
            }

        }
    public String toString()
    {
        return "passenger"+pid;
    }

    public void printlist()
    {
        for(flight f:mylist)
        {
            System.out.print(f.id+",");
        }
    }


}
