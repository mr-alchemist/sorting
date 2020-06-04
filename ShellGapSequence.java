
public class ShellGapSequence implements GapSequence {//последовательность, предложенная Шеллом
	private int current;
	public ShellGapSequence(int n) {
		current = n;
	}
	
	@Override
	public int next() {
		current = current/2;
		return current;
	}

}
