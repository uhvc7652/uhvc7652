
package ChessClass;

public class ChessElement {
	private int[] location = new int[6];
	private int score;
	private int left;

	public ChessElement(int[] location) {// 初始化棋盘元素(location长度为3)
		for (int i = 0; i < 3; i++) {
			if (location[i] != 0) {
				this.location[location[i] - 1]++;
			}
		}
		init();
	}

	public ChessElement(int[] location, int dice, boolean isOpposite) {// 初始化棋盘元素，并在己方或对方放一个骰子(location长度为6)
		this.location = location;
		if (isOpposite) {
			this.location[dice - 1] = 0;
		} else {
			this.location[dice - 1]++;
		}
		init();
	}

	public void setScore() {// 初始化得分
		for (int i = 0; i < 6; i++) {
			score += location[i] * location[i] * (i + 1);
		}
	}

	public boolean isFull() {// 判断棋盘元素是否已经满了
		int a = 0;
		for (int i = 0; i < 6; i++) {
			a += location[i];
		}
		if (a < 3) {
			return false;
		} else {
			return true;
		}
	}

	public int getScore() {// 获取该棋盘元素的当前得分
		return score;
	}

	private void setLeft() {//获取剩余空位数量
		for (int i = 0; i < 6; i++) {
			left += location[i];
		}
		left = 9 - left;
	}

	public int getNewScore(int x) {// 填入x后该棋盘元素的得分
		int result = 0;
		int a[] = new int[6];
		for (int i=0;i<6;i++) {
			a[i]=location[i];
		}
		a[x - 1] = 0;
		for (int i = 0; i < 6; i++) {
			result += a[i] * a[i] * (i + 1);
		}
		return result;
	}

	public int getAddition(int x) {// 填入x后该棋盘元素的得分增量
		return getNewScore(x) - score;
	}

	public int getDecrement(int x) {// 对面填入x后该棋盘元素的得分减量
		int result = 0;
		int a[] = new int[6];
		for (int i=0;i<6;i++) {
			a[i]=location[i];
		}
		a[x - 1] = 0;
		for (int i = 0; i < 6; i++) {
			result += a[i] * a[i] * (i + 1);
		}
		result = getScore() - result;
		return result;
	}

	public int[] getLocation() {// 以一维数组形式输出棋盘元素
		return location;
	}

	public int getLeft() {//输出剩余空位数量
		return left;
	}

	private void init() {//初始化
		setScore();
		setLeft();
	}
}
