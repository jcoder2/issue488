package com.jcoder.web.assets;

import java.util.List;

import com.google.common.collect.Lists;

import de.agilecoders.wicket.core.markup.html.references.BootstrapJavaScriptReference;

import org.apache.wicket.Application;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

public class JavaScript extends JavaScriptResourceReference {
	private static final long serialVersionUID = 1L;

    private JavaScript() {
        super( JavaScript.class, "script.js" );
    }

    @Override
    public Iterable<? extends HeaderItem> getDependencies() {
        final List<HeaderItem> dependencies = Lists.newArrayList( super.getDependencies() );
        dependencies.add( JavaScriptHeaderItem.forReference( Application.get().getJavaScriptLibrarySettings().getJQueryReference() ) );
        dependencies.add( JavaScriptHeaderItem.forReference( BootstrapJavaScriptReference.instance() ) );

        return dependencies;
    }

    public static JavaScript getInstance() {
        return JavaScriptHolder.INSTANCE;
    }

    private static class JavaScriptHolder {
        public static final JavaScript INSTANCE = new JavaScript();
    }
}
