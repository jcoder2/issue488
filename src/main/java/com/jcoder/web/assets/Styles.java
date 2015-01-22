package com.jcoder.web.assets;

import org.apache.wicket.request.resource.CssResourceReference;

public class Styles extends CssResourceReference {
	private static final long serialVersionUID = 1L;

    private Styles() {
        super( Styles.class, "style.css" );
    }

    public static Styles getInstance() {
        return StylesHolder.INSTANCE;
    }

    private static class StylesHolder {
        public static final Styles INSTANCE = new Styles();
    }
}
