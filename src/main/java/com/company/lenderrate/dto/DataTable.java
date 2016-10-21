package com.company.lenderrate.dto;

import java.util.*;

/**
 * Created by Administrator on 10/21/2016.
 */
public class DataTable {
    private List<Row> rows;
    private Map<String,List<String>> processResult;

    public DataTable() {
        if(rows==null) {
            rows = new ArrayList<>();
        }
    }

    public void addRow(Row row) {
        // Because we need to return a Map <Name,List<ChildrenName>> so , we can't allow duplicate name here .
        if(rows.contains(row)) {
            System.out.println("Name can't be duplicated");
        }
        if(validatedRow(row.getID(),row.getName(),row.getPID())) {
            rows.add(row);
            updateChildrenForRowsAfterInsert(rows,row);
        }
    }

    private boolean validatedRow(String ID, String name,String PID) {
        if(ID==null) {
            System.out.println("Row " + name + ":ID can't be Null");
            return false;
        }
        if(name==null) {
            System.out.println("Row " + name + ":name can't be Null");
            return false;
        }
        if(PID!=null) {
            if(PID.equals(ID)) {
                System.out.println("Row " + name + ":PID can't be equal ID");
                return false;
            }
        }
        return true;
    }

    /**
     * Update list children for each row in Datatable base on the row is just inserted .
     * We have 2 case here .
     * - The row is just inserted is child of a row in Datatable .
     * - The row is just inserted is parent of a row in Datatable .
     * @param rows
     * @param rowInserted
     */
    private void updateChildrenForRowsAfterInsert(List<Row> rows, Row rowInserted) {
        Iterator<Row> rowIterator = rows.iterator();
        while (rowIterator.hasNext()){
            Row rowNext = rowIterator.next();
            if(!isRootElement(rowNext)) {
                if(isChildren(rowInserted, rowNext)) {
                    rowInserted.getChildren().add(rowNext);
                }
            }
            if (!isRootElement(rowInserted)) {
                if(isChildren(rowNext,rowInserted)) {
                    rowNext.getChildren().add(rowInserted);
                }
            }

        }
    }

    private boolean isRootElement(Row rowNext) {
        if(rowNext.getPID()==null){
            return true;
        }
        return false;
    }

    /**
     * Return true if rowChild is child of rowParrent and return false when he not .
     * @param rowParrent
     * @param rowChild
     * @return boolean
     */

    private boolean isChildren(Row rowParrent, Row rowChild) {
        return rowChild.getPID().equals(rowParrent.getID());
    }

    public Map<String,List<String>> processDataTable() {
        List<String> descendantNames;
        if(processResult==null) {
            processResult = new HashMap<>();
        } else {
            processResult.clear();
        }
        for(Row row : rows) {
            descendantNames = new ArrayList<>();
            buildListDescendants(descendantNames,row);
            processResult.put(row.getName(),descendantNames);
        }
        return processResult;
    }

    /**
     * Insert name of children of Row into a List
     * @param descendantNames
     * @param row
     */
    private void buildListDescendants(List<String> descendantNames,Row row) {
        if(row.getChildren()!=null) {
            for (Row rowChild : row.getChildren()) {
                // avoid unlimited loop in case [cycle relationship : A is parent of B , B is parent of C , C is parent of A .]
                if(!descendantNames.contains(rowChild.getName())) {
                    descendantNames.add(rowChild.getName());
                    buildListDescendants(descendantNames,rowChild);
                }
            }
        }

    }


}
