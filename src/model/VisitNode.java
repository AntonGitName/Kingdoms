package model;

import java.util.Comparator;

public class VisitNode {
	public final int x;
	public final int y;
	public int visitNum = 0;

	public VisitNode prev;
	public int distFunc = 0;

	public static Comparator<VisitNode> DISTANCE_ORDER = new Comparator<VisitNode>() {

		@Override
		public int compare(VisitNode o1, VisitNode o2) {
			return o1.distFunc - o2.distFunc;
		}

	};

	public VisitNode(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
