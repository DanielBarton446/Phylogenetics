
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JFrameGraphics 
{

	public static void addLabel(JPanel panel, String word, int x, int y)
	{
		JLabel label = new JLabel(word);
		panel.add(label);
		label.setBounds(x, y, 800, 600); //TODO: @param worldx, worldy.
	}
	
	
	public static void drawTree(JPanel panel, PhyNode root)
	{
		int oldChildrenAtLevel = 1; //root level size is always 1
		int currentChildrenAtLevel = 0; 
		Q2Gen <PhyNode> q = new Q2Gen <PhyNode>();
		q.add(root);
		int x = 0;
		int y = 0;
		int xOffset = 50;
		int parentY = 0;
		

		
		while(q.length() > 0)
		{

			if (oldChildrenAtLevel == 0)
			{
				x += xOffset;
				oldChildrenAtLevel = currentChildrenAtLevel;
				currentChildrenAtLevel = 0;
				
			}		
			
			PhyNode temp = q.remove();
			oldChildrenAtLevel--;
			addLabel(panel, temp.getSpeciesName(), x, y);
			temp.setX(x);
			temp.setY(y);
			parentY = temp.getY();
			y = parentY - 50; //TODO: Proper padding
			y += 25;
			//TODO: get position of parent
			//		increase position on screen by constant * children/2
			//	 	for each child decrease position on screen by constant
			
			
			
			List<PhyNode> children = temp.getChildren();
			for (PhyNode child : children)
			{
				q.add(child);
//TODO: DRAW LINE HERE FROM CHILD TO PARENT
				currentChildrenAtLevel++;
			}
			
		}
	}
	
	
	public static void GUI()
	{
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JPanel panel = (JPanel) frame.getContentPane();
		
		panel.setLayout(null);
		
		drawTree(panel, Phylogenetics.root);




		
	}
	
	
}
