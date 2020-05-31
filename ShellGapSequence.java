
public class ShellGapSequence implements GapSequenceIterator {//последовательность, предложенная Шеллом
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
