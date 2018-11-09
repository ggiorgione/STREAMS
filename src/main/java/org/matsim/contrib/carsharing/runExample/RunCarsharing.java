package org.matsim.contrib.carsharing.runExample;


import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.population.Person;
import org.matsim.contrib.carsharing.config.CarsharingConfigGroup;
import org.matsim.contrib.carsharing.control.listeners.CarsharingListener;
import org.matsim.contrib.carsharing.events.handlers.PersonArrivalDepartureHandler;
import org.matsim.contrib.carsharing.manager.CarsharingManagerInterface;
import org.matsim.contrib.carsharing.manager.CarsharingManagerNew;
import org.matsim.contrib.carsharing.manager.PropertyManager;
import org.matsim.contrib.carsharing.manager.PropertyManagerImpl;
import org.matsim.contrib.carsharing.manager.demand.*;
import org.matsim.contrib.carsharing.manager.demand.membership.MembershipContainer;
import org.matsim.contrib.carsharing.manager.demand.membership.MembershipReader;
import org.matsim.contrib.carsharing.manager.routers.RouteCarsharingTrip;
import org.matsim.contrib.carsharing.manager.routers.RouteCarsharingTripImpl;
import org.matsim.contrib.carsharing.manager.routers.RouterProvider;
import org.matsim.contrib.carsharing.manager.routers.RouterProviderImpl;
import org.matsim.contrib.carsharing.manager.supply.CarsharingSupplyContainer;
import org.matsim.contrib.carsharing.manager.supply.CarsharingSupplyInterface;
import org.matsim.contrib.carsharing.manager.supply.costs.CostsCalculatorContainer;
import org.matsim.contrib.carsharing.models.*;
import org.matsim.contrib.carsharing.qsim.CarsharingQsimFactoryNew;
import org.matsim.contrib.carsharing.readers.CarsharingXmlReaderNew;
import org.matsim.contrib.carsharing.replanning.CarsharingSubtourModeChoiceStrategy;
import org.matsim.contrib.carsharing.replanning.RandomTripToCarsharingStrategy;
import org.matsim.contrib.carsharing.rest.*;
import org.matsim.contrib.carsharing.scoring.CarsharingScoringFunctionFactory;
import org.matsim.contrib.carsharing.simulationTime.SimulationTimeModule;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.AbstractModule;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy.OverwriteFileSetting;
import org.matsim.core.influx.InfluxModule;
import org.matsim.core.scenario.ScenarioUtils;

import java.io.IOException;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

/** 
 * @author balac
 */

public class RunCarsharing {


	private final static Logger log = Logger.getLogger(RunCarsharing.class);

	private static String PATH = null;
	private static String CONFIG_XML_FILE = null;
	private static String CONFIG_EXAMOTIVE_FILE = null;
	private static String URL_INFLUX_DB = null;



