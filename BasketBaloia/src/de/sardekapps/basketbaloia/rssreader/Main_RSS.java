package de.sardekapps.basketbaloia.rssreader;

import java.util.HashMap;
import java.util.LinkedList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import de.sardekapps.basketbaloia.R;

public class Main_RSS extends Activity {
	/**
	 * constantes necesarias a utilizar al guardar la informaci�n 
	 * en un HashMap, por el momento son necesarias para el parser
	 * de XML
	 * 
	 */
	static final String DATA_TITLE = "T";
	static final String DATA_LINK  = "L";
	public static final String DATA_DESCRIPTION = "D";
	
	/**
	 * Utilizaremos un SimpleAdapter que toma un listado de mapas para llenar su informaci�n, la 
	 * implementaci�n se realizar� con un LinkedList y un HashMap. Para este ejemplo utilizaremos un
	 * campo est�tico no final, es decir, una variable de clase, al realizar sus aplicaciones NO 
	 * deber�an hacerlo, lo correcto es utilizar clases de aplicaci�n, pero esto lo veremos hasta
	 * la siguiente gu�a. Adem�s, m�s adelante es buena idea utilizar otro almacenamiento no vol�til
	 * como una base de datos de SQLite.
	 */
	static LinkedList<HashMap<String, String>> data;

	/**
	 * Guardamos la direcci�n del feed como otra variable de clase para poder modificarla sin
	 * complicaciones m�s adelante.
	 */
	static String feedUrl = "http://ws.acb.com/rss";	
	
	/**
	 * para el di�logo de progreso es necesaria una variable global porque iniciamos el di�logo en
	 * una funci�n y lo ocultamos en otra
	 */
	private ProgressDialog progressDialog;
	
