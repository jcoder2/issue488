package com.jcoder.web.panel;

import java.io.Serializable;

public class MyForm implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public enum Type {
        Big, Little;
        
        public boolean equals( String value ){
            boolean result = false;
            if( value != null ){
               result = this.toString().equals( value );
            }
            return result;
        }    
    };
    
    private Type selectedType;

	public MyForm() {
	}
	
	public final Type getSelectedType() {
        return selectedType;
    }
    public final void setSelectedType( Type selectedType ) {
        this.selectedType = selectedType;
    }

	@Override
	public String toString() {
		return "MyForm [selectedType=" + selectedType + "]";
	}
}
