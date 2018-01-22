package com.nagarro.assignment1.data;

public enum ItemType {
	Raw("raw"), Manufactured("manufacturd"), Imported("imported");

	private String name;

	private ItemType(final String name) {
		this.name = name;
	}

	public static ItemType getType(final String typeName) {
		ItemType type = null;
		for (ItemType itemType : ItemType.values()) {
			if (itemType.getName().equals(typeName)) {
				type = itemType;
				break;
			}
		}
		return type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Item ItemCreation(String name, ItemType getItemtype, double price, double quantity) {
		Item item = null;
		switch (getItemtype) {

		case Raw:
			item = new RawItem(name, price, quantity);
			break;
		case Manufactured:
			item = new ManufacturedItem(name, price, quantity);
			break;
		case Imported:
			item = new ImportedItem(name, price, quantity);
			break;

		}
		return item;

	}

}
