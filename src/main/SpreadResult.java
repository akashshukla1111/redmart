package main;

public class SpreadResult {

	private String input;
	private Double ans;
	private boolean isVisited;

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public Double getAns() {
		return ans;
	}

	public void setAns(Double ans) {
		this.ans = ans;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean visited) {
		isVisited = visited;
	}

	@Override
	public String toString() {
		return "SpreadResult{" + "input='" + input + '\'' + ", ans=" + ans + ", isVisited=" + isVisited + '}';
	}
}
