package gyqx.spdherp.stocInfoMgr.vo;

public enum StocLevel {

	ONE("一级库房", 0), TWO("二级库房", 1), THREE("三级库房", 2), FORE("四级库房", 3), FIVE("五级库房", 4);

	private String name;
	private int index;

	private StocLevel(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public int getIndex() {
		return index;
	}

}
