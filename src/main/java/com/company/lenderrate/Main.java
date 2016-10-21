package com.company.lenderrate;


import com.company.lenderrate.dto.DataTable;
import com.company.lenderrate.dto.Row;

import java.util.List;
import java.util.Map;

public class Main {

    /**
     * Explain algorithm :
     * We organize our Datatable by a List of Row .
     * We will arrange the child-parent relationship each time we insert a new row .
     * It is may be take more time to insert a row .
     * But it will decrease the time when we want to access or perform an action into a specific children or descendant of Row .
     * (EX : show how many children Row A have , Get the 2th child of row , Get all Row have 2 children , Get row don't have any child .)
     * --- Cases covered :
     *  + Cycle relationship
     *  + Null ID , Name
     *  + ID and PID is the same
     * @param args
     */
    public static void main(String[] args) {
        DataTable dataTable = new DataTable();
        dataTable.addRow(new Row("0","A",null));
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
