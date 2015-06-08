package BO;
import java.util.Map;
import java.util.HashMap;

import java.util.ArrayList;

public class MetaData {
	public ArrayList<String> listOfTables;
	public Map<String, ArrayList<String>> metaData;
	
	public MetaData()
	{
		this.listOfTables = new ArrayList<String>();
		this.metaData = new HashMap<String, ArrayList<String>>();
	}
}
