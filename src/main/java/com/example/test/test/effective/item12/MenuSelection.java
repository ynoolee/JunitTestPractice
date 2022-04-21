package com.example.test.test.effective.item12;

public enum MenuSelection {
	CREATE(){
		@Override
		public String toString() {
			return "$classname{}";
		}
	},
	LIST;


}
