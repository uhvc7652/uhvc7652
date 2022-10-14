package AI;

import java.util.List;

public class AIBattle {
	public Integer nextStep(List<Integer> ownBoard, List<Integer> otherBoard, Integer figure) {//AI大战函数
		int result = 0;
		int[][] ownB = new int[3][3];
		int[][] othB = new int[3][3];

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				ownB[i][j] = ownBoard.get(i * 3 + j);
				othB[i][j] = otherBoard.get(i * 3 + j);
			}
		}
		int a = figure;
		InterfaceToJs itj = new InterfaceToJs(ownB, othB, a);
		result = itj.getPosition();
		return result;
	}
}
