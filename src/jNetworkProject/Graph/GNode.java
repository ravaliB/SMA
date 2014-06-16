package jNetworkProject.Graph;

public class GNode {
	//ID unique du noeud
	public int id;
	
	//Position dans l'espace
	public int pos_x;
	public int pos_y;

	
	public GNode(int id)
	{
		this.id = id;
	}
	
	public GNode(int pos_x, int pos_y)
	{
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GNode other = (GNode) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
