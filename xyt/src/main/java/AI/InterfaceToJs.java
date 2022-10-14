package AI;

import ChessClass.WholeChess;

public class InterfaceToJs {
	private WholeChess wholechess;
	private int dice = 0;

	public int getPosition() {
		return wholechess.getPosition(dice);//获取落点
	}

	private void init(int[][] AIChess, int[][] PlayerChess, int dice) {//初始化
		this.wholechess = new WholeChess(AIChess, PlayerChess, dice);
		this.dice = dice;
	}

	public InterfaceToJs(int [][] AIChess, int[][] PlayerChess, int dice) {
		init(AIChess, PlayerChess, dice);
	}

}