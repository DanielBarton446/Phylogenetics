// Author: Chris Dovolis
//         University of Minnesota Twin Cities Professor


public class Q2Gen <T> implements QGen <T> 
{

    private T[] q;
    private int size;
    private int front;
    private int rear;

    
    @SuppressWarnings("unchecked")
	public Q2Gen() 
    {
        q = (T[]) new Object[0];  
    }

    @SuppressWarnings("unchecked")
    public Q2Gen(int initLength) 
    {

        if (initLength < 0)
          throw new IllegalArgumentException("capacity requested is negative");
        q = (T[]) new Object[initLength];
    }

    

    @SuppressWarnings("unchecked")
    public void add(T o) 
    {
        
        if (size == q.length) 
        {  
          Object[] newq = new Object[2 * q.length + 1];
          if (front <= rear)                   
            System.arraycopy(q, front, newq, 0, size);
          else if (front > rear) 
          {  
        	  
            System.arraycopy(q, front, newq, 0, q.length - front);
            System.arraycopy(q, 0, newq, q.length - front, rear + 1);
            front = 0; 
            rear = size - 1;
          }
          q = (T[]) newq;
        }  
        
        rear = (rear + 1) % q.length;
        q[rear] = o;
        size++;    
    }

    public T remove() 
    {

    	  if (size == 0)
          return null;

        T answer = q[front];
        front = (front + 1) % q.length;
        size--;
        return answer;
    }

    public int length() 
    {
        return size;
    }


}  
