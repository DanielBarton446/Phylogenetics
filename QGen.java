public interface QGen <T> 
{


    void add(T o);
    // adds to the queue


    T remove();
    //removes and returns item that is first in the queue


    int length();
    //returns length of the queue


}  
