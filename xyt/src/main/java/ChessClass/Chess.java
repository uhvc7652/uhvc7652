package ChessClass;


public class Chess {
	public ChessElement ce0;
	public ChessElement ce1;
	public ChessElement ce2;
	private int left;
	private int score;

	public Chess(int[][] chess) {
		ce0 = new ChessElement(chess[0]);
		ce1 = new ChessElement(chess[1]);
		ce2 = new ChessElement(chess[2]);
		init();
	}

	public Chess(ChessElement ce0, ChessElement ce1, ChessElement ce2) {
		this.ce0 = ce0;
		this.ce1 = ce1;
		this.ce2 = ce2;
		init();
	}

	private void init() {
		setLeft();
		setScore();
	}

	private void setScore() {//设置分数
		score += ce0.getScore() + ce1.getScore() + ce2.getScore();
	}

	private void setLeft() {//计算剩余数
		left += ce0.getLeft() + ce1.getLeft() + ce2.getLeft();
	}

	public int getScore() {//输出分数
		return score;
	}

	public int getLeft() {//输出总剩余数
		return left;
	}
	
	public int getLeft(int num) {//输出剩余数
		if (num==0) {
			return ce0.getLeft();
		}
		else if(num==1) {
			return ce1.getLeft();
		}
		else {
			return ce2.getLeft();
		}
	}
	
	public int getValueAddtionOfElement(int num,int dice) {//输出chessElement的Addition
		int result = 0;
		if (num==0) {
			result = ce0.getAddition(dice);
		}
		else if (num==1){
			result = ce1.getAddition(dice);
		}
		else {
			result = ce2.getAddition(dice);
		}
		return result;
	}
	
	public int getValueDecrementOfElement(int num,int dice) {//输出chessElement的Decrement
		int result = 0;
		if (num==0) {
			result = ce0.getDecrement(dice);
		}
		else if (num==1){
			result = ce1.getDecrement(dice);
		}
		else {
			result = ce2.getDecrement(dice);
		}
		return result;
	}
}
