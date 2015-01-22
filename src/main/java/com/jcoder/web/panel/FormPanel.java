package com.jcoder.web.panel;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.jcoder.web.behavior.TestPopoverBehavior;
import com.jcoder.web.event.TypeChangeEvent;

public class FormPanel extends Panel {
	
	private static final long serialVersionUID = 1L;

	private final IModel<MyForm> formData = Model.of( new MyForm() );
	private final Form<MyForm> form;
	private final WebMarkupContainer link;
		
	public FormPanel( final String markupId ){
		super( markupId );
		        
        form = new Form<MyForm>( "form", new CompoundPropertyModel<MyForm>( formData.getObject() ) );
        add( form );
        
        form.add( typeToggle( "typeGroup" ) ); 
        
        form.add( link = createLink( "link" ) );
        link.setOutputMarkupId( true );
    }
	
	private Component typeToggle( final String markupId ){
        
        IModel<MyForm.Type> model = new PropertyModel<MyForm.Type>( formData.getObject(), "selectedType" );
        
        RadioGroup<MyForm.Type> typeGroup = new RadioGroup<MyForm.Type>( markupId, model );
        typeGroup.add( new AjaxFormChoiceComponentUpdatingBehavior() {
            private static final long serialVersionUID = 1L;
            
            @Override
            protected void onUpdate( AjaxRequestTarget target ){
            	MyForm data = formData.getObject();            	
                send( getPage(), Broadcast.BREADTH,
                			new TypeChangeEvent( target, data.getSelectedType().toString() ) );
//                target.add( link );
            }
        });
        typeGroup.add( new Radio<MyForm.Type>( "big", Model.of( MyForm.Type.Big ) ) );
        typeGroup.add( new Radio<MyForm.Type>( "little", Model.of( MyForm.Type.Little ) ) );
        
        return typeGroup;
    }
	
    private WebMarkupContainer createLink( final String markupId ){
                
    	TestPopoverBehavior behaviour = 
                new TestPopoverBehavior( Model.of( "RichPopover" ) );
        WebMarkupContainer linkContainer = new WebMarkupContainer( markupId );
        linkContainer.add( behaviour );
//        linkContainer.setOutputMarkupId( true );
        
        return linkContainer;
    }
}