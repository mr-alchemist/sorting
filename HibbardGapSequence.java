
public class HibbardGapSequence implements GapSequence {
	int exp2;
	public HibbardGapSequence(int n) {
		exp2 = 2;
		while(exp2 <= n) {
			exp2 += exp2;
		}
	}
	
	@Override
	public int next() {
		exp2 = exp2/2;
		return exp2 - 1;
	}

}
