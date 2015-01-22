package com.jcoder.web.panel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.jcoder.web.event.TypeChangeEvent;

public class DataPanel extends Panel {

    private static final long serialVersionUID = 1L;
        
    private final List<DataRow> dataRows = new ArrayList<DataRow>();
        
    private final WebMarkupContainer container;
    
    private static String type = "";

    public DataPanel( final String markupId ) {
        super( markupId );
        
        System.out.println("datepanel load");
        
        refresh();
                
        DataView<DataRow> dataview = createDataView( "data", dataRows );
         
        container = new WebMarkupContainer( "container" );
        container.setOutputMarkupPlaceholderTag( true );
        container.add( dataview );

        add( container );
    }
    
    @Override
    public void onEvent( IEvent<?> event ) {
        
        super.onEvent( event );
        
        if( event.getPayload() instanceof TypeChangeEvent ) {
            
        	TypeChangeEvent payload = (TypeChangeEvent)event.getPayload();
        	
        	type = payload.getType();
        	
        	refresh();
        	
        	payload.getTarget().add( container );
        }
    }

    private void refresh(){

    	dataRows.clear();
System.out.println("type="  +type);
        for( int x = 1; x < 3; x++ ){

            DataRow row = new DataRow( "name" + x, type );
            dataRows.add( row );
        }
    }

    private DataView<DataRow> createDataView( String componentId, List<DataRow> data ) {
        
        return new DataView<DataRow>( componentId, new ListDataProvider<DataRow>( data ) ) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem( final Item<DataRow> item ) {
            	DataRow row = item.getModelObject();
        
                item.add( new Label( "name", row.getName() ) );
                item.add( new Label( "type", row.getType() ) );
            }
        };
    }
    
    private class DataRow implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private final IModel<String> name;
        private final IModel<String> type;
        
        public DataRow( String name, String type ) {
            this.name = Model.of( name );
            this.type = Model.of( type );
        }

        public final IModel<String> getName() {
            return name;
        }
        
        public final IModel<String> getType() {
            return type;
        }
        
		@Override
		public String toString() {
			return "DataRow [name=" + name + ", type=" + type + "]";
		}
    }
}
