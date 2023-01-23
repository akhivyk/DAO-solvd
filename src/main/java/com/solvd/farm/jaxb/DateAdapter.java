package com.solvd.farm.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter extends XmlAdapter<String, Date> {
    @Override
    public Date unmarshal(String date) throws Exception {
        return new SimpleDateFormat("MM-dd-yyyy").parse(date);
    }

    @Override
    public String marshal(Date date) throws Exception {
        return new SimpleDateFormat("MM-dd-yyyy").format(date);
    }
}