	public static void main(String[] args) throws IOException {

		Logger.getLogger( "org.matsim.core.controler.Injector" ).setLevel(Level.OFF);

		setPatterns(args);

		final Config config = ConfigUtils.loadConfig(PATH + CONFIG_XML_FILE);
		config.controler().setOverwriteFileSetting(OverwriteFileSetting.overwriteExistingFiles);

		//final Config config = ConfigUtils.loadConfig(PATH + "config.xml");
		
		if(Integer.parseInt(config.getModule("qsim").getValue("numberOfThreads")) > 1)
			Logger.getLogger( "org.matsim.core.controler" ).warn("Carsharing contrib is not stable for parallel qsim!! If the error occures please use 1 as the number of threads.");

        int numberOfThreads = Integer.parseInt(config.getModule("global").getValue("numberOfThreads"));
        Logger.getLogger("org.matsim.core.controler" ).info("Number of Thread for replanning");

		CarsharingUtils.addConfigModules(config);

		final Scenario sc = ScenarioUtils.loadScenario(config);
		
		//--------------------------- Parse income & vot -----------------------
		parseCustomeAttr(PATH + config.plans().getInputFile(), sc);

		final Controler controler = new Controler( sc );
		
		installCarSharing(controler);

		controler.run();


	}
	public static void parseCustomeAttr(String FileName,Scenario sc) {

		try {

			File fXmlFile = new File(FileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			//for skipping population_v6.dtd
			dBuilder.setEntityResolver(new EntityResolver() {
		        @Override
		        public InputSource resolveEntity(String publicId, String systemId)
		                throws SAXException, IOException {
		            if (systemId.contains("population_v6.dtd")) {
		                return new InputSource(new StringReader(""));
		            } else {
		                return null;
		            }
		        }
		    });

			Document doc = dBuilder.parse(fXmlFile);

			// optional, but recommended
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("person");

			//System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				//System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					String id = eElement.getAttribute("id");
					String personIncome = eElement.getAttribute("income");
					String personVot = eElement.getAttribute("vot");
					
					Id<Person> personId = Id.create(id, Person.class);
					
					Integer income = new Integer(personIncome);
					Double vot = new Double(personVot);

		
					Person person = sc.getPopulation().getPersons().get(personId);
					if (income >= 0) {
						person.getAttributes().putAttribute("income", income);
						//System.out.println("--------------- id: "+person.getId()+",  income: "+person.getAttributes().getAttribute("income"));
					} else {
						person.getAttributes().putAttribute("income", -1);
						System.err.println("Income is not a positive number.");
					}
					
					if (vot >= 0) {
						person.getAttributes().putAttribute("vot", vot);
						//System.out.println("--------------- id: "+person.getId()+",  vot: "+person.getAttributes().getAttribute("vot"));
					} else {
						person.getAttributes().putAttribute("vot", -1.0);
						System.err.println("Income is not a positive number.");
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static void setPatterns(String[] args){
		System.out.println(args[0]);
		PATH = args[0];
		CONFIG_XML_FILE = args[1];
		CONFIG_EXAMOTIVE_FILE = args[2];
		URL_INFLUX_DB = args[3];

		log.info("Scenarios path: " + PATH + " Config file name : " + CONFIG_XML_FILE + " Examotive file: " + CONFIG_EXAMOTIVE_FILE + " InfluxDb URL: " + URL_INFLUX_DB);
	}

	public static void installCarSharing(final Controler controler) throws IOException {
		
		final Scenario scenario = controler.getScenario();
		CarsharingXmlReaderNew reader = new CarsharingXmlReaderNew(scenario.getNetwork());
		
		final CarsharingConfigGroup configGroup = (CarsharingConfigGroup)
				scenario.getConfig().getModule( CarsharingConfigGroup.GROUP_NAME );

		reader.readFile(PATH + configGroup.getvehiclelocations());

		Set<String> carsharingCompanies = reader.getCompanies().keySet();
		
		MembershipReader membershipReader = new MembershipReader();
		
		membershipReader.readFile(PATH + configGroup.getmembership());

		final MembershipContainer memberships = membershipReader.getMembershipContainer();
		
		final CostsCalculatorContainer costsCalculatorContainer = CarsharingUtils.createCompanyCostsStructure(carsharingCompanies);
		
		final CarsharingListener carsharingListener = new CarsharingListener();
		//final CarsharingSupplyInterface carsharingSupplyContainer = new CarsharingSupplyContainer(controler.getScenario());


		final KeepingTheCarModel keepingCarModel = new KeepingTheCarModelExample();
		final ChooseTheCompany chooseCompany = new ChooseTheCompanyExample();
		final ChooseVehicleType chooseCehicleType = new ChooseVehicleTypeExample();
		final RouterProvider routerProvider = new RouterProviderImpl();
		final CurrentTotalDemand currentTotalDemand = new CurrentTotalDemandImpl(controler.getScenario().getNetwork());
		//final CarsharingManagerInterface carsharingManager = new CarsharingManagerNew();
		final RouteCarsharingTrip routeCarsharingTrip = new RouteCarsharingTripImpl();
		final VehicleChoiceAgent vehicleChoiceAgent = new VehicleChoiceAgentImpl();
		//===adding carsharing objects on supply and demand infrastructure ===

		final HttpInvoker httpInvoker = new HttpInvoker();
		final RestService restService = new ExaRestService();

		controler.addOverridingModule(new AbstractModule() {

			@Override
			public void install() {
				bind(KeepingTheCarModel.class).toInstance(keepingCarModel);
				bind(ChooseTheCompany.class).toInstance(chooseCompany);
				bind(ChooseVehicleType.class).toInstance(chooseCehicleType);
				bind(RouterProvider.class).toInstance(routerProvider);
				bind(CurrentTotalDemand.class).toInstance(currentTotalDemand);
				bind(RouteCarsharingTrip.class).toInstance(routeCarsharingTrip);
				bind(CostsCalculatorContainer.class).toInstance(costsCalculatorContainer);
				bind(MembershipContainer.class).toInstance(memberships);
			    bind(CarsharingSupplyInterface.class).to(CarsharingSupplyContainer.class);
			    bind(CarsharingManagerInterface.class).to(CarsharingManagerNew.class);
			    bind(VehicleChoiceAgent.class).toInstance(vehicleChoiceAgent);

				bind(PropertyManager.class).to(PropertyManagerImpl.class);
				bind(RestClientImpl.class).asEagerSingleton();
				bind(KeycloakTokenManager.class).asEagerSingleton();

				bind(HttpInvoker.class).toInstance(httpInvoker);
				bind(RestService.class).toInstance(restService);

			    bind(DemandHandler.class).asEagerSingleton();
			}			
			@Provides @Singleton
			CarsharingSupplyContainer provideCarsharingSupplyContainer(Scenario scenario) {
			    //return new CarsharingSupplyContainer(scenario);
				return new CarsharingSupplyContainer(scenario, PATH);
			}

			@Provides @Singleton
			PropertyManagerImpl propertiesManagerImp(){
				return new PropertyManagerImpl(PATH + CONFIG_EXAMOTIVE_FILE);
			}
			
			
		
		});

		controler.addOverridingModule(new SimulationTimeModule(controler));
		controler.addOverridingModule(new InfluxModule(URL_INFLUX_DB));

		//=== carsharing specific replanning strategies ===
		
		controler.addOverridingModule( new AbstractModule() {
			@Override
			public void install() {
				this.addPlanStrategyBinding("RandomTripToCarsharingStrategy").to( RandomTripToCarsharingStrategy.class ) ;
				this.addPlanStrategyBinding("CarsharingSubtourModeChoiceStrategy").to( CarsharingSubtourModeChoiceStrategy.class ) ;
			}
		});
		
		//=== adding qsimfactory, controller listeners and event handlers
		controler.addOverridingModule(new AbstractModule() {
			@Override
			public void install() {
				bindMobsim().toProvider(CarsharingQsimFactoryNew.class);
		        addControlerListenerBinding().toInstance(carsharingListener);
		        addControlerListenerBinding().to(CarsharingManagerNew.class);		        
				//bindScoringFunctionFactory().to(CarsharingScoringFunctionFactory.class);		      
		        addEventHandlerBinding().to(PersonArrivalDepartureHandler.class);
		        addEventHandlerBinding().to(DemandHandler.class);
			}
		});
		//=== adding carsharing specific scoring factory ===
		controler.addOverridingModule(new AbstractModule() {
			
			@Override
			public void install() {
				        
				bindScoringFunctionFactory().to(CarsharingScoringFunctionFactory.class);	
			}
		});

		//=== routing moduels for carsharing trips ===

		controler.addOverridingModule(CarsharingUtils.createRoutingModule());			
	}

}
