package com.jcoder.web.event;

import org.apache.wicket.ajax.AjaxRequestTarget;

public class TypeChangeEvent {
	
	private final AjaxRequestTarget target;
    private final String type;
	
    public TypeChangeEvent( AjaxRequestTarget target, String type ) {
        this.target = target;
        this.type = type;
    }

    public final AjaxRequestTarget getTarget() {
        return target;
    }

	public String getType() {
		return type;
	}
}
