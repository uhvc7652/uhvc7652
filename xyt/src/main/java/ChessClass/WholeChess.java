package ChessClass;

import AI.Pattern;

public class WholeChess {
	private Chess ChessAI,ChessPlayer;
	private int dice;
	public WholeChess(int[][] AIChess, int[][] PlayerChess, int dice) {
		ChessAI = new Chess(AIChess);
		ChessPlayer = new Chess(PlayerChess);
		this.dice = dice;
	}
	public int getPosition(int dice) {
		Pattern pattern = patternJudge();
		int result = 0;
		if (pattern == Pattern.STEADY) {
			System.out.println('a');
			result = getSteadyPosition();
		}
		else if (pattern == Pattern.AGGRESSIVE) {
			System.out.println('b');
			result = getAggressivePosition();
		}
		else {
			System.out.println('c');
			result = getGreedyPosition();
		}
		System.out.println(result);
		return result;
	}
	private Pattern patternJudge() {
		Pattern result = Pattern.STEADY;
		
		int situation = situationJudge();
		
		int gap = ChessAI.getScore() - ChessPlayer.getScore();
		if (Math.abs(gap)<=5) {
			result = Pattern.STEADY;
		}
		else if (gap>6) {
			result = Pattern.STEADY;
		}
		else {
			if (situation<=0) {
				result = Pattern.AGGRESSIVE;
			}
			else {
				result = Pattern.GREEDY;
			}
		}
		return result;
	}
	private int situationJudge() {
		int result = 0;
		int AILeft = ChessAI.getLeft();
		int PlayerLeft = ChessPlayer.getLeft();
		
		if (AILeft < PlayerLeft) {
			result = 1;
		}
		else  {
			result = -1;	
		}
		if (AILeft<=4) {}
		else {
			result = 0;
		}
		return result;
	}
	
	private int getGreedyPosition() {//Greedy模式下落点
		int result = 0;
		int value0 = calculateValueNow(0,false);
		int value1 = calculateValueNow(1,false);
		int value2 = calculateValueNow(2,false);
		System.out.println(value0);
		System.out.println(value1);
		System.out.println(value2);
		if (ChessAI.ce0.isFull()) {
			value0=-999999;
		}
		if (ChessAI.ce1.isFull()) {
			value1=-999999;
		}
		if (ChessAI.ce2.isFull()) {
			value2=-999999;
		}
		if (value0 >= value1 && value0 >= value2 && !ChessAI.ce0.isFull()) {
			result = 1;
		}
		else if (value1 >= value0 && value1 >= value2 && !ChessAI.ce1.isFull()) {
			result = 2;
		}
		else {
			result = 3;
		}
		return result;
	}
	private int getAggressivePosition() {//Aggressive模式下落点
		int result = 0;
		int value0 = calculateValueNow(0,true);
		int value1 = calculateValueNow(1,true);
		int value2 = calculateValueNow(2,true);
		System.out.println(value0);
		System.out.println(value1);
		System.out.println(value2);
		if (ChessAI.ce0.isFull()) {
			value0=-999999;
		}
		if (ChessAI.ce1.isFull()) {
			value1=-999999;
		}
		if (ChessAI.ce2.isFull()) {
			value2=-999999;
		}
		if (value0 >= value1 && value0 >= value2 && !ChessAI.ce0.isFull()) {
			result = 1;
		}
		else if (value1 >= value0 && value1 >= value2 && !ChessAI.ce1.isFull()) {
			result = 2;
		}
		else {
			result = 3;
		}
		return result;
	}
	private int getSteadyPosition() {//Steady模式下落点
		int result = 0;
		double value0 = calculateValue(ChessAI.ce0, ChessPlayer.ce0, dice, ChessAI.getLeft(), ChessPlayer.getLeft(), 1);
		double value1 = calculateValue(ChessAI.ce1, ChessPlayer.ce1, dice, ChessAI.getLeft(), ChessPlayer.getLeft(), 1);
		double value2 = calculateValue(ChessAI.ce2, ChessPlayer.ce2, dice, ChessAI.getLeft(), ChessPlayer.getLeft(), 1);
		System.out.println(value0);
		System.out.println(value1);
		System.out.println(value2);
		if (ChessAI.ce0.isFull()) {
			value0=-999999;
		}
		if (ChessAI.ce1.isFull()) {
			value1=-999999;
		}
		if (ChessAI.ce2.isFull()) {
			value2=-999999;
		}
		if (value0 >= value1 && value0 >= value2 && !ChessAI.ce0.isFull()) {
			result = 1;
		}
		else if (value1 >= value0 && value1 >= value2 && !ChessAI.ce1.isFull()) {
			result = 2;
		}
		else {
			result = 3;
		}
		return result;
	}
	private double calculateValue(ChessElement a, ChessElement b, int tempDice, int leftA, int leftB, int count) {//Greedy和Aggressive模式value计算
		if (a.isFull() || count >= 7) {
			return 0;
		}
		int[] la = new int[6];//新分配一个int[6]，如果直接使用a.getLocation将导致a.Location被修改
		int[] lb = new int[6];
		double result = 0;
		
		for (int i=0;i<6;i++) {
			la[i]=a.getLocation()[i];
			lb[i]=b.getLocation()[i];
		}
		
		result = a.getAddition(tempDice) + b.getDecrement(tempDice);
		for (int i = 1; i <= 6; i++) {
			result -= calculateValue( new ChessElement(lb, tempDice, true), new ChessElement(la, tempDice, false), i, leftB, leftA - 1, count + 1) / 36;
		}
		return result;
	}
	private int calculateValueNow(int num, boolean isAggressive) {//Steady模式value计算
		int result = 0;
		int a = ChessAI.getValueAddtionOfElement(num, dice);
		int b = ChessPlayer.getValueDecrementOfElement(num, dice);
		if (a + ChessAI.getScore() + b > ChessPlayer.getScore() + 6) {
			result = 100;
		}
		if(isAggressive) {
			result = result + (a + 3 * b);
		}
		else {
			result = result + (a + b)*(int)(ChessAI.getLeft(num)/3.0);
		}
		
		return result;
	}
}
