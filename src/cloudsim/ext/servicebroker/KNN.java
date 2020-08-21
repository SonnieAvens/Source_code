package cloudsim.ext.servicebroker;

import cloudsim.ext.GeoLocatable;
import cloudsim.ext.datacenter.DatacenterController;


public interface KNN {

	String getDestination(GeoLocatable inquirer);
}
