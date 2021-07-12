package jaxb.model;

import javax.xml.bind.annotation.adapters.XmlAdapter;


public class EnumAdapter extends XmlAdapter<String,Levels> {

    @Override
    public Levels unmarshal(String stringLevel) throws Exception {
        return Levels.valueOf(stringLevel.toUpperCase());
        }

    @Override
    public String marshal(Levels level) throws Exception {
        return level.toString();
    }
}
