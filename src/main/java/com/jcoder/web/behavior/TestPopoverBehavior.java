package com.jcoder.web.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.model.IModel;

import com.jcoder.web.panel.DataPanel;

import de.agilecoders.wicket.core.markup.html.bootstrap.components.PopoverConfig;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.RichPopoverBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipConfig;

public class TestPopoverBehavior extends RichPopoverBehavior {

    private static final long serialVersionUID = 1L;
    
    private static final PopoverConfig config =
            new PopoverConfig().withTrigger( TooltipConfig.OpenTrigger.click )
                               .withPlacement( TooltipConfig.Placement.right );
        
    private DataPanel body = null;
    private boolean bodyEnabled = false;

    /**
     * @param label
     */
    public TestPopoverBehavior( final IModel<String> label ) {
        super( label, config );
    }

    @Override
    public Component newBodyComponent( String markupId ) {
System.out.println("New data panel");
        return body = new DataPanel( markupId );
    }
    
    @Override
    public void onEvent(Component component, IEvent<?> event) {
        if( body != null && isBodyEnabled() ) body.onEvent( event );
    }

    public boolean isBodyEnabled() {
        return bodyEnabled;
    }

    public void setBodyEnabled(boolean bodyEnabled) {
        this.bodyEnabled = bodyEnabled;
    }
}
