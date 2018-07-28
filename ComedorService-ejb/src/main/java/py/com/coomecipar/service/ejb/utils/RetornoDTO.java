/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.ejb.utils;

import java.util.List;
/**
 *
 * @author mojeda
 */
public class RetornoDTO<T> {
    
    private boolean error;
    
    private String mensaje;
    
    /**
     * Total number of pages
     */
    private int total;
    /**
     * The current page number
     */
    private int page;
    /**
     * Total number of records
     */
    private int records;
    /**
     * The actual data
     */
    private List<T> rows;

    
    public RetornoDTO() {

    }

    public RetornoDTO(int total, int page, int records, List<T> rows) {
        this.total = total;
        this.page = page;
        this.records = records;
        this.rows = rows;
    }

    /**
     * @return the error
     */
    public boolean isError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(boolean error) {
        this.error = error;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return the records
     */
    public int getRecords() {
        return records;
    }

    /**
     * @param records the records to set
     */
    public void setRecords(int records) {
        this.records = records;
    }

    /**
     * @return the rows
     */
    public List<T> getRows() {
        return rows;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(List<T> rows) {
        this.rows = rows;
    }
    
    
}