	/**
	 * Android nos presenta la restricciones que no podemos alterar los elementos de interfaz 
	 * gr�fica en un hilo de ejecuci�n que no sea el principal por lo que es necesario utilizar 
	 * un manejador(Handler) para enviar un mensaje de un hilo a otro cuando la carga de datos
	 * haya terminado.
	 */	
	private final Handler progressHandler = new Handler() {
		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg) {
			if (msg.obj != null) {
				data = (LinkedList<HashMap<String, String>>)msg.obj;
				setData(data);					
			}
			progressDialog.dismiss();
	    }
	};
	
    /** Este m�todo es llamado cuando la actividad es creada */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_rss);
        /** 
         * Le ponemos nombre a la ventana 
         */
        setTitle("Basket News");
		
		feedUrl = "http://ws.acb.com/rss";
		loadData();
        
        
        Button btn = (Button) findViewById(R.id.btnACB);
        
        btn.setOnClickListener(new OnClickListener() {			
    		@Override
    		public void onClick(View v) {
//    			ListView lv = (ListView) findViewById(R.id.lstData);
    			
    			feedUrl = "http://ws.acb.com/rss";
    			
//    			if (lv.getAdapter() != null) {
//    				AlertDialog.Builder builder = new AlertDialog.Builder(Main_RSS.this);
//    				builder.setMessage("Sure?")
//    				       .setCancelable(false)
//    				       .setPositiveButton("Si", new DialogInterface.OnClickListener() {
//    				           public void onClick(DialogInterface dialog, int id) {
    				        	   loadData();
//    				           }
//    				       })
//    				       .setNegativeButton("No", new DialogInterface.OnClickListener() {
//    				           public void onClick(DialogInterface dialog, int id) {
//    				                dialog.cancel();					            
//    				           }
//    				       })
//    				       .create()
//    				       .show();
//    				
//    			/**
//    			 * Si el ListView no contiene datos (es null) cargamos con loadData()
//    			 */    				
//    			} else {
//    				loadData();
//    			}
    		}
    	});
        
        
        Button btnNBA = (Button) findViewById(R.id.btnNBA);
        
        btnNBA.setOnClickListener(new OnClickListener() {			
    		@Override
    		public void onClick(View v) {
//    			ListView lv = (ListView) findViewById(R.id.lstData);
    			
    			feedUrl = "http://sports.espn.go.com/espn/rss/nba/news";

//    			if (lv.getAdapter() != null) {
//    				AlertDialog.Builder builder = new AlertDialog.Builder(Main_RSS.this);
//    				builder.setMessage("Sure?")
//    				       .setCancelable(false)
//    				       .setPositiveButton("Si", new DialogInterface.OnClickListener() {
//    				           public void onClick(DialogInterface dialog, int id) {
    				        	   loadData();
//    				           }
//    				       })
//    				       .setNegativeButton("No", new DialogInterface.OnClickListener() {
//    				           public void onClick(DialogInterface dialog, int id) {
//    				                dialog.cancel();					            
//    				           }
//    				       })
//    				       .create()
//    				       .show();
//    				
//    			/**
//    			 * Si el ListView no contiene datos (es null) cargamos con loadData()
//    			 */    				
//    			} else {
//    				loadData();
//    			}
    		}
    	});
        
        Button btnBBL = (Button) findViewById(R.id.btnBBL);
        
        btnBBL.setOnClickListener(new OnClickListener() {			
    		@Override
    		public void onClick(View v) {
//    			ListView lv = (ListView) findViewById(R.id.lstData);
    			
    			feedUrl = "http://www.beko-bbl.de/magazin/rss.php";
    			
//    			if (lv.getAdapter() != null) {
//    				AlertDialog.Builder builder = new AlertDialog.Builder(Main_RSS.this);
//    				builder.setMessage("Sure?")
//    				       .setCancelable(false)
//    				       .setPositiveButton("Si", new DialogInterface.OnClickListener() {
//    				           public void onClick(DialogInterface dialog, int id) {
    				        	   loadData();
//    				           }
//    				       })
//    				       .setNegativeButton("No", new DialogInterface.OnClickListener() {
//    				           public void onClick(DialogInterface dialog, int id) {
//    				                dialog.cancel();					            
//    				           }
//    				       })
//    				       .create()
//    				       .show();
//    				
//    			/**
//    			 * Si el ListView no contiene datos (es null) cargamos con loadData()
//    			 */    				
//    			} else {
//    				loadData();
//    			}
    		}
    	});
        
        Button btnLEGA = (Button) findViewById(R.id.btnLEGA);
        
        btnLEGA.setOnClickListener(new OnClickListener() {			
    		@Override
    		public void onClick(View v) {
//    			ListView lv = (ListView) findViewById(R.id.lstData);
    			
    			feedUrl = "http://api.twitter.com/1/statuses/user_timeline.rss?screen_name=LegaBasketA";
    			
//    			if (lv.getAdapter() != null) {
//    				AlertDialog.Builder builder = new AlertDialog.Builder(Main_RSS.this);
//    				builder.setMessage("Sure?")
//    				       .setCancelable(false)
//    				       .setPositiveButton("Si", new DialogInterface.OnClickListener() {
//    				           public void onClick(DialogInterface dialog, int id) {
    				        	   loadData();
//    				           }
//    				       })
//    				       .setNegativeButton("No", new DialogInterface.OnClickListener() {
//    				           public void onClick(DialogInterface dialog, int id) {
//    				                dialog.cancel();					            
//    				           }
//    				       })
//    				       .create()
//    				       .show();
//    				
//    			/**
//    			 * Si el ListView no contiene datos (es null) cargamos con loadData()
//    			 */    				
//    			} else {
//    				loadData();
//    			}
    		}
    	});
        
        Button btnLKL = (Button) findViewById(R.id.btnLKL);
        
        btnLKL.setOnClickListener(new OnClickListener() {			
    		@Override
    		public void onClick(View v) {
//    			ListView lv = (ListView) findViewById(R.id.lstData);
    			
    			feedUrl = "http://feeds.feedburner.com/BiE?format=xml";

//    			if (lv.getAdapter() != null) {
//    				AlertDialog.Builder builder = new AlertDialog.Builder(Main_RSS.this);
//    				builder.setMessage("Sure?")
//    				       .setCancelable(false)
//    				       .setPositiveButton("Si", new DialogInterface.OnClickListener() {
//    				           public void onClick(DialogInterface dialog, int id) {
    				        	   loadData();
//    				           }
//    				       })
//    				       .setNegativeButton("No", new DialogInterface.OnClickListener() {
//    				           public void onClick(DialogInterface dialog, int id) {
//    				                dialog.cancel();					            
//    				           }
//    				       })
//    				       .create()
//    				       .show();
//    				
//    			/**
//    			 * Si el ListView no contiene datos (es null) cargamos con loadData()
//    			 */    				
//    			} else {
//    				loadData();
//    			}
    		}
    	});
        
        
        
        
        ListView lv = (ListView) findViewById(R.id.lstData);
        /**
         * Cuando el usuario haga click en alg�n elemento de la lista, lo llevaremos al 
         * enlace del elemento a trav�s del navegador.
         */
        lv.setOnItemClickListener(new OnItemClickListener() {

    		@Override
    		public void onItemClick(AdapterView<?> av, View v, int position,
    				long id) {
		        /**
		         * Obtenemos el elemento sobre el que se presion�
		         */
    			HashMap<String, String> entry = data.get(position);

		        /**
		         * Preparamos el intent ACTION_VIEW y luego iniciamos la actividad (navegador en este caso)
		         */
    			Intent browserAction = new Intent(Intent.ACTION_VIEW, 
    					Uri.parse(entry.get(DATA_LINK)));
    			startActivity(browserAction);				
    		}
    	});        
    }
    
    /** 
     * Funci�n auxiliar que recibe una lista de mapas, y utilizando esta data crea un adaptador
     * para poblar al ListView del dise�o
     * */
    private void setData(LinkedList<HashMap<String, String>> data){
    	SimpleAdapter sAdapter = new SimpleAdapter(getApplicationContext(), data, 
    			android.R.layout.two_line_list_item, 
    			new String[] { DATA_TITLE, DATA_DESCRIPTION }, 
    			new int[] { android.R.id.text1, android.R.id.text2 });
    	ListView lv = (ListView) findViewById(R.id.lstData);
    	lv.setAdapter(sAdapter);
    }   
    
    /**
     * Funci�n auxiliar que inicia la carga de datos, muestra al usuario un di�logo de que
     * se est�n cargando los datos y levanta un thread para lograr la carga.
     */
    private void loadData() {
    	progressDialog = ProgressDialog.show(
    			Main_RSS.this,
    			"", 
    			"Por favor espere mientras se cargan los datos...", 
    			true);
    	
    	new Thread(new Runnable(){
    		@Override
    		public void run() {
    			XMLParser parser = new XMLParser(feedUrl); 
                Message msg = progressHandler.obtainMessage();
                msg.obj = parser.parse();
    			progressHandler.sendMessage(msg);
    		}}).start();
    }    
}