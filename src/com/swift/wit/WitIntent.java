package com.swift.wit;

import java.util.ArrayList;
import java.util.List;

public class WitIntent {

String intent;
List <WitEntity> entitys = new ArrayList<WitEntity>();

public WitIntent(String intent, List<WitEntity> entitys){
this.intent = intent;
this.entitys = entitys;
}
public String getIntent(){
return intent;	
}
public List<WitEntity> getEntitys(){
return entitys;	
}
}
