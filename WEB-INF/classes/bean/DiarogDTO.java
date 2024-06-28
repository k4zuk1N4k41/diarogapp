package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class DiarogDTO implements Serializable{
    
    private ArrayList<DiarogBean> list;

    public DiarogDTO()
    {
        list = new ArrayList<DiarogBean>();
    }
    
    public void add(DiarogBean db)
    {
        list.add(db);
    }

    public DiarogBean get(int i)
    {
        return list.get(i);
    }

    public int size()
    {
        return list.size();
    }
}
