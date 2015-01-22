package com.jcoder.web;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.filter.FilteredHeaderItem;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.WebPage;

import com.jcoder.web.assets.JavaScript;
import com.jcoder.web.panel.FormPanel;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.HtmlTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.OptimizedMobileViewportMetaTag;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeCssReference;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage( final PageParameters parameters ) {
		super( parameters );

		add( new HtmlTag( "html" ) );

        add( new OptimizedMobileViewportMetaTag( "viewport" ) );

        add( new BootstrapBaseBehavior() );
        
        add( new FormPanel( "testPanel" ) );
        
        add( new HeaderResponseContainer( "footerContainer", "footer-container" ) );
    }
	
    @Override
    public void renderHead( IHeaderResponse response ) {
        super.renderHead( response );
        Bootstrap.renderHead( response );
        response.render( CssHeaderItem.forReference( FontAwesomeCssReference.instance() ) );
        JavaScriptHeaderItem jsHeaderItem = JavaScriptHeaderItem.forReference( JavaScript.getInstance() );
        response.render( new FilteredHeaderItem( jsHeaderItem, "footer-container" ) );
    }
}
