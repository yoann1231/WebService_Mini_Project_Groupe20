package people.management.web.service;
import java.util.*;
import people.management.web.data.*;
public class PeopleService {
 private static Map<Integer, People> PEOPLE_DATA = new HashMap<Integer, People>();
 
 private int getNewId() {
 int newId = 0;
 for (int id : PEOPLE_DATA.keySet()) {
 if (newId < id)
 newId = id;
 }
 return ++newId;
 }
 public People addPeople(People s) {
 int id = getNewId();
 if(PEOPLE_DATA.get(s.getId()) != null) {
 return null;
 }
 s.setId(id);
 PEOPLE_DATA.put(id, s);
 return s;
 }
 public boolean deletePeople(int id) {
 if(PEOPLE_DATA.get(id) == null) {
 return false;
 }
 PEOPLE_DATA.remove(id);
 return true;
 }
 public People getPeople(int id) {
 return PEOPLE_DATA.get(id);
 }
}