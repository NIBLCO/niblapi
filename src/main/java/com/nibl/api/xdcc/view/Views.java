package com.nibl.api.xdcc.view;

public class Views {

	public interface Bot {}

	public interface FullBot extends Bot, Pack {}

	public interface InternalBot extends FullBot {}

	public interface Pack {}

}
