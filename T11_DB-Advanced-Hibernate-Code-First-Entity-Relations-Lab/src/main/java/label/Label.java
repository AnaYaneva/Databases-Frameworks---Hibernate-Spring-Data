package label;

import shampoo.BasicShampoo;

import java.io.Serializable;

interface Label extends Serializable{
     int getId();

     void setId(int id);

     String getTitle();

     void setTitle(String title);

     BasicShampoo getShampoo();

     void setShampoo(BasicShampoo shampoo);

     String getSubtitle();

     void setSubtitle(String subtitle);
}