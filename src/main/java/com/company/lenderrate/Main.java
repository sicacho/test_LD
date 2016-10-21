package com.company.lenderrate;


import com.company.lenderrate.dto.DataTable;
import com.company.lenderrate.dto.Row;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
	// write your code here
        DataTable dataTable = new DataTable();
        dataTable.addRow(new Row("0","A","5"));
        dataTable.addRow(new Row("1","B","0"));
        dataTable.addRow(new Row("2","C","0"));
        dataTable.addRow(new Row("4","D","1"));
        dataTable.addRow(new Row("5","E","1"));
        dataTable.addRow(new Row("6","F","4"));
        dataTable.addRow(new Row("3","G","0"));
        Map<String,List<String>> map = dataTable.processDataTable();
        for (Map.Entry<String, List<String>> entry : map.entrySet())
        {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }
}
