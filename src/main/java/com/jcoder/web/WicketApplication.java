package com.jcoder.web;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.RenderJavaScriptToFooterHeaderResponseDecorator;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.prettyprint.PrettifyCssResourceReference;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.prettyprint.PrettifyJavaScriptReference;
import de.agilecoders.wicket.core.markup.html.references.ModernizrJavaScriptReference;
import de.agilecoders.wicket.core.request.resource.caching.version.Adler32ResourceVersion;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.ThemeProvider;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.html5player.Html5PlayerCssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.html5player.Html5PlayerJavaScriptReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.OpenWebIconsCssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUICoreJavaScriptReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUIDraggableJavaScriptReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUIMouseJavaScriptReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUIResizableJavaScriptReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUIWidgetJavaScriptReference;
import de.agilecoders.wicket.less.BootstrapLess;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchTheme;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchThemeProvider;
import de.agilecoders.wicket.webjars.WicketWebjars;

import org.apache.wicket.ResourceBundles;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.caching.FilenameWithVersionResourceCachingStrategy;
import org.apache.wicket.request.resource.caching.NoOpResourceCachingStrategy;
import org.apache.wicket.request.resource.caching.version.CachingResourceVersion;
import org.apache.wicket.serialize.java.DeflatedJavaSerializer;
import org.apache.wicket.settings.IRequestCycleSettings;

import com.jcoder.web.assets.JavaScript;
import com.jcoder.web.assets.Styles;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see com.jcoder.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
	
    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {

        super.init();

        WicketWebjars.install( this );
        
        //getDebugSettings().setAjaxDebugModeEnabled( true );

        configureBootstrap();
        configureResourceBundles();

        optimizeForWebPerformance();
    }
    
    /**
     * optimize wicket for a better web performance
     */
    private void optimizeForWebPerformance() {
        if ( usesDeploymentConfig() ) {
            getResourceSettings().setCachingStrategy(
                    new FilenameWithVersionResourceCachingStrategy( "-v-", new CachingResourceVersion( new Adler32ResourceVersion() ) ) );

            getFrameworkSettings().setSerializer( new DeflatedJavaSerializer( getApplicationKey() ) );
        }
        else {
            getResourceSettings().setCachingStrategy( new NoOpResourceCachingStrategy() );
        }

        setHeaderResponseDecorator( new RenderJavaScriptToFooterHeaderResponseDecorator() );
        getRequestCycleSettings().setRenderStrategy( IRequestCycleSettings.RenderStrategy.ONE_PASS_RENDER );
    }

    /**
     * configure all resource bundles (css and js)
     */
    private void configureResourceBundles() {

        ResourceBundles bundles = getResourceBundles();
        bundles.addJavaScriptBundle( WicketApplication.class, "core.js",
                (JavaScriptResourceReference) getJavaScriptLibrarySettings().getJQueryReference(),
                (JavaScriptResourceReference) getJavaScriptLibrarySettings().getWicketEventReference(),
                (JavaScriptResourceReference) getJavaScriptLibrarySettings().getWicketAjaxReference(),
                (JavaScriptResourceReference) ModernizrJavaScriptReference.instance() );

        bundles.addJavaScriptBundle( WicketApplication.class, "bootstrap.js",
                (JavaScriptResourceReference) Bootstrap.getSettings().getJsResourceReference(),
                (JavaScriptResourceReference) PrettifyJavaScriptReference.INSTANCE,
                JavaScript.getInstance() );

        bundles.addJavaScriptBundle( WicketApplication.class, "bootstrap-extensions.js",
                JQueryUICoreJavaScriptReference.instance(),
                JQueryUIWidgetJavaScriptReference.instance(),
                JQueryUIMouseJavaScriptReference.instance(),
                JQueryUIDraggableJavaScriptReference.instance(),
                JQueryUIResizableJavaScriptReference.instance(),
                Html5PlayerJavaScriptReference.instance() );

        bundles.addCssBundle( WicketApplication.class, "bootstrap-extensions.css",
                Html5PlayerCssReference.instance(), OpenWebIconsCssReference.instance() );

        bundles.addCssBundle( WicketApplication.class, "style.css",
                (CssResourceReference) PrettifyCssResourceReference.INSTANCE,
                Styles.getInstance() );
        		
    }

    /**
     * configures wicket-bootstrap and installs the settings.
     */
    private void configureBootstrap() {
        final ThemeProvider themeProvider = new BootswatchThemeProvider(BootswatchTheme.Spacelab);

        final IBootstrapSettings settings = new BootstrapSettings();
        settings.setJsResourceFilterName( "footer-container" ).setThemeProvider( themeProvider );
        Bootstrap.install( this, settings );

        BootstrapLess.install( this );
    }

}
