import java.util.ArrayList;
import java.util.List;

public class PhyNode 
{ 


    private int x,y; //position of node for UI -- will be determined later
    private String speciesName = null; 
    private String dna = null;
    
    private List<PhyNode> children = new ArrayList<PhyNode>(); 
    														   
    private PhyNode parent = null;
        
    public PhyNode(String speciesName, String dna) 
    {
    	this.x = 0; //TODO: determine where location of first Node is to be
    	this.y = 0;    	
    	this.speciesName = speciesName;
    	this.dna = dna;
    }
    public PhyNode(String speciesName, String dna, PhyNode parent) 
    {
    	this.x = 0; //TODO: determine where location of first Node is to be
    	this.y = 0;    	
    	this.speciesName = speciesName;
    	this.dna = dna;
    	this.parent = parent;
    }
        
    public void addChild(PhyNode child) 
    {
    	child.setParent(this); 
    	this.children.add(child); 
    }

	public void setParent(PhyNode parent) 
	{
		this.parent = parent;
	}
	
	public PhyNode getParent()
	{
		return this.parent;
	}
	
	public List<PhyNode> getChildren()
	{
		return this.children;
	}

	public String getDNA() 
	{
		return this.dna;
	}
	
	public String getSpeciesName() 
	{
		return this.speciesName;
	}
	
	/**
	 * Iterative BFS printing all nodes using a Queue distinguishing among levels of the tree
	 * @param node
	 */
	
	public static void printAllBFS(PhyNode node) 
	{
		int oldChildrenAtLevel = 1; //root level size is always 1
		int currentChildrenAtLevel = 0; 
		Q2Gen <PhyNode> q = new Q2Gen <PhyNode>();
		q.add(node);
		while(q.length() > 0)
		{
			if (oldChildrenAtLevel == 0)
			{
				oldChildrenAtLevel = currentChildrenAtLevel;
				currentChildrenAtLevel = 0;
				System.out.println("==========NEW LEVEL=========="); //TODO: Distinguish difference in position(UI) relative to level of the tree
			}		
			PhyNode temp = q.remove();
			oldChildrenAtLevel--;
			System.out.println(temp);

			for (PhyNode child : temp.children)
			{
				q.add(child);
				currentChildrenAtLevel++;
			}
			
		}
		
		
	}
	
	public int numDifferences(PhyNode other)
	{
		int numDifs = 0;
		for(int i = 0; i < this.dna.length(); i++)
		{
			if(this.dna.toCharArray()[i] != other.dna.toCharArray()[i])
				numDifs++;
		}
			
		return numDifs;
	}
	
	public String toString()
	{
		return "Species: " + this.speciesName + 
		       "\nDNA: "  + this.dna;
	}

	/**
	 * driver function for testing the tree
	 */
	
    public static void main(String[] args) {
    	PhyNode root = new PhyNode("Human", "AAAA");
    	PhyNode child1 = new PhyNode("Monkey", "GGGG");
    	PhyNode child2 = new PhyNode("Child 2", "QQQQ");
    	PhyNode grandChild = new PhyNode("GrandChild", "FFFF");
    	PhyNode grandChild2 = new PhyNode("GrandChild 2", "KKKK");
    	PhyNode grandChild3 = new PhyNode("GrandChild 3", "VVVV");
    	PhyNode greatGrandChild1 = new PhyNode("Great GrandChild 1", "UUUU");
    	root.addChild(child1);
    	root.addChild(child2);
    	child1.addChild(grandChild);
    	child1.addChild(grandChild2); 
    	child2.addChild(grandChild3);
    	grandChild3.addChild(greatGrandChild1);
	printAllBFS(root);

    }
 

}
