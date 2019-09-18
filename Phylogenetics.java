import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Phylogenetics
{
	//TODO: Inefficient to use a list as many get calls occur instead, 
	//      represent as an array
    static List<PhyNode> children = new ArrayList<PhyNode>(); 
    static PhyNode root = null;
    
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter File Path, or the file name if in same path");
        String filePath = sc.nextLine();
            try{
            readAndSortData(filePath);
            JFrameGraphics.GUI();
        	PhyNode.printAllBFS(root);
            sc.close();
            } catch(Exception e){
                System.out.println("File Path does not exist");
                sc.close();
            }
    }


    public static void readAndSortData(String filePath) throws Exception
    { 
        File file = new File(filePath);

        BufferedReader br = new BufferedReader(new FileReader(file));
        String fileLine;
		String header = br.readLine();
        String[] data = null;
        try 
        {
        fileLine = br.readLine();
        data = fileLine.split(",");
        } 
        catch(Exception e) 
        {
        	System.out.println("No Species were found in File");
        	br.close();
        	return;
        }
        
        root = new PhyNode(data[0], data[1]); 
        while((fileLine = br.readLine()) != null)
        	{
            data = fileLine.split(",");
            PhyNode child = new PhyNode(data[0], data[1]);
            children.add(child);
            }
        Phylogenetics.sortData(root, children);
        br.close();
        
    }
    
    /**
     * 
     * @param root -- root of tree
     * @param children -- all other species to compare and become children or
     *                    sub-children of root
     * 
     * Sorts the species in a tree which relates the parent's child based upon
     * the least number of differences in the DNA sequence among all other 
     * unsorted species.
     * 
     * -> Time Complexity: O(n^3) or higher
     * 	  TODO: Optimize time complexity
     * 
     */
    
    ///TODO: DEBUG  
    public static void sortData(PhyNode root, List<PhyNode> children)  
    {
    	List<PhyNode> inTree = new ArrayList<PhyNode>();
    	inTree.add(root);
    	PhyNode mostRelatedParent = root;
    	int differences;
    	int leastDifferences;
    	int loop = 0;
    	
    	for(PhyNode child : children )
    	{
    		// Resets root to be default parent
    		leastDifferences = root.numDifferences(child);
    		
    		for(PhyNode node : inTree)
    		{
    			differences = node.numDifferences(child);

    			if (differences < leastDifferences)
    			{
    				leastDifferences = differences;
    				mostRelatedParent = node;
    			}
    		}
    		
    		// Establishes Parent-Child relationship in tree
    		child.setParent(mostRelatedParent);
    		mostRelatedParent.addChild(child);
    		inTree.add(child);
    	}
    	
    }
    
    
    





}
