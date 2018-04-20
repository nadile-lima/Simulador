package upe.poli.simulador.areacoverage;

public class Cell {

	public enum State{
		UNEXPLORED,
		FREE,
		WALL,
		FRONTIER
	}
	
	private State state = Cell.State.UNEXPLORED;
	
	public State getState(){
		return this.state;
	}
	
	public void setState(State newState){
		this.state = newState;
	}
	
	public boolean isWall(){
		boolean ret = false;
		
		if(getState().equals(Cell.State.WALL))
			ret = true;
		
		return ret;
	}
	
	public boolean isOcupied(){
		boolean ret = false;
		// returns true if some robot is in the cell
		return ret;
	}
}
