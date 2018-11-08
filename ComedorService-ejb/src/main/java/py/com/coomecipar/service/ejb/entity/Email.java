/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.ejb.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import py.com.coomecipar.service.ejb.utils.AppUtil;

/**
 *
 * @author miguel.ojeda
 */
public class Email {

    private String from;

    private List<String> to;

    private List<String> cc;
    
    private String ccSend;
    
    private String toSend;

    private String subject;

    private String message;
    
    private String firma;
    
    private String telefono;
    
    private String ciudad;

    private boolean isHtml;

    public Email() {
        this.to = new ArrayList<String>();
        this.cc = new ArrayList<String>();
    }

    public Email(String from, String toList, String subject, String message) {
        this();
        this.from = from;
        this.subject = subject;
        this.message = message;
        this.to.addAll(Arrays.asList(splitByComma(toList)));
    }

    public Email(String from, String toList, String ccList, String subject, String message) {
        this();
        this.from = from;
        this.subject = subject;
        this.message = message;
        this.to.addAll(Arrays.asList(splitByComma(toList)));
        if(ccList.compareToIgnoreCase("") != 0
                && ccList.compareToIgnoreCase("null") != 0){
            this.cc.addAll(Arrays.asList(splitByComma(ccList)));
        }        
    }

    //getters and setters not mentioned for brevity
    private String[] splitByComma(String toMultiple) {
        String[] toSplit = toMultiple.split(",");
        return toSplit;
    }

    public String getToAsList() {
        return AppUtil.concatenate(this.getTo(), ",");
    }

    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public List<String> getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(List<String> to) {
        this.to = to;
    }

    /**
     * @return the cc
     */
    public List<String> getCc() {
        return cc;
    }

    /**
     * @param cc the cc to set
     */
    public void setCc(List<String> cc) {
        this.cc = cc;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the isHtml
     */
    public boolean isIsHtml() {
        return isHtml;
    }

    /**
     * @param isHtml the isHtml to set
     */
    public void setIsHtml(boolean isHtml) {
        this.isHtml = isHtml;
    }

    public String getCcSend() {
        return ccSend;
    }

    public void setCcSend(String ccSend) {
        this.ccSend = ccSend;
    }

    public String getToSend() {
        return toSend;
    }

    public void setToSend(String toSend) {
        this.toSend = toSend;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    

}
