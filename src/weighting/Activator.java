package weighting;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import br.ufpe.cin.dsoa.api.selector.RankStrategy;

public class Activator implements BundleActivator {
	
	private ServiceRegistration registration;

	@Override
	public void start(BundleContext context) throws Exception {
		
		RankStrategy simpleAdditiveWeighting = new SimpleAdditiveWeighting();
		registration = context.registerService(RankStrategy.class.getName(), simpleAdditiveWeighting, null);
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		registration.unregister();
	}
}
