
public interface IList {
	
	public int size();
	public Node getNode(int pos);
	public void add(Object object, int priority);
	public void remove(int pos);
	public int getHighestPriorityPosition();

}
