package weighting;

import java.util.List;

import br.ufpe.cin.dsoa.api.selector.RankStrategy;
import br.ufpe.cin.dsoa.api.service.Constraint;
import br.ufpe.cin.dsoa.api.service.ServiceInstance;

public class SimpleAdditiveWeighting implements RankStrategy {

	@Override
	public ServiceInstance ranking(List<Constraint> constraints, List<ServiceInstance> candidates) {

		Normalizer.normalize(constraints, candidates);
		return null;
	}

}
